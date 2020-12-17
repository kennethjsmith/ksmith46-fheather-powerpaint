package controller.tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Path2D;

/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class represents the Pencil Tool object.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public class PencilTool extends AbstractPaintTool {

    /**
     * The name of the tool.
     */
    private static final String MY_NAME = "Pencil";
    
    /**
     * The mnemonic of the tool.
     */
    private static final int MY_MNEMONIC = KeyEvent.VK_P;
    
    /**
     * Stores the points in this shape.
     */
    private Path2D.Double myPath;
    
    /**
     * Constructs this pencil tool.
     */
    public PencilTool() {
        super(MY_NAME, MY_MNEMONIC);
        myPath = new Path2D.Double();
    }
    
    /**
     * Constructs this pencil tool.
     */
    public PencilTool(String theName, int theMnemonic) {
        super(theName, theMnemonic);
        myPath = new Path2D.Double();
    }
    
    /**
     * Sets the start point via a call to the super method,
     * and moves the start point of a new sub-path. 
     */
    @Override
    public void setStartPoint(final Point thePoint) {
        super.setStartPoint(thePoint);
        myPath.moveTo(thePoint.getX(), thePoint.getY());
    }
    
    /**
     * Gets the shape.
     * @return myPath the current path
     */
    @Override
    public Shape getShape() {
        return myPath;
    }
    
    /**
     * Resets the tools start point and creates a new path.
     */
    @Override
    public void reset() {
        super.reset();
        myPath = new Path2D.Double();
    }

    /**
     * Creates a line to a next point in the path.
     */
    @Override
    public void setNextPoint(final Point thePoint) {
        myPath.lineTo(thePoint.getX(), thePoint.getY());
    }

}
