package com.wiley_edge_corp.flooring_mastery.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Used to create Order Objects
 */
public class Order {
    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal labourCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal labourCost;
    private BigDecimal tax;
    private BigDecimal total;

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLabourCostPerSquareFoot() {
        return labourCostPerSquareFoot;
    }

    public void setLabourCostPerSquareFoot(BigDecimal labourCostPerSquareFoot) {
        this.labourCostPerSquareFoot = labourCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLabourCost() {
        return labourCost;
    }

    public void setLabourCost(BigDecimal labourCost) {
        this.labourCost = labourCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getOrderNumber() == order.getOrderNumber() && Objects.equals(getCustomerName(), order.getCustomerName()) && Objects.equals(getState(), order.getState()) && Objects.equals(getTaxRate(), order.getTaxRate()) && Objects.equals(getProductType(), order.getProductType()) && Objects.equals(getArea(), order.getArea()) && Objects.equals(getCostPerSquareFoot(), order.getCostPerSquareFoot()) && Objects.equals(getLabourCostPerSquareFoot(), order.getLabourCostPerSquareFoot()) && Objects.equals(getMaterialCost(), order.getMaterialCost()) && Objects.equals(getLabourCost(), order.getLabourCost()) && Objects.equals(getTax(), order.getTax()) && Objects.equals(getTotal(), order.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderNumber(), getCustomerName(), getState(), getTaxRate(), getProductType(), getArea(), getCostPerSquareFoot(), getLabourCostPerSquareFoot(), getMaterialCost(), getLabourCost(), getTax(), getTotal());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", customerName='" + customerName + '\'' +
                ", state='" + state + '\'' +
                ", taxRate=" + taxRate +
                ", productType='" + productType + '\'' +
                ", area=" + area +
                ", costPerSquareFoot=" + costPerSquareFoot +
                ", labourCostPerSquareFoot=" + labourCostPerSquareFoot +
                ", materialCost=" + materialCost +
                ", labourCost=" + labourCost +
                ", tax=" + tax +
                ", total=" + total +
                '}';
    }
}
