import java.awt.Color;
import java.awt.Graphics;

class GamePanel extends BasePanel
{
    public GamePanel(BobHolder bh5In)
    {
        super(bh5In, "Game");
        

        
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawRects(g);
    }

    public void drawRects(Graphics g)
    {
        g.setColor(Color.PINK);
        g.fillRect(250, 200, 640, 260);
        g.fillRect(640, 200, 640, 260);
        g.fillRect(250, 460, 640, 260);
        g.fillRect(640, 460, 640, 260);
    }
}
