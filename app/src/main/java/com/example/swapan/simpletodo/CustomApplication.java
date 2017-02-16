package com.example.swapan.simpletodo;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by swapan on 2/13/17.
 */

public class CustomApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // This instantiates DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());
        // add for verbose logging
        //FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);

        Stetho.initializeWithDefaults(this);
    }
}
