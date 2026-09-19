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

    /**
     * Gets the next order number from the orderDao
     * @return next order number
     */
    @Override
    public int getNextOrderNumber() {
        return orderDao.getNextOrderNumber();

    }

    /**
     * The first time an order is passed in it will not have all of its field set by the view,
     * the methods validates the date, if valid, writes the rest of the calculated fields and return object.
     *
     * If an order is passed in and the order number is not -1, it means this is a valid order that should have all of its fields,
     * it will pass it on to orderDao to save to memory.
     *
     * @param order order to be validated
     * @return order if is valid, null otherwise
     */
    @Override
    public Order addOrder(Order order) {
        if (order.getOrderNumber() == -1) {
            if (order.getOrderDate().isBefore(LocalDate.now()) || order.getOrderDate().equals(LocalDate.now())) {
                return null;
            }
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
