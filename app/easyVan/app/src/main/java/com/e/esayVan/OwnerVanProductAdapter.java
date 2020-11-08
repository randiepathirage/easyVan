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


public class OwnerVanProductAdapter extends RecyclerView.Adapter<OwnerVanProductAdapter.VansViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<OwnerVanProduct> VanList;

    //getting the context and product list with constructor
    public OwnerVanProductAdapter(Context mCtx, List<OwnerVanProduct> vehicleList) {
        this.mCtx = mCtx;
        this.VanList = vehicleList;
    }

    @Override
    public com.e.esayVan.OwnerVanProductAdapter.VansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new com.e.esayVan.OwnerVanProductAdapter.VansViewHolder(view);
    }

    @Override
    public void onBindViewHolder(com.e.esayVan.OwnerVanProductAdapter.VansViewHolder holder, int position) {
        //getting the product of the specified position
        OwnerVanProduct vans = VanList.get(position);

        //binding the data with the viewholder views
        holder.username.setText(vans.getUsername());
        holder.password.setText(vans.getPassword());
    }


    @Override
    public int getItemCount() {
        return VanList.size();
    }


    class VansViewHolder extends RecyclerView.ViewHolder {

        TextView username , password ;

        public VansViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            password = itemView.findViewById(R.id.password);
        }
    }
}

