package com.example.administrator.fragmentdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContactsFragment extends Fragment {

    private static final String TAG = "ContactsFragment_";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("info", TAG + "::onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("info", TAG + "::onCreate");
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.i("info", TAG + "::onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("info", TAG + "::onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("info", TAG + "::onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("info", TAG + "::onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("info", TAG + "::onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("info", TAG + "::onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("info", TAG + "::onDetach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("info", TAG + "::onCreateView");
        View contactsLayout = inflater.inflate(R.layout.contacts_layout,
                container, false);
        return contactsLayout;
    }


}
