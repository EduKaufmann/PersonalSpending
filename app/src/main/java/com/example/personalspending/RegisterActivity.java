package com.example.personalspending;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtValue;
    EditText edtPlace;
    Spinner spnType;
    EditText edtDate;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtName= findViewById(R.id.edtName);
        edtValue= findViewById(R.id.edtValue);
        edtPlace= findViewById(R.id.edtPlace);
        spnType =  findViewById(R.id.spnType);
        edtDate= findViewById(R.id.edtDate);
        btnAdd = findViewById(R.id.btnAdd);

        edtDate.setHint(edtDate.getHint().toString()+" ("+getString(R.string.date_format_display)+")");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg;
                boolean success = false;

                if(edtName.length() > 0 &&
                    (edtValue.length() > 0 && Float.parseFloat(edtValue.getText().toString()) > 0 && Float.parseFloat(edtValue.getText().toString()) < 999999999) &&
                    edtPlace.length() > 0 &&
                    edtDate.length() > 0){

                    try{
                        SimpleDateFormat dateFormatter = new SimpleDateFormat(getString(R.string.date_format));
                        Date date = dateFormatter.parse(edtDate.getText().toString());
                        Bill bill= new Bill(edtName.getText().toString().trim(),  Float.parseFloat(edtValue.getText().toString()),edtPlace.getText().toString().trim(),spnType.getSelectedItemPosition(),date);
                        BillDao billDao= new BillDao(getBaseContext());

                        if(billDao.save(bill)){
                            success = true;
                            msg = getString(R.string.saveSuccess);
                        }
                        else
                            msg = getString(R.string.saveError);
                    }catch(Exception e){
                        msg = getString(R.string.dateError);
                    }

                }else
                    msg = getString(R.string.fieldsError);

                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                if(success)
                    finish();
            }
        });
    }
}
