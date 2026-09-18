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
        return orderDao.getNextOrderNumber();

    }

    @Override
    public Order addOrder(Order order) {
        if (order.getOrderNumber() == -1) {
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

        } else {
            orderDao.addOrder(order);
            auditDao.writeAuditEntry("Write order: " +  order);
        }

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
        return null;

    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) {
        return null;

    }

    @Override
    public void exportAllData() {


    }

    @Override
    public List<Tax> getTaxes() {
        return taxDao.getAllTaxes();

    }

    @Override
    public List<Product> getProducts() {
        return productDao.getAllProducts();

    }

    private void writeToAudit(String message) {


    }
}
