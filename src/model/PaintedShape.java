package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;

public class PaintedShape {
    
    // fields
    
    private Shape myShape;
    private Color myCurrentColor;
    private Color myCurrentColor2;
    private int myCurrentWidth;
    private boolean myFill;

    public PaintedShape(Shape theShape, Color theCurrentColor, Color theCurrentColor2, int theCurrentWidth, boolean theFill) {
        myShape = theShape;
        myCurrentColor = theCurrentColor;
        myCurrentColor2 = theCurrentColor2;
        myCurrentWidth = theCurrentWidth;
        myFill = theFill;
                
    }

    public Stroke getStroke() {
       return new BasicStroke(myCurrentWidth);
    }

    public Color getDrawColor() {
        return myCurrentColor;
    }

    public Shape getShape() {
        return myShape;
    }

    public boolean isFilled() {
        return true;
    }
    
}
