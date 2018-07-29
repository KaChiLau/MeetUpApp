package com.example.kachi.storyfinderfinale.app;

/**
 * Created by KaChi on 2016/12/11.
 */

import android.app.Application;
import android.content.Context;


public class App extends Application {

    private static App instance;

    public static Context getContext(){
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

}
