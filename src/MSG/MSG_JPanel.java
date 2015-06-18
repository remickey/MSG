/**
 * The MSG_JPanel Class extends the JPanel class and acts as a formated
 * JPanel with a specific layout that is used for each GUI being constructed.
 * The class also overrides the paint method of JPanel to implement a background
 * image that is used across each user-created GUI.
 *
 * Note: This class was created in NetBeans IDE.
 * @author Ryan Mickey
 * @version 2
 **/

package MSG;

import javax.swing.*;
import java.awt.*;

public class MSG_JPanel extends JPanel{
    // Initialize a Dimenison object containing the screen resolution
    final private Dimension resolution =
            Toolkit.getDefaultToolkit().getScreenSize();
    // Initialize a double to hold the aspect ratio of the monitor
    final private double aspect_ratio =
            ((double)resolution.width / (double)resolution.height);
    /**
     * Class constructor that constructs an MSG_JPanel
     **/
    public MSG_JPanel(){
        // Initialize the JPanel to have a BorderLayout
        setLayout(new BorderLayout());
        // Set the JPanel so that the background can show through
        setOpaque(false);
    }

    /**
     * Override the paint method of JPanel to use the drawImage method of
     * the Graphics class and display the given image on the JPanel.
     * The super keyword is used to call the parent method and pass it the new
     * Graphics object that has been defined in this method.
     * @param g a Graphics object that is modified during the method
     **/
    public void paint(Graphics g){
        // Store the background image being used as an Image
        Image background = Toolkit.getDefaultToolkit().getImage("bg_16_10.jpg");
        // Hardcode the width and height of the background image being used
        int actual_image_width = 1680,
            actual_image_height = 1050;
        // Determine the scale of the image in relation to the monitor's
        // resolution.
        double xScale = (double)resolution.width/actual_image_width,
               yScale = (double)resolution.height/actual_image_height;
        // Test to determine the which scale is the great value.  This
        // is used to ensure that the image will scale to fill the MSG_JFrame
        double scale = Math.max(xScale, yScale);
        // Store the new size of the image so that it is appropriately scaled
        int scaled_image_width = (int)(scale * actual_image_width),
            scaled_image_height = (int)(scale * actual_image_height);
        // Draw the now-scaled image onto the JFrame and paint this graphic
        g.drawImage(background, 0,0, scaled_image_width, scaled_image_height, this);
        super.paint(g);
    }
}