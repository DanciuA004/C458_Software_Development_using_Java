package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.model.Tax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

/**
 * Handles loading tax rate for each state from taxes.txt
 */
public class TaxDaoFileImpl implements TaxDao {
    private static final String TAX_FILE = "sample/data/taxes.txt";
    private static final String DELIMITER = ",";
    private Map<String, Tax> allTaxes = new HashMap<>();

    private void loadFile() {
        // Create Scanner for reading the file
        Scanner scanner = null;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAX_FILE)));
            // Skip the header of the file
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {

        }

        // Unmarshalling and populating to allTaxes
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(DELIMITER);
            BigDecimal TaxRate = new BigDecimal(line[2]);
            Tax tax = new Tax(line[0], line[1], TaxRate);
            allTaxes.put(tax.getStateAbbreviation(), tax);
        }
    }

    @Override
    public List<Tax> getAllTaxes() {
        loadFile();
        return new ArrayList<>(allTaxes.values());
    }
}
