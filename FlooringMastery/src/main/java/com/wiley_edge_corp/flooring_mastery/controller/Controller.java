package com.wiley_edge_corp.flooring_mastery.controller;

import com.wiley_edge_corp.flooring_mastery.service.ServiceLayer;
import com.wiley_edge_corp.flooring_mastery.view.View;

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
            choice = getChoice();

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

    private int getChoice() {
        return view.viewMenuAndGetChoice();

    }

    private void displayOrders() {
        view.viewDisplayOrdersBanner();

    }

    private void addOrder() {
        view.viewAddOrderBanner();

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
