package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

import model.PaintedShape;
import model.UWColor;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import controller.tools.EraserTool;
import controller.tools.PaintTool;

public class PaintPanel extends JPanel{
    /**
     * The panel width.
     */
    public static final int WIDTH = 400;

    /**
     * The panel height.
     */
    public static final int HEIGHT = 400;

    /**
     * The background color of the panel.
     */
    public static final Color BACKGROUND_COLOR = Color.WHITE;

    /**
     * The color to paint with.
     */
    public static final Color FOREGROUND_COLOR = Color.RED;

    /**
     * The line width.
     */
    public static final int LINE_WIDTH = 10;
    
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 8452917670991316606L; 
        
    public int myCurrentWidth;

    public PaintTool myCurrentTool;

    public Color myCurrentColor;

    private LinkedList<PaintedShape> myShapeList;

    private Color myPrimaryColor;

    private Color mySecondaryColor;

    /**
     * Constructs a new general path panel.
     */
    public PaintPanel(PaintTool theTool) {
        super();       
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        setBackground(BACKGROUND_COLOR);
        final MyMouseHandler mouseHandler = new MyMouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        myCurrentWidth = LINE_WIDTH;
        myCurrentTool = theTool;
        myCurrentColor = myPrimaryColor;
        myShapeList = new LinkedList<>();
        myPrimaryColor = UWColor.getPurple();
        mySecondaryColor = UWColor.getGold();
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        final Iterator<PaintedShape> it = myShapeList.descendingIterator();
        while (it.hasNext()) {
            final PaintedShape current = it.next();
            g2d.setStroke(current.getStroke());
            g2d.setPaint(current.getDrawColor());
            final Shape s = current.getShape();
            
            g2d.draw(s);
        }
    
        // draw the current shape
        g2d.setStroke(new BasicStroke(myCurrentWidth,
                        BasicStroke.CAP_SQUARE,
                        BasicStroke.JOIN_MITER,
                        1,
                        null,
                        0));
        g2d.setPaint(myCurrentColor);
        final Shape s = myCurrentTool.getShape();
        g2d.draw(s);
    }
    
    
    /**
     * Clears the painting surface.
     */
    public void clear() {
        myShapeList.clear();
        firePropertyChange("clear", null, false);
        repaint();
        PowerPaintGUI.setClearButton(false);
    }
    
    /**
     * Sets the current paint tool.
     * 
     * @param theTool the current tool to set
     */
    public void setCurrentTool(final PaintTool theTool) {
        
        if (theTool == null) {
//firePropertyChange(myCurrentTool.getName(), myCurrentTool, theTool); 
            myCurrentTool = theTool;
        } else {
//firePropertyChange(myCurrentTool.getName(), myCurrentTool, theTool); 
            myCurrentTool = theTool;
        }
        
    }
    
    /**
     * Returns the current paint tool.
     * 
     * @return the current paint tool
     */
    public PaintTool getCurrentTool() {
        return myCurrentTool;
    }
    
    /**
     * Set the current draw color.
     * 
     * @param theColor the paint color to use
     */
    public void setPrimaryColor(final Color theColor) {
        myPrimaryColor = theColor;
    }
    
    
    /**
     * Sets the current secondary color.
     * 
     * @param theColor the paint color to use
     */
    public void setSecondaryColor(final Color theColor) {
        mySecondaryColor = theColor;
    }
    
    /**
     * Returns the primary color.
     * 
     * @return the primary color
     */
    public Color getPrimaryColor() {
        return myPrimaryColor;
    }
    
    
    /**
     * Returns the secondaryColor.
     * 
     * @return the secondaryColor
     */
    public Color getSecondaryColor() {
        return mySecondaryColor;
    }
    
    /**
     * Sets the width at width the current tool will draw.
     * 
     * @param theWidth the stroke width to set
     */
    public void setCurrentWidth(final int theWidth) {
        myCurrentWidth = theWidth;
    }
    
    // Inner Class
    /**
     *  Listens for mouse events to draw on our panel.
     * @author 12538 
     *
     */
    private class MyMouseHandler extends MouseInputAdapter {

        @Override
        public void mousePressed(final MouseEvent theEvent) {
            if (myCurrentWidth > 0) {
                if (myCurrentTool instanceof EraserTool) {
                    myCurrentColor = Color.white;
                } else {
                    if (theEvent.getButton() == 1) {
                        myCurrentColor = myPrimaryColor;
                    } else {
                        myCurrentColor = mySecondaryColor;
                    }
                }
            }
                myCurrentTool.setStartPoint(theEvent.getPoint());
                repaint(); 
        }
        
    
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            if (myCurrentWidth > 0) {
                myCurrentTool.setNextPoint(theEvent.getPoint());
                repaint();
            }
        }
    
        @Override
        public void mouseReleased(final MouseEvent theEvent) {            
            
            if (myCurrentWidth > 0) {
                myCurrentTool.setNextPoint(theEvent.getPoint());
                myShapeList.push(new PaintedShape(myCurrentTool.getShape(),
                                                  myCurrentColor,
                                                  myCurrentColor,
                                                  myCurrentWidth));
                PowerPaintGUI.setClearButton(true);
                firePropertyChange("clear", null, true);
                myCurrentTool.reset();
                repaint();
            }
        }
    }
}
