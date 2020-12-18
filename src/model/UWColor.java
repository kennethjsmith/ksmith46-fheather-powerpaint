package model;

import java.awt.Color;

/**
 * UWT TCSS 305 Section C Programming Practicum - Prof. Tom Capaul
 * 
 * This class holds the color values used by Power Paint on startup.
 * 
 * @authors Heather Finch (fheather) and Ken Smith (ksmith46)
 * @version 12/16/2020
 */
public final class UWColor {
    
    /** The hex RGB value for UW Purple. */
    private static final String UW_PURPLE = "#4b2e83";
    
    /** The hex RGB value for UW Gold. */
    private static final String UW_GOLD = "#b7a57a";
    
    /**
     * Empty constructor to prevent instantiation.
     */
    private UWColor() {
    }
    
    /**
     * Returns a default purple color.
     * 
     * @return Color.decode(UW_PURPLE) the color for UW purple
     */
    public static Color getPurple() {
        return Color.decode(UW_PURPLE);
    }
    
    /**
     * Returns a default gold color.
     * 
     * @return Color.decode(UW_GOLD) the color for UW gold
     */
    public static Color getGold() {
        return Color.decode(UW_GOLD);
    }
}
