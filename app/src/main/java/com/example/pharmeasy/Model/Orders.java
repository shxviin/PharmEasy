package com.example.pharmeasy.Model;

public class Orders {
    private Integer id;
    private String customerName;
    private String prescription;
    private String address;
    private String phone;

    public Orders(Integer id,String customer, String prescription, String address, String phone ) {
        this.id = id;
        this.customerName = customer;
        this.prescription = prescription;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPrescription() {
        return prescription;
    }
}
