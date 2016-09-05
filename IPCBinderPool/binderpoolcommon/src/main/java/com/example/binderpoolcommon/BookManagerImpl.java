package com.example.binderpoolcommon;

import android.os.RemoteException;

import com.example.model2.IBookManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaolei on 2016/8/23.
 */
public class BookManagerImpl extends IBookManager.Stub {
    @Override
    public List<String> getBookList() throws RemoteException {
        List<String>list=new ArrayList<>();
        list.add("Book1");
        list.add("Book2");
        list.add("Book3");
        return list;
    }
}
