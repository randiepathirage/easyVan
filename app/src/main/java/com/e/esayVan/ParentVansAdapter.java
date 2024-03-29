package com.e.esayVan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ParentVansAdapter extends RecyclerView.Adapter<ParentVansAdapter.VansViewHolder> {
        //this context we will use to inflate the layout
        private Context mCtx;
        String number;

        //we are storing all the products in a list
        private List<ParentVans> vehicleList;
        List<ParentVans> filterVans;

        //getting the context and product list with constructor
        public ParentVansAdapter(Context mCtx, List<ParentVans> vehicleList) {
            this.mCtx = mCtx;
            this.vehicleList = vehicleList;
            this.filterVans=vehicleList;
        }

        @Override
        public ParentVansAdapter.VansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflating and returning our view holder
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.parent_newsfeed_layout, null);
            return new ParentVansAdapter.VansViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ParentVansAdapter.VansViewHolder holder, int position) {
            //getting the product of the specified position
            final ParentVans vans = vehicleList.get(position);

            //binding the data with the viewholder views
            holder.textViewNoOfSeatsAvailable.setText("Number of seats Available: "+String.valueOf(vans.getNo_of_seats_available()));
            holder.textViewTotalNoOfSeats.setText("Total no of seats: "+String.valueOf(vans.getTotal_no_of_seats()));
            holder.textViewModel.setText("Vehicle model: "+vans.getModel());
            holder.textViewType.setText("Type: "+vans.getType());
            holder.textViewStartLocation.setText(vans.getStart_location());
            holder.textViewSchools.setText("-"+ vans.getSchool());
            holder.textViewTowns.setText("("+ vans.getTown()+")");

         if(vans.getAC_nonAC()==1){
                holder.textViewAC.setText("Fully air condition");
            }else{
             holder.textViewAC.setText("not air conditioned");
         }
         if(vans.getCaretaker()==1){
                holder.textViewCaretaker.setText(",with a caretaker");
         }else{
             holder.textViewCaretaker.setText(",without caretaker");
         }



           // Glide.with(mCtx)
           //         .load(ParentVans.getImage())
           //         .into(holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mCtx, NewsfeedMoreVanDetails.class);
                    number=String.valueOf(vans.getNumber());
                    intent.putExtra("number",number);//passing vehicle no to the next view
                    mCtx.startActivity(intent);

                }
            });

        }


        @Override
        public int getItemCount() {
            return filterVans.size();
        }


        class VansViewHolder extends RecyclerView.ViewHolder {

            TextView textViewNoOfSeatsAvailable,textViewTotalNoOfSeats,textViewModel,textViewType,textViewAC,textViewCaretaker,textViewStartLocation,textViewSchools,textViewTowns;

            public VansViewHolder(View itemView) {
                super(itemView);

                textViewNoOfSeatsAvailable = itemView.findViewById(R.id.textViewNoOfSeatsAvailable);
                textViewTotalNoOfSeats = itemView.findViewById(R.id.textViewTotalNoOfSeats);
                textViewModel = itemView.findViewById(R.id.textViewModel);
                textViewType= itemView.findViewById(R.id.textViewType);
                textViewAC=itemView.findViewById(R.id.textViewAC);
                textViewCaretaker=itemView.findViewById(R.id.textViewCaretaker);
                textViewStartLocation=itemView.findViewById(R.id.textViewStartLocation);
                textViewSchools=itemView.findViewById(R.id.textViewSchools);
                textViewTowns=itemView.findViewById(R.id.textViewTowns);
            }
        }

        public Filter getFilter(){
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {

                    String key=charSequence.toString();
                    if(key.isEmpty()){
                        filterVans=vehicleList;
                    }else {
                        List<ParentVans> lstFiltered=new ArrayList<>();
                        for(ParentVans row:vehicleList){
                            if(row.getSchool().toLowerCase().contains(key.toLowerCase())){
                                lstFiltered.add(row);
                            }
                        }

                        filterVans=lstFiltered;

                    }
                    FilterResults filterResults=new FilterResults();
                    filterResults.values=filterVans;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                        filterVans=(List<ParentVans>)filterResults.values;
                        notifyDataSetChanged();
                }
            };
        }
}
