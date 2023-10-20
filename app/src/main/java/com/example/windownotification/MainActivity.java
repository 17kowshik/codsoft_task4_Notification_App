package com.example.windownotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;

public class MainActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(view -> {
            final String CHANNEL_ID = "example_notification_channel";
            final int NOTIFICATION_ID = 1;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Example Channel", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
            builder.setContentTitle("Window Notification !!");
            builder.setContentText("Congratulations on unlocking a new achievement! Keep thriving for the stars and achieving greatness in your tremendous journey!");
            builder.setSmallIcon(R.drawable.baseline_notifications_24);
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
            if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            notificationManager.notify(NOTIFICATION_ID, builder.build());
        });
    }
}