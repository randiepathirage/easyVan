package com.e.esayVan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DriverProductProfileAdapter extends RecyclerView.Adapter<DriverProductProfileAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<DriverProductProfile> driverProductProfileList;

    public DriverProductProfileAdapter(Context mCtx, List<DriverProductProfile> driverProductProfileList) {
        this.mCtx = mCtx;
        this.driverProductProfileList = driverProductProfileList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.driver_profilecardview, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        DriverProductProfile product = driverProductProfileList.get(position);

        holder.nic.setText(product.getNic());
        holder.fname.setText(product.getFname());
        holder.lname.setText(product.getLname());
        holder.mname.setText(product.getMname());
        holder.contact.setText(product.getContact());
        holder.address.setText(product.getAddress());

    }

    @Override
    public int getItemCount() {
        return driverProductProfileList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView nic,fname,mname,lname,contact,address;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            nic = itemView.findViewById(R.id.nic);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            mname = itemView.findViewById(R.id.mname);
            contact = itemView.findViewById(R.id.contact);
            address = itemView.findViewById(R.id.address);

        }
    }
}