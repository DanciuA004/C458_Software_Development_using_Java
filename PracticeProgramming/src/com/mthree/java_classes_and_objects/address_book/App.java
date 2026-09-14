package com.mthree.java_classes_and_objects.address_book;


import com.mthree.java_classes_and_objects.address_book.controller.AddressBookController;
import com.mthree.java_classes_and_objects.address_book.dao.AddressBookDao;
import com.mthree.java_classes_and_objects.address_book.dao.AddressBookDaoImpl;
import com.mthree.java_classes_and_objects.address_book.ui.AddressBookView;
import com.mthree.java_classes_and_objects.address_book.ui.UserIO;
import com.mthree.java_classes_and_objects.address_book.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        AddressBookDao dao = new AddressBookDaoImpl();
        UserIO io = new UserIOConsoleImpl();
        AddressBookView view = new AddressBookView(io);
        AddressBookController controller = new AddressBookController(dao, view);

        controller.run();
    }
}
