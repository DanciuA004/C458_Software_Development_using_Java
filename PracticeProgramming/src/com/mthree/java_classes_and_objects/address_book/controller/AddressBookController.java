package com.mthree.java_classes_and_objects.address_book.controller;

import com.mthree.java_classes_and_objects.address_book.dao.AddressBookDao;
import com.mthree.java_classes_and_objects.address_book.dto.Address;
import com.mthree.java_classes_and_objects.address_book.ui.AddressBookView;

import java.util.List;

public class AddressBookController {
    private AddressBookDao dao;
    private AddressBookView view;

    public AddressBookController(AddressBookDao dao, AddressBookView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        int menuOption = 0;
        boolean exit = false;

        while (!exit) {
            menuOption = getMenuOption();

            switch (menuOption) {
                case 1:
                    addAddress();
                    break;
                case 2:
                    System.out.println("FIND ADDRESS");
                    break;
                case 3:
                    countAddresses();
                    break;
                case 4:
                    listAddresses();
                    break;
                case 5:
                    System.out.println("DELETE ADDRESS");
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        exitMessage();
    }

    private int getMenuOption() {
        return view.viewMenuAndGetChoice();
    }

    // Option 1
    private void addAddress() {
        view.viewAddAddressBanner();

        Address address = view.getAddress();
        dao.addAddress(address.getPostCode(),  address);

        view.viewAddAddressSuccessBanner();
    }

    // Option 2
    private void findAddress() {

    }

    // Option 3
    private void countAddresses() {
        view.viewCountAddressesBanner();
        int count = dao.countAddresses();
        view.countAddresses(count);
    }

    // Option 4
    private void listAddresses() {
        view.viewAllAddressesBanner();

        List<Address> addresses = dao.getAllAddresses();
        view.viewAllAddresses(addresses);
    }

    // Option 5
    private void deleteAddress() {

    }

    private void exitMessage() {
        view.viewExitBanner();
    }
}
