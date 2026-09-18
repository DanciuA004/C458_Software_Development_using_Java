package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.model.Product;
import com.wiley_edge_corp.flooring_mastery.model.Tax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class ProductDaoFileImpl implements ProductDao {
    private static final String PRODUCT_FILE = "sample/data/products.txt";
    private static final String DELIMITER = ",";
    Map<String, Product> allProducts = new HashMap<>();

    private void loadFile() {
        // Create Scanner for reading the file
        Scanner scanner = null;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
            // Skip the header of the file
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {

        }

        // Unmarshalling and populating to allTaxes
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(DELIMITER);
            BigDecimal costPerSquareFoot = new BigDecimal(line[1]);
            BigDecimal laborCostPerSquareFoot = new BigDecimal(line[2]);
            Product product = new Product(line[0], costPerSquareFoot,  laborCostPerSquareFoot);
            allProducts.put(line[0], product);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        loadFile();
        return new ArrayList<>(allProducts.values());
    }
}
