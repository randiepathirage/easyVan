package com.e.esayVan.Owner;

public class OwnerDriversProduct {

    //View for driver list

    private String username;
    private String vehicleNo;
    private String licenseNo;
    private String contactNo;
    private String email;

    public OwnerDriversProduct(String username, String vehicleNo, String licenseNo, String contactNo, String email) {
        this.username = username;
        this.vehicleNo = vehicleNo;
        this.licenseNo = licenseNo;
        this.contactNo = contactNo;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEmail() {
        return email;
    }
}
