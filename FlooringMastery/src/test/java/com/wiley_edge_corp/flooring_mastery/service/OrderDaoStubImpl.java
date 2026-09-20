package com.wiley_edge_corp.flooring_mastery.service;

import com.wiley_edge_corp.flooring_mastery.dao.OrderDao;
import com.wiley_edge_corp.flooring_mastery.model.Order;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class OrderDaoStubImpl implements OrderDao {
    private Map<LocalDate, Map<Integer, Order>> orders =  new HashMap<>();

    @Override
    public int getNextOrderNumber() {
        return orders.values().stream()
                .flatMap(map -> map.keySet().stream())
                .max(Integer::compareTo)
                .orElse(-1) + 1;
    }

    @Override
    public Order addOrder(Order order) {
        // Write to memory location
        Map<Integer, Order> ordersOnDate = orders.get(order.getOrderDate());

        if (ordersOnDate == null) {
            ordersOnDate = new HashMap<>();
            orders.put(order.getOrderDate(), ordersOnDate);
        }

        ordersOnDate.put(order.getOrderNumber(), order);

        return order;
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
        Map<Integer, Order> ordersOnDate = orders.get(date);

        if (ordersOnDate == null) {
            return null;
        }

        return new ArrayList<>(ordersOnDate.values());
    }

    @Override
    public Map<LocalDate, Map<Integer, Order>> getAllOrders() {
        return null;

    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) {
        return null;

    }

    public Map<LocalDate, Map<Integer, Order>> getOrders() {
        return orders;
    }
}
