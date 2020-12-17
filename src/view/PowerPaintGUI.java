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

import controller.ColorIcon;
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
    private PaintPanel myPanel;   
    private JToolBar myToolBar;
    private JMenuBar myMenuBar;
    
    private PencilTool myPencilTool;
    private RectangleTool myRectangleTool;
    private EraserTool myEraserTool;
    private LineTool myLineTool;
    private static JMenuItem myClearButton;
    private EllipseTool myEllipseTool;
    private ImageIcon myPaintIcon;

    private ToolBarAction myPencilAction;

    private ToolBarAction myLineAction;

    private ToolBarAction myRectangleAction;

    private ToolBarAction myEllipseAction;

    private ToolBarAction myEraserAction;
        
    /**
     * Constructs the GUI.
     */
    public PowerPaintGUI() {
        super("Power Paint");
        myPaintIcon = new ImageIcon("images\\paintbrush.png");
        setIconImage(myPaintIcon.getImage());
        
        myPencilTool = new PencilTool();
        myLineTool = new LineTool();
        myRectangleTool = new RectangleTool();
        myEraserTool = new EraserTool();
        myEllipseTool = new EllipseTool();
        
        myPanel = new PaintPanel(myLineTool);
        myToolBar = new JToolBar();
        myMenuBar = new JMenuBar();
        
        myPencilAction = new ToolBarAction ("Pencil", new ImageIcon("images\\pencil.gif"), myPencilTool);
        myLineAction = new ToolBarAction("Line", new ImageIcon("images\\line.gif"), myLineTool);
        myRectangleAction = new ToolBarAction("Rectangle", new ImageIcon("images\\rectangle.gif"), myRectangleTool);
        myEllipseAction = new ToolBarAction("Ellipse", new ImageIcon("images\\ellipse.gif"), myEllipseTool);
        myEraserAction = new ToolBarAction("Eraser", new ImageIcon("images\\eraser.gif"), myEraserTool);
                
                
        assembleToolBar();        
        assembleMenuBar();
               
        this.add(myToolBar, BorderLayout.SOUTH);
        this.add(myMenuBar, BorderLayout.NORTH);
        this.add(myPanel, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        myPanel.setVisible(true);        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void setClearButton(boolean theBool) {
        myClearButton.setEnabled(theBool);
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
        
       lineButton.setSelected(true);
    }
    
    /**
     * Assembles a complete Menu Bar.
     */
    private void assembleMenuBar() {
        
        //add option menu
        JMenu myOptions = new JMenu("Options");
        myOptions.setMnemonic(KeyEvent.VK_O);
        
        JMenuItem inSaveButton = new JMenuItem("Save");
        inSaveButton.addActionListener(e -> myPanel.save());
        inSaveButton.setMnemonic(KeyEvent.VK_S);
        myOptions.add(inSaveButton);
        
        JMenuItem inLoadButton = new JMenuItem("Load");
        inLoadButton.addActionListener(e -> myPanel.load());
        inLoadButton.setMnemonic(KeyEvent.VK_D);
        
        myOptions.add(inLoadButton);
        
        //create thickness submenu
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
        
        //clear button
        myClearButton = new JMenuItem("Clear");
        myClearButton.setMnemonic(KeyEvent.VK_C);
        myClearButton.addActionListener(e -> myPanel.clear());
        myClearButton.setEnabled(false);
        myOptions.add(myClearButton);
        myMenuBar.add(myOptions);
        
        //Edit menu
        JMenu myEdit = new JMenu("Edit");
        myEdit.setMnemonic(KeyEvent.VK_E);
        JMenuItem myUndoButton = new JMenuItem("Undo");
        // myUndoButton.setSelected(false);
        myUndoButton.addActionListener(e -> myPanel.undoLastDrawing());
        myUndoButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        
        JMenuItem myRedoButton = new JMenuItem("Redo");
       // myRedoButton.setSelected(false);
        myRedoButton.addActionListener(e -> myPanel.redoLastUno());
        myRedoButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        myEdit.add(myUndoButton);
        myEdit.add(myRedoButton);
        myMenuBar.add(myEdit);
        
        // Tools submenu
        JMenu myTools = new JMenu("Tools");
        myTools.setMnemonic(KeyEvent.VK_T);
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
        
        myLineMenuItem.setSelected(true);
        
        myMenuBar.add(myTools);
        
        JMenu myHelpMenu = new JMenu("Help");
        myMenuBar.add(myHelpMenu);
        myHelpMenu.setMnemonic(KeyEvent.VK_H);
        
        JMenuItem myAboutButton = new JMenuItem("About...");
        myAboutButton.setMnemonic(KeyEvent.VK_A);
        myHelpMenu.add(myAboutButton);
        
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

        private final PaintTool myTool;
        
        ToolBarAction(final String theName, final PaintTool theTool){
            super(theName);
            myTool = theTool;
            
            putValue(Action.MNEMONIC_KEY,
                     KeyEvent.getExtendedKeyCodeForChar(theTool.getMnemonic()));
            
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
            
            putValue(Action.MNEMONIC_KEY,
                     KeyEvent.getExtendedKeyCodeForChar(theTool.getMnemonic()));
            
            putValue(Action.SELECTED_KEY, true);
        }
        
        /**
         * 
         */
        @Override
        public void actionPerformed(ActionEvent arg0) {
            myPanel.setCurrentTool(myTool);
            putValue(Action.SELECTED_KEY, true);
        }
    }   
}
    