# ⭐ Mandatory Code Project: Flooring Mastery

## 1. Submitting Your Work

You will submit your work in two stages.

### 1.1. Step 1

Before starting to code, plan out the program using a flowchart and class diagrams.

- The flowchart should describe the individual steps the program must complete, including any loops and conditional outputs. 
- The class diagrams should describe the classes you intend to use in the program.

Once you have finished this documentation, submit your files to the Flowcharts and Class Diagrams assessment in Engage. An instructor will review your submission.

- If the plan is acceptable, you may start to code. 
- If there are problems, the instructor will work with you to improve the plan before you start to code.

### 1.2. Step 2

Write code that meets the requirements explained below. After completing the assessment and verifying that it meets the stated requirements, you must submit your work using both GitHub and Engage.

## 2. Architectural Guidance

1. Use N-tier development and MVC principles in structuring code to handle products, taxes, and orders appropriately. Your application should follow the MVC pattern presented in the course.
2. Use unit tests and integration tests to ensure that your application's data layers and business logic (service layer) code is covered.

## 3. Rules of Flooring Mastery

1. We are using an enterprise MVC architecture for this project, so your code must be organized into reasonable classes. You will draw a UML class diagram and a high-level flowchart before proceeding with writing code. 
2. For an enterprise architecture, the code must have layers:
   - The model package may only contain classes that have data members (properties). 
   - The dao package contains classes that are responsible for persisting data.
   - The controller package contains classes that orchestrate the program.
   - The view package contains classes that interact with the user.
   - The servicepackage contains the service layer components.
   - The UserIO class (along with the view component) will handle all console IO for the user.
3. Build this application following the process outlined in the Agile Approach Checklist for Console Applications

## 3. Requirements

Sample Data Files: `SampleFileData.zip`

The application will use three separate file formats for information.

### 3.1. Orders Files & Format

All orders are stored within an Orders folder. A new order file is created for each sales day. The file name is guaranteed to be unique for that day because the date is part of the file name. When creating new order files the file name should be in this format: `Orders_MMDDYYYY.txt.`

With this format, the order file for August 21st, 2025 would be named Orders_08212025.txt.

The order file should contain a header row. Data rows will contain the following fields and should be in this order:

1. OrderNumber – Integer 
2. CustomerName – String 
3. State – String 
4. TaxRate – BigDecimal 
5. ProductType – String 
6. Area – BigDecimal 
7. CostPerSquareFoot – BigDecimal 
8. LaborCostPerSquareFoot – BigDecimal 
9. MaterialCost – BigDecimal 
10. LaborCost – BigDecimal 
11. Tax – BigDecimal 
12. Total – BigDecimal

Here is a sample line from an orders file:

    1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06

Orders_06012013.txt is a sample file which is included in the sample download above.

Some of the order fields are calculations:

- MaterialCost = (Area * CostPerSquareFoot)
- LaborCost = (Area * LaborCostPerSquareFoot)
- Tax = (MaterialCost + LaborCost) * (TaxRate/100)  
  - Tax rates are stored as whole numbers
- Total = (MaterialCost + LaborCost + Tax)

### 3.2. Tax File & Format

The tax information can be found in Data/Taxes.txt. This file will contain the following fields:

1. StateAbbreviation – String 
2. StateName – String 
3. TaxRate – BigDecimal

Here is a sample line from the taxes file:

    TX,Texas,4.45

### 3.3. Products File & Format

The current product information can be found in Data/Products.txt. It contains the following fields:

1. ProductType – String 
2. CostPerSquareFoot – BigDecimal 
3. LaborCostPerSquareFoot – BigDecimal

Here is a sample line from the products file:

    Tile,3.50,4.15

### 3.4. User Stories

The UI should start with a menu to prompt the user for what they would like to do:

      * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * <<Flooring Program>>
    * 1. Display Orders
    * 2. Add an Order
    * 3. Edit an Order
    * 4. Remove an Order
    * 5. Export All Data
    * 6. Quit
    *
      * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *


### 3.5. Display Orders

