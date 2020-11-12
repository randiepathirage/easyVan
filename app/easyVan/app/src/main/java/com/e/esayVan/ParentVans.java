package com.e.esayVan;

public class ParentVans {
    private static String image;
    private String number;
        private int no_of_seats_available;
        private int total_no_of_seats;
        private String model;
        private String type;
        private String permit_no;

    public ParentVans(String number, int no_of_seats_available, int total_no_of_seats, String model, String type, String permit_no, String image) {
        this.number = number;
        this.no_of_seats_available = no_of_seats_available;
        this.total_no_of_seats = total_no_of_seats;
        this.model = model;
        this.type = type;
        this.permit_no = permit_no;
        this.image = image;
    }

    public String getNumber() {
            return number;
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

        public static String getImage() {
            return image;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPermit_no() {
            return permit_no;
        }

}
