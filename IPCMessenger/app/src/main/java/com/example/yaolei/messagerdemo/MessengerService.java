package com.example.yaolei.messagerdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yaolei on 2016/8/21.
 */
public class MessengerService extends Service {

    private static final String TAG = "MessengerService_";

    private class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Const.MSG_FROM_CLIENT:
                    Log.i(TAG,"receive msg from client:"+msg.getData().getString("msg"));
                    Toast.makeText(MessengerService.this, msg.getData().getString("msg"), Toast.LENGTH_SHORT).show();
                   final Messenger client = msg.replyTo;
                    final Message replyMsg = new Message();
                    replyMsg.what = Const.MSG_FROM_SERVICE;
                    Bundle bundle = new Bundle();
                    bundle.putString("reply","嗯，你的消息我已经收到，稍后会回复你。");
                    replyMsg.setData(bundle);
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                client.send(replyMsg);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    },5000);
                    break;
                default:
                    super.handleMessage(msg);
                    break;

            }
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
//        return super.onStartCommand(intent, flags, startId);
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(MessengerService.this, "service destroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
