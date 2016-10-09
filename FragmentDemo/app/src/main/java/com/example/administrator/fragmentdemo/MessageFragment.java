package com.example.administrator.fragmentdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessageFragment extends Fragment {
	private static final String TAG = "MessageFragment";

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
	

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.message_layout,
				container, false);
		Log.i("info", TAG + "::onCreateView");
		return messageLayout;
	}
	


}
