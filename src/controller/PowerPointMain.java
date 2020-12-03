package controller;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.PowerPaintGUI;

public class PowerPointMain {
    
    /**private constructor to inhibit instantiation. */
    private PowerPointMain() {
        // Do not instantiate objects of this class.
        throw new IllegalStateException();
    }
    
    /**
     * Set the look and feel for the GUI program.
     */
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            /* Turn off metal's use of bold fonts. */
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        } 
    }
    
    /**
     * Creates and display PowerPaint.
     * 
     * @param theArgs Command line arguments (ignored).
     */
    public static void main(final String... theArgs) {
        
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setLookAndFeel();
                new PowerPaintGUI();
            }
        });
    }
}
