package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.model.Order;
import com.wiley_edge_corp.flooring_mastery.service.ServiceLayerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoFileImplTest {
    OrderDao orderDao;
    Order order;

    @BeforeEach
    void setUp() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        orderDao =
                ctx.getBean("orderDao", OrderDaoFileImpl.class);

        // Test Customer
        order = new Order();
        order.setOrderNumber(0);
        order.setCustomerName("Test Customer 1");
        order.setState("California");
        order.setOrderDate(LocalDate.now().plusDays(1));
        order.setTaxRate(new BigDecimal("25.00"));
        order.setProductType("Wood");
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLabourCostPerSquareFoot(new BigDecimal("4.75"));
        order.setArea(new BigDecimal("110"));
        order.setMaterialCost(new BigDecimal("566.50"));
        order.setLabourCost(new BigDecimal("522.50"));
        order.setTax(new BigDecimal("272.25"));
        order.setTotal(new BigDecimal("1361.25"));
    }

    @Test
    void getNextOrderNumber() {
        // Before any orders are added, next order number should be 0
        assertEquals(0, orderDao.getNextOrderNumber());

        // New customer 2
        Order order2 = new Order();
        order2.setOrderNumber(1);

        // Add customers to orders
        orderDao.addOrder(order);
        orderDao.addOrder(order2);

        // After two orders are added (0 and 1), the next order number should be 2
        assertEquals(2, orderDao.getNextOrderNumber());
    }

    @Test
    void addOrder() {
        orderDao.addOrder(order);
        assertEquals(order, orderDao.getOrder(order.getOrderDate(), order.getOrderNumber()));
    }

    @Test
    void getOrder() {
        orderDao.addOrder(order);
        assertEquals(order, orderDao.getOrder(order.getOrderDate(), order.getOrderNumber()));
        assertNull(orderDao.getOrder(order.getOrderDate().plusDays(1), 5));
    }

    @Test
    void editOrder() {
        // Save initial customer in orders
        orderDao.addOrder(order);

        // Ensure order is added
        Order result1 = orderDao.getOrder(order.getOrderDate(), order.getOrderNumber());
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
        orderDao.editOrder(newOrder);

        // Ensure order is updated
        Order result2 = orderDao.getOrder(newOrder.getOrderDate(), newOrder.getOrderNumber());
        assertNotNull(result2);
        assertEquals(newOrder, result2);
    }

    @Test
    void getOrdersForDate() {
        orderDao.addOrder(order);

        List<Order> result = orderDao.getOrdersForDate(order.getOrderDate());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(order, result.get(0));
    }

    @Test
    void removeOrder() {
        orderDao.addOrder(order);

        orderDao.removeOrder(order.getOrderDate(), order.getOrderNumber());
        Order result = orderDao.getOrder(order.getOrderDate(), order.getOrderNumber());
        assertNull(result);
    }
}