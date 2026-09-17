package com.mthree.address_book.dao;

import com.mthree.address_book.dto.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddressBookDaoImpl implements  AddressBookDao {
    Map<String, Address> addresses = new HashMap<String, Address>();

    @Override
    public void addAddress(String lastName, Address address) {
        addresses.put(lastName, address);
    }

    @Override
    public void updateAddress(String lastName,  Address address) {
        addresses.put(lastName, address);
    }

    @Override
    public Address findAddress(String lastName) {
        return addresses.get(lastName);
    }

    @Override
    public void listAddressCount() {

    }

    @Override
    public ArrayList<Address> listAllAddresses() {
        return new ArrayList<>(addresses.values());
    }

    @Override
    public void deleteAddress(String lastName) {

    }
}
