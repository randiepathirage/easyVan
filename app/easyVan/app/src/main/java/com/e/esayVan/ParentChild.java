package com.e.esayVan;

public class ParentChild {
    private String grade, school,firstName,lastName,pickupLocation,dropoffLocation,childNo;

    public ParentChild(String grade, String school, String firstName, String lastName, String pickupLocation, String dropoffLocation,String childNo) {
        this.grade = grade;
        this.school = school;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.childNo=childNo;
    }

    public String getGrade() {
        return grade;
    }

    public String getChildNo() {
        return childNo;
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
}
