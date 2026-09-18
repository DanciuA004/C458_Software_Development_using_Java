package com.wiley_edge_corp.flooring_mastery.dao;

import com.wiley_edge_corp.flooring_mastery.model.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoFileImpl implements OrderDao{
    private static final String ORDER_FOLDER = "orders";
    private static final String DELIMITER = ",";
    Map<LocalDate, Map<Integer, Order>> orders =  new HashMap<>();
    int largestOrderNumber = -1;

    private void writeToFile() {

    }

    private void appendToFile(Order order) throws IOException {
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

        String fileName =  order.getOrderDate().toString() + ".txt";
        String fullPath = ORDER_FOLDER + File.separator + fileName;

        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(fullPath, true));
            out.println(orderAsString);
        } catch (IOException e) {

        }
        
        out.flush();
        out.close();
    }



    private void loadFromFile() {


    }

    @Override
    public int getNextOrderNumber() {
        return ++largestOrderNumber;
    }

    @Override
    public Order addOrder(Order order) {
        // Write to memory location
        Map<Integer, Order> ordersOnDate = orders.get(order.getOrderDate());

        if (ordersOnDate == null) {
            ordersOnDate = new HashMap<>();
            orders.put(order.getOrderDate(), ordersOnDate);
        }

        ordersOnDate.put(order.getOrderNumber(), order);

        // Write to file
        try {
            appendToFile(order);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return order;
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) {
        return null;

    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber) {
        return null;

    }

    @Override
    public List<Order> getOrdersForDate(LocalDate date) {
        return null;

    }

    @Override
    public Map<LocalDate, Map<Integer, Order>> getAllOrders() {
        return null;

    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) {
        return null;

    }
}
