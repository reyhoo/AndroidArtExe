package com.example.binderpoolservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.binderpoolcommon.BinderPool;

/**
 * Created by yaolei on 2016/8/23.
 */
public class BinderPoolService extends Service {

    private IBinder mBinderPoolServiceImpl = new BinderPool.BinderPoolImpl();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinderPoolServiceImpl;
    }
}
