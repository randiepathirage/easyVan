package com.e.esayVan;

public class ParentNotifications {
    String message,date,time ;//,childName;

    public ParentNotifications(String message, String date,String time/*,String childName*/) {

       // this.childName=childName;
        this.date=date;
        this.time=time;
        this.message=message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
/*
    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }*/
}
