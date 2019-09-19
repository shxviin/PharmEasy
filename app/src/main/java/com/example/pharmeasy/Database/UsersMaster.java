package com.example.pharmeasy.Database;

import android.provider.BaseColumns;

public class UsersMaster {

    public UsersMaster() {
    }

    public  static class Users implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_MOBILE = "mobile";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_CURRENT = "current";
    }

    public static class Prescriptions implements BaseColumns{
        public static final String TABLE_NAME = "prescriptions";
        public static final String COLUMN_NAME_PATIENTNAME = "patientName";
        public static final String COLUMN_NAME_DESCRIPTION="description";
        public static final String COLUMN_NAME_ADDRESS="address";
        public static final int COLUMN_NAME_PHONE = Integer.parseInt("phone");
        public static final String COLUMN_NAME_MED1="medicine_1";
        public static final String COLUMN_NAME_MED2="medicine_2";
        public static final String COLUMN_NAME_MED3="medicine_3";
        public static final String COLUMN_NAME_MED4="medicine_4";
        public static final String COLUMN_NAME_MED5="medicine_5";
        public static final String COLUMN_NAME_MED6="medicine_6";
        public static final String COLUMN_NAME_MED7="medicine_7";
        public static final String COLUMN_NAME_MED8="medicine_8";
    }
}
