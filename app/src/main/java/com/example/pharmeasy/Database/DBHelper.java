package com.example.pharmeasy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PharmEasy";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UsersMaster.Users.TABLE_NAME + " (" +
                        UsersMaster.Users._ID+" INTEGER PRIMARY KEY,"+
                        UsersMaster.Users.COLUMN_NAME_USERNAME + " TEXT,"+
                        UsersMaster.Users.COLUMN_NAME_PASSWORD + " TEXT,"+
                        UsersMaster.Users.COLUMN_NAME_MOBILE + " TEXT,"+
                        UsersMaster.Users.COLUMN_NAME_ADDRESS + " TEXT,"+
                        UsersMaster.Users.COLUMN_NAME_TYPE + " TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public  void addUser(String userName,String password,String mobile,String address,String type){

        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(UsersMaster.Users.COLUMN_NAME_USERNAME,userName);
        values.put(UsersMaster.Users.COLUMN_NAME_PASSWORD,password);
        values.put(UsersMaster.Users.COLUMN_NAME_MOBILE,mobile);
        values.put(UsersMaster.Users.COLUMN_NAME_ADDRESS,address);
        values.put(UsersMaster.Users.COLUMN_NAME_TYPE,type);

        long newRowId = db.insert(UsersMaster.Users.TABLE_NAME,null,values);



    }
}
