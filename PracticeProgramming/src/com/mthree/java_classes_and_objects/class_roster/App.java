package com.mthree.java_classes_and_objects.class_roster;

import com.mthree.java_classes_and_objects.class_roster.controller.ClassRosterController;
import com.mthree.java_classes_and_objects.class_roster.dao.ClassRosterDao;
import com.mthree.java_classes_and_objects.class_roster.dao.ClassRosterDaoFileImpl;
import com.mthree.java_classes_and_objects.class_roster.ui.ClassRosterView;
import com.mthree.java_classes_and_objects.class_roster.ui.UserIO;
import com.mthree.java_classes_and_objects.class_roster.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterController controller =
                new ClassRosterController(myDao, myView);
        controller.run();
    }
}
