package com.wiley_edge_corp.flooring_mastery.view;

import com.wiley_edge_corp.flooring_mastery.model.Product;
import com.wiley_edge_corp.flooring_mastery.model.Tax;
import com.wiley_edge_corp.flooring_mastery.service.ServiceLayer;
import com.wiley_edge_corp.flooring_mastery.service.ServiceLayerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {
    ApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    View view =
            ctx.getBean("view", View.class);
    ServiceLayer service =
            ctx.getBean("service", ServiceLayerImpl.class);

    @Test
    void validateNameInput() {
        assertEquals("Valid Name", view.validateNameInput("Valid Name"));
        assertNull(view.validateNameInput("  "), "Invalid name should return null");
    }

    @Test
    void validateStateInput() {
        BigDecimal taxRate = new BigDecimal("4.45");
        Tax tax = new Tax("TX", "Texas", taxRate);
        assertEquals(tax, view.validateStateInput("Texas", service.getTaxes()));
        assertNull(view.validateStateInput("Arkansas", service.getTaxes()), "Invalid tax should return null");
    }

    @Test
    void validateProductInput() {
        BigDecimal costPerSquareFoot = new BigDecimal("5.15");
        BigDecimal labourCostPerSquareFoot = new BigDecimal("4.75");
        Product product = new Product("Wood",costPerSquareFoot,labourCostPerSquareFoot);
        assertEquals(product, view.validateProductInput("Wood", service.getProducts()));
        assertNull(view.validateProductInput("Cloth", service.getProducts()), "Invalid product should return null");
    }

    @Test
    void validateAreaInput() {
        assertEquals(new BigDecimal(150), view.validateAreaInput("150"));
        assertEquals(new BigDecimal(100), view.validateAreaInput("100"));
        assertNull(view.validateAreaInput("-2"), "Invalid area should return null");
        assertNull(view.validateAreaInput("99"), "Area should be minimum 100");
    }
}