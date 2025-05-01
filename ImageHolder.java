// Lorence Tsai

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

import java.awt.Graphics;

class ImageHolder {
    private Image[] img;
    private String name;
    private Timer timer;
    private int idx;
    private int start, end;

    public ImageHolder(int framesIn) {
        img = new Image[framesIn];
        idx = 0;
        start = 0;
        end = 0;
        TimerHandler tHandler = new TimerHandler();
        timer = new Timer(50, tHandler);
        getImages();
        timer.start();
    }

    public void getImages()
    {
        for(int i = 0; i < img.length; i++)
        {
            try
            {
                img[i] = ImageIO.read(new File("" + name + i));
            }
            catch (IOException e)
            {
                System.err.println("\n\nImage " + name + i + " does not exist\n\n");
                e.printStackTrace();
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
}