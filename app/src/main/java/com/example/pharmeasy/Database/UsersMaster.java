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
    }
}
