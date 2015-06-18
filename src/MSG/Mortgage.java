/**
 * The Mortgage class is a version of the "mortgage.java" class that was
 * provided with the original MSG.  The new class does not extend the Asset
 * class.  New methods have been created and some older methods have been removed.
 * 
 * Note: This class was created in NetBeans IDE.
 * @author Ryan Mickey, Original MSG Creator(s)
 * @version 2
 **/

package MSG;

import java.io.*;

public class Mortgage
{
    // Instance Variables
    private String assetNumber = createAssetID();// creates a unique 12-digit ID
    private String mortgageeName;	   // names of mortgagees
    private float price;		   // price paid for mortgaged property
    private String dateMortgageIssued;	   // date mortgage was issued
    private float currentWeeklyIncome;	   // weekly income of mortgagees
    private String weeklyIncomeUpdated;    // date weekly income updated
    private float annualPropertyTax;	   // annual property tax
    private float annualInsurancePremium;  // annual insurance premium
    private float mortgageBalance;	   // mortgage balance

    static final int NUMBER_OF_MORTGAGE_PAYMENTS = 1560;
    static final float MAXIMUM_PERC_OF_INCOME = (float) 0.28;
    static final float WEEKS_IN_YEAR = (float) 52.0;
    static final float INTEREST_RATE = (float) 0.04;

    // Getter-Setter Methods used to add/update the mortgage's information
    public String getAssetNumber () { return assetNumber; }
    public String getMortgageeName () { return mortgageeName; }
    public void  setMortgageeName (String n) { mortgageeName = n; }
    public float getPrice () { return price; }
    public void  setPrice (float p) { price = p; }
    public String  getDateMortgageIssued () { return dateMortgageIssued; }
    public void  setDateMortgageIssued (String d) { dateMortgageIssued = d; }
    public float getCurrentWeeklyIncome () { return currentWeeklyIncome; }
    public void  setCurrentWeeklyIncome (float c) { currentWeeklyIncome = c; }
    public String  getWeeklyIncomeUpdated () { return weeklyIncomeUpdated; }
    public void  setWeeklyIncomeUpdated (String w) { weeklyIncomeUpdated = w; }
    public float getAnnualPropertyTax () { return annualPropertyTax; }
    public void setAnnualPropertyTax (float p) { annualPropertyTax = p; }
    public float getAnnualInsurancePremium () { return annualInsurancePremium; }
    public void setAnnualInsurancePremium (float a) { annualInsurancePremium = a; }
    public float getMortgageBalance () { return mortgageBalance; }
    public void setMortgageBalance (float m) { mortgageBalance = m; }

    /**
     * The createAssetID method creates a unique-12 digit ID for each mortgage
     * being added to the MSG System.
     * 
     * @return a String with a unique 12-digit ID
     *
     **/
    private String createAssetID() {
        // Initialize a double that randomly generates a 6-digit number
        // Use the Math.random() method to generate the number and Math.round()
        // to ensure that the number is rounded to the one's place
        double six_digits = Math.round(Math.random() * 1000000);

        // Store the six-digit number into a String after casting it into
        // an int so no decimal is present
        String newID = "" + (int) six_digits;
        
        // Generate another random 6-digit number
        six_digits = Math.round(Math.random() * 1000000);
        
        // Concatenate the second 6-digit number to the end of the first 6-digit number,
        // finally creating a randomly-generated 12-digit number.
        newID += (int) six_digits;
        
        // Concatenate 0's to the beginning of the randomly-generated number
        // if it is not 12 digits.
        while (newID.length() != 12) {
            newID = "0" + newID;
        }

        // Ensure that the ID is 12-digits.  If it is more than 12 digits,
        // store only the first 12 to the newID String.
        if (newID.length() > 12) {
            newID = newID.substring(0, 12);
        }

        // Test to see if this ID exists by using the Mortgage.find() method
        // and if it does, create a new ID by calling the createAssetID method again
        if (this.find(newID)) {
            createAssetID();
        }

        // Set the AssetNumber of the Mortgage to the randomly generated 12-digit ID
        return newID;
    }  // method createAssetID

