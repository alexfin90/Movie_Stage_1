package com.example.afinocchiaro.popularmovie;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by afinocchiaro on 04/03/17.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        Realm.init(getApplicationContext());
        super.onCreate();
    }
}
