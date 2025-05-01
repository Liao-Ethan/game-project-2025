/* BobFrog.java
 * Written by Ethan Liao
 */
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BobFrog extends ImageHolder implements MouseListener
{
    public BobFrog(JPanel panelIn)
    {
        super(6, panelIn, "Bob", 600, 0);
        setFrameBounds(0, 3);
        getImages();
        addMouseListener(this);
        
    }

    public int[] getCoords()
    {
        return getCoords();
    }


    public void mouseClicked(MouseEvent evt) 
    {
        System.out.println("Apple");
        setFrameBounds(4, 5);
    }

    public void mouseEntered(MouseEvent evt) 
    {
    }

    public void mouseExited(MouseEvent e) 
    {
        
    }

    public void mousePressed(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
    }
}
