package com.wiley_edge_corp.flooring_mastery.service;

import com.wiley_edge_corp.flooring_mastery.model.Order;
import com.wiley_edge_corp.flooring_mastery.model.Product;
import com.wiley_edge_corp.flooring_mastery.model.Tax;

import java.time.LocalDate;
import java.util.List;

public interface ServiceLayer {

    public int getNextOrderNumber();

    public Order calculateOrder(Order order);

    public Order addOrder(Order order);

    public Order getOrder(LocalDate date, int orderNumber);

    public Order editOrder(Order order);

    public List<Order> getOrdersForDate(LocalDate date);

    public Order removeOrder(LocalDate date, int orderNumber);

    public void exportAllData();

    public List<Tax> getTaxes();

    public List<Product> getProducts();

}
