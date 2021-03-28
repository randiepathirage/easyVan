package com.e.esayVan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChildrenPaymentAdapter extends RecyclerView.Adapter<ChildrenPaymentAdapter.ChildrenViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<PaymentChild> childlist;
    List<PaymentChild> filterChildren;
    String no,parentNIC,mode,childNo;
    String firstName,lastName, grade, school,pick,drop,vehicleNo,ownerID;
    String userName;


    String URL_DELETE="https://10.0.2.2/easyvan/removeChildVan.php";
    String URL_CHECK="https://10.0.2.2/easyvan/checkChildStatus.php";

    //getting the context and product list with constructor
    public ChildrenPaymentAdapter(Context mCtx, List<PaymentChild> childlist) {
        this.mCtx = mCtx;
        this.childlist = childlist;
        this.filterChildren=childlist;
    }

    @Override
    public ChildrenPaymentAdapter.ChildrenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.driver_payment_layout, null);
        return new ChildrenPaymentAdapter.ChildrenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildrenPaymentAdapter.ChildrenViewHolder holder, final int position) {
        final PaymentChild children = childlist.get(position);


        //binding the data with the viewholder views
        holder.textViewName.setText(String.valueOf(children.getFirstName())+" "+String.valueOf(children.getLastName()));
        holder.textViewMonth.setText(children.getMonth());


    }


    @Override
    public int getItemCount() {
        return filterChildren.size();
    }

    class ChildrenViewHolder extends RecyclerView.ViewHolder  {

        TextView textViewName,textViewMonth;
        //OnChildListener onChildListener;

        public ChildrenViewHolder(View itemView) {
            super(itemView);

            textViewName=itemView.findViewById(R.id.textViewName);
            textViewMonth=itemView.findViewById(R.id.textViewMonth);
;

        }

    }

    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String key=charSequence.toString();
                if(key.isEmpty()){
                    filterChildren=childlist;
                }else {
                    List<PaymentChild> lstFiltered=new ArrayList<>();
                    for(PaymentChild row:childlist){
                        if(row.getMonth().toLowerCase().contains(key.toLowerCase())){
                            lstFiltered.add(row);
                        }
                    }

                    filterChildren=lstFiltered;

                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=filterChildren;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                filterChildren=(List<PaymentChild>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
