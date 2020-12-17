package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.io.Serializable;

/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class represents the individual shapes drawn by the user.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public class PaintedShape implements Serializable {
    
    /** A generated version ID for serialization. */
    private static final long serialVersionUID = 4352774499551104845L;
    
    // fields
    private Shape myShape;
    private Color myCurrentColor;
    private int myCurrentWidth;
    
    /**
     * A constructor for PaintedShape.
     * 
     * @param theShape the Shape
     * @param theCurrentColor the draw color
     * @param theCurrentWidth the width
     */
    public PaintedShape(Shape theShape, Color theCurrentColor, int theCurrentWidth) {
        myShape = theShape;
        myCurrentColor = theCurrentColor;    
        myCurrentWidth = theCurrentWidth;                
    }

    /**
     * Returns a stroke.
     * 
     * @return a new instance of a solid BasicStroke with the specified line width and with default values for the cap and join styles.
     */
    public Stroke getStroke() {
       return new BasicStroke(myCurrentWidth);
    }

    /**
     * Returns the color.
     * 
     * @return myCurrentColor
     */
    public Color getDrawColor() {
        return myCurrentColor;
    }

    /**
     * Returns the shape.
     * 
     * @return myShape
     */
    public Shape getShape() {
        return myShape;
    }
    
}
