package com.e.esayVan;

public class NewsFeedReview {
    private String review,date;
    private float rate;

    public NewsFeedReview(String review,String date,float rate) {
        this.review = review;
        this.date = date;
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

     public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
