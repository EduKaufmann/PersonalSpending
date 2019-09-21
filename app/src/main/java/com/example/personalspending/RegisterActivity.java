package com.example.personalspending;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtValue;
    EditText edtPlace;
    EditText edtType;
    EditText edtDate;
    Button btnAddSpending;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtName= findViewById(R.id.edtName);
        edtValue= findViewById(R.id.edtValue);
        edtPlace= findViewById(R.id.edtPlace);
        edtType= findViewById(R.id.edtType);
        edtDate= findViewById(R.id.edtDate);
        btnAddSpending= findViewById(R.id.btnAddSpending);

        btnAddSpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db= new DbHelper(getBaseContext());
                Carros carro= new Carros(edtNome.getText().toString(), Integer.valueOf(edtAno.getText().toString()),edtMarca.getText().toString());
                CarrosDao carrosDao= new CarrosDao(getBaseContext());
                String msg= carrosDao.save(carro);
                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
            }
        });
    }
}
