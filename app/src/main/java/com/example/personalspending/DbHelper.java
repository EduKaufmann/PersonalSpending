package com.example.personalspending;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.sql.Date;

public class DbHelper extends SQLiteOpenHelper {

    public static final String NAME_BANK = "BaseBills.db";
    public static final String TABLE = "Bills";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String VALUE = "value";
    public static final String PLACE = "place";
    public static final String TYPE = "type";
    public static final String DATE = "date";
    public static final int VERSION = 1;

    public DbHelper(Context context){
        super(context,NAME_BANK,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlVersao1="CREATE TABLE "+TABLE+ " ("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL, " + VALUE + " FLOAT, " + PLACE + " TEXT, " + TYPE + " TEXT, " + DATE + " DATE)";
        db.execSQL(sqlVersao1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+ TABLE );
        onCreate(db);
    }
}
