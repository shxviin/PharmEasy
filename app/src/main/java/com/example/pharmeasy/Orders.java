package com.example.pharmeasy;

public class Orders {
    private String customer;
    private String prescription;
    private String address;
    private String phone;

    public Orders(String customer, String prescription, String address, String phone ) {
        this.customer = customer;
        this.prescription = prescription;
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getCustomer() {
        return customer;
    }

    public String getPrescription() {
        return prescription;
    }
}
