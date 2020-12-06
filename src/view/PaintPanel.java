package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.LinkedList;

import model.PaintedShape;
import view.actions.ToolAction;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputAdapter;

import controller.tools.EraserTool;
//import Eraser;
import controller.tools.LineTool;
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
    public static final int LINE_WIDTH = 8;
    
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 8452917670991316606L; 
        
    public int myCurrentWidth;

    public PaintTool myCurrentTool;

    public Color myCurrentColor;

    private LinkedList<PaintedShape> myShapeList;

    private boolean myFill;

    private Color myDrawColor;

    private Color myFillColor;
    
    // Constructor

    /**
     * Constructs a new general path panel.
     */
    public PaintPanel(PaintTool theTool) {
        super();       
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(BACKGROUND_COLOR);
        final MyMouseHandler mouseHandler = new MyMouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        myCurrentWidth = LINE_WIDTH;
        myCurrentTool = theTool;
        myCurrentColor = Color.RED;
        myShapeList = new LinkedList<>();
        myFill = true;
        myDrawColor = Color.RED;
        myFillColor = Color.RED;
       

    }


    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
       
        // Iterate through the deque in reverse order
        // to draw the previous shapes in the correct order:
        final Iterator<PaintedShape> it = myShapeList.descendingIterator();
        while (it.hasNext()) {
//            System.out.println(myShapeList.size());
            final PaintedShape current = it.next();
//            System.out.println(current);
            g2d.setStroke(current.getStroke());
            g2d.setPaint(current.getDrawColor());
            final Shape s = current.getShape();
            
            g2d.draw(s);
            
// I don't know what the below section does. 
// When testing it, I noticed we weren't getting into the "Else" statement.
// So I put "g2d.draw(s)" above instead.   
//            if ((!(s instanceof Path2D)) && current.isFilled()) {
//                g2d.fill(current.getShape());
//            } else { 
//                g2d.draw(s);
//            }
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
        System.out.println(myCurrentTool.getStartPoint());
        
        
// I don't know what the below section does. 
// When testing it, I noticed we weren't getting into the "Else" statement.
// So I put "g2d.draw(s)" above instead.       
//        if ((!(s instanceof Path2D)) && myFill ) {
//            g2d.fill(myCurrentTool.getShape());
//        } else {
//            g2d.draw(s);
//        }
    }
    
    
    /**
     * Clears the painting surface.
     */
    public void clear() {
        myShapeList.clear();
        firePropertyChange("clear", null, false);
        repaint();
    }
    
    /**
     *  Turns the grid on or off depending on the parameter passed in.
     *  
     *  @param theGrid true to turn the grid on; false to turn the grid off
     */
    public void setFill(final boolean theGrid) {
        myFill = theGrid;
        repaint();
    }
    
    /**
     * Sets the current paint tool.
     * 
     * @param theTool the current tool to set
     */
    public void setCurrentTool(final PaintTool theTool) {
        if (theTool == null) {
            myCurrentTool = theTool;//myToolActions.get(0).getMyTool();
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
    public void setCurrentColor(final Color theColor) {
        myDrawColor = theColor;
    }
    
    
    /**
     * Sets the current fill color.
     * 
     * @param theColor the paint color to use
     */
    public void setCurrentColor2(final Color theColor) {
        myFillColor = theColor;
    }
    
    /**
     * Returns the current draw color.
     * 
     * @return the current draw color
     */
    public Color getCurrentDrawColor() {
        return myDrawColor;
    }
    
    
    /**
     * Returns the current fill color.
     * 
     * @return the current fill color
     */
    public Color getCurrentFillColor() {
        return myFillColor;
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
     * @return the tool_actions
     *
    public final List<ToolAction> getToolActions() {
        return myToolActions;
    }*/
    
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
                   // if (theEvent.getButton() == 1) {
                        myCurrentColor = myDrawColor;
                 //   } else {
                  //      myCurrentColor = myFillColor;}
                  }
            }
                myCurrentTool.setStartPoint(theEvent.getPoint());
                repaint(); 
        }
        
    
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
          //  if (myCurrentWidth > 0) {
                myCurrentTool.setNextPoint(theEvent.getPoint());
                repaint();
           // }
        }
    
        @Override
        public void mouseReleased(final MouseEvent theEvent) {            
            // No need to repaint() here. The Shape is already drawn.
            
            if (myCurrentWidth > 0) {
                myCurrentTool.setNextPoint(theEvent.getPoint());
                myShapeList.push(new PaintedShape(myCurrentTool.getShape(),
                                                  myCurrentColor,
                                                  myCurrentColor,
                                                  myCurrentWidth,
                                                  myFill));
                
                firePropertyChange("clear", null, true);
                myCurrentTool.reset();
                repaint();
            }
        }
    }
}
