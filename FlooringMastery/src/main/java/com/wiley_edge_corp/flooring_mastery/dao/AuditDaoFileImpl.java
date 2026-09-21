package com.wiley_edge_corp.flooring_mastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The audit dao handles writing to audit file for order add and order remove
 */
public class AuditDaoFileImpl implements AuditDao {
    private String AUDIT_FILE = "audit.txt";

    public AuditDaoFileImpl() {
        AUDIT_FILE = "audit.txt";
    }

    public AuditDaoFileImpl(String AUDIT_FILE) {
        this.AUDIT_FILE = AUDIT_FILE;
    }

    /**
     * Write to audit file
     *
     * @param message message to write
     */
    @Override
    public void writeAuditEntry(String message) {
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
            out.println(message);
        } catch (Exception e) {
            // Do nothing
        }

        out.flush();
        out.close();
    }
}
