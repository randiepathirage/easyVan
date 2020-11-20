package com.e.esayVan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DriverProductAdapter extends RecyclerView.Adapter<DriverProductAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<DriverProduct> productList;

    public DriverProductAdapter(Context mCtx, List<DriverProduct> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.driver_cardview_item, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        DriverProduct product = productList.get(position);

        holder.number.setText(product.getNumber());
        holder.fname.setText(product.getFname());
        holder.lname.setText(product.getLname());
        holder.grade.setText(product.getGrade());
        holder.school.setText(product.getSchool());
        holder.pick_loc.setText(product.getPick_loc());
        holder.dropoff_loc.setText(product.getDropoff_loc());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView number,fname,lname,grade,school,pick_loc,dropoff_loc;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.number);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            grade = itemView.findViewById(R.id.grade);
            school = itemView.findViewById(R.id.school);
            pick_loc = itemView.findViewById(R.id.pick_loc);
            dropoff_loc = itemView.findViewById(R.id.dropof_loc);
        }
    }
}
