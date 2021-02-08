package com.e.esayVan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdminDriverAdapter extends ArrayAdapter<AdminDriverArray> {

    Context context;
    List<AdminDriverArray> drivers;


    public AdminDriverAdapter(@NonNull Context context, List<AdminDriverArray> drivers) {
        super(context, R.layout.admin_custom_list_item,drivers);

        this.context = context;
        this.drivers = drivers;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_custom_list_item,null,true);

        TextView tvfname = view.findViewById(R.id.txt_fname);
        TextView tvlname = view.findViewById(R.id.txt_lname);

        tvfname.setText(drivers.get(position).getFname());
        tvlname.setText(drivers.get(position).getLname());

        return view;
    }
}
