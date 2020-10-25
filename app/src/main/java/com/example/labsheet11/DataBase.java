package com.example.labsheet11;

import android.provider.BaseColumns;

public class DataBase {

    public DataBase() {
    }

    public class User implements BaseColumns{
        public static  final String TABLE_NAME="user";
        public static  final String COLUMN_NAME_NAME="username";
        public static final String COLUMN_NAME_PASSWORD="password";
        public  static  final String COLUMN_NAME_TYPE="type";
    }

    public class Messages implements  BaseColumns{
        public static  final String TABLE_NAME="message";
        public static  final String COLUMN_NAME_USER="user";
        public static  final String COLUMN_NAME_SUBJECT="subject";
        public static  final  String COLUMN_NAME_MESSAGE="message";
    }
}
