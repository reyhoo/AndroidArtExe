package com.example.administrator.chapter13_crashhandler;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Path;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/13.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {


    private static final String TAG = "CrashHandler_";

    private static final boolean DEBUG = true;

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/CrashTest/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private static CrashHandler sInstance = new CrashHandler();


    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private Context mContext;


    private CrashHandler() {
    }

    public static final CrashHandler getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }


    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        dumpExceptionToSDCard(throwable);
        throwable.printStackTrace();
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(thread, throwable);
        } else {
            Process.killProcess(Process.myPid());
        }
    }

    private void dumpExceptionToSDCard(Throwable throwable) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                Log.w(TAG, "sdcard unmounted,skip dump exception!");
            }

            return;
        }
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String time = new SimpleDateFormat("yyyy-MM-dd_HH点mm分ss秒").format(new Date());
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)),true);
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            throwable.printStackTrace(pw);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                try {
                    pw.close();
                } catch (Exception e) {
                }

            }
        }
    }

    private void dumpPhoneInfo(PrintWriter pw) {
        try{
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            pw.print("App version:");
            pw.print(pi.versionName);
            pw.print("_");
            pw.println(pi.versionCode);
        }catch (Exception e){
            e.printStackTrace();
        }

        pw.print("OS Version:");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        pw.print("Vendor:");
        pw.println(Build.MANUFACTURER);

        pw.print("Model:");
        pw.println(Build.MODEL);

        pw.print("CPU ABI:");
        pw.println(Build.CPU_ABI);
    }

}
