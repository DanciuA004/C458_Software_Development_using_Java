package com.wiley_edge_corp.flooring_mastery.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Used to create Product Objects
 */
public class Product {
    private String productType;
    private BigDecimal costPerSquareFoot;
    private BigDecimal labourCostPerSquareFoot;

    public Product(String productType, BigDecimal costPerSquareFoot, BigDecimal labourCostPerSquareFoot) {
        this.productType = productType;
        this.costPerSquareFoot = costPerSquareFoot;
        this.labourCostPerSquareFoot = labourCostPerSquareFoot;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public BigDecimal getLabourCostPerSquareFoot() {
        return labourCostPerSquareFoot;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getProductType(), product.getProductType()) && Objects.equals(getCostPerSquareFoot(), product.getCostPerSquareFoot()) && Objects.equals(getLabourCostPerSquareFoot(), product.getLabourCostPerSquareFoot());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductType(), getCostPerSquareFoot(), getLabourCostPerSquareFoot());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productType='" + productType + '\'' +
                ", costPerSquareFoot=" + costPerSquareFoot +
                ", labourCostPerSquareFoot=" + labourCostPerSquareFoot +
                '}';
    }
}
