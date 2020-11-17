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

public class AdminParentAdapter extends ArrayAdapter<AdminParentArray> {

    Context context;
    List<AdminParentArray> parents;


    public AdminParentAdapter(@NonNull Context context, List<AdminParentArray> parents) {
        super(context, R.layout.admin_custom_list_item,parents);

        this.context = context;
        this.parents = parents;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_custom_list_item,null,true);

        TextView tvfname = view.findViewById(R.id.txt_fname);
        TextView tvlname = view.findViewById(R.id.txt_lname);

        tvfname.setText(parents.get(position).getFname());
        tvlname.setText(parents.get(position).getLname());

        return view;
    }
}
