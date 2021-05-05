package com.e.esayVan;

public class DriverProductProfile {

    private  String nic,fname,mname,lname,contact,address;

    public DriverProductProfile(String nic, String fname, String mname, String lname, String contact, String address) {
        this.nic = nic;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.contact = contact;
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public String getFname() {
        return fname;
    }

    public String getMname() {
        return mname;
    }

    public String getLname() {
        return lname;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }
}
