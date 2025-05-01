/* BobFrog.java
 * Written by Ethan Liao
 */
import javax.swing.JPanel;

public class BobFrog extends ImageHolder
{
    public BobFrog(JPanel panelIn)
    {
        super(1, panelIn, "Bob", 600, 0);
        setFrameBounds(0, 0);
        getImages();
    }
}
