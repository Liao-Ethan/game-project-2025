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

class ImageHolder extends JPanel
{
    private Image[] img; // array of images
    private String name; // basic name of the collective images (ie. "Bob" for frames "Bob1.png", "Bob2.png", etc)
    private Timer timer; // Timer for animation
    private int idx; // Frame idx for animation
    private int start, end; // Start and end frame for animation (customization purposes)
    private JPanel panel; // Parent panel, what we are drawing on
    private int coords[]; // Coordinates
    private int imgWidth, imgHeight;
    public ImageHolder(int framesIn, JPanel panelIn, String nameIn, int xIn, int yIn) 
    {
        img = new Image[framesIn];
        panel = panelIn;
        idx = 0;
        start = 0;
        end = 0;
        name = "assets/img/" + nameIn;
        TimerHandler tHandler = new TimerHandler();
        timer = new Timer(500, tHandler);
        coords = new int[]{xIn, yIn};
        getImages();
        timer.start();
        imgWidth = 0;
        imgHeight = 0;
    }

    // Get all the images and add them to the array img
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
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    // Set bounds so only a set amount of frames are animated
    public void setFrameBounds(int startIn, int endIn)
    {
        start = startIn;
        end = endIn;
        if (idx < start || idx > end)
        {
            idx = start;
        }

        if (end - start <=0)
        {
            killTimer();
        }
    }

    // Handler for timer, deals with changing the frame
    class TimerHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            idx++;
            if (idx >= end)
            {
                idx = start;
            }
            panel.repaint();
        }
    }

    // Draws the image, used in paintComponent
    public void drawImage(Graphics g)
    {
        g.drawImage(img[idx], coords[0], coords[1], panel);
    }

    // Setting the coordinates of the instance
    public void setCoords(int[] coordsIn)
    {
        coords = coordsIn;
        if (coords[0] <= 0)
        {
            coords[0] = 0;
        }
        else if (coords[0] >= panel.getWidth() - imgWidth)
        {
            coords[0] = panel.getWidth() - imgWidth;
        }

        if (coords[1] <= 0)
        {
            coords[1] = 0;
        }
        else if (coords[1] >= panel.getHeight() - imgHeight)
        {
            coords[1] = panel.getHeight() - imgHeight;
        }
    }

    // Used to manually hardcode image dimensions (used for the program, sometimes not the actual dimensions)
    public void resetImgDimensions(int newWidth, int newHeight)
    {
        imgWidth = newWidth;
        imgHeight = newHeight;
    }

    // End the timer
    public void killTimer()
    {
        timer.stop();
    }

    // Getters for coordinates.
    public int getX()
    {
        return coords[0];
    }

    public int getY()
    {
        return coords[1];
    }

    // Getting the parent panel
    public JPanel getParentPanel()
    {
        return panel;
    }

    // Getter setters for image dimensions
    public int getWidth()
    {
        return imgWidth;
    }

    public int getHeight()
    {
        return imgHeight;
    }

    // Get frame idx (animation)
    public int getIdx()
    {
        return idx;
    }
}
