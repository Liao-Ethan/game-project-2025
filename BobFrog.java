/* BobFrog.java
 * Written by Ethan Liao
 */

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BobFrog extends ImageHolder implements MouseMotionListener
{
    public BobFrog(JPanel panelIn)
    {
        super(1, panelIn, "Bob", 600, 0);
        setFrameBounds(0, 0);
        getImages();
    }


    public void mouseMoved(MouseEvent evt)
    {
        System.out.println("Banana");
    }

    public void mouseDragged(MouseEvent evt)
    {
        setCoords(new int[]{evt.getX(), evt.getY()});
        getParentPanel().repaint();
        System.out.println("Aple");
    }

}
