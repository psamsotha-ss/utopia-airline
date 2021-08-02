package com.ss.utopia.console;

import org.junit.Test;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BLUE_TEXT;
import static org.assertj.core.api.Assertions.assertThat;

public class ColorUtilTest {

    @Test
    public void testGetColoredMessage() {
        String blueText = ColorUtil.getColoredMessage("Testing", Color.BLUE);
        assertThat(blueText).isEqualTo(colorize("Testing", BLUE_TEXT()));
    }
}
