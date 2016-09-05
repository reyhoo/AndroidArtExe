package com.example.aidlcommon;

import com.example.aidlcommon.Book;
import com.example.aidlcommon.IOnNewBookArrivedListener;


interface IBookManager{
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}