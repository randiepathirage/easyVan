package com.e.esayVan;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ParentChildrenAdapter extends RecyclerView.Adapter<ParentChildrenAdapter.ChildrenViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
   private List<ParentChild> childlist;
   private OnChildListener monChildListener;

    //getting the context and product list with constructor
    public ParentChildrenAdapter(Context mCtx, List<ParentChild> childlist, OnChildListener onChildListener) {
        this.mCtx = mCtx;
        this.childlist = childlist;
        this.monChildListener= onChildListener;
    }

    @Override
    public ParentChildrenAdapter.ChildrenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.parent_child_details_layout, null);
        return new ParentChildrenAdapter.ChildrenViewHolder(view,monChildListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentChildrenAdapter.ChildrenViewHolder holder, final int position) {

        ParentChild children = childlist.get(position);

        //binding the data with the viewholder views
        holder.textViewFirstName.setText(String.valueOf(children.getFirstName())+" "+String.valueOf(children.getLastName()));
        holder.textViewGrade.setText(String.valueOf("Grade: "+children.getGrade()));
        holder.textViewSchool.setText(String.valueOf("School: "+children.getSchool()));
        holder.textViewPickupLocation.setText(String.valueOf("Pick up location: "+children.getPickupLocation()));
        holder.textViewDropoffLocation.setText(String.valueOf("Drop off location: "+children.getDropoffLocation()));

    }

    @Override
    public int getItemCount() {
        return childlist.size();
    }

    class ChildrenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView  textViewGrade, textViewSchool,textViewFirstName,textViewPickupLocation,textViewDropoffLocation;
        OnChildListener onChildListener;

        public ChildrenViewHolder(View itemView, OnChildListener onChildListener) {
            super(itemView);

            textViewGrade=itemView.findViewById(R.id.dspGrade);
            textViewSchool=itemView.findViewById(R.id.dspSchool);
            textViewFirstName = itemView.findViewById(R.id.dspName);
            textViewPickupLocation = itemView.findViewById(R.id.dspPickup);
            textViewDropoffLocation = itemView.findViewById(R.id.dspDropOff);


            this.onChildListener=onChildListener;

            //attached onclick listener to entire view holder
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            onChildListener.onNoteClick(getAdapterPosition());

        }
    }

    public interface OnChildListener{
        void onNoteClick(int position);
    }
}
