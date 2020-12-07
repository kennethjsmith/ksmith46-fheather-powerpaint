package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import controller.tools.EllipseTool;
import controller.tools.EraserTool;
import controller.tools.LineTool;
import controller.tools.PaintTool;
import controller.tools.PencilTool;
import controller.tools.RectangleTool;


public class PowerPaintGUI extends JFrame {
    
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
    
    //private static final int WINDOW_SIZE = 200;

    /**
     * 
     */
    private static final long serialVersionUID = 5679320332309261419L;
    /** The default size of the color panel. */
    
    
    // fields
    private PaintPanel myPanel;   
    private JToolBar myToolBar;
    private JMenuBar myMenuBar;
    
    private PencilTool myPencilTool;
    private RectangleTool myRectangleTool;
    private EraserTool myEraserTool;
    private LineTool myLineTool;
    private static JMenuItem myClearButton;
    private EllipseTool myEllipseTool;
    
    //private ColorAction myCA;
    
    /**
     * Constructs the GUI.
     * 
     */
    public PowerPaintGUI() {
        super("Power Paint");
        ImageIcon myPaintIcon = new ImageIcon("images\\paintbrush.png");
        setIconImage(myPaintIcon.getImage());
        
        myPencilTool = new PencilTool();
        myLineTool = new LineTool();
        myRectangleTool = new RectangleTool();
        myEraserTool = new EraserTool();
        myEllipseTool = new EllipseTool();
        
        myPanel = new PaintPanel(myLineTool);
        myToolBar = new JToolBar();
        myMenuBar = new JMenuBar();
                
        assembleToolBar();        
        assembleMenuBar();
        this.add(myToolBar, BorderLayout.SOUTH);
        this.add(myMenuBar, BorderLayout.NORTH);
        this.add(myPanel, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        myPanel.setVisible(true);
 
        start();
        
        
        //menubar = new PowerPaintMenuBar();
        //myFrame.setJMenuBar(menubar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void setClearButton(boolean theBool) {
        myClearButton.setEnabled(theBool);
    }
    
    /**
     * Assembles a fully-stocked tool bar.
     */
    private void assembleToolBar() {
        final ButtonGroup toolGroup = new ButtonGroup();
        
        final Icon pencilIcon = new ImageIcon("images\\pencil.gif");
        final JToggleButton pencilButton = new JToggleButton(new ToolBarAction ("Pencil", pencilIcon, myPencilTool));
        toolGroup.add(pencilButton);
        myToolBar.add(pencilButton);

        
        final JToggleButton lineButton = new JToggleButton(new ToolBarAction
              ("Line", new ImageIcon("images\\line.gif"), myLineTool));        
        toolGroup.add(lineButton);
        myToolBar.add(lineButton);

        final Icon rectangleIcon = new ImageIcon("images\\rectangle.gif");
        final JToggleButton rectangleButton = new JToggleButton(new ToolBarAction("Rectangle", rectangleIcon, myRectangleTool));
        toolGroup.add(rectangleButton);
        myToolBar.add(rectangleButton);
        
        final Icon ellipseIcon = new ImageIcon("images\\ellipse.gif");
        final JToggleButton ellipseButton = new JToggleButton(new ToolBarAction("Ellipse", ellipseIcon, myEllipseTool));
        toolGroup.add(ellipseButton);
        myToolBar.add(ellipseButton);
        
        final Icon eraserIcon = new ImageIcon("images\\eraser.gif");
        final JToggleButton eraserButton = new JToggleButton(new ToolBarAction("Eraser", eraserIcon, myEraserTool));
        toolGroup.add(eraserButton);
        myToolBar.add(eraserButton);
        
       lineButton.setSelected(true);
    }
    
    private void assembleMenuBar() {
        
        //add option menu
        JMenu myOptions = new JMenu("Options");
        
        //create thickness submenu
        JMenu myThickness = new JMenu("Thickness");
        
        //create thickness slider
        JSlider myThicknessSlider = new JSlider(0, 20, 10);
        
        //set slider variables and add to the thickness submenu
        myThicknessSlider.setMajorTickSpacing(5);
        myThicknessSlider.setMinorTickSpacing(1);
        myThicknessSlider.setPaintTicks(true);
        myThicknessSlider.setSnapToTicks(true);
        myThicknessSlider.setPaintLabels(true);
        myThicknessSlider.addChangeListener(e -> myPanel.setCurrentWidth(myThicknessSlider.getValue()));
        myThickness.add(myThicknessSlider);
        
        //add the thickness submenu to the options menu
        myOptions.add(myThickness);
        
        myOptions.addSeparator();
        
        //
        myOptions.add(new JMenuItem("Primary Color..."));
        myOptions.add(new JMenuItem("Secondary Color..."));
        myOptions.addSeparator();
        
        //clear button
        myClearButton = new JMenuItem("Clear");
        myClearButton.addActionListener(e -> myPanel.clear());
        myClearButton.setEnabled(false);
        myOptions.add(myClearButton);
        myMenuBar.add(myOptions);
        
        JMenu myTools = new JMenu("Tools");
        ButtonGroup myToolButtonGroup = new ButtonGroup();
        JRadioButtonMenuItem myPencilMenuItem = new JRadioButtonMenuItem(new ToolBarAction("Pencil", myPencilTool));
        JRadioButtonMenuItem myLineMenuItem = new JRadioButtonMenuItem(new ToolBarAction("Line", myLineTool));
        JRadioButtonMenuItem myRectangleMenuItem = new JRadioButtonMenuItem(new ToolBarAction("Rectangle", myRectangleTool));
        JRadioButtonMenuItem myEllipseMenuItem = new JRadioButtonMenuItem(new ToolBarAction("Ellipse", myEllipseTool));
        JRadioButtonMenuItem myEraserMenuItem = new JRadioButtonMenuItem(new ToolBarAction("Eraser", myEraserTool));
        myTools.add(myPencilMenuItem);
        myTools.add(myLineMenuItem);
        myTools.add(myRectangleMenuItem);
        myTools.add(myEllipseMenuItem);
        myTools.add(myEraserMenuItem);
        myToolButtonGroup.add(myPencilMenuItem);
        myToolButtonGroup.add(myLineMenuItem);
        myToolButtonGroup.add(myRectangleMenuItem);
        myToolButtonGroup.add(myEllipseMenuItem);
        myToolButtonGroup.add(myEraserMenuItem);
        
        myLineMenuItem.setSelected(true);
        
        myMenuBar.add(myTools);
        
        myMenuBar.add(new JMenu("Help"));
        
        
    }
    
    private class ToolBarAction extends AbstractAction {
        
        /** A generated serialization ID. */
        private static final long serialVersionUID = 5378597116905801274L;

        private final PaintTool myTool;
        
        ToolBarAction(final String theName, final PaintTool theTool){
            super(theName);
            myTool = theTool;
            
            // set a mnemonic on the first character of the name
            putValue(Action.MNEMONIC_KEY,
                     KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
            
            // coordinate button selection
            putValue(Action.SELECTED_KEY, true);

        }
        
        ToolBarAction(final String theName, final Icon theIcon, final PaintTool theTool){
            super(theName);
            
            final ImageIcon icon = (ImageIcon) theIcon;
            final Image largeImage =
                icon.getImage().getScaledInstance(15, -1, java.awt.Image.SCALE_SMOOTH);
            final ImageIcon largeIcon = new ImageIcon(largeImage);
            putValue(Action.LARGE_ICON_KEY, largeIcon);
            myTool = theTool;
            
            // set a mnemonic on the first character of the name
            putValue(Action.MNEMONIC_KEY,
                     KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
            
            // coordinate button selection
            putValue(Action.SELECTED_KEY, true);

        }
        
        @Override
        public void actionPerformed(ActionEvent arg0) {
            myPanel.setCurrentTool(myTool);
        }
    }
   
    /**
     * Performs all tasks necessary to display the UI.
     *
     */
    private void start() {
        
        
//        ??? Below taken from HelloGoodByeExample
//        final Action[] actions = {new HelloAction(panel), new GoodbyeAction(panel)};
//
//        for (final Action current : actions) {
//            menuBar.createMenuButton(current);
//            toolBar.createToggleButton(current);
//        }
        
        
        // set a custom icon on the JFrame title bar
        //final URL url = PowerPaintGUI.class.getResource(ICON_DIRECTORY + "/w.gif"
        //final ImageIcon img = new ImageIcon(url);
        //setIconImage(img.getImage().getScaledInstance(15,  -1,  java.awt.Image.SCA
//        
//        CA = new ColorAction(myPaintPanel);
//        //final ColorAction2 ca2 = new ColorAction2(myPaintPanel);
// //       final PowerPaintMenuBar menubar = new PowerPaintMenuBar(this, ca, ca2); 
//        menubar = new PowerPaintMenuBar();
//        myPaintPanel.addPropertyChangeListener((PropertyChangeListener) menubar);
//        
//        this.setJMenuBar(menubar);
////       
//        final PowerPaintToolBar toolbar = new PowerPaintToolBar("My Tool Actions");
//        myPanel.add(toolbar, BorderLayout.SOUTH);
    }
}
    