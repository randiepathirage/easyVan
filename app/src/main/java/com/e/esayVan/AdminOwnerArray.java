package com.e.esayVan;

public class AdminOwnerArray {
    private String nic,fname,lname,contact,address,username,email;

    public AdminOwnerArray(String nic, String contact, String lname, String fname, String address, String username, String email) {
        this.nic = nic;
        this.contact = contact;
        this.lname = lname;
        this.fname = fname;
        this.address = address;
        this.username = username;
        this.email = email;


    }

    public String getoNic() {
        return nic;
    }
    public String getoFname() {
        return fname;
    }
    public String getoLname() {
        return lname;
    }
    public String getoContact() {
        return contact;
    }
    public String getoAddress() {
        return address;
    }
    public String getoUsername() {
        return username;
    }
    public String getoEmail() {
        return email;
    }
}
