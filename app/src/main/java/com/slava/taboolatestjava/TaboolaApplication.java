package com.slava.taboolatestjava;

import android.app.Application;
import android.content.Context;


import com.slava.taboolatestjava.persistence.ArticlesDatabase;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class TaboolaApplication extends Application {

    public static Context applicationContext;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        ArticlesDatabase.getAppDatabase();
    }
}
