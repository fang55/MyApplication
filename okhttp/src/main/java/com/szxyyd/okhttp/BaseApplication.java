package com.szxyyd.okhttp;

import android.app.Application;


import com.facebook.stetho.Stetho;

import java.util.concurrent.TimeUnit;

/**
 * Created by jq on 2016/7/21.
 */
public class BaseApplication  extends Application {
    private static BaseApplication instance;

    public BaseApplication() {
    }

    public static BaseApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Stetho.initializeWithDefaults(this);
    }
}
