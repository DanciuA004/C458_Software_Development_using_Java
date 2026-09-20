package com.wiley_edge_corp.flooring_mastery.service;

import com.wiley_edge_corp.flooring_mastery.dao.*;
import com.wiley_edge_corp.flooring_mastery.model.Order;
import com.wiley_edge_corp.flooring_mastery.model.Product;
import com.wiley_edge_corp.flooring_mastery.model.Tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 * The service handles business logic and validation, further delegates work to dao.
 */
public class ServiceLayerImpl implements ServiceLayer {
    AuditDao auditDao;
    OrderDao orderDao;
    ProductDao productDao;
    TaxDao taxDao;

    public ServiceLayerImpl(AuditDao auditDao, OrderDao orderDao, ProductDao productDao, TaxDao taxDao) {
        this.auditDao = auditDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    /**
     * Get next order number
     * @return next order number
     */
    @Override
    public int getNextOrderNumber() {
        return orderDao.getNextOrderNumber();

    }

    /**
     * Completes order variables
     *
     * @param order incomplete order object
     * @return completed order object
     */
    @Override
    public Order calculateOrder(Order order) {
        // Date validation - must be a date after today
        if (order.getOrderDate().isBefore(LocalDate.now()) || order.getOrderDate().equals(LocalDate.now())) {
            return null;
        }

        // Order date
        order.setOrderNumber(getNextOrderNumber());

        // MaterialCost = (Area * CostPerSquareFoot)
        order.setMaterialCost(order.getArea().multiply(order.getCostPerSquareFoot())
                .setScale(2, RoundingMode.HALF_UP));

        // LaborCost = (Area * LaborCostPerSquareFoot)
        order.setLabourCost(order.getArea().multiply(order.getLabourCostPerSquareFoot())
                .setScale(2, RoundingMode.HALF_UP));

        // Tax = (MaterialCost + LaborCost) * (TaxRate/100)
        order.setTax(
                order.getMaterialCost()
                        .add(order.getLabourCost())
                        .multiply(order.getTaxRate().divide(BigDecimal.valueOf(100)))
                        .setScale(2, RoundingMode.HALF_UP)
        );

        // Total = (MaterialCost + LaborCost + Tax)
        order.setTotal(order.getMaterialCost().add(order.getLabourCost()).add(order.getTax()));

        return order;
    }

    /**
     * Add order to orders
     * Write to audit
     *
     * @param order order to add
     * @return order object
     */
    @Override
    public Order addOrder(Order order) {
        orderDao.addOrder(order);
        writeToAudit("Write order: " +  order.getOrderNumber());
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

    /**
     * Get all orders for a specific date
     *
     * @param date date for order retrieval
     * @return List of orders from date
     */
    @Override
    public List<Order> getOrdersForDate(LocalDate date) {
        return orderDao.getOrdersForDate(date);

    }

    /**
     * Remove order from orders
     *
     * @param date date for order
     * @param orderNumber order number for order
     * @return order object
     */
    @Override
    public Order removeOrder(LocalDate date, int orderNumber) {
        writeToAudit("Remove order: " +  orderNumber);
        return orderDao.removeOrder(date, orderNumber);

    }

    /**
     * Export all data to external file
     */
    @Override
    public void exportAllData() {
        orderDao.exportAllOrders();

    }

    /**
     * Get all tax objects
     *
     * @return list of tax objects
     */
    @Override
    public List<Tax> getTaxes() {
        return taxDao.getAllTaxes();

    }

    /**
     * Get all product objects
     *
     * @return list of product objects
     */
    @Override
    public List<Product> getProducts() {
        return productDao.getAllProducts();

    }

    /**
     * Write a message to audit
     *
     * @param message message to input
     */
    private void writeToAudit(String message) {
        auditDao.writeAuditEntry(message);

    }
}
