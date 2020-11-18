package com.e.esayVan.Owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.e.esayVan.R;

import java.util.List;

public class OwnerVansProductAdapter extends RecyclerView.Adapter<OwnerVansProductAdapter.ProductViewHolder>  {

    //View for driver list

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<OwnerVansProduct> productList;

    //getting the context and product list with constructor
    public OwnerVansProductAdapter(Context mCtx, List<OwnerVansProduct> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_owner_vans_list_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        OwnerVansProduct product = productList.get(position);

        //binding the data with the viewholder views
        holder.vehicleNo.setText(product.getVehicle_No());
        holder.NoOfSeatsAV.setText(product.getNoOfSeatsAV());
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView vehicleNo , NoOfSeatsAV;

        public ProductViewHolder(View itemView) {
            super(itemView);
            vehicleNo = itemView.findViewById(R.id.VehicleNO);
            NoOfSeatsAV= itemView.findViewById(R.id.NoOfSeat);

        }
    }
}