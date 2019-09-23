package com.example.personalspending;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class DbHelper extends SQLiteOpenHelper {

    //mudar nome do banco
    public static final String NOME_BANCO ="BaseCarros.db";
    public static final String TABELA ="Carros";
    public static final String ID ="_id";
    public static final String NOME ="nome";
    public static final String MARCA ="marca";
    public static final String ANO ="ano";
    public static final int VERSAO =1;

    public DbHelper(Context context){
        super(context,NOME_BANCO,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlVersao1="CREATE TABLE "+TABELA+ " ("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NOME + " TEXT NOT NULL, " + MARCA + " TEXT, "+ ANO + " INT)";
        db.execSQL(sqlVersao1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+ TABELA );
        onCreate(db);
    }
}
