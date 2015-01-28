package com.firoze.androidplayground.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by firozerakib on 1/27/15.
 * Service to show Notification
 */
public class NotificationService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY_COMPATIBILITY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
