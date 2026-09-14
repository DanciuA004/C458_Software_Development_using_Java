package com.mthree.java_classes_and_objects.address_book.dto;

public class Address {
    private String firstName;
    private String lastName;
    private String address;
    private String postCode;

    public Address(String postCode) {
        this.postCode = postCode;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }
}
