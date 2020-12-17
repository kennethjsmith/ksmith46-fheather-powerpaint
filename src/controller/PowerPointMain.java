package controller;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.PowerPaintGUI;

/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class contains the main method that runs the Power Paint program.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
final public class PowerPointMain {
    
    /**private constructor to inhibit instantiation. */
    private PowerPointMain() {
        // Do not instantiate objects of this class.
        throw new IllegalStateException();
    }
    
    /**
     * Set the look and feel for the GUI program. Catches multiple types of exceptions.
     */
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            // Turn off metal's use of bold fonts. 
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
     * Calls invokeLater with a anonymous implementer of Runnable parameter. 
     * This inner class has a run method which creates and displays PowerPaintGUI object.
     * 
     * @param args command line arguments (ignored)
     */
    public static void main(final String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setLookAndFeel();
                new PowerPaintGUI();
            }
        });
    }
}
