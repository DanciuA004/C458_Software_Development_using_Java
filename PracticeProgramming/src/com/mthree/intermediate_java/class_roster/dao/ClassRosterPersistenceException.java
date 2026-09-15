package com.mthree.intermediate_java.class_roster.dao;

public class ClassRosterPersistenceException extends Exception {
    public ClassRosterPersistenceException(String message) {
        super(message);
    }

    public ClassRosterPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
