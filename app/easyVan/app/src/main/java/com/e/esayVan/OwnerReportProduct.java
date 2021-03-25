package com.e.esayVan;

public class OwnerReportProduct {
    private String vehicle_No;
    private String type;
    private String amount;
    private String date;

    public OwnerReportProduct(String vehicle_No, String type, String amount, String date) {
        this.vehicle_No = vehicle_No;
        this.type = type;
        this.amount= amount;
        this.date = date;
    }

    public String getVehicle_No() {
        return vehicle_No;
    }

    public String getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}