    /**
     * The find method locates a given mortgage record by searching the
     * mortgage IDs.
     * @return true if the mortgage is located, false otherwise
     *
     **/
    public boolean find(String findMortgageID)
    {
        try {
            File mortgageFile = new File("mortgage.dat"); // file of mortgage records
            boolean found = false;		          // result of comparison

            if (mortgageFile.exists()) {
                RandomAccessFile inFile = new RandomAccessFile(mortgageFile, "r");

                while (!found && (inFile.getFilePointer() != inFile.length())) {
                    read(inFile);

                    if (assetNumber.compareTo(findMortgageID) == 0) {
                        found = true;
                    }
                }
                
                inFile.close();
            }
            return found;
        } catch (Exception e) {
            return false;
        }
    }  // find method

    /**
     * The mortgage_find method locates a given mortgage record if it exists
     * by searching for its name.  This mortgage is used for retrieving info
     * to produce a full Mortgage Report.
     *
     * @return the mortgage when found
     *
     **/
    public Mortgage mortgage_find(Object mortgagee) throws IOException
    {
        // file of mortgage records
        File mortgageFile = new File("mortgage.dat");

        //create a new mortgage
        Mortgage mortgage = new Mortgage();
        //if the file exsists find the full mortgage
        if (mortgageFile.exists()) {
            RandomAccessFile inFile = new RandomAccessFile(mortgageFile, "r");
            //while pointer isnt at the end of the file
            while (inFile.getFilePointer() != inFile.length()) {
                //read in the mortgages one by one
                read(inFile);
                mortgage = this;

                //if the mortgage matching the name parameter is found return
                //the mortgage
                if (mortgageeName.equals(mortgagee)) {
                    inFile.close();
                    return mortgage;
                } //end if
            } //end while
        } //end if
        return mortgage;
    }  // mortgage_find method

    /**
     * The read method reads a mortgage from a given file (fileName).  This
     * method assumes that the file already exists.
     *
     * @param randomAccessFile fileName is the name of the file being read
     *
     **/
    public void read(RandomAccessFile fileName)
    {
        try {
            String inputString = new String();	// for storing mortgage record
            int i = 0;    			// position within string

            inputString = fileName.readLine();

            StringBuffer input = new StringBuffer();
            while (inputString.charAt(i) != '|') {
                input.append(inputString.charAt(i));
                i++;
            }

            assetNumber = input.toString();
            i++;

            input = new StringBuffer();
            while (inputString.charAt(i) != '|') {
                input.append(inputString.charAt(i));
                i++;
            }

            mortgageeName = input.toString();
            i++;

            input = new StringBuffer();
            while (inputString.charAt(i) != '|') {
                input.append(inputString.charAt(i));
                i++;
            }

            Float tempFloat = new Float(input.toString());
            price = tempFloat.floatValue();
            i++;

            input = new StringBuffer();
            while (inputString.charAt(i) != '|') {
                input.append(inputString.charAt(i));
                i++;
            }

            dateMortgageIssued = input.toString();
            i++;

            input = new StringBuffer();
            while (inputString.charAt(i) != '|') {
                input.append(inputString.charAt(i));
                i++;
            }

            tempFloat = new Float(input.toString());
            currentWeeklyIncome = tempFloat.floatValue();
            i++;

            input = new StringBuffer();
            while (inputString.charAt(i) != '|') {
                input.append(inputString.charAt(i));
                i++;
            }

            weeklyIncomeUpdated = input.toString();
            i++;

            input = new StringBuffer();
            while (inputString.charAt(i) != '|') {
                input.append(inputString.charAt(i));
                i++;
            }

            tempFloat = new Float(input.toString());
            annualPropertyTax = tempFloat.floatValue();
            i++;

            input = new StringBuffer();
            while (inputString.charAt(i) != '|') {
                input.append(inputString.charAt(i));
                i++;
            }

            tempFloat = new Float(input.toString());
            annualInsurancePremium = tempFloat.floatValue();
            i++;

            input = new StringBuffer();
            while (i < inputString.length()) {
                input.append(inputString.charAt(i));
                i++;
            }

            tempFloat = new Float(input.toString());
            mortgageBalance = tempFloat.floatValue();
            i++;
        } catch (Exception e) {
            System.out.println("***** Error: Mortgage.read () *****");
            System.out.println("\t" + e);
        }

    }  // read method

