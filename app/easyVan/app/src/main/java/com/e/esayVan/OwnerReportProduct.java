package com.e.esayVan;

public class OwnerReportProduct {


    //View for driver list

    private String vehicle;
    private String type;
    private String date;
    private String amount;

    public OwnerReportProduct(String vehicle, String type, String date, String amount) {
        this.vehicle = vehicle ;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

}
