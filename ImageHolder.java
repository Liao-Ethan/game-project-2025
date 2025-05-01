
// Lorence Tsai
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;
import javax.swing.JPanel;

import java.awt.Graphics;

class ImageHolder {
    private Image[] img;
    private String name;
    private Timer timer;
    private int idx;
    private int start, end;
    private JPanel panel;
    private int coords[];
    public ImageHolder(int framesIn, JPanel panelIn, String nameIn, int xIn, int yIn) {
        img = new Image[framesIn];
        panel = panelIn;
        idx = 0;
        start = 0;
        end = 0;
        name = "assets/img/" + nameIn;
        TimerHandler tHandler = new TimerHandler();
        timer = new Timer(50, tHandler);
        coords = new int[]{xIn, yIn};
        getImages();
        timer.start();
    }

    public void getImages()
    {
        for(int i = 0; i < img.length; i++)
        {
            try
            {
                img[i] = ImageIO.read(new File("" + name + (i) + ".png"));
            }
            catch (IOException e)
            {
                System.err.println("\n\nImage " + name + (i) + ".png does not exist\n\n");
                // e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public void setFrameBounds(int startIn, int endIn)
    {
        start = startIn;
        end = endIn;
    }

    class TimerHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            idx++;
            if (idx >= end)
            {
                idx = start;
            }
        }
    }

    public void drawImage(Graphics g)
    {
        g.drawImage(img[idx], coords[0], coords[1], panel);
    }

    public void setCoords(int[] coordsIn)
    {
        coords = coordsIn;
    }

    public JPanel getParentPanel()
    {
        return panel;
    }
}