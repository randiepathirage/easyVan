package com.e.esayVan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VansAdapter extends RecyclerView.Adapter<com.e.esayVan.VansAdapter.VansViewHolder> {
        //this context we will use to inflate the layout
        private Context mCtx;

        //we are storing all the products in a list
        private List<Vans> vehicleList;

        //getting the context and product list with constructor
        public VansAdapter(Context mCtx, List<Vans> vehicleList) {
            this.mCtx = mCtx;
            this.vehicleList = vehicleList;
        }

        @Override
        public com.e.esayVan.VansAdapter.VansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflating and returning our view holder
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.parent_layout, null);
            return new com.e.esayVan.VansAdapter.VansViewHolder(view);
        }


        @Override
        public void onBindViewHolder(com.e.esayVan.VansAdapter.VansViewHolder holder, int position) {
            //getting the product of the specified position
            Vans vans = vehicleList.get(position);

            //binding the data with the viewholder views
            holder.textViewNumber.setText(vans.getNumber());
            holder.textViewNoOfSeatsAvailable.setText(String.valueOf(vans.getNo_of_seats_available()));
            holder.textViewTotalNoOfSeats.setText(String.valueOf(vans.getTotal_no_of_seats()));
            holder.textViewModel.setText(vans.getModel());
            holder.textViewType.setText(vans.getType());
            holder.textViewPermitNo.setText(vans.getPermit_no());

            Glide.with(mCtx)
                    .load(Vans.getImage())
                    .into(holder.imageView);

        }


        @Override
        public int getItemCount() {
            return vehicleList.size();
        }


        class VansViewHolder extends RecyclerView.ViewHolder {

            TextView textViewNumber, textViewNoOfSeatsAvailable,textViewTotalNoOfSeats,textViewModel,textViewType,textViewPermitNo;
            ImageView imageView;

            public VansViewHolder(View itemView) {
                super(itemView);

                textViewNumber = itemView.findViewById(R.id.textViewNumber);
                textViewNoOfSeatsAvailable = itemView.findViewById(R.id.textViewNoOfSeatsAvailable);
                textViewTotalNoOfSeats = itemView.findViewById(R.id.textViewTotalNoOfSeats);
                textViewModel = itemView.findViewById(R.id.textViewModel);
                textViewType= itemView.findViewById(R.id.textViewType);
                textViewPermitNo= itemView.findViewById(R.id.textViewPermitNo);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
}
