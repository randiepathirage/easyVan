package com.e.esayVan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OwnerReportProductAdapter  extends RecyclerView.Adapter<OwnerReportProductAdapter.ProductViewHolder>  {
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
    public OwnerReportProductAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_owner_report_view_list_layout, null);
        return new OwnerReportProductAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OwnerReportProductAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        OwnerReportProduct product = productList.get(position);

        //binding the data with the viewholder views
        holder.vehicleNo.setText(product.getVehicle_No());
        holder.type.setText(product.getType());
        holder.amount.setText(product.getAmount());
        holder.date.setText(product.getDate());


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView vehicleNo , type, amount,date;

        public ProductViewHolder(View itemView) {
            super(itemView);
            vehicleNo = itemView.findViewById(R.id.R_VehicleNO);
            type= itemView.findViewById(R.id.R_type);
            amount = itemView.findViewById(R.id.R_amount);
            date = itemView.findViewById(R.id.R_date);


        }
    }
}
