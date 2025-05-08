/* Ethan Liao
 * LilyPad.java
 */
import java.awt.Font;
import java.awt.Graphics;

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
    }

    public void drawText(Graphics g)
    {
        g.setFont(new Font("serif", Font.PLAIN, 32));
        g.drawString(text, 0, 32);
    }
}
