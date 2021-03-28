package com.e.esayVan;

public class PaymentChild {
    String firstName,lastName,Month;
    public PaymentChild(String firstName, String lastName, String month) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.Month=month;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }
}
