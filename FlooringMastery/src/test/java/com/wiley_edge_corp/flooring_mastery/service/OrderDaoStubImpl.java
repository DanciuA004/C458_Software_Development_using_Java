package com.wiley_edge_corp.flooring_mastery.service;

import com.wiley_edge_corp.flooring_mastery.dao.OrderDao;
import com.wiley_edge_corp.flooring_mastery.model.Order;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * A stub implementation to test orderDao, still uses the in memory hashMap the same,
 * but does not read or write to file.
 */
public class OrderDaoStubImpl implements OrderDao {
    private Map<LocalDate, Map<Integer, Order>> orders =  new HashMap<>();

    /**
     * Next order number
     *
     * @return next order number
     */
    @Override
    public int getNextOrderNumber() {
        return orders.values().stream()
                .flatMap(map -> map.keySet().stream())
                .max(Integer::compareTo)
                .orElse(-1) + 1;
    }

    /**
     * Adds an order object to orders map and to file
     *
     * @param order order object to add
     * @return order object
     */
    @Override
    public Order addOrder(Order order) {
        // Write to memory location
        Map<Integer, Order> ordersOnDate = orders.get(order.getOrderDate());

        // If map doesn't exist create a new one
        if (ordersOnDate == null) {
            ordersOnDate = new HashMap<>();
            orders.put(order.getOrderDate(), ordersOnDate);
        }

        // Put the order into the map
        ordersOnDate.put(order.getOrderNumber(), order);

        return order;
    }

    /**
     * Retrieve an order from the map from the date and order number
     *
     * @param date order date
     * @param orderNumber order number
     * @return order object, or null if order does not exist
     */
    @Override
    public Order getOrder(LocalDate date, int orderNumber) {
        Map<Integer, Order> ordersOnDate;
        Order order;

        try {
            ordersOnDate = orders.get(date);
            order = ordersOnDate.get(orderNumber);
        } catch (Exception e) {
            return null;
        }

        return order;
    }

    /**
     * Adds an edited order to orders
     *
     * @param order edited order to add
     * @return returns order object
     */
    @Override
    public Order editOrder(Order order) {
        // Write to memory location
        Map<Integer, Order> ordersOnDate = orders.get(order.getOrderDate());

        // Put the order into the map
        ordersOnDate.put(order.getOrderNumber(), order);

        return order;
    }

    /**
     * Get list of orders for a specific date
     *
     * @param date date for search
     * @return list of orders
     */
    @Override
    public List<Order> getOrdersForDate(LocalDate date) {
        Map<Integer, Order> ordersOnDate = orders.get(date);

        // If no orders for that date exists
        if (ordersOnDate == null) {
            return null;
        }

        return new ArrayList<>(ordersOnDate.values());
    }

    /**
     * Exports all orders to external file
     *
     * @return orders map
     */
    @Override
    public Map<LocalDate, Map<Integer, Order>> exportAllOrders() {
        return null;
    }

    /**
     * Remove order from orders and rewrite file
     *
     * @param date date for order
     * @param orderNumber order number
     * @return order object
     */
    @Override
    public Order removeOrder(LocalDate date, int orderNumber) {
        // Map of orders for date
        Map<Integer, Order> ordersOnDate = orders.get(date);

        // If there are no orders for that date
        if (ordersOnDate == null) {
            return null;
        }

        // Remove object
        Order removedOrder = ordersOnDate.remove(orderNumber);

        if (removedOrder == null) {
            return null;
        }


        // If there are no more orders for this date,
        // remove the date from the map.
        if (ordersOnDate.isEmpty()) {
            orders.remove(date);
        }

        return removedOrder;
    }
}
