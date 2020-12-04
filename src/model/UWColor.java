package model;

import java.awt.Color;

public class UWColor {
    /** The hex RGB value for UW Purple. */
    private static final String UW_PURPLE = "#4b2e83";
    
    /** The hex RGB value for UW Gold. */
    private static final String UW_GOLD = "#b7a57a";
    
    public UWColor() {
        
    }
    
    public Color getPurple() {
        return Color.decode(UW_PURPLE);
    }
    
    public Color getGold() {
        return Color.decode(UW_GOLD);
    }
}