    /**
     * The write method writes a mortgage record to a specified file (fileName)
     *
     * @param randomAccessFile fileName is the name of the file being written to
     *
     **/
    public void write(RandomAccessFile fileName)
    {
        try {
            fileName.writeBytes(assetNumber + "|" + mortgageeName + "|");
            fileName.writeBytes(price + "|" + dateMortgageIssued.toString() + "|");
            fileName.writeBytes(currentWeeklyIncome + "|" +
                                        weeklyIncomeUpdated.toString() + "|");
            fileName.writeBytes(annualPropertyTax + "|" +
                                        annualInsurancePremium + "|");
            fileName.writeBytes(mortgageBalance + "\n");
        } catch (Exception e) {
            System.out.println("***** Error: Mortgage.write () *****");
            System.out.println("\t" + e);
        }

    }  // write method

    /**
     * The performDeletion method performs the actual deletion of a mortgage
     * record from a file.
     *
     **/
    public void performDeletion()
    {
        try {
            File mortgageFile = new File("mortgage.dat");     // file of mortgage records
            File tempMortgageFile = new File("mortgage.tmp"); // temporary file of records
            Mortgage mortgage = new Mortgage();	              // record to be checked

            if (!mortgageFile.exists()) {
                return;
            }

            RandomAccessFile inFile = new RandomAccessFile(mortgageFile, "r");
            RandomAccessFile outFile = new RandomAccessFile(tempMortgageFile, "rw");

            while (inFile.getFilePointer() != inFile.length()) {
                mortgage.read(inFile);

                if (assetNumber.compareTo(mortgage.getAssetNumber()) != 0) {
                    mortgage.write(outFile);
                }
            }

            inFile.close();
            outFile.close();

            mortgageFile.delete();
            tempMortgageFile.renameTo(mortgageFile);
        } catch (Exception e) {
            System.out.println("***** Error: Mortgage.performDeletion () *****");
            System.out.println("\t" + e);
        }

    }  // performDeletion method

    /**
     * The save method saves an individual mortgage record into a file ordered
     * by the mortgageID
     *
     **/
    public void save()
    {
        try {
            File mortgageFile = new File("mortgage.dat");     // file of mortgage records
            File tempMortgageFile = new File("mortgage.tmp"); // temporary file of records
            Mortgage mortgage = new Mortgage();	              // record read, then written
            boolean found = false;		              // terminates while-loop

            RandomAccessFile newFile = new RandomAccessFile(tempMortgageFile, "rw");

            if (!mortgageFile.exists()) {
                write(newFile);
            } else {
                RandomAccessFile oldFile = new RandomAccessFile(mortgageFile, "r");

                int compareMortgages;	// to find correct place for the new mortgage

                while (oldFile.getFilePointer() != oldFile.length()) {
                    mortgage.read(oldFile);

                    compareMortgages = assetNumber.compareTo(mortgage.getAssetNumber());

                    if ((!found) && (compareMortgages < 0)) {
                        write(newFile);
                        mortgage.write(newFile);
                        found = true;
                    } else if (compareMortgages == 0) {
                        write(newFile);
                        found = true;
                    } else {
                        mortgage.write(newFile);
                    }
                }  // while

                if (!found) {
                    write(newFile);
                }

                oldFile.close();
            }

            newFile.close();

            mortgageFile.delete();
            tempMortgageFile.renameTo(mortgageFile);

        } catch (Exception e) {
            System.out.println("***** Error: Mortgage.save () *****");
            System.out.println("\t" + e);
        }
    }  // save method
}  // class Mortgage