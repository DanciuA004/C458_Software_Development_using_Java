package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.model.Tax;

import java.util.List;
import java.util.Map;

public class TaxDaoFileImpl implements TaxDao {
    private static final String TAX_FILE = " ";
    private static final String DELIMITER = "::";
    private Map<String, Tax> allTaxes;

    @Override
    public List<Tax> getAllTaxes() {
        return null;
    }
}
