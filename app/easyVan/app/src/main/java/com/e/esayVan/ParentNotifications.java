package com.e.esayVan;

public class ParentNotifications {
    String message,date,time,type,id;

    public ParentNotifications(String id, String message, String date,String time,String type) {

        this.id=id;
        this.type=type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


}
