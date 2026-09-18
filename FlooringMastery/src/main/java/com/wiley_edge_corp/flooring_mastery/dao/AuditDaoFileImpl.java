package com.wiley_edge_corp.flooring_mastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AuditDaoFileImpl implements AuditDao {
    private static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String message) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
            out.println(message);
        } catch (IOException e) {

        }

        out.flush();
        out.close();
    }
}
