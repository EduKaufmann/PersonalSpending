package com.example.personalspending;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import android.text.format.DateFormat;

public class MainActivity extends AppCompatActivity {
    BillAdapter adapter;
    BillDao billDao;
    ListView list;
    TextView txtTotalspend;
    TextView txtMonthlyExpenses;

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
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text or do whatever you need.
                final Bill current = (Bill) adapter.getItem(position);

                Intent it = new Intent(getBaseContext(), RegisterActivity.class);
                it.putExtra("isNew",false);
                startActivity(it);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(current.getName())
                        .setMessage(getString(R.string.remove_bill_confirmation))
                        .setPositiveButton(getText(R.string.exclude), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String msg;
                                if(billDao.delete(current)){
                                    adapter.remove(current);
                                    adapter.notifyDataSetChanged();
                                    msg = getString(R.string.excluded_bill);
                                }
                                else
                                    msg = getString(R.string.excludeError);

                                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text or do whatever you need.
                final Bill current = (Bill) adapter.getItem(position);

                Intent it = new Intent(getBaseContext(), RegisterActivity.class);
                it.putExtra("bill",current);
                it.putExtra("isNew",false);
                startActivity(it);

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long id) {
                // When clicked, show a toast with the TextView text or do whatever you need.
                final Bill current = (Bill) adapter.getItem(position);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(current.getName())
                        .setMessage(getString(R.string.remove_bill_confirmation))
                        .setPositiveButton(getText(R.string.exclude), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String msg;
                                if(billDao.delete(current)){
                                    adapter.remove(current);
                                    adapter.notifyDataSetChanged();
                                    msg = getString(R.string.excluded_bill);
                                }
                                else
                                    msg = getString(R.string.excludeError);

                                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        Log.d("WAI","Resume");

        billDao = new BillDao(getApplicationContext());
        ArrayList<Bill> listBill = billDao.list();
        adapter = new BillAdapter(this,listBill);
        ListView list = findViewById(R.id.spendingList);
        list.setAdapter(adapter);

        Float totalSpend = Float.valueOf(0);
        Float monthlyExpenses = Float.valueOf(0);
        DecimalFormat formatter = (new DecimalFormat("#,##0.00"));

        for(Bill item: listBill){
            totalSpend += item.getValue();

            String monthNumber  = (String) DateFormat.format("MM",   item.getDate());
            String thisMonthNumber  = (String) DateFormat.format("MM",   Calendar.getInstance().getTime());
            if (monthNumber.equals(thisMonthNumber)){
                monthlyExpenses += item.getValue();
            }
        }

        TextView txtMonthlyExpenses = findViewById(R.id.txtMonthlyExpenses);
        txtMonthlyExpenses.setText("Gastos Mensais R$"+formatter.format(monthlyExpenses));
        TextView txtTotalspend = findViewById(R.id.txtTotalspend);
        txtTotalspend.setText("Gastos Totais R$"+formatter.format(totalSpend));
    }
}
