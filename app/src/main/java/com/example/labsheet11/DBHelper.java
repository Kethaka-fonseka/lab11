package com.example.labsheet11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
public static final String DATABASE_NAME="courseApp.db";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE=
                " CREATE TABLE "+ DataBase.User.TABLE_NAME+" (" +
                        DataBase.User._ID+" INTEGER PRIMARY KEY," +
                        DataBase.User.COLUMN_NAME_NAME+" TEXT," +
                        DataBase.User.COLUMN_NAME_PASSWORD+" TEXT," +
                        DataBase.User.COLUMN_NAME_TYPE+" TEXT)";

        String CREATE_MESSAGE_TABLE=
                " CREATE TABLE "+ DataBase.Messages.TABLE_NAME+ " (" +
                        DataBase.Messages._ID+" INTEGER PRIMARY KEY," +
                        DataBase.Messages.COLUMN_NAME_USER+ " TEXT," +
                        DataBase.Messages.COLUMN_NAME_SUBJECT+ " TEXT," +
                        DataBase.Messages.COLUMN_NAME_MESSAGE+" TEXT)";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_MESSAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long registerUser(String username,String password,String type){
    SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DataBase.User.COLUMN_NAME_NAME,username);
        values.put(DataBase.User.COLUMN_NAME_PASSWORD,password);
        values.put(DataBase.User.COLUMN_NAME_TYPE,type);

        long val=db.insert(DataBase.User.TABLE_NAME,null,values);
        return  val;
    }

    public String loginUser(String username,String password) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {
                DataBase.User._ID,
                DataBase.User.COLUMN_NAME_NAME,
                DataBase.User.COLUMN_NAME_PASSWORD,
                DataBase.User.COLUMN_NAME_TYPE
        };

        String sortby = DataBase.User.COLUMN_NAME_NAME + " DESC";
        Cursor cursor = db.query(
                DataBase.User.TABLE_NAME,
                columns,
                DataBase.User.COLUMN_NAME_NAME + " =?",
                new String[]{username},
                null,
                null,
                sortby
        );

        ArrayList usernames = new ArrayList();
        ArrayList passwords = new ArrayList();
        ArrayList types = new ArrayList();

        while (cursor.moveToNext()) {
            String uname = cursor.getString(cursor.getColumnIndexOrThrow(DataBase.User.COLUMN_NAME_NAME));
            String pwd = cursor.getString(cursor.getColumnIndexOrThrow(DataBase.User.COLUMN_NAME_PASSWORD));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(DataBase.User.COLUMN_NAME_TYPE));
            usernames.add(uname);
            passwords.add(pwd);
            types.add(type);
        }
        cursor.close();
        if (usernames.indexOf(username) >= 0) {
            if (passwords.get(usernames.indexOf(username)).equals(password)) {
                return types.get(usernames.indexOf(username)).toString();
            } else {
                return "failed";
            }

        } else {
            return "failed";
        }
    }

    public long sendMesage(String username,String subject,String message) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBase.Messages.COLUMN_NAME_USER, username);
        values.put(DataBase.Messages.COLUMN_NAME_SUBJECT, subject);
        values.put(DataBase.Messages.COLUMN_NAME_MESSAGE, message);
        long val = db.insert(DataBase.Messages.TABLE_NAME, null, values);
        return val;
    }

    public ArrayList viewMessages(String req){
        SQLiteDatabase db=getReadableDatabase();
        String [] columns={
                DataBase.Messages._ID,
                DataBase.Messages.COLUMN_NAME_USER,
                DataBase.Messages.COLUMN_NAME_SUBJECT,
                DataBase.Messages.COLUMN_NAME_MESSAGE
        };
        String sortBy= DataBase.Messages._ID+ " DESC";
        Cursor cursor=db.query(
                DataBase.Messages.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortBy
        );

        ArrayList subjects=new ArrayList();
        ArrayList messages=new ArrayList();

        while(cursor.moveToNext()){
            String sub=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Messages.COLUMN_NAME_SUBJECT));
            String msg=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Messages.COLUMN_NAME_MESSAGE));
            messages.add(msg);
            subjects.add(sub);
        }
        cursor.close();
        if (req=="subject"){
            return  subjects;
        }
        else if(req=="message"){
            return  messages;
        }
        else{
            return  null;
        }
    }






    public String viewTopMessages(String req){
        SQLiteDatabase db=getReadableDatabase();
        String [] columns={
                DataBase.Messages._ID,
                DataBase.Messages.COLUMN_NAME_USER,
                DataBase.Messages.COLUMN_NAME_SUBJECT,
                DataBase.Messages.COLUMN_NAME_MESSAGE
        };
        String sortBy= DataBase.Messages._ID+ " DESC";
        Cursor cursor=db.query(
                DataBase.Messages.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortBy
        );

        ArrayList subjects=new ArrayList();
        ArrayList messages=new ArrayList();

        while(cursor.moveToNext()){
            String sub=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Messages.COLUMN_NAME_SUBJECT));
            String msg=cursor.getString(cursor.getColumnIndexOrThrow(DataBase.Messages.COLUMN_NAME_MESSAGE));
            messages.add(msg);
            subjects.add(sub);
        }
        cursor.close();
        if (req=="subject"){
            return  subjects.get(0).toString();
        }
        else if(req=="message"){
            return  messages.get(0).toString();
        }
        else{
            return  null;
        }
    }



    }
