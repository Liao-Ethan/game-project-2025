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
    private String text;
    public LilyPad(JPanel panelIn, int xIn, int yIn)
    {
        super(1, panelIn, "LilyPad", xIn, yIn);
        setFrameBounds(0, 0);
        getImages();
        text = "";
    }

    public void setWord(String question)
    {
        text = question.substring(0, question.indexOf(" "));
        // System.out.println("Text = " + text);
    }

    public Font loadFont()
    {
        Font chineseFont = null;
        String fileName = "assets/fonts/YRDZST Medium/YRDZST Medium.ttf";
        try 
        {
            //create the font to use. Specify the size!
            chineseFont = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(36f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(chineseFont);
        } 
        catch (IOException e)
        {
            System.err.println("File \"YRDZST Medium.ttf\" does not exist.");
            e.printStackTrace();
            System.exit(2);
        } 
        catch(FontFormatException e) {
            System.err.println("File \"YRDZST Medium.ttf\" does not exist.");
            e.printStackTrace();
            System.exit(3);
        }

        return chineseFont;
    }

    public void drawText(Graphics g, String text)
    {
        g.setFont(new Font("Serif", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString(text, getX(), getY() + 150);
    }

}