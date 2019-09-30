package com.example.personalspending;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Bill {
    public static String types[];
    public static String dateFormat;

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

    public Integer getId() {
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

    public String getDateFormated(){SimpleDateFormat
            dateFormatter = new SimpleDateFormat(this.dateFormat);
            return dateFormatter.format(this.getDate());
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
