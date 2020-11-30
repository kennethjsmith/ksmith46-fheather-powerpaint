package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;



public class PowerPaintGUI {
    
    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /** The number of rows. */
    private static final int ROW = 4;
    
    /** The number of columns. */
    private static final int COL = 1;
    
    /** Amount in Pixels for the Horizontal margin. */
    private static final int HORIZONATAL_MARGIN = 20; 
    
    /** Amount in Pixels for the Vertical margin. */
    private static final int VERTICALL_MARGIN = 10; 
    
    /** Amount in columns for the text area. */
    private static final int TEXT_AREA_COLS = 25; 
    
    /** The default size of the color panel. */
    private static final int WINDOW_SIZE = 200;
    
    
    /**
     * Constructs the GUI.
     * 
     */
    public PowerPaintGUI() {
        super();
    }
    
    /**
     * Lay out the components.
     */
    private void layoutComponents() {
    }
    
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     * 
     * NOTE: This is the place where all of the parts and pieces of this project are in 
     *      the same place. This is where we should instantiate our MOdel and add it to the
     *      controller and view.  
     */
    public static void createAndShowGUI() {  
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final PaintPanel panel = new PaintPanel();
                final JFrame frame = new JFrame("Power Paint XD");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(panel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
   
}
