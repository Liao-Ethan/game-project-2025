/* Water.java
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

public class Water extends ImageHolder
{
    public Water(JPanel panelIn)
    {
        super(1, panelIn, "waterbg", 0, 0);
        setFrameBounds(0, 0);
        getImages();
    }
}