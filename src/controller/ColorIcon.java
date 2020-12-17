package controller;

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
     * @param theNewColor
     */
    public void updateColor(Color theNewColor){
        myIconColor = theNewColor;
    }
    
    /**
     * @return HEIGHT
     */
    @Override
    public int getIconHeight() {
        return HEIGHT;
    }
    
    /**
     * @return WIDTH
     */
    @Override
    public int getIconWidth() {
        return WIDTH;
    }
    
    /**
     * 
     */
    @Override
    public void paintIcon(Component arg0, Graphics arg1, int arg2, int arg3) {
        doDrawing(arg1, arg2, arg3);
    }
    
    /**
     * 
     * @param g
     * @param x
     * @param y
     */
    public void doDrawing(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g; //.create();
        g2d.setColor(myIconColor);
        g2d.fillRect(x + 1, y + 1, WIDTH - 2, HEIGHT - 2);
    }
}
