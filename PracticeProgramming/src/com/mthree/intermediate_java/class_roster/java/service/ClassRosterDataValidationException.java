package com.mthree.intermediate_java.class_roster.java.service;

public class ClassRosterDataValidationException extends Exception {

    public ClassRosterDataValidationException(String message) {
        super(message);
    }

    public ClassRosterDataValidationException(String message,
                                              Throwable cause) {
        super(message, cause);
    }
}
