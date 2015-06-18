/**
 * The MSGMainMenu class constructs a main menu GUI onto a MSG_JPanel and
 * adds the GUI on the MSG_JFrame.  This version includes functionality for
 * all of the Mortgage Buttons (meaning they transition to additional GUIs) and
 * prints all of the actions being performed into the command prompt/console.
 *
 * Note: This class was created in NetBeans IDE.
 * @author Ryan Mickey
 * @version 2
 **/

package MSG;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MSGMainMenu extends MSG_JFrame{
    // Store the reference of the current MSG_JFrame in the frame variable.
    // This is used when switching between frames to terminate the previous frame
    private MSG_JFrame frame = this;
    // Initialize the JButtons that are used for user selection
    JButton mortgage_add = new JButton("Add"),
            mortgage_select = new JButton("Select"),
            investment_add = new JButton("Add"),
            investment_select = new JButton("Select"),
            operations_fundsAvailable = new JButton("Funds Available"),
            operations_updateExpenses = new JButton("Update Expenses"),
            exit = new JButton("Exit to Desktop");
    /**
     * Class constructor constructs an MSGMainMenu object by building the panel,
     * adding all the components to the MSG_JFrame, and making it visible
     * to the user.
     **/
    public MSGMainMenu(){
        this.buildPanel();
        this.add(main_Panel);
        setVisible(true);
    }
    /**
     * The buildPanel method constructs the GUI using a combination of
     * additional JPanels with specified layouts, JButtons, and JLabels.
     *
     */
    private void buildPanel(){
        // Initialize the main_Panel object
        main_Panel = new MSG_JPanel();
        
        // Initialize GridLayouts that will be needed for the JPanels that
        // construct the GUI.
        GridLayout inside_Columns = new GridLayout(0,3), // Used on the whole screen
                inside_Rows = new GridLayout(8,0), // Used to divide up the columns
                buttons = new GridLayout(0,2); // Used to divide a row for buttons
        // Initialize the necessary JPanels that will be used to create the GUI.
        JPanel grid = new JPanel(inside_Columns), // Used as the "main" grid
                top_row = new JPanel(new BorderLayout()), // Used for the logo
                middle_column = new JPanel(inside_Rows), // Used for middle column
                right_column = new JPanel(inside_Rows), // Used for right column
                mortgage_buttons = new JPanel(buttons), // Used for mortgage buttons
                investment_buttons = new JPanel(buttons), // Used for investment buttons
                operations_buttons = new JPanel(buttons), // Used for operations buttons
                exit_button = new JPanel(buttons); // Used for the exit button
        // Use setOpaque on each JPanel so that they are not opaque, allowing
        // the background image to show through
        grid.setOpaque(false);
        top_row.setOpaque(false);
        middle_column.setOpaque(false);
        right_column.setOpaque(false);
        mortgage_buttons.setOpaque(false);
        investment_buttons.setOpaque(false);
        operations_buttons.setOpaque(false);
        exit_button.setOpaque(false);

        // Initialize JLabels that will act as titles for the buttons
        JLabel mortgage_title = new JLabel("Mortgage"),
                investment_title = new JLabel("Investment"),
                operations_title = new JLabel("Operations");
        // Initialize a new Font object named resize that is used to resize the
        // JLabels previously initialized
        Font resize = new Font("Dialog", Font.PLAIN, 24);
        mortgage_title.setFont(resize);
        investment_title.setFont(resize);
        operations_title.setFont(resize);

        // Create the top row of MSG_JPanel that will hold the MSG logo
        // Initialize a JLabel to hold the ImageIcon, then set it to be
        // horizonally aligned to the center of the screen
        JLabel MSG_logo = new JLabel(new ImageIcon("MSG-logo.png"));
        MSG_logo.setHorizontalAlignment(WIDTH / 2);
        // Set the top_row JPanel to be 1/3 of the screen
        top_row.setPreferredSize(new Dimension(this.getWidth(),
                (int)((double)this.getHeight() / 3)));
        // Add the MSG Logo to the top_row JPanel's Center
        top_row.add(MSG_logo, BorderLayout.CENTER);
        // Add the top_row to the NORTH portion of the main_Panel
        main_Panel.add(top_row, BorderLayout.NORTH);

        // Add an empty cell to occupy the left-most column/cell of the main grid
        grid.add(new JLabel());

        // Create the middle column of the main grid that will hold both
        // the titles and the buttons
        // Add the mortgage_title to the middle column
        middle_column.add(mortgage_title);
        // Horizonally align the mortgage_title
        mortgage_title.setHorizontalAlignment(WIDTH / 2);
        // Add the mortgage_add and mortgage_select JButtons to the
        // mortgage_buttons JPanel and add the JPanel to the next cell
        mortgage_buttons.add(mortgage_add);
        mortgage_buttons.add(mortgage_select);
        middle_column.add(mortgage_buttons);
        // Add the investment_title to the middle column
        middle_column.add(investment_title);
        // Horizonally align the investment_title
        investment_title.setHorizontalAlignment(WIDTH / 2);
        // Add the investment_add and investment_select JButtons to the
        // investment_buttons JPanel and add the JPanel to the next cell
        investment_buttons.add(investment_add);
        investment_buttons.add(investment_select);
        middle_column.add(investment_buttons);
        // Add the operations_title to the middle column
        middle_column.add(operations_title);
        // Horizonally align the operations_title
        operations_title.setHorizontalAlignment(WIDTH / 2);
        // Add the operations_fundsAvailable and operations_updateExpenses
        // JButtons to the operations_buttons JPanel and add the JPanel
        // to the next cell
        operations_buttons.add(operations_fundsAvailable);
        operations_buttons.add(operations_updateExpenses);
        middle_column.add(operations_buttons);
        // Add middle_column to the main grid
        grid.add(middle_column);

        // Create the right column of the main grid that will hold
        // only the "Exit to Desktop" button
        // Add 7 empty cells to occupy the first 7 cells of the right column
        right_column.add(new JLabel());
        right_column.add(new JLabel());
        right_column.add(new JLabel());
        right_column.add(new JLabel());
        right_column.add(new JLabel());
        right_column.add(new JLabel());
        right_column.add(new JLabel());
        // Add an empty cell to the exit_button JPanel to occupy the left column
        exit_button.add(new JLabel());
        // Add the exit button to the right column of the exit_button JPanel
        exit_button.add(exit);
        // Add the exit_button JPanel into the final cell of the right_column
        right_column.add(exit_button);
        // Add right_column to the main grid
        grid.add(right_column);

        // Add the entire grid into the main_Panel at the Center of the
        // BorderLayout
        main_Panel.add(grid, BorderLayout.CENTER);

        // Add three single JLabels with spaces to act as spacers
        // for the left, right, and bottom of the GUI
        main_Panel.add(new JLabel("      "), BorderLayout.WEST);
        main_Panel.add(new JLabel("      "), BorderLayout.EAST);
        main_Panel.add(new JLabel(" "), BorderLayout.SOUTH);

        // Register an event listener with all of the JButtons
        mortgage_add.addActionListener(new ButtonListener());
        mortgage_select.addActionListener(new ButtonListener());
        investment_add.addActionListener(new ButtonListener());
        investment_select.addActionListener(new ButtonListener());
        operations_fundsAvailable.addActionListener(new ButtonListener());
        operations_updateExpenses.addActionListener(new ButtonListener());
        exit.addActionListener(new ButtonListener());
    }
    /**
     * ButtonListener is a private inner class that handles the event
     * when a user clicks a button.
     *
     **/
    private class ButtonListener implements ActionListener{
        /**
         * The actionPerformed class determines which button is clicked by
         * a user and then performs a series of tasks based on this selection.
         * @param Action Event e
         **/
        public void actionPerformed(ActionEvent e) {
            // Test to determine which button is clicked
            if(e.getSource() == mortgage_add){
                // Create an instance of the NewMortgageGUI class
                // and dispose of the current MSG_JFrame that contains
                // the instance of the MSGMainMenu classf
                NewMortgageGUI new_mortgage = new NewMortgageGUI();
                frame.setVisible(false);
                frame.dispose();
            }else if(e.getSource() == mortgage_select){
            	// Display a JOptionPane stating the action being performed
                JOptionPane.showMessageDialog(null, "Switch to MortgageSelectGUI",
                        "",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if(e.getSource() == investment_add){
                // Display a JOptionPane stating the action being performed
                JOptionPane.showMessageDialog(null, "Switch to InvestmentAddGUI",
                        "",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if(e.getSource() == investment_select){
                // Display a JOptionPane stating the action being performed
                JOptionPane.showMessageDialog(null, "Switch to InvestmentSelectGUI",
                        "",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if(e.getSource() == operations_fundsAvailable){
                // Display a JOptionPane stating the action being performed
                JOptionPane.showMessageDialog(null, "Switch to FundsAvailableGUI",
                        "",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if(e.getSource() == operations_updateExpenses){
                // Display a JOptionPane stating the action being performed
                JOptionPane.showMessageDialog(null, "Switch to UpdateExpensesGUI",
                        "",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if(e.getSource() == exit){
                // Create an Array of Strings that is used for the OptionDialog
                // used to confirm/deny the user's request
                String[] options = {"Yes", "No"};
                // Create an int that holds the return value of the
                // showOptionDialog method that is used to confirm
                // the user does in fact want to exit the program
                int n = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to exit?",
                        null,
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null, //do not use a custom Icon
                        options, //the titles of buttons
                        options[0]); //default button title
                // An if-else statement that determines which button is
                // selected (Yes or No) and what action to take
                  if(n == 0){
                      // Terminate the program
                      System.exit(0);
                  } // else remain on the MSGMainMenu
            }
        }
    }
}