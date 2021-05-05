package com.e.esayVan;

public class AdminChildArray {
    private String parentnic,fname,lname,school,grade,pickup,vehiclenum,pname;
    private String childnum,fee;


    public AdminChildArray(String childnum,String parentnic,String fname,String lname,String school,String grade,String pickup,String vehiclenum,String fee,String pname) {
        this.childnum = childnum;
        this.parentnic = parentnic;
        this.fname = fname;
        this.lname = lname;
        this.school = school;
        this.grade = grade;
        this.pickup = pickup;
        this.vehiclenum = vehiclenum;
        this.fee = fee;
        this.pname = pname;


    }

    public String getChildnum() {
        return childnum;
    }
    public String getParentNIC() {
        return parentnic;
    }
    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public String getSchool() {
        return school;
    }
    public String getGrade() {
        return grade;
    }
    public String getPickup() {
        return pickup;
    }
    public String getVehiclenum() {
        return vehiclenum;
    }
    public String getFee() {
        return fee;
    }
    public String getPname() {
        return pname;
    }
}
