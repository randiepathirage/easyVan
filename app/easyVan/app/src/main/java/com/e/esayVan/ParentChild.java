package com.e.esayVan;

public class ParentChild {
    private String grade, school,firstName,lastName,pickupLocation,dropoffLocation,vehicleNo,startDate,monthlyFee;

    public ParentChild(String grade, String school, String firstName, String lastName, String pickupLocation, String dropoffLocation, String vehicleNo, String startDate, String monthlyFee) {
        this.grade = grade;
        this.school = school;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.vehicleNo = vehicleNo;
        this.startDate = startDate;
        this.monthlyFee=monthlyFee;
    }

    public String getGrade() {
        return grade;
    }

    public String getSchool() {
        return school;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public String getMonthlyFee() {
        return monthlyFee;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getStartDate() {
        return startDate;
    }
}
