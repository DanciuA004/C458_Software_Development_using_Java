package com.wiley_edge_corp.flooring_mastery.service;

import com.wiley_edge_corp.flooring_mastery.controller.Controller;
import com.wiley_edge_corp.flooring_mastery.model.Order;
import com.wiley_edge_corp.flooring_mastery.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ServiceLayerImplTest {
    ApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    ServiceLayer service =
            ctx.getBean("service", ServiceLayerImpl.class);

    @Test
    void testAddValidOrderCalculatesOrder() {
        Order order = new Order();
        order.setOrderNumber(-1);
        order.setCustomerName("Test Customer");
        order.setState("California");
        order.setOrderDate(LocalDate.now().plusDays(1));
        order.setTaxRate(new BigDecimal("25.00"));
        order.setProductType("Wood");
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLabourCostPerSquareFoot(new BigDecimal("4.75"));
        order.setArea(new BigDecimal("110"));

        Order result = service.addOrder(order);

        assertEquals(0, result.getOrderNumber());
        assertEquals(new BigDecimal("566.50"), result.getMaterialCost());
        assertEquals(new BigDecimal("522.50"), result.getLabourCost());
        assertEquals(new BigDecimal("272.25"), result.getTax());
        assertEquals(new BigDecimal("1361.25"), result.getTotal());
    }

    @Test
    void testAddOrderInvalidDate() {
        Order order = new Order();
        order.setOrderNumber(-1);
        order.setCustomerName("Test Customer");
        order.setState("California");
        order.setOrderDate(LocalDate.now().minusDays(2));
        order.setTaxRate(new BigDecimal("25.00"));
        order.setProductType("Wood");
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLabourCostPerSquareFoot(new BigDecimal("4.75"));
        order.setArea(new BigDecimal("110"));

        assertNull(service.addOrder(order), "Invalid State should be rejected");
    }
}