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
 * The view handles displaying information to the user,
 * and receiving information back.
 */
public class View {
    UserIO io;

    public View (UserIO io) {
        this.io = io;

    }

    // ######### MAIN MENU #########

    /**
     * Main Menu
     * @return user's choice
     */
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

    // ######### HELPER FUNCTIONS #########

    /**
     * Displays a single order's information
     * @param order order to display
     */
    public void displayOrderInfo(Order order) {
        io.print(order.toString());

    }

    /**
     * Order existence error message
     */
    public void viewOrderDoesNotExist() {
        io.print("Order does not exist.");
        io.readString("Please hit enter to continue.");
    }

    /**
     * Integer input error message
     */
    public void viewIncorrectNumberInput() {
        io.print("Please enter a valid number.");

    }

    /**
     * Order Number input from user
     * @return order number
     */
    public int getOrderNumberInput() {
        return io.readInt("Enter Order Number: ");

    }

    /**
     * Date input from user
     * @return date
     */
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

    /**
     * Date error message
     */
    public void viewIncorrectDateInput() {
        io.print("Date must be after the current date.");

    }

    /**
     * Confirmation from user to continue
     * @return boolean
     */
    public boolean getConfirmation() {
        if (io.readString("Do you want to continue? (y/n)").equals("y")) {
            return true;
        }
        return false;
    }

    // ######### DISPLAY ORDERS  #########

    /**
     * Display Order banner
     */
    public void viewDisplayOrdersBanner() {
        io.print("  Display Orders Menu: ");

    }

    /**
     * Displayers each order in the list of orders
     * @param orders list of orders
     */
    public void displayOrders(List<Order> orders) {
        for (Order order : orders) {
            displayOrderInfo(order); // helper function
        }
        io.readString("Please hit enter to continue.");
    }

    // ######### ADD ORDER #########

    /**
     * Add Order banner
     */
    public void viewAddOrderBanner() {
        io.print("  Add an Order: ");

    }

    /**
     * Gets input for adding an order
     *
     * @param taxes list of states and their tax rates
     * @param products list of products and their prices
     * @return partial order to add
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

    /**
     * Successful Add Order banner
     */
    public void viewSuccessAddOrder() {
        io.print("Add Order Successful.");
        io.readString("Please hit enter to continue. ");
    }

    // ######### EDIT ORDER #########

    /**
     * Edit Order Banner
     */
    public void viewEditOrderBanner() {
        io.print("  Edit an Order: ");

    }

    /**
     * Get details to edit an order
     *
     * @param orderSaved the order they are editing, used for printing values and saving same values
     * @param taxes list of states and their tax rates
     * @param products list of all products and prices
     * @return the new edited order object
     */
    public Order getEditOrderInput(Order orderSaved, List<Tax> taxes,  List<Product> products) {
        Order order = new Order();
        order.setOrderNumber(orderSaved.getOrderNumber());
        order.setOrderDate(orderSaved.getOrderDate());

        // Name
        io.print("Current Name: " + orderSaved.getCustomerName());
        String name = io.readString("Enter New Name: ");

        // If user wants to keep current name
        if (name.equals("")) {
            // set name to old name
            order.setCustomerName(orderSaved.getCustomerName());

        } else {
            // else get new name
            while (validateNameInput(name) == null) {
                name = io.readString("Enter New Name: ");
            }
            // set new name
            order.setCustomerName(name);
        }

        // State
        io.print("Current State: " + orderSaved.getState());
        String stateString = io.readString("Enter New State: ");

        // If user wants to keep current state
        if (stateString.equals("")) {
            // set state to old state
            order.setState(orderSaved.getState());
            order.setTaxRate(orderSaved.getTaxRate());

        } else {

            Tax state = validateStateInput(stateString, taxes);
            while (state == null) {
                // else get new state
                stateString = io.readString("Enter State: ");
                state = validateStateInput(stateString, taxes);
            }
            // set new state
            order.setState(state.getStateName());
            order.setTaxRate(state.getTaxRate());
        }

        // Product
        io.print("Current Product Name: " + orderSaved.getProductType());

        // print all products
        io.print("Products: ");
        for (Product p : products) {
            io.print("  " + p.toString());
        }

        String productString = io.readString("Enter Product Name: ");

        // If user wants to keep current product
        if  (productString.equals("")) {
            // set product to old product
            order.setProductType(orderSaved.getProductType());
            order.setCostPerSquareFoot(orderSaved.getCostPerSquareFoot());
            order.setLabourCostPerSquareFoot(orderSaved.getLabourCostPerSquareFoot());

        } else {
            // else get new product
            Product product = validateProductInput(productString, products);
            while (product == null) {
                io.print("Products: ");
                for (Product p : products) {
                    io.print("  " + p.toString());
                }

                productString = io.readString("Enter Product Name: ");
                product = validateProductInput(productString, products);
            }
            // set new order product
            order.setProductType(product.getProductType());
            order.setCostPerSquareFoot(product.getCostPerSquareFoot());
            order.setLabourCostPerSquareFoot(product.getLabourCostPerSquareFoot());
        }

        // Area
        io.print("Current Area: " + orderSaved.getArea());
        String areaString = io.readString("Enter Area: ");

        // if user wants to keep current area
        if (areaString.equals("")) {
            // set area to old area
            order.setArea(orderSaved.getArea());

        } else {
            // else get new area
            BigDecimal area = validateAreaInput(areaString);

            while (area == null) {
                areaString = io.readString("Enter Area: ");
                area = validateAreaInput(areaString);
            }

            // set new order area
            order.setArea(area);
        }

        return order;
    }

    /**
     * Successful Edit Order banner
     */
    public void viewSuccessEditOrder() {
        io.print("Edit Order Successful.");
        io.readString("Please hit enter to continue. ");
    }

    // ######### REMOVE ORDER #########

    /**
     * Remove Order banner
     */
    public void viewRemoveOrderBanner() {
        io.print("  Remove an Order: ");

    }

    /**
     * Successful Remove Order banner
     */
    public void viewSuccessRemoveOrder() {
        io.print("Remove Order Successful.");
        io.readString("Please hit enter to continue. ");
    }

    // ######### EXPORT ALL DATA #########

    /**
     * Export All Data banner
     */
    public void viewExportAllDataBanner() {
        io.print("  Export All Data: ");

    }

    /**
     * Successful Export All Data banner
     */
    public void viewSuccessExportAllData() {
        io.print("Export All Data Successful.");
        io.readString("Please hit enter to continue. ");
    }

    // ######### EXIT #########

    /**
     * Exit Message
     */
    public void viewExitMessage() {
        io.print("  Goodbye!");

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
            // If valid
            return name;
        }

        // If invalid
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
                // If valid
                return tax;
            }
        }

        // If invalid
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
                // If valid
                return product;
            }
        }

        // If invalid
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
        BigDecimal area;
        try {
            area = new BigDecimal(areaString);
        }  catch (Exception e) {
            // If invalid
            return null;
        }

        if (area.intValue() >= 100) {
            // If valid
            return area;
        }

        // If invalid (valid BigDecimal but too small)
        io.print("Invalid area.");
        io.print("Minimum order size is 100 sq ft.");
        return null;
    }
}
