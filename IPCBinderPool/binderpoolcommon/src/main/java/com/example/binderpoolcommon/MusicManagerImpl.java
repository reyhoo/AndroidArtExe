package com.example.binderpoolcommon;

import android.os.RemoteException;

import com.example.model1.IMusicManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaolei on 2016/8/23.
 */
public class MusicManagerImpl extends IMusicManager.Stub{
    @Override
    public List<String> getMusicList() throws RemoteException {
        List<String>list=new ArrayList<>();
        list.add("Music1");
        list.add("Music2");
        list.add("Music3");
        return list;
    }
}
