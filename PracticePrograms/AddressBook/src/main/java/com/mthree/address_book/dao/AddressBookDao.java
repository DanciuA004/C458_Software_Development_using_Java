package com.mthree.address_book.dao;

import com.mthree.address_book.dto.Address;

import java.util.ArrayList;
import java.util.Map;

public interface AddressBookDao {

    void addAddress(String lastName, Address address);

    void updateAddress(String lastName,  Address address);

    Address findAddress(String lastName);

    void listAddressCount();

    ArrayList<Address> listAllAddresses();

    void deleteAddress(String lastName);
}
