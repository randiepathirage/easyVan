package com.e.esayVan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class OwnerReportProductAdapter extends RecyclerView.Adapter<OwnerReportProductAdapter.ProductViewHolder>  {

    //View for driver list

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<OwnerReportProduct> productList;

    //getting the context and product list with constructor
    public OwnerReportProductAdapter(Context mCtx, List<OwnerReportProduct> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_owner_report_view_list_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        OwnerReportProduct product = productList.get(position);

        //binding the data with the viewholder views
        holder.vehicle.setText(product.getVehicle());
        holder.type.setText(product.getType());
        holder.date.setText(product.getDate());
        holder.amount.setText(product.getAmount());

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView vehicle, type , date , amount;

        public ProductViewHolder(View itemView) {
            super(itemView);

            vehicle = itemView.findViewById(R.id.R_vehicle);
            type= itemView.findViewById(R.id.R_type);
            date = itemView.findViewById(R.id.R_date);
            amount = itemView.findViewById(R.id.R_amount);

        }
    }
}
