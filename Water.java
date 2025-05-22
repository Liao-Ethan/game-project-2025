/* Water.java
 * 
 */
import javax.swing.JPanel;

public class Water extends ImageHolder
{
    public Water(JPanel panelIn)
    {
        // Sets up Water class
        super(1, panelIn, "waterbg", 0, 0);
        setFrameBounds(0, 0);
        getImages();
    }
}