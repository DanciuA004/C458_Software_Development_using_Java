package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.model.Order;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * The order dao handles reading and writing to files of order objects
 */
public class OrderDaoFileImpl implements OrderDao{
    private String ORDER_FOLDER;
    private static final String DELIMITER = "::";
    String exportFile;
    private Map<LocalDate, Map<Integer, Order>> orders =  new HashMap<>();

    // For regular orders
    public OrderDaoFileImpl() {
        ORDER_FOLDER = "orders";
        exportFile = "export.txt";
        loadFromFile();
    }

    // For testing, Spring Di set up in test resources
    public OrderDaoFileImpl(String ORDER_FOLDER, String exportFile) {
        this.ORDER_FOLDER = ORDER_FOLDER;
        this.exportFile = exportFile;
    }

    /**
     * Re-write entire file if one order is changes or removed
     *
     * @param date date for file name to write to
     */
    private void writeToFile(LocalDate date) {
        // File Name
        String fileName = date.toString() + ".txt";
        String fullPath = ORDER_FOLDER + File.separator + fileName;

        // Get order objects for date
        Map<Integer, Order> ordersOnDate = orders.get(date);

        // If orders for that date do not exist, return null
        if (ordersOnDate == null) {
            return;
        }

        // Create file writer
        try (PrintWriter out = new PrintWriter(new FileWriter(fullPath))) {

            ordersOnDate = orders.get(date);

            for (Order order : ordersOnDate.values()) {
                out.println(
                        order.getOrderNumber() + DELIMITER +
                                order.getCustomerName() + DELIMITER +
                                order.getState() + DELIMITER +
                                order.getTaxRate() + DELIMITER +
                                order.getProductType() + DELIMITER +
                                order.getArea() + DELIMITER +
                                order.getCostPerSquareFoot() + DELIMITER +
                                order.getLabourCostPerSquareFoot() + DELIMITER +
                                order.getMaterialCost() + DELIMITER +
                                order.getLabourCost() + DELIMITER +
                                order.getTax() + DELIMITER +
                                order.getTotal()
                );
            }
        } catch (Exception e) {

        }
    }

