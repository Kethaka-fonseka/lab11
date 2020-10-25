package com.example.labsheet11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.PasswordAuthentication;

public class Login extends AppCompatActivity {
    private static final String CHANNEL_ID = "C111";
    Button Register;
EditText UserName,Password;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Register=findViewById(R.id.btnRegister);
        UserName=findViewById(R.id.etUserName);
        Password=findViewById(R.id.etPassword);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.labsheet11.Register.class));
            }
        });
    }

    public void login(View view){
        DBHelper dbHelper=new DBHelper(this);
        String type=dbHelper.loginUser(UserName.getText().toString(), Password.getText().toString());
        if (type.equals("Teacher")){
            Toast.makeText(this,"Login success",Toast.LENGTH_SHORT).show();
         Intent intent=new Intent(getApplicationContext(),Teacher.class);
         intent.putExtra("username",UserName.getText().toString());
         startActivity(intent);

        }
       else if (type.equals("Student")){
            Toast.makeText(this,"Login success",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(),Student.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = getString(R.string.channel_name);
                String description =
                        getString(R.string.channel_description);
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new
                        NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);

// or other notification behaviors after this
                NotificationManager notificationManager =
                        getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }

            // Create an explicit intent for an Activity in your app
            Intent intent1 = new Intent(this, Notification.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(this, 0, intent1, 0);
            NotificationCompat.Builder builder = new
                    NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setContentTitle("My notification")
                    .setContentText("You Have New Message")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            NotificationManagerCompat notificationManager
                    = NotificationManagerCompat.from(this);

            notificationManager.notify(0, builder.build());





            intent.putExtra("username",UserName.getText().toString());
            startActivity(intent);
        }
        else {
            Toast.makeText(this,"Login unsuccess",Toast.LENGTH_SHORT).show();
        }
    }



}