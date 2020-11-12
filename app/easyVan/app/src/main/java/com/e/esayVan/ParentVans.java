package com.e.esayVan;

public class ParentVans {
        private int no_of_seats_available;
        private int total_no_of_seats;
        private String model,start_location;
        private String type;
        private int AC_nonAC,caretaker;


    public ParentVans(int no_of_seats_available, int total_no_of_seats, String model, String type,int AC_nonAC,int caretaker, String start_location) {
        this.no_of_seats_available = no_of_seats_available;
        this.total_no_of_seats = total_no_of_seats;
        this.model = model;
        this.type = type;
        this.start_location=start_location;
        this.AC_nonAC=AC_nonAC;
        this.caretaker=caretaker;

    }

    public int getNo_of_seats_available() {
        return no_of_seats_available;
    }

    public int getAC_nonAC() {
        return AC_nonAC;
    }

    public int getCaretaker() {
        return caretaker;
    }

    public int getTotal_no_of_seats() {
        return total_no_of_seats;
    }

    public String getStart_location() {
        return start_location;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }


    public void setNo_of_seats_available(int no_of_seats_available) {
        this.no_of_seats_available = no_of_seats_available;
    }

    public void setTotal_no_of_seats(int total_no_of_seats) {
        this.total_no_of_seats = total_no_of_seats;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

}
