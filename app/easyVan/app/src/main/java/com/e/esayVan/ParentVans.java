package com.e.esayVan;

public class ParentVans {
        private int no_of_seats_available;
        private int total_no_of_seats;
        private String model;
        private String type,start_location;
        private boolean AC_nonAC,caretaker;


    public ParentVans(int no_of_seats_available, int total_no_of_seats, String model, String type, String start_location, boolean AC_nonAC, boolean caretaker) {
        this.no_of_seats_available = no_of_seats_available;
        this.total_no_of_seats = total_no_of_seats;
        this.model = model;
        this.type = type;
        this.start_location = start_location;
        this.AC_nonAC = AC_nonAC;
        this.caretaker = caretaker;
    }

    public int getNo_of_seats_available() {
        return no_of_seats_available;
    }

    public int getTotal_no_of_seats() {
        return total_no_of_seats;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getStart_location() {
        return start_location;
    }

    public boolean isAC_nonAC() {
        return AC_nonAC;
    }

    public boolean isCaretaker() {
        return caretaker;
    }
}
