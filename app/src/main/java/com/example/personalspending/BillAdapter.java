package com.example.personalspending;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BillAdapter extends ArrayAdapter {

    public BillAdapter(Context context, List<Bill> objetcts) {
        super(context, 0, objetcts);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView =convertView;
        DecimalFormat formatter = (new DecimalFormat("#,###.00"));
        SimpleDateFormat dateFormatter = new SimpleDateFormat(Bill.dateFormat);

        if(listItemView==null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_bill, parent, false);
        }
        Bill current = (Bill) getItem(position);
        TextView name = (TextView) listItemView.findViewById(R.id.txtName);
        TextView type = (TextView) listItemView.findViewById(R.id.txtType);
        TextView value = (TextView) listItemView.findViewById(R.id.txtValue);
        TextView date = (TextView) listItemView.findViewById(R.id.txtDate);
        name.setText(current.getName());
        type.setText(Bill.types[current.getType()]);
        value.setText("R$"+formatter.format(current.getValue()));
        date.setText(dateFormatter.format(current.getDate()));

        return  listItemView;

    }
}
