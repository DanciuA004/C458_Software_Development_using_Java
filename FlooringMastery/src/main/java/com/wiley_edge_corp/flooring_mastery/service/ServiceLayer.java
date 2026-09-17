package com.wiley_edge_corp.flooring_mastery.service;

import com.wiley_edge_corp.flooring_mastery.model.Order;
import com.wiley_edge_corp.flooring_mastery.model.Product;
import com.wiley_edge_corp.flooring_mastery.model.Tax;

import java.time.LocalDate;
import java.util.List;

public interface ServiceLayer {

    public int getNextOrderNumber();

    public Order addOrder(Order order);

    public Order editOrder(LocalDate date, int orderNumber);

    public Order removeOrder(LocalDate date, int orderNumber);

    public Order getOrder(LocalDate date, int orderNumber);

    public List<Order> getOrdersForDate(LocalDate date);

    public List<Tax> getTaxes();

    public List<Product> getProducts();

    public void exportAllData();

}
