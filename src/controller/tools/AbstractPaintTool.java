package controller.tools;

import java.awt.Point;

/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class represents an abstract paint tool, it implements PaintTool and has generic methods used by subclasses.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public abstract class AbstractPaintTool implements PaintTool {
    
    
    /** A point outside the drawing area. */
    public static final Point NO_POINT = new Point (-50, -50);
        
    /** The name of this tool. */
    private final String myName;
    
    /** The mnemonic for this tool. */
    private final int myMnemonic;
    
    /** The initial anchor point for the current shape drawn by this tool. */
    private Point myStartPoint;
    
    /**
     * Constructs a paint tool.
     * 
     * @param theName the name of this tool
     * @param theMnemonic the mnemonic for this tool
     */
    public AbstractPaintTool(final String theName, final int theMnemonic) {
        myName = theName;
        myMnemonic = theMnemonic;
        myStartPoint = NO_POINT;
    }
    
    /**
     * Returns the name of the tool.
     * 
     * @return myName the name of the tool
     */
    @Override
    public String getName() {
        return myName;
    }
    
    /**
     * Returns the mnemonic for the tool.
     * 
     * @return myMnemonic the mnemonic for the tool
     */
    @Override
    public int getMnemonic() {
        return myMnemonic;
    }
    
    /**
     * Sets the start point.
     * 
     * @param thePoint the point for the new start point.
     */
    @Override
    public void setStartPoint(final Point thePoint) {
        myStartPoint = thePoint;
    }
    
    /**
     * Returns the start point.
     * 
     * @return myStartPoint the start point
     */
    @Override
    public Point getStartPoint() {
        return myStartPoint;
    }
    
    /**
     * Resets the tools start point.
     */
    @Override
    public void reset() {
        setStartPoint(NO_POINT);
    }
}
