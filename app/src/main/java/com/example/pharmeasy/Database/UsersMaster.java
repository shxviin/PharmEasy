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
        public static final String COLUMN_NAME_DIAGNOSIS="diagnosis";
        public static final String COLUMN_NAME_ADDRESS="address";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_PRESCRIPTION="prescription";

    }

    public static class Orders implements BaseColumns{
        public static final String TABLE_NAME = "orders";
        public static final String COLUMN_NAME_CUSTOMER_NAME = "cusName";
        public static final String COLUMN_NAME_PRESCRIPTION="prescription";
        public static final String COLUMN_NAME_ADDRESS="address";
        public static final String COLUMN_NAME_PHONE = "phone";

    }

    public static class Delivery implements BaseColumns{
        public static final String TABLE_NAME = "delivery";
        public static final String COLUMN_NAME_CUSTOMER_NAME = "cusName";
        public static final String COLUMN_NAME_PRESCRIPTION="prescription";
        public static final String COLUMN_NAME_ADDRESS="address";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_OWNER = "owner";

    }
}
