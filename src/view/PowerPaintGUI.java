package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import controller.tools.EllipseTool;
import controller.tools.EraserTool;
import controller.tools.LineTool;
import controller.tools.PaintTool;
import controller.tools.PencilTool;
import controller.tools.RectangleTool;
import model.UWColor;
import view.actions.ColorAction;
import view.actions.ColorAction2;



/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class contains the main method that runs the Power Paint program.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public class PowerPaintGUI extends JFrame {

    /** A generated serialization ID. */
    private static final long serialVersionUID = 5679320332309261419L;    
    
    // fields
    
    /** A panel that will be drawn on. */
    private PaintPanel myPanel;  
    
    /** A toolbar that will be added to the frame. */
    private JToolBar myToolBar;
    
    /** A menubar that will be added to the frame. */
    private JMenuBar myMenuBar;
    
    /** A pencil tool. */
    private PencilTool myPencilTool;
    
    /** A rectangle tool. */
    private RectangleTool myRectangleTool;
    
    /** An eraser tool. */
    private EraserTool myEraserTool;
   
    /** A line tool. */
    private LineTool myLineTool;
    
    /** An ellipse tool. */
    private EllipseTool myEllipseTool;

    /** The action for the pencil tool. */
    private ToolBarAction myPencilAction;
    
    /** The action for the line tool. */
    private ToolBarAction myLineAction;
    
    /** The action for the rectangle tool. */
    private ToolBarAction myRectangleAction;
    
    /** The action for the ellipse tool. */
    private ToolBarAction myEllipseAction;
    
    /** The action for the eraser tool.*/
    private ToolBarAction myEraserAction;
        
    /** The clear button. */
    private static JMenuItem myClearButton;
    
    /** The undo button. */
    private static JMenuItem myUndoButton;
    
    /** The redo button. */
    private static JMenuItem myRedoButton;
    
    /** The icon used in the menu bar for the primary and secondary colors. */
    private ImageIcon myPaintIcon;
    
    /**
     * Constructs the GUI.
     */
    public PowerPaintGUI() {
        super("Power Paint");
        myPaintIcon = new ImageIcon("images\\paintbrush.png");
        setIconImage(myPaintIcon.getImage());
        
        // Creates the tools.
        myPencilTool = new PencilTool();
        myLineTool = new LineTool();
        myRectangleTool = new RectangleTool();
        myEraserTool = new EraserTool();
        myEllipseTool = new EllipseTool();
        
        // Creates the panel.
        myPanel = new PaintPanel(myLineTool);
        // Creates the toolbar.
        myToolBar = new JToolBar();
        // Creates the menubar.
        myMenuBar = new JMenuBar();
        
        // Adds the action to the tools.
        myPencilAction = new ToolBarAction ("Pencil", new ImageIcon("images\\pencil.gif"), myPencilTool);
        myLineAction = new ToolBarAction("Line", new ImageIcon("images\\line.gif"), myLineTool);
        myRectangleAction = new ToolBarAction("Rectangle", new ImageIcon("images\\rectangle.gif"), myRectangleTool);
        myEllipseAction = new ToolBarAction("Ellipse", new ImageIcon("images\\ellipse.gif"), myEllipseTool);
        myEraserAction = new ToolBarAction("Eraser", new ImageIcon("images\\eraser.gif"), myEraserTool);
                
        // Assembles the toolbar.  
        assembleToolBar();       
        // Assembles the menubar.
        assembleMenuBar();
               
        // Adds everything to the frame and prepares it.
        this.add(myToolBar, BorderLayout.SOUTH);
        this.add(myMenuBar, BorderLayout.NORTH);
        this.add(myPanel, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        myPanel.setVisible(true);        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Enables and disables the clear button as needed.
     * Clear button is disabled when there is nothing to clear from the panel.
     * 
     * @param theBool true if the clear button can be clicked, false otherwise
     */
    public static void setClearButton(boolean theBool) {
        myClearButton.setEnabled(theBool);
    }
    
    /**
     * Enables and disables the undo button as needed.
     * Undo button is disabled when there is nothing to undo (ie myShapeList is empty).
     * 
     * @param theBool true if the undo button can be clicked, false otherwise
     */
    public static void setUndoButton(boolean theBool) {
        myUndoButton.setEnabled(theBool);
    }
    
    /**
     * Enables and disables the redo button as needed.
     * Redo button is disabled when the last thing the user did was not undo.
     * 
     * @param theBool true if the redo button can be clicked, false otherwise
     */
    public static void setRedoButton(boolean theBool) {
        myRedoButton.setEnabled(theBool);
    }
    
    /**
     * Assembles a complete Tool Bar.
     */
    private void assembleToolBar() {
        final ButtonGroup toolGroup = new ButtonGroup();
        
        final JToggleButton pencilButton = new JToggleButton(myPencilAction);
        toolGroup.add(pencilButton);
        myToolBar.add(pencilButton);

        final JToggleButton lineButton = new JToggleButton(myLineAction);        
        toolGroup.add(lineButton);
        myToolBar.add(lineButton);

        final JToggleButton rectangleButton = new JToggleButton(myRectangleAction);
        toolGroup.add(rectangleButton);
        myToolBar.add(rectangleButton);
        
        final JToggleButton ellipseButton = new JToggleButton(myEllipseAction);
        toolGroup.add(ellipseButton);
        myToolBar.add(ellipseButton);
        
        final JToggleButton eraserButton = new JToggleButton(myEraserAction);
        toolGroup.add(eraserButton);
        myToolBar.add(eraserButton);
        
        // Sets the line button to be selection upon startup.
        lineButton.setSelected(true);
    }
    
    /**
     * Assembles a complete Menu Bar.
     */
    private void assembleMenuBar() {
        
        // Add option menu.
        JMenu myOptions = new JMenu("Options");
        myOptions.setMnemonic(KeyEvent.VK_O);
        myMenuBar.add(myOptions);
        
        // Save button
        JMenuItem inSaveButton = new JMenuItem("Save");
        inSaveButton.addActionListener(e -> myPanel.save());
        inSaveButton.setMnemonic(KeyEvent.VK_S);
        myOptions.add(inSaveButton);
        
        // Load button
        JMenuItem inLoadButton = new JMenuItem("Load");
        inLoadButton.addActionListener(e -> myPanel.load());
        inLoadButton.setMnemonic(KeyEvent.VK_D);
        myOptions.add(inLoadButton);
        
        // Thickness submenu
        JMenu myThickness = new JMenu("Thickness");
        myThickness.setMnemonic(KeyEvent.VK_T);
        JSlider myThicknessSlider = new JSlider(0, 20, 10);
        myThicknessSlider.setMajorTickSpacing(5);
        myThicknessSlider.setMinorTickSpacing(1);
        myThicknessSlider.setPaintTicks(true);
        myThicknessSlider.setSnapToTicks(true);
        myThicknessSlider.setPaintLabels(true);
        myThicknessSlider.addChangeListener(e -> myPanel.setCurrentWidth(myThicknessSlider.getValue()));
        myThickness.add(myThicknessSlider);
        myOptions.add(myThickness);        
        myOptions.addSeparator();
        
        //Primary and Secondary Color 
        myOptions.add(new JMenuItem(new ColorAction(myPanel)));
        myOptions.add(new JMenuItem(new ColorAction2(myPanel)));
        myOptions.addSeparator();
        
        // Clear button
        myClearButton = new JMenuItem("Clear");
        myClearButton.setMnemonic(KeyEvent.VK_C);
        myClearButton.addActionListener(e -> myPanel.clear());
        myClearButton.setEnabled(false);
        myOptions.add(myClearButton);
        
        // Edit menu
        JMenu myEdit = new JMenu("Edit");
        myEdit.setMnemonic(KeyEvent.VK_E);
        myMenuBar.add(myEdit);
        
        // Undo button
        myUndoButton = new JMenuItem("Undo");
        myUndoButton.setEnabled(false);
        myUndoButton.addActionListener(e -> myPanel.undoLastDrawing());
        myUndoButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        myEdit.add(myUndoButton);
        
        // Redo Button
        myRedoButton = new JMenuItem("Redo");
        myRedoButton.setEnabled(false);
        myRedoButton.addActionListener(e -> myPanel.redoLastUno());
        myRedoButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        myEdit.add(myRedoButton);
        
        // Tools submenu
        JMenu myTools = new JMenu("Tools");
        myTools.setMnemonic(KeyEvent.VK_T);
        myMenuBar.add(myTools);
        ButtonGroup myToolButtonGroup = new ButtonGroup();
        
        JRadioButtonMenuItem myPencilMenuItem = new JRadioButtonMenuItem(myPencilAction);
        JRadioButtonMenuItem myLineMenuItem = new JRadioButtonMenuItem(myLineAction);
        JRadioButtonMenuItem myRectangleMenuItem = new JRadioButtonMenuItem(myRectangleAction);
        JRadioButtonMenuItem myEllipseMenuItem = new JRadioButtonMenuItem(myEllipseAction);
        JRadioButtonMenuItem myEraserMenuItem = new JRadioButtonMenuItem(myEraserAction);
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
        
        // Sets the line tool to the initially selected tool upon startup.
        myLineMenuItem.setSelected(true);
        
        // Help menu
        JMenu myHelpMenu = new JMenu("Help");
        myMenuBar.add(myHelpMenu);
        myHelpMenu.setMnemonic(KeyEvent.VK_H);
        
        // About button
        JMenuItem myAboutButton = new JMenuItem("About...");
        myAboutButton.setMnemonic(KeyEvent.VK_A);
        myHelpMenu.add(myAboutButton);
        
        // About button action listener creates a popup 
        myAboutButton.addActionListener(e -> {
            JFrame myOptionFrame = new JFrame("About");
            JOptionPane.showMessageDialog(null, "Heather Finch + Ken Smith\n"
                   + "Autumn 2020\n"
                   + "TCSS 305 Assignment 4", "About", JOptionPane.INFORMATION_MESSAGE, (Icon)myPaintIcon);           
        });         
    }
    
    /**
     *  
     *  
     * @authors Ken Smith (ksmith46) and Heather Finch (fheather)
     */
    private class ToolBarAction extends AbstractAction {
        
        /** A generated serialization ID. */
        private static final long serialVersionUID = 5378597116905801274L;

        /** The paint tool that the action is for. */
        private final PaintTool myTool;
        
        /**
         * Constructs a tool bar action that changes the tool and updates all relevant buttons.
         * 
         * @param theName the name for the button
         * @param theIcon the icon for the tool
         * @param theTool the tool
         */
        ToolBarAction(final String theName, final Icon theIcon, final PaintTool theTool){
            super(theName);
            
            final ImageIcon inIcon = (ImageIcon) theIcon;
            final Image inLargeImage =
                inIcon.getImage().getScaledInstance(15, -1, java.awt.Image.SCALE_SMOOTH);
            final ImageIcon inLargeIcon = new ImageIcon(inLargeImage);
            putValue(Action.LARGE_ICON_KEY, inLargeIcon);
            myTool = theTool;
            
            putValue(Action.MNEMONIC_KEY,
                     KeyEvent.getExtendedKeyCodeForChar(theTool.getMnemonic()));
            
            putValue(Action.SELECTED_KEY, true);
        }
        
        /**
         * Updates the current tool in myPanel and toggles the selected button.
         */
        @Override
        public void actionPerformed(ActionEvent arg0) {
            myPanel.setCurrentTool(myTool);
            putValue(Action.SELECTED_KEY, true);
        }
    }   
}
    