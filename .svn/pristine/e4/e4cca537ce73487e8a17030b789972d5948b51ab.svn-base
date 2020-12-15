package controller.tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class EllipseTool extends AbstractPaintTool implements PaintTool {

    /**
     * The name of the tool.
     */
    private static final String MY_NAME = "Ellipse";
    
    /**
     * The mnemonic of the tool.
     */
    private static final int MY_MNEMONIC = KeyEvent.VK_E;
    
    /**
     * The end point.
     */
    private Point myNextPoint;
    
    /**
     * Constructs this rectangle tool.
     */
    public EllipseTool() {
        super(MY_NAME, MY_MNEMONIC);
        myNextPoint = NO_POINT;
    }
    
    /**
     * Constructs this ellipse tool.
     * 
     * @param theName the name of this tool
     * @param theMnemonic the mnemonic of this tool
     */
    public EllipseTool(final String theName, final int theMnemonic) {
        super(theName, theMnemonic);
        myNextPoint = NO_POINT;
    }
    
    @Override
    public Shape getShape() {
        final Ellipse2D.Double rect = new Ellipse2D.Double();
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