    /**
     * Append new order to file
     *
     * @param order order object to add
     * @throws IOException
     */
    private void appendToFile(Order order) {
        // Marshalls the order object
        String orderAsString = order.getOrderNumber() + DELIMITER +
                order.getCustomerName() + DELIMITER +
                order.getState() + DELIMITER +
                order.getTaxRate() + DELIMITER +
                order.getProductType() + DELIMITER +
                order.getArea() +  DELIMITER +
                order.getCostPerSquareFoot() + DELIMITER +
                order.getLabourCostPerSquareFoot() + DELIMITER +
                order.getMaterialCost() + DELIMITER +
                order.getLabourCost() + DELIMITER +
                order.getTax() + DELIMITER +
                order.getTotal();

        // Get full path name
        String fileName =  order.getOrderDate().toString() + ".txt";
        String fullPath = ORDER_FOLDER + File.separator + fileName;

        // Create file writer
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(fullPath, true)); // append is true
            out.println(orderAsString);
        } catch (Exception e) {

        }

        out.flush();
        out.close();
    }

    /**
     * Populate orders with all orders from Orders folder
     */
    private void loadFromFile() {
        // Get the list of files in the folder
        File folder = new File(ORDER_FOLDER);
        File[] files = folder.listFiles();

        // If there are no files, do not continue
        if (files == null) {
            return;
        }

        // For each file in the folder
        for (File file : files) {
            LocalDate date = null;

            Scanner scanner = null;
            try {
                // Create Scanner for reading the file
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(file)));

                date = LocalDate.parse(file.getName().replace(".txt", ""));

            } catch (FileNotFoundException e) {

            }

            // For each line in the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Create a list of all the fields
                String[] fields = line.split(DELIMITER);

                Order order = new Order();

                // Create an order object will all fields
                order.setOrderNumber(Integer.parseInt(fields[0]));
                order.setCustomerName(fields[1]);
                order.setState(fields[2]);
                order.setTaxRate(new BigDecimal(fields[3]));
                order.setProductType(fields[4]);
                order.setArea(new BigDecimal(fields[5]));
                order.setCostPerSquareFoot(new BigDecimal(fields[6]));
                order.setLabourCostPerSquareFoot(new BigDecimal(fields[7]));
                order.setMaterialCost(new BigDecimal(fields[8]));
                order.setLabourCost(new BigDecimal(fields[9]));
                order.setTax(new BigDecimal(fields[10]));
                order.setTotal(new BigDecimal(fields[11]));
                order.setOrderDate(date);

                // Get the relevant mapping for the order's date
                Map<Integer, Order> ordersOnDate =
                        orders.get(date);

                // If there are no other orders with this date create a new map
                if (ordersOnDate == null) {
                    ordersOnDate = new HashMap<>();
                    orders.put(date, ordersOnDate);
                }

                // Put the order into the map
                ordersOnDate.put(order.getOrderNumber(), order);
            }
        }
    }

    /**
     * Next order number
     *
     * @return next order number
     */
    @Override
    public int getNextOrderNumber() {
        return orders.values().stream()
                .flatMap(map -> map.keySet().stream())
                .max(Integer::compareTo)
                .orElse(-1) + 1;
    }

    /**
     * Adds an order object to orders map and to file
     *
     * @param order order object to add
     * @return order object
     */
    @Override
    public Order addOrder(Order order) {
        // Write to memory location
        Map<Integer, Order> ordersOnDate = orders.get(order.getOrderDate());

        // If map doesn't exist create a new one
        if (ordersOnDate == null) {
            ordersOnDate = new HashMap<>();
            orders.put(order.getOrderDate(), ordersOnDate);
        }

        // Put the order into the map
        ordersOnDate.put(order.getOrderNumber(), order);

        // Write to file
        try {
            appendToFile(order);
        } catch (Exception e) {
            // do nothing
        }

        return order;
    }

    /**
     * Retrieve an order from the map from the date and order number
     *
     * @param date order date
     * @param orderNumber order number
     * @return order object, or null if order does not exist
     */
    @Override
    public Order getOrder(LocalDate date, int orderNumber) {
        Map<Integer, Order> ordersOnDate;
        Order order;

        try {
            ordersOnDate = orders.get(date);
            order = ordersOnDate.get(orderNumber);
        } catch (Exception e) {
            return null;
        }

        return order;
    }

    /**
     * Adds an edited order to orders
     *
     * @param order edited order to add
     * @return returns order object
     */
    @Override
    public Order editOrder(Order order) {
        // Write to memory location
        Map<Integer, Order> ordersOnDate = orders.get(order.getOrderDate());

        // Put the order into the map
        ordersOnDate.put(order.getOrderNumber(), order);

        // Write to file
        try {
            writeToFile(order.getOrderDate());
        } catch (Exception e) {
            // do nothing
        }

        return order;
    }

    /**
     * Get list of orders for a specific date
     *
     * @param date date for search
     * @return list of orders
     */
    @Override
    public List<Order> getOrdersForDate(LocalDate date) {
        Map<Integer, Order> ordersOnDate = orders.get(date);

        // If no orders for that date exists
        if (ordersOnDate == null) {
            return null;
        }

        return new ArrayList<>(ordersOnDate.values());
    }

    /**
     * Exports all orders to external file
     *
     * @return orders map
     */
    @Override
    public Map<LocalDate, Map<Integer, Order>> exportAllOrders() {
        try {
            // Create file writer
            PrintWriter out = new PrintWriter(new FileWriter(exportFile));

            // for each date
            for (Map.Entry<LocalDate, Map<Integer, Order>> dateEntry : orders.entrySet()) {

                // Get LocalDate date
                LocalDate date = dateEntry.getKey();

                // Get orders for date
                Map<Integer, Order> ordersOnDate = dateEntry.getValue();

                // For each order
                for (Order order : ordersOnDate.values()) {

                    // Marshal order
                    String orderAsString = order.getOrderNumber() + DELIMITER +
                            order.getCustomerName() + DELIMITER +
                            order.getState() + DELIMITER +
                            order.getTaxRate() + DELIMITER +
                            order.getProductType() + DELIMITER +
                            order.getArea() + DELIMITER +
                            order.getCostPerSquareFoot() + DELIMITER +
                            order.getLabourCostPerSquareFoot() + DELIMITER +
                            order.getMaterialCost() + DELIMITER +
                            order.getLabourCost() + DELIMITER +
                            order.getTax() + DELIMITER +
                            order.getTotal() + DELIMITER +
                            date;

                    // Write to file
                    out.println(orderAsString);
                }
            }

            out.flush();
            out.close();

        } catch (Exception e) {
            // do nothing
        }

        return orders;
    }

    /**
     * Remove order from orders and rewrite file
     *
     * @param date date for order
     * @param orderNumber order number
     * @return order object
     */
    @Override
    public Order removeOrder(LocalDate date, int orderNumber) {
        // Map of orders for date
        Map<Integer, Order> ordersOnDate = orders.get(date);

        if (ordersOnDate == null) {
            return null;
        }

        // Remove object
        Order removedOrder = ordersOnDate.remove(orderNumber);

        if  (removedOrder == null) {
            return null;
        }

        // Rewrite file
        writeToFile(date);

        // If there are no more orders for this date,
        // remove the date from the map.
        if (ordersOnDate.isEmpty()) {
            orders.remove(date);

            // Delete the now-empty order file
            File file = new File(ORDER_FOLDER + File.separator + date + ".txt");
            file.delete();
        }

        return removedOrder;
    }
}