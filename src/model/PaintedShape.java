package model;

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
        // TODO Auto-generated method stub
        return null;
    }

    public Paint getDrawColor() {
        // TODO Auto-generated method stub
        return null;
    }

    public Shape getShape() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isFilled() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
