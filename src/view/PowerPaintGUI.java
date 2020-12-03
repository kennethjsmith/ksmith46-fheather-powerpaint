package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import view.actions.ColorAction;



public class PowerPaintGUI {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // constants to capture screen dimensions
    /** A ToolKit. */
    //private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    //private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /** The number of rows. */
    //private static final int ROW = 4;
    
    /** The number of columns. */
    //private static final int COL = 1;
    
    /** Amount in Pixels for the Horizontal margin. */
    //private static final int HORIZONATAL_MARGIN = 20; 
    
    /** Amount in Pixels for the Vertical margin. */
    //private static final int VERTICALL_MARGIN = 10; 
    
    /** Amount in columns for the text area. */
    //private static final int TEXT_AREA_COLS = 25; 
    
    /** The default size of the color panel. */
    //private static final int WINDOW_SIZE = 200;
    
    // fields
    private PaintPanel myPanel;
    private JFrame myFrame;
    //private PowerPaintMenuBar myMenuBar;
    //private ColorAction myCA;
    
    /**
     * Constructs the GUI.
     * 
     */
    public PowerPaintGUI() {
        super();
        myFrame = new JFrame("Power Paint");
        myPanel = new PaintPanel();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(myPanel);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        //menubar = new PowerPaintMenuBar();
        //myFrame.setJMenuBar(menubar);
        
    }
}    
    /**
     * Performs all tasks necessary to display the UI.
     *
    private void start() {
        // set a custom icon on the JFrame title bar
        //final URL url = PowerPaintGUI.class.getResource(ICON_DIRECTORY + "/w.gif"
        //final ImageIcon img = new ImageIcon(url);
        //setIconImage(img.getImage().getScaledInstance(15,  -1,  java.awt.Image.SCA
        
        CA = new ColorAction(myPaintPanel);
        //final ColorAction2 ca2 = new ColorAction2(myPaintPanel);
 //       final PowerPaintMenuBar menubar = new PowerPaintMenuBar(this, ca, ca2); 
        menubar = new PowerPaintMenuBar();
        myPaintPanel.addPropertyChangeListener((PropertyChangeListener) menubar);
        
        this.setJMenuBar(menubar);
        
        final PowerPaintToolBar toolbar = new PowerPaintToolBar(myPaintPanel);
        add(toolbar, BorderLayout.SOUTH);
*/