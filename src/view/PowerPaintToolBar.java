package view;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

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
        this.myToolBar = new JToolBar();
        addButtons(myToolBar);
    }
    
    /**
     * Build the buttons that go in this ToolBar and add them. 
     * 
     * https://docs.oracle.com/javase/tutorial/uiswing/components/toolbar.html
     * https://docs.oracle.com/javase/8/docs/api/javax/swing/JToolBar.html
     * 
     * @param theToolBar the ToolBar to add buttons to 
     */
    private void addButtons(final JToolBar theToolBar) {

        final ButtonGroup toolGroup = new ButtonGroup();
        Icon lineIcon = new ImageIcon("/ksmith46-fheather-powerpaint/images/line.gif");

        
        final JButton lineButton = new JButton(lineIcon);
        lineButton.setText("Line");
//       lineButton.setAction();
        theToolBar.add(lineButton);
        toolGroup.add(lineButton);
    }
}
