package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

public class ColorIcon implements Icon {

    private static final int HEIGHT = 20;
    private final static int WIDTH = 20;
    Color myIconColor;
    
    public ColorIcon(Color theColor) {
        super();
        myIconColor = theColor;
    }
    
    public void updateColor(Color theNewColor){
        myIconColor = theNewColor;
    }
    
    @Override
    public int getIconHeight() {
        return HEIGHT;
    }

    @Override
    public int getIconWidth() {
        return WIDTH;
    }

    @Override
    public void paintIcon(Component arg0, Graphics arg1, int arg2, int arg3) {
        doDrawing(arg1, arg2, arg3);
    }
    
    public void doDrawing(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(myIconColor);
        g2d.fillRect(x + 1, y + 1, WIDTH - 2, HEIGHT - 2);
    }
}
