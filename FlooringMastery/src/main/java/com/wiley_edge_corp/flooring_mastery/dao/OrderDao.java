package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderDao {
    public int getNextOrderNumber();

    public Order addOrder(Order order);

    public Order getOrder(LocalDate date, int orderNumber);

    public Order editOrder(LocalDate date, int orderNumber);

    public List<Order> getOrdersForDate(LocalDate date);

    public Map<LocalDate, Map<Integer, Order>> exportAllOrders();

    public Order removeOrder(LocalDate date, int orderNumber);
}
