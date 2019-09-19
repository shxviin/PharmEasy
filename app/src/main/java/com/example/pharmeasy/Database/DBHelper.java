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
                        UsersMaster.Users.COLUMN_NAME_TYPE + " TEXT,"+
                        UsersMaster.Users.COLUMN_NAME_CURRENT + " TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

        String SQL_CREATE_PRESCRIPTION=
                "CREATE TABLE " +   UsersMaster.Prescriptions.TABLE_NAME + " ("+
                        UsersMaster.Prescriptions._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_PATIENTNAME+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_DESCRIPTION+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_ADDRESS+" TEXT,"+
                        UsersMaster.Prescriptions.COLUMN_NAME_PHONE+" TEXT,"+
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
        values.put(UsersMaster.Users.COLUMN_NAME_CURRENT,"FALSE");

        long newRowId = db.insert(UsersMaster.Users.TABLE_NAME,null,values);



    }


    public String checkUser (String userName, String password){
        SQLiteDatabase db = getReadableDatabase();



        Cursor cursor = db.rawQuery("SELECT * from users where username = ? and password = ?",new String[]{userName,password});

        if (cursor.moveToFirst()) {
            SQLiteDatabase db1 = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(UsersMaster.Users.COLUMN_NAME_CURRENT,"TRUE");
            String selection = UsersMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";
            String[] selectionArgs = {userName};
            db1.update(UsersMaster.Users.TABLE_NAME,values,selection,selectionArgs);
            String type = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_TYPE));
            return type;
        }
        cursor.close();
        return "";


    }



    public String getUsername() {


        String [] projection = {
                UsersMaster.Users.COLUMN_NAME_USERNAME
        };
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(UsersMaster.Users.TABLE_NAME,
                projection,
                UsersMaster.Users.COLUMN_NAME_CURRENT + " LIKE ? ",
                new String []{"TRUE"},
                null, null, null);
//        cursor.moveToFirst();
        String currentUsername ;


//        Cursor cursor = this.getReadableDatabase().query(
//                UsersMaster.Users.TABLE_NAME, new String[] {UsersMaster.Users.COLUMN_NAME_USERNAME},
//                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
               currentUsername = cursor.getString(cursor.getColumnIndex(UsersMaster.Users.COLUMN_NAME_USERNAME));
            } while (cursor.moveToNext());
        }
        else {
            currentUsername = "123";
        }
        cursor.close();
//        currentUsername = "123";
        return currentUsername;
    }



    public String getMobile() {


        String [] projection = {
                UsersMaster.Users.COLUMN_NAME_MOBILE
        };
        SQLiteDatabase db = getWritableDatabase();


        String selection = UsersMaster.Users.COLUMN_NAME_CURRENT + " LIKE ?";
        String[] selectionArgs = {"TRUE"};



        Cursor cursor = db.query(UsersMaster.Users.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null, null, null);
        String currentUsername ;

        if (cursor.moveToFirst()) {
            do {
                currentUsername = cursor.getString(cursor.getColumnIndex(UsersMaster.Users.COLUMN_NAME_MOBILE));
            } while (cursor.moveToNext());
        }
        else {
            currentUsername = "123";
        }
        cursor.close();
        return currentUsername;
    }




    public String getAddress() {


        String [] projection = {
                UsersMaster.Users.COLUMN_NAME_ADDRESS
        };
        SQLiteDatabase db = getWritableDatabase();


        String selection = UsersMaster.Users.COLUMN_NAME_CURRENT + " LIKE ?";
        String[] selectionArgs = {"TRUE"};



        Cursor cursor = db.query(UsersMaster.Users.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null, null, null);
        String currentUsername ;

        if (cursor.moveToFirst()) {
            do {
                currentUsername = cursor.getString(cursor.getColumnIndex(UsersMaster.Users.COLUMN_NAME_ADDRESS));
            } while (cursor.moveToNext());
        }
        else {
            currentUsername = "123";
        }
        cursor.close();
        return currentUsername;
    }


    public void changeinfo (String userName, String mobile,String address){
        SQLiteDatabase db = getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(UsersMaster.Users.COLUMN_NAME_USERNAME,userName);
            values.put(UsersMaster.Users.COLUMN_NAME_MOBILE,mobile);
            values.put(UsersMaster.Users.COLUMN_NAME_ADDRESS,address);


            String selection = UsersMaster.Users.COLUMN_NAME_CURRENT + " LIKE ?";
            String[] selectionArgs = {"TRUE"};
            db.update(UsersMaster.Users.TABLE_NAME,values,selection,selectionArgs);




    }


    public void changepwd (String pwd){
        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_PASSWORD,pwd);


        String selection = UsersMaster.Users.COLUMN_NAME_CURRENT + " LIKE ?";
        String[] selectionArgs = {"TRUE"};
        db.update(UsersMaster.Users.TABLE_NAME,values,selection,selectionArgs);




    }

    public String getpwd() {


        String [] projection = {
                UsersMaster.Users.COLUMN_NAME_PASSWORD
        };
        SQLiteDatabase db = getWritableDatabase();


        String selection = UsersMaster.Users.COLUMN_NAME_CURRENT + " LIKE ?";
        String[] selectionArgs = {"TRUE"};



        Cursor cursor = db.query(UsersMaster.Users.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null, null, null);
        String currentUsername ;

        if (cursor.moveToFirst()) {
            do {
                currentUsername = cursor.getString(cursor.getColumnIndex(UsersMaster.Users.COLUMN_NAME_PASSWORD));
            } while (cursor.moveToNext());
        }
        else {
            currentUsername = "123";
        }
        cursor.close();
        return currentUsername;
    }

    public boolean addPrescription(String name,String desc, String adds,String phn,String med1,String med2,String med3,String med4,String med5,String med6,String med7,String med8){
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

        return result >= 0;

    }


}


