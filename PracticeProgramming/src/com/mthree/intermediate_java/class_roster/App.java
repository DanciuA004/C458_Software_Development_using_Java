package com.mthree.intermediate_java.class_roster;

import com.mthree.intermediate_java.class_roster.controller.ClassRosterController;
import com.mthree.intermediate_java.class_roster.dao.ClassRosterAuditDao;
import com.mthree.intermediate_java.class_roster.dao.ClassRosterAuditDaoFileImpl;
import com.mthree.intermediate_java.class_roster.dao.ClassRosterDao;
import com.mthree.intermediate_java.class_roster.dao.ClassRosterDaoFileImpl;
import com.mthree.intermediate_java.class_roster.service.ClassRosterServiceLayer;
import com.mthree.intermediate_java.class_roster.service.ClassRosterServiceLayerImpl;
import com.mthree.intermediate_java.class_roster.ui.ClassRosterView;
import com.mthree.intermediate_java.class_roster.ui.UserIO;
import com.mthree.intermediate_java.class_roster.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();

        // Instantiate the View and wire the UserIO implementation into it
        ClassRosterView myView = new ClassRosterView(myIo);

        // Instantiate the DAO
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();

        // Instantiate the Audit DAO
        ClassRosterAuditDao myAudit = new ClassRosterAuditDaoFileImpl();

        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAudit);

        // Instantiate the Controller and wire the Service Layer into it
        ClassRosterController controller = new ClassRosterController(myService, myView);

        // Kick off the Controller
        controller.run();
    }
}
