/* BobFrog.java
 * Written by Ethan Liao
 */
import javax.swing.JPanel;

public class BobFrog extends ImageHolder
{
    public BobFrog(JPanel panelIn, int xIn, int yIn)
    {
        super(1, panelIn, "Bob", xIn, yIn);
        setFrameBounds(0, 0);
        getImages();
    }

    public void getCoords1()
    {
        //System.out.println(getCoords()[0]);
        //return getCoords();
    }
}
