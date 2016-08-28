package com.example.pranav.fireapp;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Pranav on 27-08-2016.
 */
public class FireApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
