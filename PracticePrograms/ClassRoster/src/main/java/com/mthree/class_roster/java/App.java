package com.mthree.class_roster.java;

import com.mthree.class_roster.java.controller.ClassRosterController;
import com.mthree.class_roster.java.dao.ClassRosterAuditDao;
import com.mthree.class_roster.java.dao.ClassRosterAuditDaoFileImpl;
import com.mthree.class_roster.java.dao.ClassRosterDao;
import com.mthree.class_roster.java.dao.ClassRosterDaoFileImpl;
import com.mthree.class_roster.java.service.ClassRosterServiceLayer;
import com.mthree.class_roster.java.service.ClassRosterServiceLayerImpl;
import com.mthree.class_roster.java.ui.ClassRosterView;
import com.mthree.class_roster.java.ui.UserIO;
import com.mthree.class_roster.java.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class is the initial class to run to start the program and wire the classes together.
 */
public class App {
    public static void main(String[] args) {
//        // Instantiate the UserIO implementation
//        UserIO myIo = new UserIOConsoleImpl();
//
//        // Instantiate the View and wire the UserIO implementation into it
//        ClassRosterView myView = new ClassRosterView(myIo);
//
//        // Instantiate the DAO
//        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
//
//        // Instantiate the Audit DAO
//        ClassRosterAuditDao myAudit = new ClassRosterAuditDaoFileImpl();
//
//        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
//        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAudit);
//
//        // Instantiate the Controller and wire the Service Layer and View sinto it
//        ClassRosterController controller = new ClassRosterController(myService, myView);
//
//        // Kick off the Controller
//        controller.run();

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller =
                ctx.getBean("controller", ClassRosterController.class);
        controller.run();
    }
}
