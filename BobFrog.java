/* BobFrog.java
 * Written by Ethan Liao
 */
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BobFrog extends ImageHolder implements MouseListener
{
    public BobFrog(JPanel panelIn, int xIn, int yIn) // used in polymorphism
    // calls methods in ImageHolder when needed
    {
        super(6, panelIn, "Bob", xIn, yIn);
        setFrameBounds(0, 3);
        getImages();
        
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent evt) {}

    public void mouseEntered(MouseEvent evt) {}

    public void mouseExited(MouseEvent evt) {}

    public void mousePressed(MouseEvent evt) {}

    public void mouseReleased(MouseEvent evt) {}
}