Display orders will ask the user for a date and then display the orders for that date. If no orders exist for that date, it will display an error message and return the user to the main menu.


### 3.6. Add an Order

To add an order will query the user for each piece of order data necessary:

1. Order Date – Must be in the future 
2. Customer Name – May not be blank and is limited to characters [a-z][0-9] as well as periods and comma characters. "Acme, Inc." is a valid name. 
3. State – Entered states must be checked against the tax file. If the state does not exist in the tax file, we cannot sell there. If the tax file is modified to include the state, it should be allowed without changing the application code. 
4. Product Type – Show a list of available products and pricing information to choose from. Again, if a product is added to the file it should show up in the application without a code change. 
5. Area – The area must be a positive decimal. Minimum order size is 100 sq ft.

The remaining fields are calculated from the user entry and the tax/product information in the files. Show a summary of the order once the calculations are completed and prompt the user as to whether they want to place the order (Y/N). If yes, the data will be added to in-memory storage. If no, simply return to the main menu.

The system should generate an order number for the user based on the next available order # (so if there are two orders and the max order number is 4, the next order number should be 5).

### 3.7. Edit an Order

Edit will ask the user for a date and order number. If the order exists for that date, it will ask the user for each piece of order data but display the existing data. If the user enters something new, it will replace that data; if the user hits Enter without entering data, it will leave the existing data in place. For example:

    Enter customer name (Ada Lovelace):

If the user enters a new name, the name will replace Ada Lovelace. If the user hits enter without entering any data, it will leave the data as-is.

Only certain data is allowed to be changed:

- CustomerName 
- State 
- ProductType 
- Area

If the state, product type, or area are changed, the order will need to be recalculated. Order date may not be changed!

After querying for each editable field display a summary of the new order information and prompt for whether the edit should be saved. If yes, replace the data in the file then return to the main menu. If no, do not save and return to the main menu.

### 3.8. Remove an Order

For removing an order, the system should ask for the date and order number. If it exists, the system should display the order information and prompt the user if they are sure. If yes, it should be removed from the list.

### 3.9. Export All Data

When the user selects this option, the system should export all existing data to a file that exists independently of the application itself.

### 3.10. Quit

On quit, exit the application.

### 3.11. Stretch Goal: Export Data

> This step is optional. Complete this step only after all other steps work as required.

Selecting this option should save all active orders files to a file called DataExport.txt within a Backup folder. Exporting the order data multiple times should overwrite the data within the file with the latest active order information. However, unlike other order files, an order's line item in this DataExport file should also include the date in MM-DD-YYYY format, and the file's header should reflect this addition.

For example, here is a line of an example export, showcasing an order on June 12th, 2013:

    1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06,06-12-2013

## 4. Additional Guidance

- Any time a user enters invalid data, the system should ask them again until they enter valid data. A user should not be able to crash the application with invalid data. 
- We are using a layered architecture for this project. Thus your code must be organized into reasonable well-named classes and split into correct packages. 
- Please submit your project plan in addition to your code. This can be flowcharts, outlines, pseudocode, or any other documentation and planning technique. 
- You must unit test your DAO and ServiceLayers.

## 5. Some Tips

- Build this application following the process outlined in the Agile Approach Checklist for Console Applications (as specified in the Rules of Flooring Mastery section above). This process will help you approach the project in a logical and orderly manner. 
- If you get stuck, ask an instructor for general guidance on what you should be looking for. The instructor will not write the code for you, but advice is always free. 
- There are places where this specification has been left open to interpretation: this is intentional. When on the job you will frequently have to ask clarifying questions to business stakeholders. Be sure to ask an instructor for general guidance on any features you do not understand. 
- Try to work on pieces of functionality in isolation and test them before moving on. Working on too large of a chunk at once leads to difficulties in troubleshooting and poor separation of concerns. Think, then code. If you do not have a plan for each workflow, you will not be able to complete this project. 
- Use prior code samples and assessments as inspiration for this project. At this point in the course, you should be aware that there are patterns for creating a layered data-driven application.
