package com.e.esayVan;

public class AdminParentArray {
    private String nic,fname,lname,contact,address,username,email;

    public AdminParentArray(String nic, String contact, String lname, String fname, String address, String username, String email) {
        this.nic = nic;
        this.contact = contact;
        this.lname = lname;
        this.fname = fname;
        this.address = address;
        this.username = username;
        this.email = email;


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
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
}
