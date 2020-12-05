package controller.tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;

public class EraserTool extends AbstractPaintTool implements PaintTool {

    /**
     * The name of the tool.
     */
    private static final String MY_NAME = "Eraser";
    
    /**
     * The mnemonic of the tool.
     */
    private static final int MY_MNEMONIC = KeyEvent.VK_P;
    
    /**
     * The end point for the current line.
     */
    private Point myNextPoint;
    
    public EraserTool() {
        super(MY_NAME, MY_MNEMONIC);
        myNextPoint = NO_POINT;
    }

    @Override
    public Shape getShape() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setNextPoint(Point thePoint) {
        // TODO Auto-generated method stub

    }

}
