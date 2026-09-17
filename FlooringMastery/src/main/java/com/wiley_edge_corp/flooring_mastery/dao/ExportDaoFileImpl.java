package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ExportDaoFileImpl implements  ExportDao {
    private static final String EXPORT_FILE = " ";
    private static final String DELIMITER = "::";
    private List<Order> allOrders;

    @Override
    public void exportData(Map<LocalDate, Map<Integer, Order>> map) {


    }

    private void writeToFile() {


    }
}
