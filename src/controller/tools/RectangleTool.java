package controller.tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class represents the Rectangle Tool object.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public class RectangleTool extends AbstractPaintTool {

    /**
     * The name of the tool.
     */
    private static final String MY_NAME = "Rectangle";
    
    /**
     * The mnemonic of the tool.
     */
    private static final int MY_MNEMONIC = KeyEvent.VK_R;
    
    /**
     * The end point.
     */
    private Point myNextPoint;
    
    /**
     * Constructs this rectangle tool.
     */
    public RectangleTool() {
        super(MY_NAME, MY_MNEMONIC);
        myNextPoint = NO_POINT;
    }
    
    /**
     * Constructs this rectangle tool.
     * Is used as as the super constructor for subclasses.
     * 
     * @param theName the name of this tool
     * @param theMnemonic the mnemonic of this tool
     */
    public RectangleTool(final String theName, final int theMnemonic) {
        super(theName, theMnemonic);
        myNextPoint = NO_POINT;
    }
    
    /**
     * Returns a rectangle shape.
     * 
     * @return inRectangle a Rectangle2D object
     */
    @Override
    public Shape getShape() {
        final Rectangle2D.Double inRectangle = new Rectangle2D.Double();
        inRectangle.setFrameFromDiagonal(getStartPoint(), getNextPoint());
        return inRectangle;
    }    
    
    /**
     * Sets the start point via call to super method, and sets theNextPoint.
     * 
     * @param thePoint the new start point
     */
    @Override
    public void setStartPoint(final Point thePoint) {
        super.setStartPoint(thePoint);
        setNextPoint(thePoint);
    }
    
    /**
     * Sets the next point.
     * 
     * @param thePoint the new next point
     */
    @Override
    public void setNextPoint(Point thePoint) {
        myNextPoint = thePoint;
    }
    
   /**
    * This method is especially helpful for 
    * subclasses needing access to myNextPoint field
    * 
    * @return myNextPoint the tools next point.
    */
   public Point getNextPoint() {
       return myNextPoint;
   }
    
}
