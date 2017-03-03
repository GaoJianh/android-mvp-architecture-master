package com.example.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_showPid;
    private TextView tv_showResult;
    private Button btn_doAdd;

    private  IMyAidlInterface mRemoteService;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteService = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, RemoteService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        bindView();
    }

    private void bindView() {
        tv_showPid = (TextView) findViewById(R.id.showPid);
        tv_showResult = (TextView) findViewById(R.id.showResult);
        btn_doAdd = (Button) findViewById(R.id.doAdd);
        btn_doAdd.setOnClickListener(this);
    }

    private int result = 0;
    private int pid = 0;
    @Override
    public void onClick(View v) {
        try {
            result = mRemoteService.add(3, 5);
            tv_showResult.setText(result + "");
            pid = mRemoteService.getPid();
            tv_showPid.setText(pid + "");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
