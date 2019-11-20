package com.example.thealien.fireapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {

    public static final String CHANNEL_ID = "channel_id_0";
    private Context context;

    public NotificationHelper(Context context){
        this.context = context;
    }

    public void sendNotification(String message) {
        //the user will be directed to mainActivity when the notification is clicked
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(TimeLeft.getTimeLeft())
                .setContentText(message)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)//TODO choose the priority u want (for android < 8)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (isAndroidOOrHigher()) {
            builder.setChannelId("com.myApp");
            NotificationChannel channel = new NotificationChannel(
                    "com.myAlarm",
                    "Alarm",
                    NotificationManager.IMPORTANCE_HIGH // TODO choose the priority u want (for android > 8)
            );
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        if(notificationManager != null){
            notificationManager.notify(0, builder.build());
        }
    }

    private boolean isAndroidOOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

}
