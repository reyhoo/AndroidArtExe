package com.example.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.aidlcommon.Book;
import com.example.aidlcommon.IBookManager;
import com.example.aidlcommon.IOnNewBookArrivedListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by yaolei on 2016/8/21.
 */
public class BookManagerService extends Service {


    private AtomicBoolean mIsServiceDestroy = new AtomicBoolean(false);
    private List<Book> mBookList = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListeners = new RemoteCallbackList<>();
    public IBookManager.Stub binder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            if (book != null) {
                mBookList.add(book);
            }
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListeners.register(listener);
            final int N = mListeners.beginBroadcast();
            mListeners.finishBroadcast();
            Log.i("Service_", "Service_:registerListener:listeners:" + N);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListeners.unregister(listener);
            final int N = mListeners.beginBroadcast();
            mListeners.finishBroadcast();
            Log.i("Service_", "Service_:unregisterListener:listeners:" + N);
        }
    };

    private class ServerWorker implements Runnable {
        @Override
        public void run() {
            while (!mIsServiceDestroy.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mIsServiceDestroy.get()) return;
                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new book#" + bookId);
                onNewBookArrived(newBook);
            }
        }
    }

    private void onNewBookArrived(Book newBook) {
        mBookList.add(newBook);
        final int N = mListeners.beginBroadcast();
        Log.i("Service_", "Service_:onNewBookArrived:listeners:" + N);
        for (int i = 0; i < N; i++) {
            IOnNewBookArrivedListener listener = mListeners.getBroadcastItem(i);
            if (listener != null) {
                try {
                    listener.onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListeners.finishBroadcast();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "iOS"));
        new Thread(new ServerWorker()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        int check = checkCallingOrSelfPermission("com.example.permission.ACCESS_BOOK_SERVICE");
//        if(check == PackageManager.PERMISSION_DENIED){
//            return null;
//        }
        Toast.makeText(BookManagerService.this, "Service onBind", Toast.LENGTH_SHORT).show();
        return binder;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(BookManagerService.this, "Service destroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        mIsServiceDestroy.set(true);
    }
}
