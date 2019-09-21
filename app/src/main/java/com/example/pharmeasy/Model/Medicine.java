package com.example.pharmeasy.Model;

public class Medicine {
    private String id;
    private String medicine;

    public Medicine() {
    }

    public String getId() {
        return id;
    }

    public Medicine(String id, String medicine) {
        this.id = id;
        this.medicine = medicine;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getMedicine() {
        return medicine;
    }

}
