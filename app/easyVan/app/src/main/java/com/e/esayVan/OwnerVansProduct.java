package com.e.esayVan;

public class OwnerVansProduct {
    private String vehicle_No;
    private String NoOfSeatsAV;

    public OwnerVansProduct(String vehicle_No, String noOfSeatsAV) {
        this.vehicle_No = vehicle_No;
        NoOfSeatsAV = noOfSeatsAV;
    }

    public String getVehicle_No() {
        return vehicle_No;
    }

    public String getNoOfSeatsAV() {
        return NoOfSeatsAV;
    }
}