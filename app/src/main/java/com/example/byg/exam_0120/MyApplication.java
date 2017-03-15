package com.example.byg.exam_0120;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by byg on 2017-03-15.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate(); super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
