package com.mthree.java_classes_and_objects.address_book.dao;

import com.mthree.java_classes_and_objects.address_book.dto.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddressBookDaoImpl implements AddressBookDao {
    private HashMap<String, Address> addresses = new HashMap<>();

    @Override
    public void addAddress(String postCode, Address address) {
        Address newAddress = addresses.put(postCode, address);

    }

    @Override
    public Address getAddress(String lastName) {
        return null;
    }

    @Override
    public int countAddresses() {
        return addresses.size();
    }

    @Override
    public List<Address> getAllAddresses() {
        return new ArrayList<>(addresses.values());
    }

    @Override
    public void removeAddress(String lastName) {

    }
}
