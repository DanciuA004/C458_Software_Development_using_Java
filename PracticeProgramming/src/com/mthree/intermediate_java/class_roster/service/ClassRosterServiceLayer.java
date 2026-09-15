package com.mthree.intermediate_java.class_roster.service;

import com.mthree.intermediate_java.class_roster.dao.ClassRosterPersistenceException;
import com.mthree.intermediate_java.class_roster.dto.Student;

import java.util.List;

public interface ClassRosterServiceLayer {

    void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException;

    List<Student> getAllStudents() throws
            ClassRosterPersistenceException;

    Student getStudent(String studentId) throws
            ClassRosterPersistenceException;

    Student removeStudent(String studentId) throws
            ClassRosterPersistenceException;
}
