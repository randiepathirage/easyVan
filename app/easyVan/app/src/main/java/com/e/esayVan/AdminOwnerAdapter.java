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

public class AdminOwnerAdapter extends ArrayAdapter<AdminOwnerArray> {

    Context context;
    List<AdminOwnerArray> owners;


    public AdminOwnerAdapter(@NonNull Context context, List<AdminOwnerArray> owners) {
        super(context, R.layout.admin_custom_list_item,owners);

        this.context = context;
        this.owners = owners;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_custom_list_item,null,true);

        TextView tvfname = view.findViewById(R.id.txt_fname);
        TextView tvlname = view.findViewById(R.id.txt_lname);

        tvfname.setText(owners.get(position).getFname());
        tvlname.setText(owners.get(position).getLname());

        return view;
    }
}
