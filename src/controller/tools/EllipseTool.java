package controller.tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class represents the Ellipse Tool object, which is a rectangle with different getShape method.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public class EllipseTool extends RectangleTool {

    /**
     * The name of the tool.
     */
    private static final String MY_NAME = "Ellipse";
    
    /**
     * The mnemonic of the tool.
     */
    private static final int MY_MNEMONIC = KeyEvent.VK_E;
    
    
    /**
     * Constructs this rectangle tool.
     */
    public EllipseTool() {
        super(MY_NAME, MY_MNEMONIC);
    }
    
    /**
     * Returns an ellipse shape.
     * 
     * @return inEllipse an Ellipse2D object
     */
    @Override
    public Shape getShape() {
        final Ellipse2D.Double inEllipse = new Ellipse2D.Double();
        inEllipse.setFrameFromDiagonal(getStartPoint(), getNextPoint());
        return inEllipse;
    }        

}
