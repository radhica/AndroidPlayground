package com.firoze.androidplayground.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firoze.androidplayground.R;
import com.firoze.baseClasses.BaseActivity;

public class NotificationsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notifications, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getTag() {
        return "NotificationsActivity";
    }

    public void displayNotification(View v) {
        String longText = "Bacon ipsum dolor amet aute chicken pancetta spare ribs minim mollit. Pastrami proident tongue beef ribs chicken cupim drumstick corned beef, jerky biltong commodo reprehenderit. Dolore rump turducken, commodo hamburger consectetur sint ex tail pancetta shank. Est cupidatat nisi drumstick, hamburger in kevin do rump anim elit pork belly. Minim in ullamco tongue sint irure, consectetur meatball.";

        Intent resultIntent = new Intent(this, NotificationsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(this);
        myBuilder.setSmallIcon(R.drawable.ic_launcher);
        myBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        myBuilder.setContentTitle("My Title");
        myBuilder.setContentText("My Content String");
        myBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(longText));
        myBuilder.setContentIntent(pendingIntent);

        Notification myNotification = myBuilder.build();
        myNotification.defaults |= Notification.DEFAULT_ALL;
        myNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager myNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        myNotificationManager.notify(111, myNotification);
    }
}
