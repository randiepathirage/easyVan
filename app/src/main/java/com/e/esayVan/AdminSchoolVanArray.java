package com.e.esayVan;

public class AdminSchoolVanArray {
    private String number,model,dnic,onic,tnseats,noseats,start,inno,licno;

    public AdminSchoolVanArray(String num, String model, String dnic, String onic, String tnseats, String noseats, String start, String inno ,String licno) {
        this.number = num;
        this.model = model;
        this.dnic = dnic;
        this.onic = onic;
        this.tnseats = tnseats;
        this.noseats = noseats;
        this.start = start;
        this.inno = inno;
        this.licno = licno;


    }

    public String getNum() { return number; }
    public String getModel() {
        return model;
    }
    public String getDnic() {
        return dnic;
    }
    public String getOnic() {
        return onic;
    }
    public String getTnseats() {
        return tnseats;
    }
    public String getNoseats() {
        return noseats;
    }
    public String getStart() {
        return start;
    }
    public String getInno() {
        return inno;
    }
    public String getLicno() {
        return licno;
    }
}
