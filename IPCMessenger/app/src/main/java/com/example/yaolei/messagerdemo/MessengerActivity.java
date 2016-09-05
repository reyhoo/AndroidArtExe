package com.example.yaolei.messagerdemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.ServiceCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MessengerActivity extends AppCompatActivity {

    private static final String TAG = "MessengerActivity_";
    private Messenger mMessenger;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Const.MSG_FROM_SERVICE:
                    Toast.makeText(MessengerActivity.this, msg.getData().getString("reply"), Toast.LENGTH_SHORT).show();
                    break;
                default:super.handleMessage(msg);
                    break;
            }

        }
    };
    private Messenger mGetReplyMessenger = new Messenger(handler);
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            Message msg = new Message();
            msg.what = Const.MSG_FROM_CLIENT;
            Bundle bundle = new Bundle();
            bundle.putString("msg", "hello , this is client.");
            msg.setData(bundle);
            msg.replyTo = mGetReplyMessenger;
            try {
                mMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMessenger = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, connection, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }
}
