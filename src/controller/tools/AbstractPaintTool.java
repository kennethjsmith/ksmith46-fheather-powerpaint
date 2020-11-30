package controller.tools;

import java.awt.Point;

public abstract class AbstractPaintTool implements PaintTool {
    
    
    /**
     * A point outside the drawing area.
     */
    public static final Point NO_POINT = new Point (-50, -50);
        
    /**
     * The name of this tool.
     */
    private final String myName;
    
    /**
     * The mnemonic for this tool.
     */
    private final int myMnemonic;
    
    /**
     * The initial anchor point for the current shape drawn by this tool.
     */
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
    
    @Override
    public String getName() {
        return myName;
    }
    
    @Override
    public int getMnemonic() {
        return myMnemonic;
    }
    
    @Override
    public void setStartPoint(final Point thePoint) {
        myStartPoint = thePoint;
    }
    
    @Override
    public Point getStartPoint() {
        return myStartPoint;
    }
    
    @Override
    public void reset() {
        setStartPoint(NO_POINT);
    }
}
