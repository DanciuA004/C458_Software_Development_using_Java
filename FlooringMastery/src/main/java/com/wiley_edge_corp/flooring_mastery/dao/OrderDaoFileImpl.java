package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class OrderDaoFileImpl implements OrderDao{
    private static final String ORDER_FOLDER = " ";
    private static final String DELIMITER = "::";
    Map<LocalDate, Map<Integer, Order>> orders;
    int largestOrderNumber;

    private void writeToFile() {


    }

    private void loadFromFile() {


    }

    @Override
    public int getNextOrderNumber() {
        return 0;
    }

    @Override
    public Order addOrder(Order order) {
        return null;
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) {
        return null;
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber) {
        return null;
    }

    @Override
    public List<Order> getOrdersForDate(LocalDate date) {
        return List.of();
    }

    @Override
    public Map<LocalDate, Map<Integer, Order>> getAllOrders() {
        return Map.of();
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) {
        return null;
    }
}
