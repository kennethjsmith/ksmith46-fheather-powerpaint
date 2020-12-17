package view.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JColorChooser;

import view.ColorIcon;
import view.PaintPanel;

/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class creates an action that is used to update the secondary color icon in the options menu.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public class ColorAction2 extends AbstractAction{

    /** A generated version ID for serialization. */
    private static final long serialVersionUID = -4521489897253823537L;
    
    /**
     * The paint panel for this application.
     */
    private final PaintPanel myPaintPanel;
    
    /**
     * The color chooser this action opens.
     */
    private final JColorChooser myColorChooser;
    
    /**
     * The secondary color icon.
     */
    private ColorIcon secondaryColorIcon;
    
    /**
     * Constructs an Action for changing the selected Color.
     * 
     * @param thePanel the paint panel
     */
    public ColorAction2(final PaintPanel thePanel) {
        super("Secondary Color...");
        myPaintPanel = thePanel;
        putValue(MNEMONIC_KEY, KeyEvent.VK_S);
        myColorChooser = new JColorChooser(myPaintPanel.getSecondaryColor());
        myColorChooser.setColor(myPaintPanel.getSecondaryColor());
        secondaryColorIcon = new ColorIcon(myPaintPanel.getSecondaryColor());
        putValue(Action.SMALL_ICON, secondaryColorIcon);
    }
    
    /**
     * Performs an action to update the secondary color icon and color used by the color panel
     * via JColorChooser.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
        // The action when the user clicks OK in the JColorChooser dialog.
        final ActionListener onOkButtonClicked = (theActionEvent) -> {
            final Color newColor = myColorChooser.getColor();
            myPaintPanel.setSecondaryColor(newColor);
            secondaryColorIcon.updateColor(newColor);
        };
        
        // Cancel action is null because we don't need any action to happen on Cancel.
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