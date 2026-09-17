package com.wiley_edge_corp.flooring_mastery.service;

import com.wiley_edge_corp.flooring_mastery.dao.*;
import com.wiley_edge_corp.flooring_mastery.model.Order;
import com.wiley_edge_corp.flooring_mastery.model.Product;
import com.wiley_edge_corp.flooring_mastery.model.Tax;

import java.time.LocalDate;
import java.util.List;

/**
 * Handles business logic and validation, further delegates work to view and daos.
 * Sits in between the controller and the view and doas.
 */
public class ServiceLayerImpl implements ServiceLayer {
    AuditDao auditDao;
    ExportDao exportDao;
    OrderDao orderDao;
    ProductDao productDao;
    TaxDao taxDao;

    public ServiceLayerImpl(AuditDao auditDao, ExportDao exportDao, OrderDao orderDao, ProductDao productDao, TaxDao taxDao) {
        this.auditDao = auditDao;
        this.exportDao = exportDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    @Override
    public int getNextOrderNumber() {
        return -1;
    }

    @Override
    public Order addOrder(Order order) {
        return null;

    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber) {
        return null;

    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) {
        return null;

    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) {
        return null;

    }

    @Override
    public List<Order> getOrdersForDate(LocalDate date) {
        return null;

    }

    @Override
    public List<Tax> getTaxes() {
        return null;

    }

    @Override
    public List<Product> getProducts() {
        return null;

    }

    public void exportAllData() {

    }

    private void writeToAudit(String change) {

    }
}
