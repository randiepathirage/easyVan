package com.e.esayVan;

public class DriverProduct {

    private String number, fname, lname, grade, school, pick_loc, dropoff_loc;

    public DriverProduct(String number, String fname, String lname, String grade, String school, String pick_loc, String dropoff_loc) {
        this.number = number;
        this.fname = fname;
        this.lname = lname;
        this.grade = grade;
        this.school = school;
        this.pick_loc = pick_loc;
        this.dropoff_loc = dropoff_loc;
    }

    public String getNumber() {
        return number;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getGrade() {
        return grade;
    }

    public String getSchool() {
        return school;
    }

    public String getPick_loc() {
        return pick_loc;
    }

    public String getDropoff_loc() {
        return dropoff_loc;
    }
}





