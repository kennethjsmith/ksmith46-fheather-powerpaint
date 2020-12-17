package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class represents the icon for the colors in paint panel.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public class ColorIcon implements Icon {

    private static final int HEIGHT = 20;
    private final static int WIDTH = 20;
    private Color myIconColor;
    
    /**
     * Constructs a ColorIcon object.
     * 
     * @param theColor to assign
     */
    public ColorIcon(Color theColor) {
        super();
        myIconColor = theColor;
    }
    
    /**
     * Updates the color.
     * 
     * @param theNewColor
     */
    public void updateColor(Color theNewColor){
        myIconColor = theNewColor;
    }
    
    /**
     * Returns the height.
     * 
     * @return HEIGHT
     */
    @Override
    public int getIconHeight() {
        return HEIGHT;
    }
    
    /**
     * Returns the width.
     * 
     * @return WIDTH
     */
    @Override
    public int getIconWidth() {
        return WIDTH;
    }
    
    /**
     * This method paints our custom icon.
     * 
     * @param theComponent an image observer
     * @param theGraphics the graphics to draw
     * @param theX the x-coordinate
     * @param theY the y-coordinate
     */
    @Override
    public void paintIcon(Component theComponent, Graphics theGraphics, int theX, int theY) {
        Graphics2D g2d = (Graphics2D) theGraphics; //.create(); create makes a copy of the graphics object and should be disposed when done, seems unnecessary
        g2d.setColor(myIconColor);
        g2d.fillRect(theX + 1, theY + 1, WIDTH - 2, HEIGHT - 2);
    }
    
}
