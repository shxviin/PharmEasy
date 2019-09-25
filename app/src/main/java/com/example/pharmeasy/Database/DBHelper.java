package com.example.pharmeasy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pharmeasy.Database.UsersMaster.Prescriptions;

import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PharmEasyDB";

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
                "CREATE TABLE " +   Prescriptions.TABLE_NAME + " ("+
                        Prescriptions._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        Prescriptions.COLUMN_NAME_PATIENTNAME + " TEXT,"+
                        Prescriptions.COLUMN_NAME_DIAGNOSIS + " TEXT,"+
                        Prescriptions.COLUMN_NAME_ADDRESS + " TEXT,"+
                        Prescriptions.COLUMN_NAME_PHONE + " TEXT,"+
                        Prescriptions.COLUMN_NAME_PRESCRIPTION + " TEXT)";


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


    public void changeuser (){
        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_CURRENT,"FALSE");


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

    public long addPrescription(String name,String diag, String adds,String phn,String pres){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Prescriptions.COLUMN_NAME_PATIENTNAME,name);
        values.put(Prescriptions.COLUMN_NAME_DIAGNOSIS,diag);
        values.put(Prescriptions.COLUMN_NAME_ADDRESS,adds);
        values.put(Prescriptions.COLUMN_NAME_PHONE,phn);
        values.put(Prescriptions.COLUMN_NAME_PRESCRIPTION,pres);



        long result=db.insert(Prescriptions.TABLE_NAME,null,values);

        return result;
    }


    public Cursor getPatient(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " +Prescriptions.TABLE_NAME;

        Cursor data = db.rawQuery(query,null);
        return data;
    }

}


