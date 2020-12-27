package com.e.esayVan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.CommentsViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<NewsFeedReview> reviewlist;
    String no,parentNIC;

    public ReviewAdapter(Context mCtx, List<NewsFeedReview> reviewlist) {
        this.mCtx = mCtx;
        this.reviewlist = reviewlist;
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.newsfeed_reviews_layout, null);
        return new ReviewAdapter.CommentsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {

        final NewsFeedReview comment = reviewlist.get(position);

        //binding the data with the viewholder views
        holder.ratingBar2.setRating(float)(comment.getRate());
        holder.textViewDate.setText(String.valueOf(comment.getDate()));
        holder.textViewReview.setText(String.valueOf(comment.getReview()));

    }

    @Override
    public int getItemCount() {
        return reviewlist.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewReview, textViewDate;
        RatingBar ratingBar2;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);

            ratingBar2=itemView.findViewById(R.id.ratingBar2);
            textViewDate=itemView.findViewById(R.id.dspDate);
            textViewReview = itemView.findViewById(R.id.dspReview);
        }
    }
}
