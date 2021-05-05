package com.e.esayVan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OwnerDriverProductAdapter extends RecyclerView.Adapter<OwnerDriverProductAdapter.ProductViewHolder>  {

    //View for driver list

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<OwnerDriversProduct> productList;

    //getting the context and product list with constructor
    public OwnerDriverProductAdapter(Context mCtx, List<OwnerDriversProduct> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_owner_driver_list_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        final OwnerDriversProduct product = productList.get(position);

        //binding the data with the viewholder views
        holder.username.setText(product.getUsername() );
        holder.vehicleNo.setText(product.getVehicleNo());
        holder.LicenseNO.setText(product.getLicenseNo());
        holder.contact.setText(product.getContactNo());
        holder.email.setText(product.getEmail());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, OwnerEditDriverDetials.class);
                intent.putExtra("username",product.getUsername());
                intent.putExtra("vehicleNo",product.getVehicleNo());
                intent.putExtra("LicenseNo",product.getLicenseNo());
                intent.putExtra("content",product.getContactNo());
                intent.putExtra("email",product.getEmail());
                mCtx.startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView username, vehicleNo , LicenseNO , contact , email;

        public ProductViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.List_username);
           vehicleNo= itemView.findViewById(R.id.List_vehicleNo);
           LicenseNO = itemView.findViewById(R.id.List_LicenseNO);
           contact = itemView.findViewById(R.id.List_contact);
           email = itemView.findViewById(R.id.List_email);


        }
    }
}