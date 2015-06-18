/**
 * The MSG_JFrame class extends the JFrame class and acts exactly like a
 * full screen JFrame with no border.
 *
 * Note: This class was created in NetBeans IDE.
 * @author Ryan Mickey
 * @version 2
 **/

package MSG;

import javax.swing.*;
import java.awt.*;

public class MSG_JFrame extends JFrame{
    // Initialize a Dimenison object containing the screen resolution
    final private Dimension resolution =
            Toolkit.getDefaultToolkit().getScreenSize();
    // Declare an MSG_Jpanel known as main_Panel that is used by each
    // user-created GUI class.
    protected MSG_JPanel main_Panel;
    /**
     * Class constructor that constructs an MSG_JFrame object that is
     * essentially a JFrame with a fixed size containing no border.
     **/
    public MSG_JFrame(){
        // Set the boundary of the frame to be located at the orgin and
        // be the exact size of the screen's resolution
        setBounds(0,0,resolution.width, resolution.height);
        // Set the JFrame to be undecorated so that there are no borders
        setUndecorated(true);
        // Set the JFrame closing operation to exit the program upon closing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}