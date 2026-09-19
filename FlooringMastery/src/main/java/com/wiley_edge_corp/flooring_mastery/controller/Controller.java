package com.wiley_edge_corp.flooring_mastery.controller;

import com.wiley_edge_corp.flooring_mastery.model.Order;
import com.wiley_edge_corp.flooring_mastery.model.Product;
import com.wiley_edge_corp.flooring_mastery.model.Tax;
import com.wiley_edge_corp.flooring_mastery.service.ServiceLayer;
import com.wiley_edge_corp.flooring_mastery.view.View;

import java.util.List;

/**
 * Handles Main Menu choices and coordinates and delegates other work.
 * Sit at the top of the project and hands work down to the view and service.
 */
public class Controller {
    private ServiceLayer service;
    private View view;

    public Controller(ServiceLayer service, View view){
        this.service = service;
        this.view = view;
    }

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
                default:
                    unknownCommand();
            }
        }
    }

    private int getMenuSelection() {
        return view.displayMainMenuAndGetSelection();

    }

    private void displayOrders() {
        view.viewDisplayOrdersBanner();

    }

    private void addOrder() {
        view.viewAddOrderBanner();

        List<Tax> taxes = service.getTaxes();
        List<Product> products = service.getProducts();

        // Get initial order information
        Order order = view.getAddOrderInput(taxes, products);

        // Validate Date
        // Write rest of calculation heavy order information
        while (service.addOrder(order) == null) {
            view.viewIncorrectDateInput();
            order = view.getAddOrderInput(taxes, products);
        }

        // Display order and get confirmation
        view.displayOrderInfo(order);
        boolean confirm = view.getConfirmation();

        if (confirm) {
            // Send object order to orderDao, this time to be saved
            service.addOrder(order);
            view.viewSuccessAddOrder();
        }
    }

    public void editOrder() {
        view.viewEditOrderBanner();

    }

    public void removeOrder() {
        view.viewRemoveOrderBanner();

    }

    public void exportAllData() {
        view.viewExportAllDataBanner();

    }

    private void exitMessage() {
        view.viewExitMessage();

    }

    private void unknownCommand() {
        view.viewUnknownCommand();

    }
}
