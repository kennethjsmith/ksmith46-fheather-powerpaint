package view.actions;

import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;

import controller.tools.LineTool;
import controller.tools.PaintTool;
import view.PaintPanel;

public class ToolAction extends AbstractAction {
    /**
     * 
     */
    private static final long serialVersionUID = -1738024907930377530L;

    /**
     * A custom property for propertyChangeListeners to observe.
     */
    public static final String TOOL = "tool";
    
    /**
     * The paint panel for this application.
     */
    private final PaintPanel myPaintPanel;
    
    private PaintTool myTool;
    
    private Action myMouseClickedAction;
    
    
    public ToolAction(final PaintPanel thePaintPanel) {
// ??? What goes in the super method call ???
        super("");
        myPaintPanel = thePaintPanel;
        myTool = new LineTool();
        
        
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
//Lines below are copied from another class and not updated for this class
// I started to mess with it but didn't finish it.
//        final ActionListener Action.MNEMONIC_KEY = (theActionEvent) -> {
//            final Color newColor = myColorChooser.getColor();
//            myColorSwatch.setColor(newColor);
//            myPaintPanel.setCurrentColor(newColor);
        };
        
    }
    
    private void setUpActions() {
        
    }
    
    
    
    
}
