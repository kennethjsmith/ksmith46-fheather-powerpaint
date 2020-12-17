package controller.tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Path2D;

/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class represents the Eraser Tool object, which is a pencil with a different name and mnemonic.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public class EraserTool extends PencilTool {

    /**
     * The name of the tool.
     */
    private static final String MY_NAME = "Eraser";
    
    /**
     * The mnemonic of the tool.
     */
    private static final int MY_MNEMONIC = KeyEvent.VK_A;
    
    /**
     * A constructor which calls on the super constructor in the PencilTool class.
     */
    public EraserTool() {
        super(MY_NAME, MY_MNEMONIC);
    }
}
