package com.mthree.intermediate_java.class_roster.dao;

public interface ClassRosterAuditDao {

    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;
}
