package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.service.ServiceLayerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoFileImplTest {
    ApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    ProductDao productDao =
            ctx.getBean("productDao", ProductDaoFileImpl .class);

    @Test
    void getAllProducts() {
        assertNotNull(productDao.getAllProducts());
    }
}