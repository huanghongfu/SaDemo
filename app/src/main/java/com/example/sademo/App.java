package com.example.sademo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * @author imac
 * @date 19/3/19
 */
public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SaUtils.init(this);
    }
}
