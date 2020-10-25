package com.example.labsheet11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Teacher extends AppCompatActivity {
TextView title;
EditText Subject,Message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        title=findViewById(R.id.teacherTile);
       Intent intent=getIntent();
        title.setText("Welcome "+intent.getStringExtra("username"));

        Subject=findViewById(R.id.etSubject);
        Message=findViewById(R.id.etMessage);
    }

    public void insetMessage(View view){
        DBHelper dbHelper=new DBHelper(this);
        long val=dbHelper.sendMesage(title.getText().toString(),Subject.getText().toString(),Message.getText().toString());
        if(val>0){
            Toast.makeText(this,"Message successfully send",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Message sending failed",Toast.LENGTH_SHORT).show();
        }
    }
}