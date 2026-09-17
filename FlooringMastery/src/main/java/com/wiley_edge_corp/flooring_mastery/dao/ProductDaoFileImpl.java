package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoFileImpl implements ProductDao {
    private static final String PRODUCT_FILE = "";
    private static final String DELIMITER = "";
    Map<String, Product> products;

    private void loadFile() {


    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
