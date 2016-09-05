package com.example.yaolei.client;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.aidlcommon.Book;
import com.example.aidlcommon.IBookManager;
import com.example.aidlcommon.IOnNewBookArrivedListener;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IBookManager mBookManager;
    private IOnNewBookArrivedListener mListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(final Book book) throws RemoteException {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,book.toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBookManager = IBookManager.Stub.asInterface(service);
            try {
                service.linkToDeath(mDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                mBookManager.registerListener(mListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this, "bindSuccess", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBookManager = null;
        }
    };
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
//            Toast.makeText(MainActivity.this, "binderDead", Toast.LENGTH_SHORT).show();

            if (mBookManager == null) {
                return;
            }
            mBookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            mBookManager = null;
            bindService();
        }
    };

    private void bindService() {
        Intent intent = new Intent();
        intent.setClassName("com.example.aidlservice", "com.example.aidlservice.BookManagerService");
        intent.setAction("com.example.aidlservice.BookManagerService");
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.addBook).setOnClickListener(this);
        findViewById(R.id.getList).setOnClickListener(this);
        bindService();
    }


    @Override
    public void onClick(View v) {
        if (mBookManager == null) return;
        switch (v.getId()) {
            case R.id.addBook:
                Book book = new Book(1, "Hello");
                try {
                    mBookManager.addBook(book);
                    Toast.makeText(MainActivity.this, "AddBook", Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Log.i("aidlclient_", "aidlclient_:" + e);
                }
                break;
            case R.id.getList:
                try {
                    List<Book> list = mBookManager.getBookList();
                    Log.i("aidlclient_", "aidlclient_:" + list);
                    Toast.makeText(MainActivity.this, "getList:" + list, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Log.i("aidlclient_", "aidlclient_:" + e);
                }
                break;
        }

    }

    @Override
    protected void onDestroy() {
        if(mBookManager!= null && mBookManager.asBinder().isBinderAlive()){
            try {
                mBookManager.unregisterListener(mListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(conn);
        super.onDestroy();
    }
}
