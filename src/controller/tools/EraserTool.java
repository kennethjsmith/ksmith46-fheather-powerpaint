package controller.tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Path2D;

public class EraserTool extends AbstractPaintTool {

    /**
     * The name of the tool.
     */
    private static final String MY_NAME = "Eraser";
    
    /**
     * The mnemonic of the tool.
     */
    private static final int MY_MNEMONIC = KeyEvent.VK_P;
    
    /**
     * Stores the points in this shape.
     */
    private Path2D.Double myPath;
    
    public EraserTool() {
        super(MY_NAME, MY_MNEMONIC);
        myPath = new Path2D.Double();
    }

    @Override
    public void setStartPoint(final Point thePoint) {
        super.setStartPoint(thePoint);
        myPath.moveTo(thePoint.getX(), thePoint.getY());
    }
    
    @Override
    public Shape getShape() {
        return myPath;
    }
    
    @Override
    public void reset() {
        super.reset();
        myPath = new Path2D.Double();
    }

    @Override
    public void setNextPoint(final Point thePoint) {
        myPath.lineTo(thePoint.getX(), thePoint.getY());
    }

}
