package com.mthree.java_classes_and_objects.address_book.dao;

import com.mthree.java_classes_and_objects.address_book.dto.Address;

import java.util.List;

public interface AddressBookDao {

    // Add an Address
    void addAddress(String lastName, Address address);

    // Find an Address by last name
    Address getAddress(String lastName);

    // Return a count of Addresses in the AddressBookDao
    int countAddresses();

    // Return all of the Addresses in the AddressBookDao
    List<Address> getAllAddresses();

    // Remove an Address
    void removeAddress(String lastName);
}
