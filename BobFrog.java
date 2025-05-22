/* BobFrog.java
 * Written by Ethan Liao
 */
import javax.swing.JPanel;

public class BobFrog extends ImageHolder
{
    public BobFrog(JPanel panelIn, int xIn, int yIn) // used in polymorphism
    // calls methods in ImageHolder when needed
    {
        super(6, panelIn, "Bob", xIn, yIn);
        setFrameBounds(0, 3);
        getImages();
        resetImgDimensions(64, 64);
    }
}
