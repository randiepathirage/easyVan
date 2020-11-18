package com.e.esayVan.Parent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.esayVan.R;

import java.util.List;

public class ParentChildrenAdapter extends RecyclerView.Adapter<ParentChildrenAdapter.ChildrenViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
   private List<ParentChild> childlist;
   String no;

    //getting the context and product list with constructor
    public ParentChildrenAdapter(Context mCtx, List<ParentChild> childlist) {
        this.mCtx = mCtx;
        this.childlist = childlist;
       // this.monChildListener= onChildListener;
    }

    @Override
    public ParentChildrenAdapter.ChildrenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.parent_child_details_layout, null);
        return new ParentChildrenAdapter.ChildrenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentChildrenAdapter.ChildrenViewHolder holder, final int position) {

        final ParentChild children = childlist.get(position);

        //binding the data with the viewholder views
        holder.textViewFirstName.setText(String.valueOf(children.getFirstName())+" "+String.valueOf(children.getLastName()));
        holder.textViewGrade.setText(String.valueOf("Grade: "+children.getGrade()));
        holder.textViewSchool.setText(String.valueOf("School: "+children.getSchool()));
        holder.textViewPickupLocation.setText(String.valueOf("Pick up location: "+children.getPickupLocation()));
        holder.textViewDropoffLocation.setText(String.valueOf("Drop off location: "+children.getDropoffLocation()));
        no=String.valueOf(children.getChildNo());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx,ParentDetails.class);
                intent.putExtra("childNumber",no);//passing child no to the next view
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return childlist.size();
    }

    class ChildrenViewHolder extends RecyclerView.ViewHolder  {

        TextView  textViewGrade, textViewSchool,textViewFirstName,textViewPickupLocation,textViewDropoffLocation;
        //OnChildListener onChildListener;

        public ChildrenViewHolder(View itemView) {
            super(itemView);

            textViewGrade=itemView.findViewById(R.id.dspGrade);
            textViewSchool=itemView.findViewById(R.id.dspSchool);
            textViewFirstName = itemView.findViewById(R.id.dspName);
            textViewPickupLocation = itemView.findViewById(R.id.dspPickup);
            textViewDropoffLocation = itemView.findViewById(R.id.dspDropOff);

        }

    }
}
