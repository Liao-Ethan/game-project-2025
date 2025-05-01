import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BobFrog extends ImageHolder implements MouseMotionListener
{
    public BobFrog(JPanel panelIn)
    {
        super(1, panelIn, "Bob", 0, 0);
    }


    public void mouseMoved(MouseEvent evt)
    {

    }

    public void mouseDragged(MouseEvent evt)
    {
        setCoords(new int[]{evt.getX(), evt.getY()});
        getParentPanel().repaint();
    }

}
