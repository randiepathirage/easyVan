package com.e.esayVan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OwnerDriverProductAdapter extends RecyclerView.Adapter<OwnerDriverProductAdapter.ProductViewHolder> {

    //View for driver list

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<OwnerDriversProduct> productList;

    //getting the context and product list with constructor
    public OwnerDriverProductAdapter(Context mCtx, List<OwnerDriversProduct> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        OwnerDriversProduct product = productList.get(position);

        //binding the data with the viewholder views
        holder.username.setText(product.getUsername() );
        holder.password.setText(product.getPassword());
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView username, password;

        public ProductViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            password= itemView.findViewById(R.id.password);

        }
    }
}