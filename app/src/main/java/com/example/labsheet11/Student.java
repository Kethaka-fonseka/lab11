package com.example.labsheet11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Student extends AppCompatActivity {
TextView title;
RecyclerView recyclerView;
MessageAdapter adapter;
ArrayList subList;
ArrayList messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        title=findViewById(R.id.studentTitle);
        Intent intent=getIntent();
        title.setText("Welcome "+intent.getStringExtra("username"));
        DBHelper dbHelper=new DBHelper(this);
        subList=dbHelper.viewMessages("subject");
        messageList=dbHelper.viewMessages("message");
        recyclerView=findViewById(R.id.recycleview);
        adapter=new MessageAdapter(subList,messageList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}