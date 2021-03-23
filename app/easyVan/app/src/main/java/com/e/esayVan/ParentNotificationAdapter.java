package com.e.esayVan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParentNotificationAdapter extends RecyclerView.Adapter<ParentNotificationAdapter.NotificationViewHolder> {

    private Context mCtx;
    private List<ParentNotifications> notificationList;

    public ParentNotificationAdapter(ParentDashboard mCtx, List<ParentNotifications> notificationList) {

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
    public void onBindViewHolder(ParentNotificationAdapter.NotificationViewHolder holder, int position) {
    //getting the product of the specified position
    final ParentNotifications notifications = notificationList.get(position);
    
            //binding the data with the viewholder views
            holder.textViewMsg.setText(String.valueOf(notifications.getMessage()));
            holder.textViewDate.setText(String.valueOf(notifications.getDate()));
            holder.textViewTime.setText(notifications.getTime());

    

            holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
            Intent intent=new Intent(mCtx, NewsfeedMoreVanDetails.class);

            }
            });
    
            }
    
    
    @Override
    public int getItemCount() {
            return notificationList.size();
            }
    
    
    class NotificationViewHolder extends RecyclerView.ViewHolder {
    
        TextView textViewMsg,textViewDate,textViewTime;
    
        public NotificationViewHolder(View itemView) {
            super(itemView);

            textViewMsg = itemView.findViewById(R.id.textViewMsg);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);



        }
}
    
    
    
    
    
    
    
    
    
    
}
