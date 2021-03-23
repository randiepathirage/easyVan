package com.e.esayVan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParentNotificationAdapter extends RecyclerView.Adapter<ParentNotificationAdapter.NotificationViewHolder> {

    private Context mCtx;
    private List<ParentNotifications> notificationList;
    String type,id;

    public ParentNotificationAdapter(Context mCtx, List<ParentNotifications> notificationList) {

        this.mCtx = mCtx;
        this.notificationList = notificationList;
    }

    @Override
    public ParentNotificationAdapter.NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflating and returning our view holder
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.parent_notification_layout, null);
            return new ParentNotificationAdapter.NotificationViewHolder(view);
            }


    @Override
    public void onBindViewHolder(final ParentNotificationAdapter.NotificationViewHolder holder, int position) {
    //getting the product of the specified position
    final ParentNotifications notifications = notificationList.get(position);

        //binding the data with the viewholder views

            type=notifications.getType();

            if(type.equals("request")){
                holder.textViewMsg.setText("New request to "+notifications.getMessage());
            }else{
                holder.textViewMsg.setText(String.valueOf(notifications.getMessage()));
            }

            holder.textViewDate.setText(String.valueOf(notifications.getDate()));
            holder.textViewTime.setText(notifications.getTime());


            if(type.equals("emergency")){

               holder.cardView.setBackgroundColor(Color.parseColor("#ffe5b4"));
            }

    

            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                type=notifications.getType();
                if(type.equals("request")){
                    Intent intent = new Intent(mCtx, OwnerRespond.class);
                    id=notifications.getId();
                    intent.putExtra("reqId",id);
                    mCtx.startActivity(intent);
                }else{

                }
            }
            });
    
    }
    
    
    @Override
    public int getItemCount() {
            return notificationList.size();
            }
    
    
    class NotificationViewHolder extends RecyclerView.ViewHolder {
    
        TextView textViewMsg,textViewDate,textViewTime;
        CardView cardView;
    
        public NotificationViewHolder(View itemView) {
            super(itemView);

            textViewMsg = itemView.findViewById(R.id.textViewMsg);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            cardView=itemView.findViewById(R.id.card);



        }
}
    
    
    
    
    
    
    
    
    
    
}
