package com.e.esayVan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DriverProductAttendanceAdapter extends RecyclerView.Adapter<DriverProductAttendanceAdapter.ProductViewHolder> {


    private Context mctx;
    private List<DriverProductAttendance> driverattendancelist;

    public DriverProductAttendanceAdapter(Context mctx, List<DriverProductAttendance> driverattendancelist) {
        this.mctx = mctx;
        this.driverattendancelist = driverattendancelist;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(R.layout.driver_attendancecardview, null);
        return new ProductViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        DriverProductAttendance productAttendance = driverattendancelist.get(position);

        holder.fname.setText(productAttendance.getFname());
        holder.lname.setText(productAttendance.getLname());

    }

    @Override
    public int getItemCount() {
        return driverattendancelist.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView fname,lname;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            fname = itemView.findViewById(R.id.at_name);
            lname = itemView.findViewById(R.id.at_lname);
        }
    }
}
