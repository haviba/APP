package com.example.zx.myapplication;

import android.app.Application;
import android.content.Context;

import com.example.zx.myapplication.utils.CrashHandler;

public class HelloWorldApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        CrashHandler.getInstance().init(this);
    }

    public static HelloWorldApp getContext() {
        return (HelloWorldApp)context;
    }
}
