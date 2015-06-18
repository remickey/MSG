/**
 * The NewMortgageGUI class constructs a GUI that allows a user to add
 * a new mortgage into the MSG system.
 *
 * Note: This class was created in NetBeans IDE.
 * @author Ryan Mickey
 * @version 2
 **/

package MSG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class NewMortgageGUI extends MSG_JFrame{

    // Initialize a mortgage object
    private Mortgage newMortgage = new Mortgage();

    // Store the reference of the current MSG_JFrame in the frame variable.
    // This is used when switching between frames to terminate the previous frame
    private MSG_JFrame frame = this;
    
    // Declare two JPanels that are used by the instantiateInnerColumns
    // method and the instantiateInnerRows method when constructing the
    // main JPanel
    private JPanel inner_rows, inner_columns;

    // Create and initialize two Strings to hold the mortgage_ID and the
    // current_date
    private String mortgage_ID = newMortgage.getAssetNumber(),
            current_date = getCurrentDate();

    // Initialize the JTextFields that will be used for user input
    private JTextField mortgagee_name = new JTextField(),
            weekly_income = new JTextField(),
            price_of_home = new JTextField(),
            mortgage_balance = new JTextField(),
            annual_property_tax = new JTextField();

    // Initialize the JButtons that are used for navigation
    private JButton return_no_save = new JButton("Return without Saving"),
            save_and_return = new JButton("Save and Return");
    
    /**
     * Class constructor constructs a NewMortgageGUI object by building the panel,
     * adding all the components to the MSG_JPanel, and making it visible
     * to the user.
     **/
    public NewMortgageGUI(){
        this.buildPanel();
        this.add(main_Panel);
        setVisible(true);
    }
    
    /**
     * The buildPanel method constructs the GUI using a combination of
     * additional JPanels with specified layouts, JButtons, and JLabels.
     *
     **/
    protected void buildPanel(){
        // Initialize the main_Panel object
        main_Panel = new MSG_JPanel();

        // Create an inside grid for the "CENTER" of the main_Panel
        // Initialize the necessary GridLayouts that will be needed for the
        // JPanels that construct the GUI.
        GridLayout inside_Grid = new GridLayout(6,0);

        // Initialize the necessary JPanels that will be used to create the GUI.
        JPanel grid = new JPanel(inside_Grid), // Used as the "main" grid
               left_spacer = new JPanel(), // Used as a spacer for the left
               right_spacer = new JPanel(); // Used as a spacer for the right

        // Use setOpaque on each JPanel so that they are not opaque, allowing
        // the background image to show through
        grid.setOpaque(false);
        left_spacer.setOpaque(false);
        right_spacer.setOpaque(false);

        // Initialize the JLabels that show which JTextField is used for what
        JLabel new_Mortgage_text = new JLabel("New Mortgage"),
                mortgage_number_label = new JLabel("Mortgage Number:"),
                mortgage_number = new JLabel("       " + mortgage_ID),
                date_added_label = new JLabel("Date Added:"),
                date_added = new JLabel("       " + current_date),
                name_label = new JLabel("Mortgagee Name (Last, First):"),
                income_label = new JLabel("Current Weekly Income:"),
                price_label = new JLabel("Price of Home:"),
                balance_label = new JLabel("Mortgage Balance:"),
                tax_label = new JLabel("Annual Property Tax:");

        // Initialize a new Font object named resize that is used to resize the
        // JLabels previously initialized
        Font resize = new Font("Dialog", Font.PLAIN, 48);
        new_Mortgage_text.setFont(resize);
        // Reinitialize the object to create a smaller font
        resize = new Font("Dialog", Font.PLAIN, 20);
        mortgage_number_label.setFont(resize);
        date_added_label.setFont(resize);
        name_label.setFont(resize);
        income_label.setFont(resize);
        price_label.setFont(resize);
        balance_label.setFont(resize);
        tax_label.setFont(resize);
        // Reinitialize the object to create a smaller font
        resize = new Font("Dialog", Font.PLAIN, 16);
        mortgage_number.setFont(resize);
        date_added.setFont(resize);
        
        // Add the new_Mortgage_text JLabel that acts as a title to the
        // entire NewMortgageGUI in the first row of the main JPanel (grid)
        grid.add(new_Mortgage_text);
        // Horizonally align the new_Mortgage_text
        new_Mortgage_text.setHorizontalAlignment(WIDTH / 2);
        
        // Construct the second row of the main JPanel (grid).
        // This is done by dividing the row into 2 columns with a spacing
        // of 30 pixels between horizontal elements of the grid using
        // the instantiateInnerColumns method.
        instantiateInnerColumns(2, 30);

        // Divide the second row's left column into a JPanel with 4 rows using the
        // instantiateInneRows method
        instantiateInnerRows(4);

        // Construct the left column
        // Add an empty JLabel to occupy cell 1
        inner_rows.add(new JLabel());
        // Add the mortgage_number_label
        inner_rows.add(mortgage_number_label);
        // Add the mortgage ID number
        inner_rows.add(mortgage_number);
        // Add the first set of rows into the left column
        inner_columns.add(inner_rows);

        // Construct the right column
        // Reinstantiate the inner_rows grid for the right column
        instantiateInnerRows(4);
        inner_rows.setOpaque(false);
        // Add an empty JLabel to occupy cell 1 (of column 2)
        inner_rows.add(new JLabel());
        // Add the date_added_label
        inner_rows.add(date_added_label);
        // Add the date the mortgage is added
        inner_rows.add(date_added);
        // Add the second set of rows into the right column
        inner_columns.add(inner_rows);
        // Add the set of columns into the second row of the main grid
        grid.add(inner_columns);

        // Construct the third row of the main JPanel (grid).
        // This is done the same way as the second row.
        // Reinstantiate both inner_columns and inner_rows JPanels
        instantiateInnerColumns(2, 30);
        instantiateInnerRows(4);
        // Add an empty JLabel to occupy cell 1 (of column 1)
        inner_rows.add(new JLabel());
        // Add the name label
        inner_rows.add(name_label);
        // Add the mortgagee name JTextField
        inner_rows.add(mortgagee_name);
        // Add the first set of rows into the left column
        inner_columns.add(inner_rows);
        // Reinstantiate the inner_rows JPanel
        instantiateInnerRows(4);
        // Add an empty JLabel to occupy cell 1 (of column 2)
        inner_rows.add(new JLabel());
        // Add the income label
        inner_rows.add(income_label);
        // Add the Weekly Income JTextField
        inner_rows.add(weekly_income);
        // Add the second set of rows into the right column
        inner_columns.add(inner_rows);
        // Add the set of columns into the third row of the main grid
        grid.add(inner_columns);

        // Construct the fourth row of the main JPanel (grid).
        // This is done the same way as the previous row.
        // Reinstantiate both inner_columns and inner_rows JPanels
        instantiateInnerColumns(2, 30);
        instantiateInnerRows(4);
        //Add an empty JLabel to occupy cell 1 (of column 1)
        inner_rows.add(new JLabel());
        // Add the name label
        inner_rows.add(price_label);
        // Add the Price of Home JTextField
        inner_rows.add(price_of_home);
        // Add the first set of rows into the left column
        inner_columns.add(inner_rows);
        // Reinstantiate the inner_rows JPanel
        instantiateInnerRows(4);
        // Add an empty JLabel to occupy cell 1 (of column 2)
        inner_rows.add(new JLabel());
        // Add the mortgage balance label
        inner_rows.add(balance_label);
        // Add the Mortgage Balance JTextField
        inner_rows.add(mortgage_balance);
        // Add the second set of rows into the right column
        inner_columns.add(inner_rows);
        // Add the set of columns into the fourth row of the main grid
        grid.add(inner_columns);

        // Construct the fifth row of the main JPanel (grid).
        // This is done the same way as the previous row.
        // Reinstantiate both inner_columns and inner_rows JPanels
        instantiateInnerColumns(2, 30);
        instantiateInnerRows(4);
        // Add an empty JLabel to occupy cell 1 (of column 1)
        inner_rows.add(new JLabel());
        // Add the tax label
        inner_rows.add(tax_label);
        // Add the Annual Property Tax JTextField
        inner_rows.add(annual_property_tax);
        // Add the first set of rows into the left column
        inner_columns.add(inner_rows);
        // Reinstantiate the inner_rows JPanel
        instantiateInnerRows(1);
        // Add an empty JLabel to occupy all of the cells of column 2
        inner_rows.add(new JLabel());
        // Add an empty column into the right column
        inner_columns.add(inner_rows);
        // Add the set of columns into the fifth row of the main grid
        grid.add(inner_columns);

        // Construct the fifth row of the main JPanel (grid).
        // This is done the same way as the other rows.
        // Reinstantiate both inner_columns and inner_rows JPanels
        instantiateInnerColumns(4,0);
        instantiateInnerRows(2);
        // Add an empty JLabel to occupy cell 1 (of row 1)
        inner_rows.add(new JLabel());
        // Add the Return Without Saving button into cell 1 (of row 2)
        inner_rows.add(return_no_save);
        // Add the first set of rows into the 1st column
        inner_columns.add(inner_rows);
        // Add an empty JLabel into the 2nd column
        inner_columns.add(new JLabel());
        // Add an empty JLabel into the 3rd column
        inner_columns.add(new JLabel());
        // Reinstantiate the inner_rows JPanel
        instantiateInnerRows(2);
        // Add an empty JLabel to occupy cell 1 (of row 1)
        inner_rows.add(new JLabel());
        // Add the Save and Return button into cell 1 (of row 2)
        inner_rows.add(save_and_return);
        // Add the second set of rows into the 4th column
        inner_columns.add(inner_rows);
        // Add the set of columns into the sixth andfinal row of the
        // main grid
        grid.add(inner_columns);

        // Add the main JPanel into the center of the main_Panel
        main_Panel.add(grid, BorderLayout.CENTER);
        // Add a single JLabel with a space to act as a spacer
        // for the bottom of the GUI
        main_Panel.add(new JLabel(" "), BorderLayout.SOUTH);

        // Create a left and right spacer that is exactly 1/6th of the
        // horizontal width of the MSG_JFrame
        left_spacer.setPreferredSize(new Dimension(this.getWidth() / 6, 0));
        right_spacer.setPreferredSize(new Dimension(this.getWidth() / 6, 0));
        // Add the spacers on the WEST and EAST of the main_Panel
        main_Panel.add(left_spacer, BorderLayout.WEST);
        main_Panel.add(right_spacer, BorderLayout.EAST);
        
        // Register an event listener with all JButtons
        return_no_save.addActionListener(new ButtonListener());
        save_and_return.addActionListener(new ButtonListener());
    }

    /**
     * getCurrentDate method is used to use classes built into Java to
     * generate the current date and store it as a String.
     * @return a String containing the current date formated like: MM/dd/yy
     **/
    private String getCurrentDate(){
        // Initialize a DateFormat object that holds a String representing
        // the desired format of the date
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        // Initialize a new Date object
        Date date = new Date();
        // Return the String of the formated date containing the current date
        return dateFormat.format(date);
    }

    /**
     * instantiateInnerColumns method is used to divide the individual rows
     * of the main JPanel (grid) into a specified number of columns,
     * with a specified horizontal spacing
     * @param cell_columns is an integer that creates a grid with a specific
     *                     number of columns
     * @param spacing is an integer that is used to create horizontal spacing
     *                between cells of a grid
     **/
    private void instantiateInnerColumns(int cell_columns, int spacing){
        GridLayout cell_column_grid = new GridLayout(0,cell_columns,spacing,0);
        inner_columns = new JPanel(cell_column_grid);
        inner_columns.setOpaque(false);
    }

    /**
     * instantiateInnerRows method is used to divide the individual columns
     * into a specified number of rows
     * @param cell_rows is an Integer that creates a grid with a specific
     *                  number of rows
     **/
    private void instantiateInnerRows(int cell_rows){
        GridLayout cell_row_grid = new GridLayout(cell_rows, 0);
        inner_rows = new JPanel(cell_row_grid);
        inner_rows.setOpaque(false);

    }
    
    /**
     * ButtonListener is a private inner class that handles the event
     * when a user clicks a button.
     *
     **/
    private class ButtonListener implements ActionListener{
        /**
         * The actionPerformed method determines which button is clicked by
         * a user and then performs a series of tasks based on this selection.
         * @param Action Event e
         **/
        public void actionPerformed(ActionEvent e) {

            // Store the input of each JTextField as a String
            String input_mortgagee_name = mortgagee_name.getText(),
                    input_weekly_income = weekly_income.getText(),
                    input_price_of_home = price_of_home.getText(),
                    input_mortgage_balance = mortgage_balance.getText(),
                    input_annual_property_tax = annual_property_tax.getText();

            // Test to determine which button is clicked
            if(e.getSource() == return_no_save){

                // Test to ensure the user wishes to return without saving
                // when at least some form of input has occured.
                if(!input_mortgagee_name.equals("") ||
                        !input_weekly_income.equals("") ||
                        !input_price_of_home.equals("") ||
                        !input_mortgage_balance.equals("") ||
                        !input_annual_property_tax.equals("")){

                    // Create an Array of Strings that is used for the OptionDialog
                    // used to confirm/deny the user's request
                    String[] options = {"Yes", "No"};
                    // Create an int that holds the return value of the
                    // showOptionDialog method that is used to confirm
                    // the user does in fact want to exit the program
                    int n = JOptionPane.showOptionDialog(null,
                            "Are you sure you want to return without saving?",
                            null,
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null, //do not use a custom Icon
                            options, //the titles of buttons
                            options[0]); //default button title
                    // An if-else statement that determines which button is
                    // selected (Yes or No) and what action to take
                    if(n == 0){
                        // If the user wishes to return and lose their data,
                        // create a new instance of the MSGMainMenu and dipose
                        // of the current MSG_JFrame that contains the instance
                        // of the NewMortgageGUI class
                        MSGMainMenu new_Main = new MSGMainMenu();
                        frame.setVisible(false);
                        frame.dispose();
                    } // otherwise, remain on the NewMortgageGUI JFrame
                    
                }else{
                    // If the user has not input any data, return to MSGMainMenu
                    // without a prompt.
                    MSGMainMenu new_Main = new MSGMainMenu();
                    frame.setVisible(false);
                    frame.dispose();
                }
                
            }else if(e.getSource() == save_and_return){
                
                // Test to ensure that the user has filled of the JTextFields
                // with some form of information when attempting to save.
                if(input_mortgagee_name.equals("") ||
                        input_weekly_income.equals("") ||
                        input_price_of_home.equals("") ||
                        input_mortgage_balance.equals("") ||
                        input_annual_property_tax.equals("")){

                    // Prompt the user with an error message if at least
                    // one JTextField is empty
                    JOptionPane.showMessageDialog(null, "You must fill in all "
                            + "of the text fields before you can save.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);

                }else{
                    boolean wrongInput = false;

                    // Test to see if the user has incompatable input by assinging
                    // each number to a float value
                    try{
                        float float_income = Float.parseFloat(input_weekly_income),
                              float_price = Float.parseFloat(input_price_of_home),
                              float_balance = Float.parseFloat(input_mortgage_balance),
                              float_tax = Float.parseFloat(input_annual_property_tax);
                    }catch(Exception ex){
                        wrongInput = true;
                    }
                    // If wrong input, display error, otherwise, save information
                    if (wrongInput) {
                        JOptionPane.showMessageDialog(null,
                                "Error :  At least one of the inputs is "
                                + "incorrectly formatted.\n"
                                + "Note :  All numerical values should only "
                                + "contain numbers.", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }else {
                        // Save the new mortgage to the MSG System
                        // and return to the MSGMainMenu.
                        newMortgage.setMortgageeName(input_mortgagee_name);

                        newMortgage.setCurrentWeeklyIncome(
                                            Float.parseFloat(input_weekly_income));

                        newMortgage.setPrice(Float.parseFloat(input_price_of_home));

                        newMortgage.setMortgageBalance(
                                        Float.parseFloat(input_mortgage_balance));

                        newMortgage.setAnnualPropertyTax(
                                    Float.parseFloat(input_annual_property_tax));

                        newMortgage.setDateMortgageIssued(current_date);
                        
                        newMortgage.save();
                        
                        MSGMainMenu new_Main = new MSGMainMenu();
                        
                        frame.setVisible(false);
                        frame.dispose();
                    }
                }
            } // end if-else for determining the button clicked
        } // actionPerformed method
    } // ButtonListener class
}