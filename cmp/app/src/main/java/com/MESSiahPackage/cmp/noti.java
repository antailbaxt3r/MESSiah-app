package com.MESSiahPackage.cmp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class noti extends Application {

    public static final String channelId="channel";

            @Override
    public void onCreate(){
                super.onCreate();

                createNotificationChannel();
            }
    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel=new NotificationChannel(
                    channelId,
                    "channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("this is channel");
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
