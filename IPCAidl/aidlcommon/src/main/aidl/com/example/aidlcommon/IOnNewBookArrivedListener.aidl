// IOnNewBookArrivedListener.aidl
package com.example.aidlcommon;


import com.example.aidlcommon.Book;
interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book book);
}
