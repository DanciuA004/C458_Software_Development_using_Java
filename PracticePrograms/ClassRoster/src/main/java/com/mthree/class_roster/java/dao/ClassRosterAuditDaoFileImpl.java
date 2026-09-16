package com.mthree.class_roster.java.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * This class is used to write to the audit file when a student is added or removed
 */
public class ClassRosterAuditDaoFileImpl implements ClassRosterAuditDao {
    public static final String AUDIT_FILE = "src/main/java/com/mthree/class_roster/audit.txt";

    /**
     * This method takes the entry information and attempts to write it to the audit file in format.
     * If unsuccessful it will throw an error.
     *
     * @param entry Whether a student was added or removed
     * @throws ClassRosterPersistenceException Failure when trying to write to a file.
     */
    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new ClassRosterPersistenceException("Could not persist audit information.", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}
