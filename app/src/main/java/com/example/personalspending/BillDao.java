package com.example.personalspending;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class BillDao {
    private SQLiteDatabase db;
    private  DbHelper bank;


    public BillDao(Context context){
        bank = new DbHelper(context);
    }

    public Boolean save(Bill bill){
        ContentValues values;
        long result;
        db = bank.getWritableDatabase();
        try{
            values=new ContentValues();
            values.put(bank.NAME, bill.getName());
            values.put(bank.VALUE, bill.getValue());
            values.put(bank.PLACE, bill.getPlace());
            values.put(bank.TYPE, bill.getType());
            values.put(bank.DATE, bill.getDate().toString());
            result= db.insert(bank.TABLE,null,values);
            db.close();
            if (result !=-1){

                return true;
            }
        }catch (SQLException e){
            Log.e("ERRO",e.getMessage());
        }

        return false;

    }


    public ArrayList<Bill> list(){
        ArrayList<Bill> lista= new ArrayList<>();
        Cursor cursor;
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        String[] campos={DbHelper.ID, DbHelper.NAME, DbHelper.VALUE,DbHelper.PLACE,DbHelper.TYPE,DbHelper.DATE};
        db= bank.getReadableDatabase();
        cursor = db.query(DbHelper.TABLE,campos,null,null,null,null,null);
        if (cursor!=null){
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                int id= cursor.getInt(0) ;
                String name = cursor.getString(1) ;
                Float value = cursor.getFloat(2) ;
                String place = cursor.getString(3) ;
                Integer type = cursor.getInt(3) ;
                Date date = dateFormat.parse(cursor.getString(4)) ;
                Bill b= new Bill(id,name,place,type);
                lista.add(b);
            }
            return lista;
        }
        return null;
    }

    public String deletar(Carros c){
        String where = DbHelper.ID+ "= " +c.getId();
        db= bank.getReadableDatabase();
        db.delete(DbHelper.TABELA,where,null);
        db.close();
        return "Removido";
    }
}
