package com.mthree.intermediate_java.class_roster.tests;

import com.mthree.intermediate_java.class_roster.java.dao.ClassRosterAuditDao;
import com.mthree.intermediate_java.class_roster.java.dao.ClassRosterPersistenceException;

public class ClassRosterAuditDaoStubImpl implements ClassRosterAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        //do nothing . . .
    }
}
