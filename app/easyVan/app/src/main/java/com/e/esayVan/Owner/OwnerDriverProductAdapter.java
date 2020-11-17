package com.e.esayVan.Owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.e.esayVan.R;

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
        View view = inflater.inflate(R.layout.list_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        OwnerDriversProduct product = productList.get(position);

        //binding the data with the viewholder views
        holder.username.setText(product.getUsername() );
        holder.vehicleNo.setText(product.getVehicleNo());
        holder.LicenseNO.setText(product.getLicenseNo());
        holder.contact.setText(product.getContactNo());
        holder.email.setText(product.getEmail());
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