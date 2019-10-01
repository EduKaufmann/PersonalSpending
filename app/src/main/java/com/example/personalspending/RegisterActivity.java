package com.example.personalspending;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    Boolean isNew;

    Bill current;
    BillDao billDao;

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


        Intent it = getIntent();
        isNew = it.getBooleanExtra("isNew",true);
        current = (Bill)it.getSerializableExtra("bill");

        if(!isNew){
            edtName.setText(current.getName());
            edtValue.setText(current.getValue().toString());
            edtPlace.setText(current.getPlace());
            edtDate.setText(current.getDateFormated());
            spnType.setSelection(current.getType());
        }




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
                        billDao= new BillDao(getBaseContext());
                        if(!isNew)
                            bill.setId(current.getId());
                        else
                            bill.setId(0);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_register, menu);

        MenuItem actionDelete = menu.findItem(R.id.action_delete);

        if(isNew){
            setTitle(getString(R.string.addSpending));
            actionDelete.setVisible(false);
        }
        else
            setTitle(getString(R.string.editSpending));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();

        if (id==R.id.action_delete) {

            billDao = new BillDao(getBaseContext());


            new AlertDialog.Builder(this)
                    .setTitle(current.getName())
                    .setMessage(getString(R.string.remove_bill_confirmation))
                    .setPositiveButton(getText(R.string.exclude), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String msg;
                            if(billDao.delete(current)){
                                msg = getString(R.string.excluded_bill);
                            }
                            else
                                msg = getString(R.string.excludeError);

                            Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                            finish();
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }

        if (id==android.R.id.home) {
            finish();
        }
        return true;
    }
}
