/* BobFrog.java
 * Written by Ethan Liao
 */

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BobFrog extends ImageHolder implements MouseMotionListener, MouseListener
{
    public BobFrog(JPanel panelIn)
    {
        super(1, panelIn, "Bob", 600, 0);
        setFrameBounds(0, 0);
        getImages();
        addMouseMotionListener(this);

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

    public void mouseClicked(MouseEvent evt)
    {

    }

    public void mousePressed(MouseEvent evt)
    {

    }

    public void mouseReleased(MouseEvent evt)
    {

    }

    public void mouseEntered(MouseEvent evt)
    {

    }

    public void mouseExited(MouseEvent evt)
    {
        
    }
}
