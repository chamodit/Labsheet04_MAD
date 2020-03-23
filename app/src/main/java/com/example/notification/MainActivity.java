package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "newNotification";
    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.Channel_name);
            String description = getString(R.string.Channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
        }

        button = findViewById(R.id.btnSignup);
        editText = findViewById(R.id.name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String personName = editText.getText().toString();
                displayNotification(personName);
            }
        });

    }

   public void displayNotification(String personName){
       Intent intent = new Intent(this, ActivityLogin.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
       PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
       NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
               .setSmallIcon(R.drawable.ic_launcher_background)
               .setContentTitle("Hello " + personName + " ! welcome to the MAD team”")
               .setContentText("Click here to register")
               .setPriority(NotificationCompat.PRIORITY_DEFAULT)
               .setContentIntent(pendingIntent)
               .setAutoCancel(true);
       NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
       notificationManager.notify(0, builder.build());
   }


}
