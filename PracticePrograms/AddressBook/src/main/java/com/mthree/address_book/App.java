package com.mthree.address_book;

import com.mthree.address_book.controller.Controller;
import com.mthree.address_book.dao.AddressBookDao;
import com.mthree.address_book.dao.AddressBookDaoImpl;
import com.mthree.address_book.ui.AddressBookView;
import com.mthree.address_book.ui.UserIO;
import com.mthree.address_book.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        AddressBookView view = new AddressBookView(io);
        AddressBookDao dao = new AddressBookDaoImpl();
        Controller controller = new Controller(dao, view);

        controller.run();
    }
}