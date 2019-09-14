package com.example.pharmeasy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

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

        String SQL_CREATE_PRESCRIPTION=
                "CREATE TABLE " +   UsersMaster.Prescriptions.TABLE_NAME + " ("+
                        UsersMaster.Prescriptions._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_PATIENTNAME+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_DESCRIPTION+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_ADDRESS+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_PHONE+" INTEGER,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_MED1+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_MED2+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_MED3+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_MED4+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_MED5+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_MED6+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_MED7+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_MED8+" TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_PRESCRIPTION);
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


    public String checkUser (String userName, String password){
        SQLiteDatabase db = getReadableDatabase();

//        String[] columns = {
//                UsersMaster.Users.COLUMN_NAME_USERNAME,
//                UsersMaster.Users.COLUMN_NAME_PASSWORD,
//                UsersMaster.Users.COLUMN_NAME_TYPE
//        };


        Cursor cursor = db.rawQuery("SELECT * from users where username = ? and password = ?",new String[]{userName,password});

        if (cursor.moveToFirst()) {
            String type = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_TYPE));
            return type;
        }
        cursor.close();
        return "";
//        if (cursor.getCount() > 0) return type;
//        else return "";

    }

    public boolean addPrescription(String name,String desc, String adds,int phn,String med1,String med2,String med3,String med4,String med5,String med6,String med7,String med8){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UsersMaster.Prescriptions.COLUMN_NAME_PATIENTNAME,name);
        values.put(UsersMaster.Prescriptions.COLUMN_NAME_DESCRIPTION,desc);
        values.put(UsersMaster.Prescriptions.COLUMN_NAME_ADDRESS,adds);
        values.put(String.valueOf(UsersMaster.Prescriptions.COLUMN_NAME_PHONE),phn);
        values.put(UsersMaster.Prescriptions.COLUMN_NAME_MED1,med1);
        values.put(UsersMaster.Prescriptions.COLUMN_NAME_MED2,med2);
        values.put(UsersMaster.Prescriptions.COLUMN_NAME_MED3,med3);
        values.put(UsersMaster.Prescriptions.COLUMN_NAME_MED4,med4);
        values.put(UsersMaster.Prescriptions.COLUMN_NAME_MED5,med5);
        values.put(UsersMaster.Prescriptions.COLUMN_NAME_MED6,med6);
        values.put(UsersMaster.Prescriptions.COLUMN_NAME_MED7,med7);
        values.put(UsersMaster.Prescriptions.COLUMN_NAME_MED8,med8);

        long result=db.insert(UsersMaster.Prescriptions.TABLE_NAME,null,values);

        if(result<0){
            return  false;
        }else {
            return true;
        }

    }
}
