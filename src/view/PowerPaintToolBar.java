package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import controller.tools.LineTool;
import controller.tools.PaintTool;

public class PowerPaintToolBar extends JToolBar {

    /**
     * Generated Serial ID
     */
    private static final long serialVersionUID = 8884872643669219945L;
    
    /** 
     * The tool bar for this GUI.
     *  https://docs.oracle.com/javase/tutorial/uiswing/components/toolbar.html
     *  https://docs.oracle.com/javase/8/docs/api/javax/swing/JToolBar.html
     */
    private final JToolBar myToolBar;
    
    public PowerPaintToolBar() {
        super();
        
        myToolBar = new JToolBar();
        this.addButtons();
        this.setVisible(true);
    }
    
    
    /**
     * Build the buttons that go in this ToolBar and add them. 
     * 
     * https://docs.oracle.com/javase/tutorial/uiswing/components/toolbar.html
     * https://docs.oracle.com/javase/8/docs/api/javax/swing/JToolBar.html
     * 
     * @param theToolBar the ToolBar to add buttons to 
     */
    private void addButtons() {

        final ButtonGroup toolGroup = new ButtonGroup();
        
        final Icon lineIcon = new ImageIcon("/ksmith46-fheather-powerpaint/images/line.gif");
        final JToggleButton lineButton = new JToggleButton(new ToolBarAction("Line", lineIcon, new LineTool()));
        toolGroup.add(lineButton);
        myToolBar.add(lineButton);

//COMMENTED OUT BECAUSE WE DONT HAVE THESE TOOLS SET UP YET
//        final Icon pencilIcon = new ImageIcon("/ksmith46-fheather-powerpaint/images/pencil.gif");
//        final JToggleButton pencilButton = new JToggleButton(new ToolBarAction ("Pencil", pencilIcon, new PencilTool()));
//        toolGroup.add(pencilButton);
//        myToolBar.add(pencilButton);
//
//        final Icon rectangleIcon = new ImageIcon("/ksmith46-fheather-powerpaint/images/rectangle.gif");
//        final JToggleButton rectangleButton = new JToggleButton(new ToolBarAction("Rectangle", rectangleIcon, new RectangleTool()));
//        toolGroup.add(rectangleButton);
//        myToolBar.add(rectangleButton);
//        
//        final Icon ellipseIcon = new ImageIcon("/ksmith46-fheather-powerpaint/images/ellipse.gif");
//        final JToggleButton ellipseButton = new JToggleButton(new ToolBarAction("Ellipse", ellipseIcon, new EllipseTool()));
//        toolGroup.add(ellipseButton);
//        myToolBar.add(ellipseButton);
//
//        final Icon eraserIcon = new ImageIcon("/ksmith46-fheather-powerpaint/images/eraser.gif");
//        final JToggleButton eraserButton = new JToggleButton(new ToolBarAction("Eraser", eraserIcon, new EraserTool()));
//        toolGroup.add(eraserButton);
//        myToolBar.add(eraserButton);
        
        toolGroup.clearSelection();

    }
    
    private class ToolBarAction extends AbstractAction {
        
        /** A generated serialization ID. */
        private static final long serialVersionUID = 5378597116905801274L;

        private final PaintTool myTool;
        
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
            System.out.println("actionPerformed");
        }
    }
}
