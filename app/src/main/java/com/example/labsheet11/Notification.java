package com.example.labsheet11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Notification extends AppCompatActivity {
TextView subject,mesage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        subject=findViewById(R.id.notificationSub);
        mesage=findViewById(R.id.notificationMessage);
        DBHelper dbHelper=new DBHelper(this);
        String Sub=dbHelper.viewTopMessages("subject");
        String Msg=dbHelper.viewTopMessages("message");

        subject.setText(Sub);
        mesage.setText(Msg);

    }
}