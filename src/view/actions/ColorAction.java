package view.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JColorChooser;

import controller.ColorIcon;
import model.UWColor;
//import view.ColorSwatch;
import view.PaintPanel;

public class ColorAction extends AbstractAction{

    /** A generated version ID for serialization. */
    private static final long serialVersionUID = 7869489286195463142L;

    /**
     * A custom property for propertyChangeListeners to observe.
     */
    public static final String COLOR = "color";
        
    /**
     * The paint panel for this application.
     */
    private final PaintPanel myPaintPanel;
    
    /**
     * The color swatch indicating the chosen color.
     */
    //private final ColorSwatch myColorSwatch;
    
    /**
     * The color chooser this action opens.
     */
    private final JColorChooser myColorChooser;
    
    ColorIcon primaryColorIcon;
    
    /**
     * Constructs an Action for changing the selected Color.
     * 
     * @param thePanel the paint panel
     */
    public ColorAction(final PaintPanel thePanel) {
        super("Primary Color...");
        myPaintPanel = thePanel;
        putValue(MNEMONIC_KEY, KeyEvent.VK_P);
        //myColorSwatch = new ColorSwatch(myPaintPanel.getPrimaryColor());
        myColorChooser = new JColorChooser(myPaintPanel.getPrimaryColor());
        myColorChooser.setColor(myPaintPanel.getPrimaryColor());    
        primaryColorIcon = new ColorIcon(myPaintPanel.getPrimaryColor()); //myColorSwatch.getColor());
        putValue(Action.SMALL_ICON, primaryColorIcon);
    }
    
    /**
     * 
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
      
        final ActionListener onOkButtonClicked = (theActionEvent) -> {
            final Color newColor = myColorChooser.getColor();
            //myColorSwatch.setColor(newColor);
            myPaintPanel.setPrimaryColor(newColor);
            primaryColorIcon.updateColor(newColor);
        };
        
        JColorChooser.
            createDialog(null,
                        "Select a color",
                        true,
                        myColorChooser,
                        onOkButtonClicked,
                        null).
            setVisible(true);
    }
}
