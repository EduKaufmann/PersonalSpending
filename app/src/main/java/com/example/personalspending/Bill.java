package com.example.personalspending;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Bill {
    private int _id;
    private String name;
    private Float value;
    private String place;
    private Integer type;
    private Date date;

    public Bill(String name, Float value, String place, Integer type, Date date) {
        this.name = name;
        this.value = value;
        this.place = place;
        this.type = type;
        this.date = date;
    }

    public Bill(int id, String name, Float value, String place, Integer type, Date date) {
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

    public Float getValue() {
        return value;
    }

    public String getPlace() {
        return place;
    }

    public Integer getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "_id='" + _id + '\'' +
                "name='" + name + '\'' +
                "value='" + value + '\'' +
                "place='" + place + '\'' +
                "type='" + type + '\'' +
                "date='" + date + '\'' +
                '}';
    }
}
