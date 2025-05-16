/* Ethan Liao
 * LilyPad.java
 */
import javax.swing.JPanel;

public class LilyPad extends ImageHolder
{
    public LilyPad(JPanel panelIn, int xIn, int yIn)
    {
        super(1, panelIn, "LilyPad", xIn, yIn);
        setFrameBounds(0, 0);
        getImages();
    }
}
