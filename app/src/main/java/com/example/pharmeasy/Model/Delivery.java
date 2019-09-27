package com.example.pharmeasy.Model;

public class Delivery {
    private Integer id;
    private String customer;
    private String prescription;
    private String address;
    private String phone;
    private String status;
    private String owner;

    public String getOwner() {
        return owner;
    }

    public Delivery(Integer id, String customer, String prescription, String address, String phone, String status, String owner) {
        this.id = id;
        this.customer = customer;
        this.prescription = prescription;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
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
