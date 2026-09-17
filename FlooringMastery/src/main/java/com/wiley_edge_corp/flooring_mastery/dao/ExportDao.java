package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.model.Order;

import java.time.LocalDate;
import java.util.Map;

public interface ExportDao {
    public void exportData(Map<LocalDate, Map<Integer, Order>> map);
}
