package com.zackyzhang.geolocationphotos;

import android.app.Application;

import com.squareup.leakcanary.*;

import timber.log.Timber;

/**
 * Created by lei on 5/11/17.
 */

public class MyApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
