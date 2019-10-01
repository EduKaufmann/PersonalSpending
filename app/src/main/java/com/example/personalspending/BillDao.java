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
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Log.w("DateOutput",dateFormatter.format(bill.getDate()));
        long result;
        db = bank.getWritableDatabase();
        try{
            values=new ContentValues();
            values.put(bank.NAME, bill.getName());
            values.put(bank.VALUE, bill.getValue());
            values.put(bank.PLACE, bill.getPlace());
            values.put(bank.TYPE, bill.getType());
            values.put(bank.DATE, dateFormatter.format(bill.getDate()));
            Log.w("Values",values.toString());

            if(bill.getId() != null){
                result = db.update(bank.TABLE,values,"_ID = " + bill.getId(),null);
            }else
                result= db.insert(bank.TABLE,null,values);
            db.close();
            if (result !=-1){

                return true;
            }
        }catch (SQLException e){
            Log.e("ERROR",e.getMessage());
        }

        return false;

    }


    public ArrayList<Bill> list(){
        ArrayList<Bill> lista= new ArrayList<>();
        Cursor cursor;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] campos={DbHelper.ID, DbHelper.NAME, DbHelper.VALUE,DbHelper.PLACE,DbHelper.TYPE,DbHelper.DATE};
        db= bank.getReadableDatabase();
        cursor = db.query(DbHelper.TABLE,campos,null,null,null,null,DbHelper.DATE);
        if (cursor!=null){
            if(cursor.moveToFirst())
            do{
                Log.d("RegisterOutput",cursor.getString(0) + " - " + cursor.getString(1) + " - " + cursor.getString(2) + " - " + cursor.getString(3) +  " - " + cursor.getString(4) + " - " + cursor.getString(5));
                int id= cursor.getInt(0) ;
                String name = cursor.getString(1) ;
                Float value = cursor.getFloat(2) ;
                String place = cursor.getString(3) ;
                Integer type = cursor.getInt(4) ;
                Date date = null;
                try{
                    date = dateFormat.parse(cursor.getString(5)) ;
                } catch (Exception e){
                    Log.e("Error", "Unable to parse date:" + cursor.getString(5), e);
                }
                Bill b = new Bill(id,name,value,place,type,date);
                lista.add(b);
            }while (cursor.moveToNext());
            return lista;
        }
        return null;
    }

    public boolean delete(Bill b){
        try{
            String where = DbHelper.ID+ "= " +b.getId();
            db= bank.getReadableDatabase();
            db.delete(DbHelper.TABLE,where,null);
            db.close();
            return true;
        } catch (Exception e){
            Log.e("Error", "Unable to delete bill(id="+b.getId()+" name="+b.getName()+".", e);
            return false;
        }
    }
}