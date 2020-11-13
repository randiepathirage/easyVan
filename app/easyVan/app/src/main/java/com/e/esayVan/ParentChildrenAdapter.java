package com.e.esayVan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ParentChildrenAdapter extends RecyclerView.Adapter<ParentChildrenAdapter.ChildrenViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
   private List<ParentChild> childlist;

    //getting the context and product list with constructor
    public ParentChildrenAdapter(Context mCtx, List<ParentChild> childlist) {
        this.mCtx = mCtx;
        this.childlist = childlist;
    }

    @Override
    public ParentChildrenAdapter.ChildrenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.parent_child_details_layout, null);
        return new ParentChildrenAdapter.ChildrenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentChildrenAdapter.ChildrenViewHolder holder, int position) {

        ParentChild children = childlist.get(position);

        //binding the data with the viewholder views
        holder.textViewFirstName.setText(String.valueOf(children.getFirstName())+" "+String.valueOf(children.getLastName()));
        holder.textViewGrade.setText(String.valueOf("Grade: "+children.getGrade()));
        holder.textViewSchool.setText(String.valueOf("School: "+children.getSchool()));
        holder.textViewPickupLocation.setText(String.valueOf("Pick up location: "+children.getPickupLocation()));
        holder.textViewDropoffLocation.setText(String.valueOf("Drop off location: "+children.getDropoffLocation()));
        holder.textViewStartDate.setText(String.valueOf("Start date: "+children.getStartDate()));
        holder.textViewMonthlyFee.setText(String.valueOf("Monthly fee: "+children.getMonthlyFee()));
        holder.textViewVehicleNo.setText(String.valueOf("Vehicle No: "+children.getVehicleNo()));
    }

    @Override
    public int getItemCount() {
        return childlist.size();
    }

    class ChildrenViewHolder extends RecyclerView.ViewHolder {

        TextView  textViewGrade, textViewSchool,textViewFirstName,textViewPickupLocation,textViewDropoffLocation,textViewVehicleNo,textViewStartDate,textViewMonthlyFee;;

        public ChildrenViewHolder(View itemView) {
            super(itemView);

            textViewGrade=itemView.findViewById(R.id.dspGrade);
            textViewSchool=itemView.findViewById(R.id.dspSchool);
            textViewFirstName = itemView.findViewById(R.id.dspName);
            textViewPickupLocation = itemView.findViewById(R.id.dspPickup);
            textViewDropoffLocation = itemView.findViewById(R.id.dspDropOff);
            textViewVehicleNo=itemView.findViewById(R.id.dspVehicleNo);
            textViewStartDate=itemView.findViewById(R.id.dspStartDate);
            textViewMonthlyFee=itemView.findViewById(R.id.dspFees);
        }
    }
}
