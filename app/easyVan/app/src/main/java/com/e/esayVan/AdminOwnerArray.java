package com.e.esayVan;

public class AdminOwnerArray {
    private String nic,fname,lname,contact,address;

    public AdminOwnerArray(String nic, String contact, String lname, String fname, String address) {
        this.nic = nic;
        this.contact = contact;
        this.lname = lname;
        this.fname = fname;
        this.address = address;
    }

    public String getNic() {
        return nic;
    }
    public String getFname() {
        return fname;
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
