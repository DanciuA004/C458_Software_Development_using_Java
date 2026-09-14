package com.mthree.java_classes_and_objects.address_book.ui;

import com.mthree.java_classes_and_objects.address_book.dto.Address;

import java.util.List;

public class AddressBookView {
    private UserIO io;

    public AddressBookView(UserIO io) {
        this.io = io;
    }

    public int viewMenuAndGetChoice() {
        io.print("\nWelcome to the Address Book!");
        io.print("1. Add New Address");
        io.print("2. Find Address");
        io.print("3. List Address Count");
        io.print("4. List All Addresses");
        io.print("5. Delete Address");
        io.print("6. Exit");
        return io.readInt("Please enter your choice: ", 1, 6);
    }

    public Address getAddress() {
        String firstName = io.readString("Please enter your first name: ");
        String lastName = io.readString("Please enter your last name: ");
        String address = io.readString("Please enter your address: ");
        String postCode = io.readString("Please enter your post code: ");

        Address newAddress = new Address(postCode);
        newAddress.setFirstName(firstName);
        newAddress.setLastName(lastName);
        newAddress.setAddress(address);

        return newAddress;
    }

    public void viewAddress() {

    }

    public void countAddresses(int count) {
        io.print(count + " addresses were found.");
    }

    public void viewAllAddresses(List<Address> addresses) {
        for (Address address : addresses) {
            String addressInfo = String.format("Address: %s %s, Occupant: %s %s",
                    address.getAddress(),
                    address.getPostCode(),
                    address.getFirstName(),
                    address.getLastName()
            );
            io.print(addressInfo);
        }
    }

    public void viewAddAddressBanner() {
        io.print("=~= Add New Address =~=");
    }

    public void viewAddAddressSuccessBanner() {
        io.print("Successfully added address.");
    }

    public void viewFindAddressBanner() {
        io.print("=~= Search For Address =~=");
    }

    public void viewCountAddressesBanner() {
        io.print("=~= Total Address Count =~=");
    }

    public void viewAllAddressesBanner() {
        io.print("=~= All Addresses =~=");
    }

    public void viewDeleteAddressBanner() {
        io.print("=~= Delete Address =~=");
    }

    public void viewExitBanner() {
        io.print("=~= GOODBYE =~=");
    }
}
