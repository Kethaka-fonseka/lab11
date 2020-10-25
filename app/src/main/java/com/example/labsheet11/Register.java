package com.example.labsheet11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
EditText UserName,Password;
CheckBox teacher,student;
String box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        UserName=findViewById(R.id.RegUsername);
        Password=findViewById(R.id.Regpassword);
        teacher=findViewById(R.id.teacherBox);
        student=findViewById(R.id.studentBox);


    }
public void registerUser(View view){
        DBHelper dbHelper=new DBHelper(this);
        long val=dbHelper.registerUser(UserName.getText().toString(),Password.getText().toString(),box);
        if(val>0){
            Toast.makeText(this,"Registration Success",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Registration Unsuccess",Toast.LENGTH_SHORT).show();
        }
}

public void checkbox(View view){
        boolean checked=((CheckBox)view).isChecked();

        switch (view.getId()){
            case  R.id.teacherBox:
                if (checked){
                    Toast.makeText(this,"Teacher",Toast.LENGTH_SHORT).show();
                    box="Teacher";
                }
                break;
            case R.id.studentBox:
                if (checked){
                    Toast.makeText(this,"Student",Toast.LENGTH_SHORT).show();
                    box="Student";
                }
                break;
        }
}

}