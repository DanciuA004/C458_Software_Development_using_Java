package com.wiley_edge_corp.flooring_mastery.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Used to create Tax Objects
 */
public class Tax {
    private String stateAbbreviation;
    private String stateName;
    private BigDecimal taxRate;

    public Tax(String stateAbbreviation, String stateName, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return Objects.equals(getStateAbbreviation(), tax.getStateAbbreviation()) && Objects.equals(getStateName(), tax.getStateName()) && Objects.equals(getTaxRate(), tax.getTaxRate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStateAbbreviation(), getStateName(), getTaxRate());
    }

    @Override
    public String toString() {
        return "Tax{" +
                "stateAbbreviation='" + stateAbbreviation + '\'' +
                ", stateName='" + stateName + '\'' +
                ", taxRate=" + taxRate +
                '}';
    }
}
