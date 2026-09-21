package com.wiley_edge_corp.flooring_mastery.dao;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class TaxDaoFileImplTest {
    ApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    TaxDao taxDao =
            ctx.getBean("taxDao", TaxDaoFileImpl .class);
    @Test
    void getAllTaxes() {
        assertNotNull(taxDao.getAllTaxes());
    }
}