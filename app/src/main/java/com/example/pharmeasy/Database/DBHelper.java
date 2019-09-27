package com.example.pharmeasy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pharmeasy.Database.UsersMaster.Prescriptions;
import com.example.pharmeasy.Database.UsersMaster.Orders;
import com.example.pharmeasy.Database.UsersMaster.Delivery;

import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PharmEasyDB";
    private static final String TAG ="DBHelper" ;

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

        String SQL_CREATE_ORDERS=
                "CREATE TABLE " +   Orders.TABLE_NAME + " ("+
                        "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        Orders.COLUMN_NAME_CUSTOMER_NAME + " TEXT,"+
                        Orders.COLUMN_NAME_PRESCRIPTION + " TEXT," +
                        Orders.COLUMN_NAME_ADDRESS + " TEXT,"+
                        Orders.COLUMN_NAME_PHONE + " TEXT)";


        sqLiteDatabase.execSQL(SQL_CREATE_ORDERS);

        String SQL_CREATE_DELIVERY=
                "CREATE TABLE " +   Delivery.TABLE_NAME + " ("+
                        "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        Delivery.COLUMN_NAME_CUSTOMER_NAME + " TEXT,"+
                        Delivery.COLUMN_NAME_PRESCRIPTION + " TEXT," +
                        Delivery.COLUMN_NAME_ADDRESS + " TEXT,"+
                        Delivery.COLUMN_NAME_PHONE + " TEXT,"+
                        Delivery.COLUMN_NAME_STATUS + " TEXT," +
                        Delivery.COLUMN_NAME_OWNER + " TEXT)";


        sqLiteDatabase.execSQL(SQL_CREATE_DELIVERY);



        String SQL_CREATE_FEED =
        "CREATE TABLE " +   UsersMaster.Feedback.TABLE_NAME + " ("+
                        "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                UsersMaster.Feedback.COLUMN_NAME_NAME + " TEXT,"+
                UsersMaster.Feedback.COLUMN_NAME_EMAIL + " TEXT," +
                UsersMaster.Feedback.COLUMN_NAME_MESSAGE + " TEXT)";


        sqLiteDatabase.execSQL(SQL_CREATE_FEED);

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



    public String gettype() {


        String [] projection = {
                UsersMaster.Users.COLUMN_NAME_TYPE
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
                currentUsername = cursor.getString(cursor.getColumnIndex(UsersMaster.Users.COLUMN_NAME_TYPE));
            } while (cursor.moveToNext());
        }
        else {
            currentUsername = "";
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

    public long addOrder(String name,String pres, String adds,String phn){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Orders.COLUMN_NAME_CUSTOMER_NAME,name);
        values.put(Orders.COLUMN_NAME_PRESCRIPTION,pres);
        values.put(Orders.COLUMN_NAME_ADDRESS,adds);
        values.put(Orders.COLUMN_NAME_PHONE,phn);

        long result = db.insert(Orders.TABLE_NAME,null,values);

        return result;
    }


    public void deleteAccount (){
        SQLiteDatabase db = getReadableDatabase();


        String selection = UsersMaster.Users.COLUMN_NAME_CURRENT + " LIKE ?";
        String[] selectionArgs = {"TRUE"};
        db.delete(UsersMaster.Users.TABLE_NAME,selection,selectionArgs);




    }


    public Cursor getPatient(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " +Prescriptions.TABLE_NAME;

        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor searchPatient(String search){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " +Prescriptions.TABLE_NAME+ " WHERE " +Prescriptions.COLUMN_NAME_PATIENTNAME +
                " LIKE '%" + search + "%'";

        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getPatientID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + "*" + " FROM " +Prescriptions.TABLE_NAME +
                " WHERE " +Prescriptions.COLUMN_NAME_PATIENTNAME + " = '" + name + "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public void updatePatientname(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Prescriptions.TABLE_NAME + " SET " + Prescriptions.COLUMN_NAME_PATIENTNAME +
                " = '" + newName + "' WHERE " + Prescriptions._ID + " = '" + id + "'" +
                " AND " + Prescriptions.COLUMN_NAME_PATIENTNAME + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query" + query);
        Log.d(TAG, "updateName: Setting Name to " + newName);
        db.execSQL(query);
    }

    public void updatePatientaddress(String address, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Prescriptions.TABLE_NAME + " SET " + Prescriptions.COLUMN_NAME_ADDRESS +
                " = '" + address + "' WHERE " + Prescriptions._ID + " = '" + id + "'" ;
        Log.d(TAG, "updateName: query" + query);
        Log.d(TAG, "updateName: Setting Address to " + address);
        db.execSQL(query);
    }
    public void updatePatientmobile(String mobile, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Prescriptions.TABLE_NAME + " SET " + Prescriptions.COLUMN_NAME_PHONE +
                " = '" + mobile + "' WHERE " + Prescriptions._ID + " = '" + id + "'" ;
        Log.d(TAG, "updateName: query" + query);
        Log.d(TAG, "updateName: Setting phone number to " + mobile);
        db.execSQL(query);
    }
    public void updatePatientdiagnosis(String diagnosis, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Prescriptions.TABLE_NAME + " SET " + Prescriptions.COLUMN_NAME_DIAGNOSIS +
                " = '" + diagnosis + "' WHERE " + Prescriptions._ID + " = '" + id + "'" ;
        Log.d(TAG, "updateName: query" + query);
        Log.d(TAG, "updateName: Setting diagnosis to " + diagnosis);
        db.execSQL(query);
    }
    public void updatePatientprescription(String prescription, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Prescriptions.TABLE_NAME + " SET " + Prescriptions.COLUMN_NAME_PRESCRIPTION +
                " = '" + prescription + "' WHERE " + Prescriptions._ID + " = '" + id + "'" ;
        Log.d(TAG, "updateName: query" + query);
        Log.d(TAG, "updateName: Setting prescription to " + prescription);
        db.execSQL(query);
    }


    public void deletePatient(int id,String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + Prescriptions.TABLE_NAME + " WHERE "
                +Prescriptions._ID + " = '" + id + "'" + " AND " + Prescriptions.COLUMN_NAME_PATIENTNAME +
                " = '" + name +"'";
        Log.d(TAG, "deletePatient: query " + query);
        Log.d(TAG, "deletePatient: Deleting  " + name + " from database.");

        db.execSQL(query);
    }

    public  void addfeed(String name,String email,String message){

        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(UsersMaster.Feedback.COLUMN_NAME_NAME,name);
        values.put(UsersMaster.Feedback.COLUMN_NAME_EMAIL,email);
        values.put(UsersMaster.Feedback.COLUMN_NAME_MESSAGE,message);


        long newRowId = db.insert(UsersMaster.Feedback.TABLE_NAME,null,values);



    }


}


