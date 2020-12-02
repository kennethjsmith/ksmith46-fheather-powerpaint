package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

import controller.tools.LineTool;

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

    // Should this be private?
    public final LineTool myLineTool = new LineTool(); 
    
    private final PowerPaintToolBar myToolBar = new PowerPaintToolBar();
    
    /**
     * Keeps track of how many times the user has clicked. 
     * Used in MouseListener.
     */
    private int clickCounter;
    
    // Constructor

    /**
     * Constructs a new general path panel.
     */
    public PaintPanel() {
        super();       
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(BACKGROUND_COLOR);
        addMouseListener(new MyMouseListener());
        clickCounter = 0;
        add(myToolBar, BorderLayout.PAGE_END);
    }

    /**
     * Paints the current path.
     * 
     * @param theGraphics The graphics context to use for painting.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setPaint(FOREGROUND_COLOR);
        g2d.setStroke(new BasicStroke(LINE_WIDTH));
        g2d.draw(myLineTool.getShape());

    }
    
 // Inner Class

    /**
     * Listens for mouse clicks, to draw on our panel.
     */
    private class MyMouseListener extends MouseAdapter {
        /**
         * Handles a click event.
         * 
         * @param theEvent The event.
         */
        @Override
        public void mouseClicked(final MouseEvent theEvent) {
            clickCounter++;            
            Point newPoint = new Point (theEvent.getPoint());
            
            // If the user has clicked an odd number of times, set the startPoint for the line
            if(clickCounter % 2 == 1) {
                myLineTool.setStartPoint(newPoint);
            // Otherwise the user has clicked an even number of times, finish the line
            }else {
                myLineTool.setNextPoint(newPoint);
            }
            repaint();
        }
    }
}
