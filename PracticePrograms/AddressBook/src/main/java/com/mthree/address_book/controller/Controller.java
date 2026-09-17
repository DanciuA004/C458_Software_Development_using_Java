package com.mthree.address_book.controller;

import com.mthree.address_book.dao.AddressBookDao;
import com.mthree.address_book.dto.Address;
import com.mthree.address_book.ui.AddressBookView;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    AddressBookDao dao;
    AddressBookView view;

    public Controller(AddressBookDao dao, AddressBookView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        int choice = -1;

        while (choice != 0) {

            choice = view.viewMenuAndGetChoice();

            switch (choice) {
                case 1:
                    addAddress();
                    break;
                case 2:
                    updateAddress();
                    break;
                case 3:
                    findAddress();
                    break;
                case 4:
                    listAddressCount();
                    break;
                case 5:
                    listAllAddresses();
                    break;
                case 6:
                    deleteAddress();
                    break;
                case 0:
                    exitMessage();
                    break;
                default:
                    invalidChoice();
            }
        }
    }

    public void addAddress() {
        view.viewAddAddressBanner();
        Address address = view.addAddress();
        dao.addAddress(address.getLastName(), address);
        view.viewSuccessfulAddAddressBanner();
    }

    public void updateAddress() {
        view.viewUpdateAddressBanner();
        Address address = view.updateAddress();
        dao.updateAddress(address.getLastName(), address);
        view.viewSuccessfulUpdateAddressBanner();
    }

    public void findAddress() {
        view.viewFindAddressBanner();
        String lastName = view.findAddressGetLastName();
        Address address = dao.findAddress(lastName);
        view.findAddressDisplayAddress(address);
    }

    public void listAddressCount() {
        view.viewListAddressCountBanner();
    }

    public void listAllAddresses() {
        view.viewListAllAddressesBanner();
        List<Address> addresses  = dao.listAllAddresses();
        view.listAllAddresses(addresses);
    }

    public void deleteAddress() {
        view.viewDeleteAddressBanner();
    }

    public void exitMessage() {

    }

    public void invalidChoice() {

    }
}
