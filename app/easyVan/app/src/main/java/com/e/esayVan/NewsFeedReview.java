package com.e.esayVan;

public class NewsFeedReview {
    private String review,date;
    private String rate;

    public NewsFeedReview(String review,String date,String rate) {
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

   public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
