package com.mthree.address_book.ui;

import com.mthree.address_book.dto.Address;

import java.util.List;

public class AddressBookView {
    UserIO io;

    public AddressBookView(UserIO io) {
        this.io = io;
    }

    public int viewMenuAndGetChoice() {
        io.print("Main Menu: ");
        io.print("    1. Add Address");
        io.print("    2. Update Address");
        io.print("    3. Find Address");
        io.print("    4. List Address Count");
        io.print("    5. List All Addresses");
        io.print("    6. Delete Address");
        io.print("    0. Exit");
        return io.readInt("Please select an option: ", 0, 6);
    }

    public Address addAddress() {
        String firstName = io.readString("  First Name: ");
        String lastName = io.readString("   Last Name: ");
        String street = io.readString(" Street: ");
        String city = io.readString("   City: ");
        String state = io.readString("  State: ");
        String zip = io.readString("    Zip Code: ");

        Address address = new Address(lastName);
        address.setFirstName(firstName);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);

        return address;
    }

    public Address updateAddress() {
        String firstName = io.readString("  First Name: ");
        String lastName = io.readString("   Last Name: ");
        String street = io.readString(" Street: ");
        String city = io.readString("   City: ");
        String state = io.readString("  State: ");
        String zip = io.readString("    Zip Code: ");

        Address address = new Address(lastName);
        address.setFirstName(firstName);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);

        return address;
    }

    public String findAddressGetLastName() {
        return io.readString("  Last Name: ");
    }

    public void findAddressDisplayAddress(Address address) {
        io.print("\n" + address.getFirstName() + " " + address.getLastName());
        io.print(address.getStreet());
        io.print(address.getCity() + ", " + address.getState());
        io.print(address.getZip());

        io.readString("Please hit enter to continue. ");
    }

    public void listAddressCount() {

        io.readString("Please hit enter to continue. ");
    }

    public void listAllAddresses(List<Address> addresses) {
        for (Address address : addresses) {
            io.print("\n" + address.getFirstName() + " " + address.getLastName());
            io.print(address.getStreet());
            io.print(address.getCity() + ", " + address.getState());
            io.print(address.getZip());
        }
        io.readString("Please hit enter to continue. ");
    }

    public void deleteAddress() {

        io.readString("Please hit enter to continue. ");
    }

    public void viewAddAddressBanner() {
        io.print("Add Address Menu: ");
    }

    public void viewSuccessfulAddAddressBanner() {
        io.print("Add Address Successful");
        io.readString("Please hit enter to continue. ");
    }

    public void viewUpdateAddressBanner() {
        io.print("Update Address Menu: ");
    }

    public void viewSuccessfulUpdateAddressBanner() {
        io.print("Update Address Successful");
        io.readString("Please hit enter to continue. ");
    }

    public void viewFindAddressBanner() {
        io.print("Find Address Menu: ");
    }

    public void viewListAddressCountBanner() {
        io.print("List Address Count: ");
    }

    public void viewListAllAddressesBanner() {
        io.print("List All Addresses: ");
    }

    public void viewDeleteAddressBanner() {
        io.print("Delete Address Menu: ");
    }
}
