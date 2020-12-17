package controller.tools;

import java.awt.Point;
import java.awt.Shape;

/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class represents an interface for all paint tools.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public interface PaintTool {

    /**
     * Returns the name of this tool.
     * 
     * @return the name of this tool
     */
    String getName();
    
    /**
     * Returns the Mnemonic for this tool.
     * 
     * @return the mnemonic for this tool
     */
    int getMnemonic();
    
    /**
     * Returns the Shape that this tool draws.
     * 
     * @return the Shape to draw
     */
    Shape getShape();
    
    /**
     * Sets the initial anchor point for the current Shape drawn by this tool.
     * 
     * @param thePoint the start point to set
     */
    void setStartPoint(Point thePoint);
    
    /**
     * Returns the initial anchor point for the current Shape drawn by this tool.
     * 
     * @return the start point for the current shape drawn by this tool
     */
    Point getStartPoint();
    
    /**
     * Sets the next point for the current Shape drawn by this tool.
     * 
     * @param thePoint the next point to set
     */
    void setNextPoint(Point thePoint);
    
    /**
     * Resets the tool using default values.
     */
    void reset();

}
