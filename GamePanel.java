/*Ethan Liao and Lorence Tsai
 * GamePanel.java
 * Done by Lorence Tsai
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GamePanel extends BasePanel
{
    private BobHolder bh5;
    private JButton submit;
    private boolean submitted;
    private Paint paint;
    public GamePanel(BobHolder bh5In)
    {
        super(bh5In, "game");
        bh5 = bh5In;
        submitted = false;
        submit = new JButton("Submit");
        paint = new Paint();
        add(paint, BorderLayout.CENTER);

        JButton home = new JButton("home");
        HomeButtonListener hbl = new HomeButtonListener();

        home.addActionListener(hbl);
        submit.addActionListener(hbl);
        getPanel("right").add(home);
        paint.repaint();
    }

    public void checkCorrect()
    {
        int xCoord = paint.getBobCoords()[0];
        int yCoord = paint.getBobCoords()[1];
        if (xCoord >= 50 && xCoord <= 290 && yCoord >= 140 && yCoord <= 380)
        {
            System.out.println("Correct");
        }
    }

    class HomeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equals("home"))
            {
                bh5.getCards().show(bh5, "home");
            }
            else if (command.equals("Submit"))
            {
                submitted = true;
            }
            else
            {
                submitted = false;
            }
        }
    }
}

class Paint extends JPanel
{
    private LilyPad[] pads;
    private BobFrog bob;

    public Paint()
    {
        pads = new LilyPad[4];
        bob = new BobFrog(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //drawRects(g);

        for (int i=0; i<2; i++)
        {
            for (int j=0; j<2; j++)
            {
                pads[i+j] = new LilyPad(this, 50 + (j*360), 140 + (i*240));
                pads[i+j].drawImage(g);
            }
            
        }

        bob.drawImage(g);
    }

    public void drawRects(Graphics g)
    {
        g.setColor(Color.PINK);
        g.fillRect(0, 140, 380, 240);
        g.fillRect(380, 140, 380, 240);
        g.fillRect(0, 390, 380, 240);
        g.fillRect(380, 390, 380, 240);

        g.setColor(Color.BLACK);
        g.drawRect(0, 140, 380, 240);
        g.drawRect(380, 140, 380, 240);
        g.drawRect(0, 390, 380, 240);
        g.drawRect(380, 390, 380, 240);

        
    }

    public int[] getBobCoords()
    {
        return bob.getCoords();
    }
}