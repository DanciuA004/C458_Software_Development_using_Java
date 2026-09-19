package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.model.Order;
import com.wiley_edge_corp.flooring_mastery.service.ServiceLayer;
import com.wiley_edge_corp.flooring_mastery.service.ServiceLayerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoFileImplTest {
    ApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    OrderDao orderDao =
            ctx.getBean("orderDao", OrderDaoFileImpl.class);

    @Test
    void testGetNextOrderNumber() {
        assertEquals(0, orderDao.getNextOrderNumber());
        Order order = new Order();
        orderDao.addOrder(order);
        assertEquals(1, orderDao.getNextOrderNumber());
    }

    @Test
    void testAddOrder() {
    }

    @Test
    void testGetOrder() {
    }

    @Test
    void testEditOrder() {
    }

    @Test
    void testGetOrdersForDate() {
    }

    @Test
    void testGetAllOrders() {
    }

    @Test
    void testRemoveOrder() {
    }
}