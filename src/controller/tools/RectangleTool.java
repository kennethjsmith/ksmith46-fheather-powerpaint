package controller.tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * The rectangle tool for the PowerPaint application.
 * 
 * @author 
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
     * 
     * @param theName the name of this tool
     * @param theMnemonic the mnemonic of this tool
     */
    public RectangleTool(final String theName, final int theMnemonic) {
        super(theName, theMnemonic);
        myNextPoint = NO_POINT;
    }
    
    @Override
    public Shape getShape() {
        final Rectangle2D.Double rect = new Rectangle2D.Double();
        rect.setFrameFromDiagonal(getStartPoint(), myNextPoint);
        return rect;
    }    
    
    @Override
    public void setStartPoint(final Point thePoint) {
        super.setStartPoint(thePoint);
        myNextPoint = thePoint;
    }
    
    @Override
    public void setNextPoint(Point thePoint) {
        myNextPoint = thePoint;
    }
    
    @Override
    public void reset() {
        super.reset();
        myNextPoint = getStartPoint();
    }



}
