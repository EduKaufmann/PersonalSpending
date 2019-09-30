package com.example.personalspending;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("WAI","Create");

        Bill.types = getResources().getStringArray(R.array.types);
        Bill.dateFormat = getString(R.string.date_format);

        TextView empty = findViewById(R.id.empty);

        ListView list = findViewById(R.id.spendingList);

        list.setEmptyView(empty);

        TextView btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(it);

            }
        });
    }

    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        Log.d("WAI","Resume");
        BillDao billDao = new BillDao(getApplicationContext());
        BillAdapter adapter = new BillAdapter(this,billDao.list());
        ListView list = findViewById(R.id.spendingList);
        list.setAdapter(adapter);
    }
}
