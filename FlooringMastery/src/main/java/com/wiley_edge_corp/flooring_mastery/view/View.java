package com.wiley_edge_corp.flooring_mastery.view;

/**
 * Handles displaying information to the user and getting user options back.
 * Sits between the UserIO and the Controller.
 */
public class View {
    UserIO io;

    public View (UserIO io) {
        this.io = io;
    }

    public int viewMenuAndGetChoice() {

        io.print("<< Flooring Program >>");
        io.print("  1. Display Orders");
        io.print("  2. Add an Order");
        io.print("  3. Edit an Order");
        io.print("  4. Remove an Order");
        io.print("  5. Export all Data");
        io.print("  0. Exit");

        return io.readInt("Please enter your choice: ", 0, 6);
    }

    public void viewDisplayOrdersBanner() {
        io.print("Display Orders Menu: ");
    }

    public void viewAddOrderBanner() {
        io.print("Add an Order: ");
    }

    public void viewEditOrderBanner() {
        io.print("Edit an Order: ");
    }

    public void viewRemoveOrderBanner() {
        io.print("Remove an Order: ");
    }

    public void viewExportAllDataBanner() {
        io.print("Export All Data: ");
    }

    public void viewExitMessage() {
        io.print("Goodbye!");
    }

    public void viewUnknownCommand() {
        io.print("Unknown Command.");
    }
}
