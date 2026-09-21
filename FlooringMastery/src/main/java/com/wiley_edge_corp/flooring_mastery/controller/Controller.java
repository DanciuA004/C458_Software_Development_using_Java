package com.wiley_edge_corp.flooring_mastery.controller;

import com.wiley_edge_corp.flooring_mastery.model.Order;
import com.wiley_edge_corp.flooring_mastery.model.Product;
import com.wiley_edge_corp.flooring_mastery.model.Tax;
import com.wiley_edge_corp.flooring_mastery.service.ServiceLayer;
import com.wiley_edge_corp.flooring_mastery.view.View;

import java.time.LocalDate;
import java.util.List;

/**
 * The controller coordinates the application.
 * It takes in input from the View, decides what needs to happen,
 * and then calls business logic in the Service layer.
 */
public class Controller {
    private ServiceLayer service;
    private View view;

    public Controller(ServiceLayer service, View view){
        this.service = service;
        this.view = view;
    }

    /**
     * Main Menu
     */
    public void run () {
        int choice = -1;

        while (choice != 0) {
            choice = getMenuSelection();

            switch (choice) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    exportAllData();
                    break;
                case 0:
                    exitMessage();
                    break;
            }
        }
    }

    /**
     * Prints the menu and gets user's choice
     * @return user's choice
     */
    private int getMenuSelection() {
        return view.displayMainMenuAndGetSelection();

    }

    /**
     * Displays all orders for a date
     */
    private void displayOrders() {
        view.viewDisplayOrdersBanner();

        // Date
        LocalDate date = view.getDateInput();
        while (date == null) {
            date = view.getDateInput();
        }

        // Gets all of the orders for that date
        List<Order> orders = service.getOrdersForDate(date);

        if (orders == null) {
            // If there is no order for that date
            view.viewOrderDoesNotExist();
        } else {
            // Displays all of the orders
            view.displayOrders(orders);
        }
    }

    /**
     * Adds an order to orders
     */
    private void addOrder() {
        view.viewAddOrderBanner();

        // Initiates taxes and products
        List<Tax> taxes = service.getTaxes();
        List<Product> products = service.getProducts();

        // Get initial order information
        Order order = view.getAddOrderInput(taxes, products);

        // Validate Date
        // Write rest of calculation heavy order information
        while (service.calculateOrder(order) == null) {
            view.viewIncorrectDateInput();
            order = view.getAddOrderInput(taxes, products);
        }

        // Display order and get confirmation
        view.displayOrderInfo(order);

        if (view.getConfirmation()) {
            // Once confirmation add order
            service.addOrder(order);
            view.viewSuccessAddOrder();
        }
    }

    /**
     * Edit some values of an existing order
     */
    public void editOrder() {
        view.viewEditOrderBanner();

        // Initiates taxes and products
        List<Tax> taxes = service.getTaxes();
        List<Product> products = service.getProducts();

        // Date
        LocalDate date = view.getDateInput();
        while (date == null) {
            date = view.getDateInput();
        }

        // Order Number
        int orderNumber = view.getOrderNumberInput();
        while (orderNumber == -1) {
            view.viewIncorrectNumberInput();
            orderNumber = view.getOrderNumberInput();
        }

        // Get saved order
        Order orderSaved = service.getOrder(date, orderNumber);

        if  (orderSaved == null) {
            view.viewOrderDoesNotExist();

        } else {
            // Get new order details
            Order newOrder = view.getEditOrderInput(orderSaved, taxes, products);

            // Get confirmation
            if (view.getConfirmation()) {
                service.editOrder(newOrder);
                view.viewSuccessEditOrder();
            }
        }
    }

    /**
     * Remove a specific order using date and order number
     */
    public void removeOrder() {
        view.viewRemoveOrderBanner();

        // Date
        LocalDate date = view.getDateInput();
        while (date == null) {
            date = view.getDateInput();
        }

        // Order number
        int orderNumber =  view.getOrderNumberInput();
        while (orderNumber == -1) {
            view.viewIncorrectNumberInput();
            orderNumber = view.getOrderNumberInput();
        }

        // Remove order
        Order order = service.removeOrder(date, orderNumber);

        if (order == null) {
            // If order does not exist
            view.viewOrderDoesNotExist();
        } else {
            // If order does exist
            view.viewSuccessExportAllData();
        }
    }

    /**
     * Exports all orders to an external file
     */
    public void exportAllData() {
        view.viewExportAllDataBanner();
        service.exportAllData();
        view.viewSuccessExportAllData();
    }

    /**
     * Tell user goodbye when they exit the application
     */
    private void exitMessage() {
        view.viewExitMessage();

    }
}
