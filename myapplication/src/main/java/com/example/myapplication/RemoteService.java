package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/3/3 0003.
 */
public class RemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    final Binder mBinder = new IMyAidlInterface.Stub() {

        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }

    };
}
