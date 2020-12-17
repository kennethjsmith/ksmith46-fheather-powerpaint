package controller.tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class represents the Line Tool object.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public class LineTool extends AbstractPaintTool {

    /**
     * The name of the tool.
     */
    private static final String MY_NAME = "Line";
    
    /**
     * The mnemonic of the tool.
     */
    private static final int MY_MNEMONIC = KeyEvent.VK_L;
    
    /**
     * The end point for the current line.
     */
    private Point myNextPoint;
    
    /**
     * Constructs this line tool.
     */
    public LineTool() {
        super(MY_NAME, MY_MNEMONIC);
        myNextPoint = NO_POINT;
    }
    
    /**
     * 
     */
    @Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint().x, getStartPoint().y,
                myNextPoint.x, myNextPoint.y);
    }
    
    /**
     * 
     */
    @Override
    public void setStartPoint(final Point thePoint) {
        super.setStartPoint(thePoint);
        myNextPoint = thePoint;
    }
    
    /**
     * 
     */
    @Override
    public void setNextPoint(Point thePoint) {
        myNextPoint = thePoint;
    }
    
    /**
     * 
     */
    @Override
    public void reset() {
        super.reset();
    }



}
