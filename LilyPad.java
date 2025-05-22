/* Ethan Liao
 * LilyPad.java
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

public class LilyPad extends ImageHolder
{
    private String text; // Stores the text that's drawn on the lily pad
    private float fontSize; // Stores the size of font for the text of the lily pad
    public LilyPad(JPanel panelIn, int xIn, int yIn)
    {
        // Set up lily pad as inherited from ImageHolder
        super(1, panelIn, "LilyPad", xIn, yIn);
        setFrameBounds(0, 0);
        getImages();
        text = "";
        fontSize = 50f;
    }

    // Sets the variable text
    public void setWord(String question)
    {
        text = question.substring(0, question.indexOf(" "));
    }

    // Loading the font (Credit to Cory from StackOverflow)
    public Font loadFont()
    {
        Font chineseFont = null;
        //String fileName = "assets/fonts/YRDZST Medium/YRDZST Medium.ttf";
        String fileName = "assets/fonts/chinese.msyh.ttf";
        try 
        {
            //create the font to use. Size changes depending on font size
            chineseFont = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(fontSize);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(chineseFont);
        } 
        catch (IOException e) // Font file doesn't exist
        {
            System.err.println("File \"YRDZST Medium.ttf\" does not exist.");
            e.printStackTrace();
            System.exit(2);
        } 
        catch(FontFormatException e) // Font file cannot be used
        {
            System.err.println("File \"YRDZST Medium.ttf\" does not exist.");
            e.printStackTrace();
            System.exit(3);
        }

        return chineseFont;
    }

    // Draws the text onto the lily pad. text and fontSize is modified here.
    public void drawText(Graphics g, String text, float fontSizeIn)
    {
        fontSize = fontSizeIn;
        g.setFont(loadFont());
        g.setColor(Color.WHITE);
        g.drawString(text, getX() + 100, getY() + 130);
    }

}