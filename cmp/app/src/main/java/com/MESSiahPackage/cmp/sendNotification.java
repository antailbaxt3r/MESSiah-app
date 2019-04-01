package com.MESSiahPackage.cmp;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.support.constraint.Constraints.TAG;
import static com.MESSiahPackage.cmp.noti.channelId;

public class sendNotification extends FirebaseMessagingService {
    private NotificationManagerCompat notificationManager;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        String namei=remoteMessage.getData().get("name").toString();
        String id = remoteMessage.getData().get("order_id").toString();
        Intent intent = new Intent(this, Orders.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        notificationManager=NotificationManagerCompat.from(this);
        Notification notification=new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.app_logo)
                .setContentTitle( namei + " your order " +id+" is ready")
                .setContentText("Go to mess to get your food served hot")
                .setSound(uri)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1,notification);

        FirebaseMessaging.getInstance().unsubscribeFromTopic(namei+id);
}}
