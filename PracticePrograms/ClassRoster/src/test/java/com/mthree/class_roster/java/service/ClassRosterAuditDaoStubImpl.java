package com.mthree.class_roster.java.service;

import com.mthree.class_roster.java.dao.ClassRosterAuditDao;
import com.mthree.class_roster.java.dao.ClassRosterPersistenceException;

public class ClassRosterAuditDaoStubImpl implements ClassRosterAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        //do nothing...
    }
}
