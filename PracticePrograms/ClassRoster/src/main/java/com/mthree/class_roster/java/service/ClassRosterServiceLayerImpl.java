package com.mthree.class_roster.java.service;

import com.mthree.class_roster.java.dao.ClassRosterAuditDao;
import com.mthree.class_roster.java.dao.ClassRosterDao;
import com.mthree.class_roster.java.dao.ClassRosterPersistenceException;
import com.mthree.class_roster.java.dto.Student;

import java.util.List;

/**
 *  This class implements the business logic (validation)
 *  It sits between the controller and to dao
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
    ClassRosterDao dao;
    private ClassRosterAuditDao audit;

    public ClassRosterServiceLayerImpl(ClassRosterDao dao,  ClassRosterAuditDao audit) {
        this.dao = dao;
        this.audit = audit;
    }

    /**
     * This method does the validation to ensure the studen details are valid
     *
     * @param student Student object to be added to roster.
     * @throws ClassRosterDuplicateIdException If the new student's id already exits
     * @throws ClassRosterDataValidationException If the input data is invalid
     * @throws ClassRosterPersistenceException If there is an issue writing to the roster file
     */
    @Override
    public void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException {

        // First check to see if there is already a student
        // associated with the given student's id
        // If so, we're all done here -
        // throw a ClassRosterDuplicateIdException
        if (dao.getStudent(student.getStudentId()) != null) {
            throw new ClassRosterDuplicateIdException(
                    "ERROR: Could not create student. Student ID "
                            + student.getStudentId()
                            + " already exists");
        }

        // Now validate all the fields on the given Student object.
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateStudentData(student);

        // We passed all our business rules checks so go ahead
        // and persist the Student object
        dao.addStudent(student.getStudentId(), student);

        // The student was successfully created, now write to the audit log
        audit.writeAuditEntry(
                "Student " + student.getStudentId() + " CREATED.");

    }

    /**
     * Makes a call to the DAO to get a list of all the students.
     * No extra code as this does not need validation.
     *
     * @return List of all students
     * @throws ClassRosterPersistenceException If there is an issue writing to the roster file
     */
    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    /**
     * Makes a call to the dao
     *
     * @param studentId ID of the student to retrieve
     * @return the Student object
     * @throws ClassRosterPersistenceException If there is an issue writing to the roster file
     */
    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    /**
     * Makes a call to the DAO to remove a student from the roster.
     * Makes a call to the audit to note that a student was removed.
     *
     * @param studentId ID of the student to remove
     * @return Student object of the removed student
     * @throws ClassRosterPersistenceException If there is an issue writing to file
     */
    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentId);
        // Write to audit log
        audit.writeAuditEntry("Student " + studentId + " REMOVED.");
        return removedStudent;
    }

    /**
     * Gets each variable of the Student object and makes sure it is not null or empty.
     *
     * @param student Student object to validate
     * @throws ClassRosterDataValidationException If there is an issue writing to file
     */
    private void validateStudentData(Student student) throws
            ClassRosterDataValidationException {

        if (student.getFirstName() == null
                || student.getFirstName().trim().length() == 0
                || student.getLastName() == null
                || student.getLastName().trim().length() == 0
                || student.getCohort() == null
                || student.getCohort().trim().length() == 0) {

            throw new ClassRosterDataValidationException(
                    "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }
    }
}
