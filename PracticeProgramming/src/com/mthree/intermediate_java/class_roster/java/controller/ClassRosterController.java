package com.mthree.intermediate_java.class_roster.java.controller;

import com.mthree.intermediate_java.class_roster.java.dao.ClassRosterDao;
import com.mthree.intermediate_java.class_roster.java.dao.ClassRosterPersistenceException;
import com.mthree.intermediate_java.class_roster.java.dto.Student;
import com.mthree.intermediate_java.class_roster.java.service.ClassRosterDataValidationException;
import com.mthree.intermediate_java.class_roster.java.service.ClassRosterDuplicateIdException;
import com.mthree.intermediate_java.class_roster.java.service.ClassRosterServiceLayer;
import com.mthree.intermediate_java.class_roster.java.ui.ClassRosterView;

import java.util.List;

/**
 * This class is the main program loop with the method `run()`
 * It calls out to the other classes of {@link com.mthree.intermediate_java.class_roster.java.ui.ClassRosterView}
 * and {@link com.mthree.intermediate_java.class_roster.java.service.ClassRosterServiceLayerImpl} to interact with the user
 * or run validated code
 */
public class ClassRosterController {
    private ClassRosterView view;
    private ClassRosterServiceLayer service;

    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
        this.service = service;
        this.view = view;
    }

    /**
     * this method has the main menu, it uses view to display the main menu and get an option back
     * it then runs a method in service, depending on what option was picked
     */
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1: // List Students
                        listStudents();
                        break;
                    case 2: // Create new Student
                        createStudent();
                        break;
                    case 3: // View one Student
                        viewStudent();
                        break;
                    case 4: // Remove a Student
                        removeStudent();
                        break;
                    case 5: // Exit
                        keepGoing = false;
                        break;
                    default: // Error
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (ClassRosterPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Calls the view to print the Main Menu and get the users chosen option back
     * @return number of the chosen option
     */
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    /**
     * This method is used to add a new student to the roster.
     *
     * It displays a banner to let the user know they are creating a new student.
     *
     * It then calls the view to ask the user for the students information.
     *
     *      If the input information is valid it will create a new Student object.
     *      If the input information is invalid, it will throw an error
     *      and then loop until the data is input correctly.
     *
     * @throws ClassRosterPersistenceException Failure when trying to write to a file.
     */
    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;

        do {
            Student currentStudent = view.getNewStudentInfo();

            try {
                service.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }

        } while (hasErrors);
    }

    /**
     * This method is used to list all of the students in the roster.
     *
     * It displays a banner to let the user know they are listing students.
     * Then it will create a List objects
     *      and make a call to service to get all the student information to populate the List.
     *
     * Then it will make a call to view to display the List of Students to the user.
     *
     * @throws ClassRosterPersistenceException Failure when trying to write to a file.
     */
    private void listStudents() throws ClassRosterPersistenceException {
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }

    /**
     * This method is used to list one of the students in the roster.
     * It displays a banner to let the user know they are looking for a student.
     *
     * It will then call view to ask the user what the ID is of the student they wish to look at.
     * It will then call service to get that student from the ID.
     * It will then call view again to display that student to the user.
     *
     * @throws ClassRosterPersistenceException Failure when trying to write to a file.
     */
    private void viewStudent() throws ClassRosterPersistenceException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }

    /**
     * This method is used to remove one of the students from the roster.
     *
     * It displays a banner to let the user know they are removing a student.
     *
     * It will then call view to ask the user what the ID is of the student they wish to remove.
     * It will then call service to remove that student from the roster.
     * It will then call view to tell the user it was or wasn't remove successfully.
     *
     * @throws ClassRosterPersistenceException Failure when trying to write to a file.
     */
    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student removedStudent = service.removeStudent(studentId);
        view.displayRemoveResult(removedStudent);
    }

    /**
     * If the user makes a choice from the menu that does not exist
     */
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    /**
     * If the user chooses to exit
     */
    private void exitMessage() {
        view.displayExitBanner();
    }
}
