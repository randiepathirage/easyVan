package com.e.esayVan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class ParentChildrenList extends RecyclerView.Adapter<com.e.esayVan.ParentChildrenList.ChildrenViewHolder> {

        //this context we will use to inflate the layout
        private Context mCtx;

        //we are storing all the products in a list
        private List<ParentChild> childlist;
        String no,parentNIC,mode,childNo;
        String firstName,lastName, grade, school,pick,drop,vehicleNo,ownerID;
        String userName;

        //getting the context and product list with constructor
        public ParentChildrenList(Context mCtx, List<ParentChild> childlist) {
            this.mCtx = mCtx;
            this.childlist = childlist;
            // this.monChildListener= onChildListener;
        }

        @Override
        public com.e.esayVan.ParentChildrenList.ChildrenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            //inflating and returning our view holder
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.parent_child_list, null);
            return new com.e.esayVan.ParentChildrenList.ChildrenViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull com.e.esayVan.ParentChildrenList.ChildrenViewHolder holder, final int position) {
            final ParentChild children = childlist.get(position);


            //binding the data with the viewholder views
            holder.textViewFirstName.setText(children.getFirstName()+" "+children.getLastName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //when viewing more child details);
                }
            });

        }

        @Override
        public int getItemCount() {
            return childlist.size();
        }

        class ChildrenViewHolder extends RecyclerView.ViewHolder  {

            TextView textViewGrade, textViewSchool,textViewFirstName,textViewPickupLocation,textViewDropoffLocation;
            //OnChildListener onChildListener;

            public ChildrenViewHolder(View itemView) {
                super(itemView);

                textViewFirstName = itemView.findViewById(R.id.dspName);


            }

        }
}
