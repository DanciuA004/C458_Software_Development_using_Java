package com.wiley_edge_corp.flooring_mastery.service;

import com.wiley_edge_corp.flooring_mastery.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceLayerImplTest {
    ServiceLayerImpl service;
    Order order;

    @BeforeEach
    void setUp() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        service =
                ctx.getBean("service", ServiceLayerImpl.class);

        // Test Customer
        order = new Order();
        order.setOrderNumber(-1);
        order.setCustomerName("Test Customer 1");
        order.setState("California");
        order.setOrderDate(LocalDate.now().plusDays(1));
        order.setTaxRate(new BigDecimal("25.00"));
        order.setProductType("Wood");
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLabourCostPerSquareFoot(new BigDecimal("4.75"));
        order.setArea(new BigDecimal("110"));
    }

    @Test
    void getNextOrderNumber() {
        // Before any orders are added, next order number should be 0
        assertEquals(0, service.getNextOrderNumber());

        // New customer 2
        Order order2 = new Order();
        order2.setOrderNumber(-1);
        order2.setCustomerName("Test Customer 2");
        order2.setState("Texas");
        order2.setOrderDate(LocalDate.now().plusDays(1));
        order2.setTaxRate(new BigDecimal("25.00"));
        order2.setProductType("Wood");
        order2.setCostPerSquareFoot(new BigDecimal("5.15"));
        order2.setLabourCostPerSquareFoot(new BigDecimal("4.75"));
        order2.setArea(new BigDecimal("110"));

        // Add customers to orders
        service.calculateOrder(order);
        service.addOrder(order);

        service.calculateOrder(order2);
        service.addOrder(order2);

        // After two orders are added (0 and 1), the next order number should be 2
        assertEquals(2, service.getNextOrderNumber());
    }

    @Test
    void calculateOrderValid() {
        Order result = service.calculateOrder(order);

        assertEquals(new BigDecimal("566.50"), result.getMaterialCost());
        assertEquals(new BigDecimal("522.50"), result.getLabourCost());
        assertEquals(new BigDecimal("272.25"), result.getTax());
        assertEquals(new BigDecimal("1361.25"), result.getTotal());
    }

    @Test
    void calculateOrderInvalid() {
        order.setOrderDate(LocalDate.now().minusDays(2)); // Invalid date
        assertNull(service.calculateOrder(order), "Invalid date should be rejected");
    }

    @Test
    void getOrder() {
        // Add order to orders first
        service.calculateOrder(order);
        service.addOrder(order);

        Order result = service.getOrder(order.getOrderDate(), order.getOrderNumber());
        assertNotNull(result);
        assertEquals(order, result);
    }

    @Test
    void editOrder() {
        // Save initial customer in orders
        service.calculateOrder(order);
        service.addOrder(order);

        // Ensure order is added
        Order result1 = service.getOrder(order.getOrderDate(), order.getOrderNumber());
        assertNotNull(result1);
        assertEquals(order, result1);

        // Create new order to edit the existing order with
        Order newOrder = new Order();
        newOrder.setOrderNumber(order.getOrderNumber());
        newOrder.setCustomerName("Test Customer 2");
        newOrder.setState("Texas");
        newOrder.setOrderDate(LocalDate.now().plusDays(1));
        newOrder.setTaxRate(new BigDecimal("4.45"));
        newOrder.setProductType("Wood");
        newOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        newOrder.setLabourCostPerSquareFoot(new BigDecimal("4.75"));
        newOrder.setArea(new BigDecimal("110"));

        // Add edited order
        service.calculateOrder(newOrder);
        service.editOrder(newOrder);

        // Ensure order is updated
        Order result2 = service.getOrder(newOrder.getOrderDate(), newOrder.getOrderNumber());
        assertNotNull(result2);
        assertEquals(newOrder, result2);
    }

    @Test
    void getOrdersForDate() {
        service.calculateOrder(order);
        service.addOrder(order);

        List<Order> result = service.getOrdersForDate(order.getOrderDate());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(order, result.get(0));
    }

    @Test
    void removeOrder() {
        service.calculateOrder(order);
        service.addOrder(order);

        service.removeOrder(order.getOrderDate(), order.getOrderNumber());
        Order result = service.getOrder(order.getOrderDate(), order.getOrderNumber());
        assertNull(result);
    }

    @Test
    void getTaxes() {
       assertNotNull(service.getTaxes());
    }

    @Test
    void getProducts() {
        assertNotNull(service.getProducts());
    }
}