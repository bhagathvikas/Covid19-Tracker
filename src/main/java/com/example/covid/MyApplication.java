package com.example.covid;

import android.app.Application;

import com.onesignal.OneSignal;

public class MyApplication extends Application {
    private static MyApplication myApplication;


    public MyApplication(){

        myApplication = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
    public static synchronized MyApplication getInstance(){
        return myApplication;
    }
}
