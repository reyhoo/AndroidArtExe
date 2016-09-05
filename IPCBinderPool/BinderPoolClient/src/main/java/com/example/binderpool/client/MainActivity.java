package com.example.binderpool.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.binderpoolcommon.BinderPool;
import com.example.model1.IMusicManager;
import com.example.model2.IBookManager;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.getMusicList).setOnClickListener(this);
        findViewById(R.id.getBookList).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        IBinder binder = null;
        switch (v.getId()) {
            case R.id.getBookList:
                binder = BinderPool.getInstance(this).queryBinder(BinderPool.BINDER_BOOK);
                IBookManager bookManager = IBookManager.Stub.asInterface(binder);
                try {
                    Toast.makeText(MainActivity.this, ""+bookManager.getBookList(), Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.getMusicList:
                binder = BinderPool.getInstance(this).queryBinder(BinderPool.BINDER_MUSIC);
                IMusicManager musicManager = IMusicManager.Stub.asInterface(binder);

                try {
                    Toast.makeText(MainActivity.this, ""+musicManager.getMusicList(), Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
