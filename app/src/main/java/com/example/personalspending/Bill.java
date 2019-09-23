package com.example.personalspending;

import java.util.Date;

public class Bill {
    private int _id;
    private String name;
    private Float value;
    private String place;
    private String type;
    private Date date;

    public Bill(String name, Float value, String place, String type, Date date) {
        this.name = name;
        this.value = value;
        this.place = place;
        this.type = type;
        this.date = date;
    }

    public Bill(int id, String name, Float value, String place, String type, Date date) {
        this._id = id;
        this.name = name;
        this.value = value;
        this.place = place;
        this.type = type;
        this.date = date;

    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return Float.toString(value);
    }

    public String getPlace() {
        return place;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "nome='" + name + '\'' +
                ", ANO=" + value +
                ", marca='" + place + '\'' +
                '}';
    }
}
