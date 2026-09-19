package com.wiley_edge_corp.flooring_mastery.view;

import com.wiley_edge_corp.flooring_mastery.model.Order;
import com.wiley_edge_corp.flooring_mastery.model.Product;
import com.wiley_edge_corp.flooring_mastery.model.Tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Handles displaying information to the user and getting user options back.
 * Sits between the UserIO and the Controller.
 */
public class View {
    UserIO io;

    public View (UserIO io) {
        this.io = io;

    }

    // ######### MAIN MENU #########

    public int displayMainMenuAndGetSelection() {

        io.print("<< Flooring Program >>");
        io.print("  1. Display Orders");
        io.print("  2. Add an Order");
        io.print("  3. Edit an Order");
        io.print("  4. Remove an Order");
        io.print("  5. Export all Data");
        io.print("  0. Exit");

        return io.readInt("Please enter your choice: ", 0, 6);
    }

    public LocalDate getDateInput() {
        String dateString = io.readString("Enter Date: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            io.print("Please enter a valid date.");
        }
        return null;
    }

    public void viewIncorrectDateInput() {
        io.print("Order date must be later than today.");

    }

    // ######### DISPLAY ORDERS  #########

    public void viewDisplayOrdersBanner() {
        io.print("Display Orders Menu: ");

    }

    public void displayOrders(List<Order> orders) {

    }

    public void displayOrderInfo(Order order) {
        io.print(order.toString());
    }

    // ######### ADD ORDER #########

    public void viewAddOrderBanner() {
        io.print("Add an Order: ");

    }

    /**
     *  query the user for each piece of order data necessary:
     *
     *     Order Date
     *     Customer Name – May not be blank and is limited to characters [a-z][0-9]
     *         as well as periods and comma characters. "Acme, Inc." is a valid name.
     *     State – Entered states must be checked against the tax file.
     *         If the state does not exist in the tax file, we cannot sell there.
     *     Product Type – Show a list of available products and pricing information to choose from.
     *     Area – The area must be a positive decimal. Minimum order size is 100 sq ft.
     *
     * @param taxes a list of all the available tax rates
     * @param products a list of all the available products
     * @return the Order object that was added
     */
    public Order getAddOrderInput(List<Tax> taxes, List<Product> products) {
        // Date
        LocalDate date;
        do {
            date = getDateInput();
        } while (date == null);

        // Name
        String name;
        do {
            name = io.readString("Enter Name: ");

        } while (validateNameInput(name) == null);

        // State
        String stateString;
        Tax state;
        do {
            stateString = io.readString("Enter State: ");
            state = validateStateInput(stateString, taxes);

        } while (state == null);

        // Product
        String productString;
        Product product;
        do {
            io.print("Products: ");
            for (Product p : products) {
                io.print("  " + p.toString());
            }

            productString = io.readString("Enter Product Name: ");
            product = validateProductInput(productString, products);

        } while (product == null);

        // Area
        String areaString;
        BigDecimal area;
        do {
            areaString = io.readString("Enter Area: ");
            area = validateAreaInput(areaString);

        } while (area == null);

        // Create Order Object

        Order order = new Order();
        order.setOrderNumber(-1);
        order.setCustomerName(name);
        order.setState(state.getStateName());
        order.setOrderDate(date);
        order.setTaxRate(state.getTaxRate());
        order.setProductType(product.getProductType());
        order.setArea(area);
        order.setCostPerSquareFoot(product.getCostPerSquareFoot());
        order.setLabourCostPerSquareFoot(product.getLabourCostPerSquareFoot());

        return order;
    }

    public void viewSuccessAddOrder() {
        io.print("Add Order Successful.");
        io.readString("Please hit enter to continue. ");
    }

    // ######### EDIT ORDER #########

    public void viewEditOrderBanner() {
        io.print("Edit an Order: ");

    }

    public int getOrderNumberInput() {
        return -1;

    }

    public Order getEditOrderInput(Order order, List<Tax> taxes,  List<Product> products) {
        return null;

    }

    public void viewSuccessEditOrder() {
        io.print("Edit Order Successful.");
        io.readString("Please hit enter to continue. ");
    }

    // ######### REMOVE ORDER #########

    public void viewRemoveOrderBanner() {
        io.print("Remove an Order: ");

    }

    public boolean getConfirmation() {
        if (io.readString("Do you want to continue? (y/n)").equals("y")) {
            return true;
        }
        return false;

    }

    public void viewSuccessRemoveOrder() {
        io.print("Remove Order Successful.");
        io.readString("Please hit enter to continue. ");
    }

    // ######### EXPORT ALL DATA #########

    public void viewExportAllDataBanner() {
        io.print("Export All Data: ");

    }

    public void viewSuccessExportAllData() {
        io.print("Export All Data Successful.");
        io.readString("Please hit enter to continue. ");
    }

    // ######### EXIT #########

    public void viewExitMessage() {
        io.print("Goodbye!");

    }

    public void displayErrorMessage(String message) {
        io.print(message);

    }

    public void viewUnknownCommand() {
        io.print("Unknown Command.");
    }

    // ######### VALIDATE #########

    /**
     * May not be blank and is limited to characters [a-z][0-9],
     * as well as periods and comma characters.
     * "Acme, Inc." is a valid name.
     *
     * @param name the name to validate
     * @return the name if it is valid, null otherwise
     */
    public String validateNameInput(String name) {
        String regex = "^(?=.*[a-zA-Z0-9])[a-zA-Z0-9., ]+$";

        if (name.matches(regex)) {
            return name;
        }

        io.print("Invalid Name.");
        io.print("Please enter a valid Name.");
        return null;
    }

    /**
     * Entered states must be checked against the tax file.
     * If the state does not exist in the tax file, we cannot sell there.
     *
     * @param stateString the state to validate
     * @param taxes the list of States to validate against
     * @return the state if it is valid, null otherwise
     */
    public Tax validateStateInput(String stateString, List<Tax> taxes) {
        for  (Tax tax : taxes) {
            if (tax.getStateName().equals(stateString)) {
                return tax;
            }
        }

        io.print("Invalid State.");
        io.print("We do not sell to that state.");
        return null;
    }

    /**
     * The product inputted must be one we sell.
     *
     * @param productString the product the user inputted
     * @param products the list of all products we offer to validate from
     * @return product object if exists, null else
     */
    public Product validateProductInput(String productString, List<Product> products) {
        for (Product product : products) {
            if (product.getProductType().equals(productString)) {
                return product;
            }
        }

        io.print("Invalid product.");
        io.print("We do not sell to that product.");
        return null;
    }

    /**
     * The area must be a positive decimal.
     * Minimum order size is 100 sq ft.
     *
     * @param areaString the area input from the user
     * @return BigDecimal return BigDecimal object if valid, null if not
     */
    public BigDecimal validateAreaInput(String areaString) {
        BigDecimal area = new BigDecimal(areaString);

        if (areaString.trim().equals("")) {
            io.print("Please enter a valid area");
            return null;
        } else if (area.intValue() >= 100) {
            return  area;
        }

        io.print("Invalid area.");
        io.print("Minimum order size is 100 sq ft.");
        return null;
    }
}
