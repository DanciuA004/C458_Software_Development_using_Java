package com.mthree.intermediate_java.class_roster.java.ui;

import com.mthree.intermediate_java.class_roster.java.dto.Student;

import java.util.List;

public class ClassRosterView {
    private UserIO io;

    public ClassRosterView(UserIO io) {
        this.io = io;
    }

    /**
     * Prints the main menu and returns the user's option.
     *
     * @return User's choice from menu
     */
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Students");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices: ", 1, 5);
    }

    /**
     *  Takes in the whole roster of Students and prints them out,
     *  one by one and formatted.
     *
     * @param studentList A list of Student objects
     */
    public void displayStudentList(List<Student> studentList) {
        for (Student currentStudent : studentList) {
            String studentInfo = String.format("#%s : %s %s",
                    currentStudent.getStudentId(),
                    currentStudent.getFirstName(),
                    currentStudent.getLastName());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    /**
     * Asks the user for information on the new student they want to add.
     *
     * @return Student object
     */
    public Student getNewStudentInfo() {
        String studentId = io.readString("Please enter Student ID: ");
        String firstName = io.readString("Please enter First Name: ");
        String lastName = io.readString("Please enter Last Name: ");
        String cohort = io.readString("Please enter Cohort: ");

        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);

        return currentStudent;
    }

    /**
     * Asks the user for the Student ID they want.
     * @return Student ID
     */
    public String getStudentIdChoice() {
        return io.readString("Please enter the Student ID: ");
    }

    /**
     * Takes in a student object and displays it formatted.
     *
     * @param student Student object
     */
    public void displayStudent(Student student) {
        if (student != null) {
            io.print(student.getStudentId());
            io.print(student.getFirstName() + " " + student.getLastName());
            io.print(student.getCohort());
            io.print("");
        } else {
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }

    /**
     * Tells the user if the Student was successfully removed or not.
     *
     * @param studentRecord
     */
    public void displayRemoveResult(Student studentRecord) {
        if(studentRecord != null){
            io.print("Student successfully removed.");
        }else{
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayCreateStudentBanner() {
        io.print("=== Create Student ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Student successfully created.  Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Students ===");
    }

    public void displayDisplayStudentBanner () {
        io.print("=== Display Student ===");
    }

    public void displayRemoveStudentBanner () {
        io.print("=== Remove Student ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
