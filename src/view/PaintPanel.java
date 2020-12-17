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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import model.PaintedShape;
import model.UWColor;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import controller.tools.AbstractPaintTool;
import controller.tools.EraserTool;
import controller.tools.PaintTool;


/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class represents the panel where the user draws shapes. 
 * It keeps track of all the shapes in a LinkedList and has a second LinkedList for tracking undo/redo.
 * 
 * This class also tracks the current width, tool and color. 
 * It holds the save feature and load, as well as an inner class used to track the mouse.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public class PaintPanel extends JPanel{
    
    // constants
    
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 8452917670991316606L; 
    
    /** The panel width. */
    public static final int WIDTH = 400;

    /** The panel height. */
    public static final int HEIGHT = 400;

    /** The background color of the panel. */
    public static final Color BACKGROUND_COLOR = Color.WHITE;

    /** The color to paint with. */
    public static final Color FOREGROUND_COLOR = Color.RED;

    /** The line width. */
    public static final int LINE_WIDTH = 10;
        
    // fields
    
    private int myCurrentWidth;

    private PaintTool myCurrentTool;

    private Color myCurrentColor;

    private LinkedList<PaintedShape> myShapeList;
    
    private LinkedList<PaintedShape> myRedoList;
    
    private Color myPrimaryColor;

    private Color mySecondaryColor;
    
    // Used for tracking the current mouse button in order to prevent multi-mouseclick bugs.
    private int myCurrentMouseButton;

    /**
     * Constructs a new PaintPanel.
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
        myShapeList = new LinkedList<>();
        myRedoList = new LinkedList<>();
        myPrimaryColor = UWColor.getPurple();
        mySecondaryColor = UWColor.getGold();
        myCurrentColor = myPrimaryColor;
        
        myCurrentMouseButton = 0; 
        
    }

    /**
     * Paints the panel with the shapes from the painted shape list and the current shape.
     * Executes every time repaint() is called.
     */
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
            myCurrentTool = theTool;
        } else {
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
    
    /**
     * Removes the last shape from the shape list and adds it to the redo list before repainting.
     */
    public void undoLastDrawing() {
        if(myShapeList.size() > 0) {
            myRedoList.push(myShapeList.get(0));
            myShapeList.remove(0);
            repaint();
        }
    }
    
    /**
     * Removes the last shape from the redo list and adds it to the shape list before repainting.
     */
    public void redoLastUno() {
        if(myRedoList.size() > 0) {
            myShapeList.push(myRedoList.get(0));
            myRedoList.remove(0);
            repaint();
        }
    }
    
    /**
     * Uses an output stream to save the current shape list.
     */
    public void save() {
        JFileChooser fileChooser = new JFileChooser();
        
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            try { 
                
                // Saving of object in a file 
                FileOutputStream fileStream = new FileOutputStream 
                                               (file); 
                ObjectOutputStream out = new ObjectOutputStream 
                                               (fileStream); 
      
                // Method for serialization of object 
                out.writeObject(myShapeList); 
      
                out.close(); 
                fileStream.close(); 
            } 
  
            catch (IOException ex) { 
                System.out.println("IOException is caught"); 
            }
        }
    }
    
    /**
     * Uses an input stream to load a shape list from the chosen file.
     */
    public void load() {
        myShapeList.clear();
        JFileChooser fileChooser = new JFileChooser();
        
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();

            try { 
                
                // Reading the object from a file 
                FileInputStream fileStream = new FileInputStream 
                                             (file); 
                ObjectInputStream in = new ObjectInputStream 
                                             (fileStream); 
      
                myShapeList = (LinkedList<PaintedShape>)in.readObject();
      
                in.close(); 
                fileStream.close(); 
                repaint();
    
            } 
      
            catch (IOException ex) { 
                System.out.println("IOException is caught"); 
            } 
      
            catch (ClassNotFoundException ex) { 
                System.out.println("ClassNotFoundException" + 
                                    " is caught"); 
            }
        }
    }
    
    // Inner Class
    /**
     *  This mouse handler extends MouseInputAdapter which implements MouseInputListener,
     *  an interface that implements both the MouseListener and MouseMotionListener interfaces.
     *  
     *  It generates sets start and next points for the current tool, 
     *  then resets these values and adds the shape to the list on release.
     *  
     *  Its very similar to a class shared in lecture, with some modification to prevent the multiple mouse button clicks
     *  from having an effect at the same time. Mouse dragged doesn't work when the current tools start point is set to the default.
     *  
     * @authors Ken Smith (ksmith46) and Heather Finch (fheather)
     */
    private class MyMouseHandler extends MouseInputAdapter {

       /**
        * Handles click event.
        */
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            if (myCurrentWidth > 0 && myCurrentMouseButton == 0) {
                myCurrentMouseButton = theEvent.getButton();
                if (myCurrentTool instanceof EraserTool) {
                    myCurrentColor = Color.white;
                } else {
                    if (myCurrentMouseButton == 1) {
                        myCurrentColor = myPrimaryColor;
                    } else {
                        myCurrentColor = mySecondaryColor;
                    }
                }
                myCurrentTool.setStartPoint(theEvent.getPoint());
                repaint(); 
            }
        }
        
       /**
        * Handles drag event.
        */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            if (myCurrentWidth > 0 && myCurrentTool.getStartPoint() != AbstractPaintTool.NO_POINT) {
                myCurrentTool.setNextPoint(theEvent.getPoint());
                repaint();
            }
        }
    
       /**
        * Handles click released event.
        */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {            
            
            if (myCurrentWidth > 0 && theEvent.getButton() == myCurrentMouseButton) {
                myCurrentTool.setNextPoint(theEvent.getPoint());
                myShapeList.push(new PaintedShape(myCurrentTool.getShape(),
                                                  myCurrentColor,
                                                  myCurrentWidth));
                myRedoList.clear();
                PowerPaintGUI.setClearButton(true);
                firePropertyChange("clear", null, true);
                // reset is important for preventing multi-click events, dragging with the non-current mouse button
                myCurrentTool.reset();
                myCurrentMouseButton = 0;
                repaint();
            }
        }
    }
}
