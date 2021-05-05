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

public class AdminSchoolVanAdapter extends ArrayAdapter<AdminSchoolVanArray> {

    Context context;
    List<AdminSchoolVanArray> vans;


    public AdminSchoolVanAdapter(@NonNull Context context, List<AdminSchoolVanArray> vans) {
        super(context, R.layout.admin_custom_list_item,vans);

        this.context = context;
        this.vans = vans;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_custom_list_item,null,true);

        TextView tvfname = view.findViewById(R.id.txt_fname);
        TextView tvlname = view.findViewById(R.id.txt_lname);

        tvfname.setText(vans.get(position).getModel());
        tvlname.setText(vans.get(position).getNum());

        return view;
    }
}
