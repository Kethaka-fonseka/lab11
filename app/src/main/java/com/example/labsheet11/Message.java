package com.example.labsheet11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Message extends AppCompatActivity {
TextView subject,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent=getIntent();
        subject=findViewById(R.id.sub);
        message=findViewById(R.id.msg);

        subject.setText(intent.getStringExtra("subject"));

        message.setText(intent.getStringExtra("message"));
    }
}