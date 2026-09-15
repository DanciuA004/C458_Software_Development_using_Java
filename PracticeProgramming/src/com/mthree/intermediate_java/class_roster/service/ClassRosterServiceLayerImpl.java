package com.mthree.intermediate_java.class_roster.service;

import com.mthree.intermediate_java.class_roster.dao.ClassRosterAuditDao;
import com.mthree.intermediate_java.class_roster.dao.ClassRosterDao;
import com.mthree.intermediate_java.class_roster.dao.ClassRosterPersistenceException;
import com.mthree.intermediate_java.class_roster.dto.Student;

import java.util.List;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
    ClassRosterDao dao;
    private ClassRosterAuditDao audit;

    public ClassRosterServiceLayerImpl(ClassRosterDao dao,  ClassRosterAuditDao audit) {
        this.dao = dao;
        this.audit = audit;
    }

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
                    "ERROR: Could not create student.  Student Id "
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

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentId);
        // Write to audit log
        audit.writeAuditEntry("Student " + studentId + " REMOVED.");
        return removedStudent;
    }

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
