package com.ss.utopia.console;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BLUE_TEXT;
import static com.diogonunes.jcolor.Attribute.BRIGHT_BLUE_TEXT;
import static com.diogonunes.jcolor.Attribute.BRIGHT_GREEN_TEXT;
import static com.diogonunes.jcolor.Attribute.BRIGHT_RED_TEXT;
import static com.diogonunes.jcolor.Attribute.BRIGHT_YELLOW_TEXT;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;
import static com.diogonunes.jcolor.Attribute.RED_TEXT;
import static com.diogonunes.jcolor.Attribute.YELLOW_TEXT;

/**
 * Utility class for working with colors
 */
public class ColorUtil {

    private ColorUtil() {}

    /**
     * Get a colorized message
     * @param message the message to colorize
     * @param color the color to make the message
     * @return the colorized message
     */
    public static String getColoredMessage(String message, Color color) {
        switch (color) {
            case BLUE: return colorize(message, BLUE_TEXT());
            case BRIGHT_BLUE: return colorize(message, BRIGHT_BLUE_TEXT());
            case GREEN: return colorize(message, GREEN_TEXT());
            case BRIGHT_GREEN: return colorize(message, BRIGHT_GREEN_TEXT());
            case RED: return colorize(message, RED_TEXT());
            case BRIGHT_RED: return colorize(message, BRIGHT_RED_TEXT());
            case YELLOW: return colorize(message, YELLOW_TEXT());
            case BRIGHT_YELLOW: return colorize(message, BRIGHT_YELLOW_TEXT());
            default: throw new UnsupportedOperationException("Color " + color + " not supported.");
        }
    }
}
