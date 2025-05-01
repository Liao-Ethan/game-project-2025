/*Ethan Liao and Lorence Tsai
 * GamePanel.java
 * Done by Lorence Tsai
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class GamePanel extends BasePanel
{
    private BobHolder bh5;
    private JButton submit;
    private Paint paint;
    private JPanel correctPanel;
    private JLabel correctLabel;
    public GamePanel(BobHolder bh5In)
    {
        super(bh5In, "game");
        bh5 = bh5In;
        submit = new JButton("Submit");
        paint = new Paint();
        add(paint, BorderLayout.CENTER);

        correctLabel = new JLabel();

        correctPanel = new JPanel();

        JButton home = new JButton("home");
        HomeButtonListener hbl = new HomeButtonListener();

        home.addActionListener(hbl);
        submit.addActionListener(hbl);

        correctPanel.setBackground(Color.WHITE);
        correctLabel.setBackground(Color.WHITE);
        correctPanel.add(correctLabel);
        getPanel("right").add(home);
        getPanel("right").add(submit);
        getPanel("right").add(correctPanel);
        paint.repaint();
    }

    public void checkCorrect()
    {
        int xCoord = paint.getBobX();
        int yCoord = paint.getBobY();
        Font font = new Font("Serif", Font.PLAIN, 25);
        correctLabel.setFont(font);
        correctLabel.setOpaque(true);

        if (xCoord >= 50 && xCoord <= 290 && yCoord >= 140 && yCoord <= 380)
        {
            System.out.println("Correct");
            correctLabel.setForeground(Color.GREEN);
            correctLabel.setText("Correct");
        }
        else
        {
            System.out.println("Incorrect");
            correctLabel.setForeground(Color.RED);
            correctLabel.setText("Incorrect");
        }

        paint.setXFrog(600);
        paint.setYFrog(0);
        //correctPanel.add(correctLabel);
        paint.repaint();
    }

    class HomeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equals("home"))
            {
                bh5.getCards().show(bh5, "home");
                correctLabel.setForeground(Color.WHITE);
            }
            else if (command.equals("Submit"))
            {
                checkCorrect();
            }
        }
    }
}

class Paint extends JPanel implements MouseMotionListener, MouseListener
{
    private LilyPad[] pads;
    private BobFrog bob;
    private int xFrog;
    private int yFrog;

    public Paint()
    {
        pads = new LilyPad[4];

        xFrog = 600;
        yFrog = 0;

        bob = new BobFrog(this, xFrog, yFrog);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void setXFrog(int xFrog)
    {
        this.xFrog = xFrog;
        repaint();
    }

    public void setYFrog(int yFrog)
    {
        this.yFrog = yFrog;
        repaint();
    }

    public int getYFrog()
    {
        return yFrog;
    }

    public int getXFrog()
    {
        return xFrog;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //drawRects(g);
        bob = new BobFrog(this, xFrog, yFrog);

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

    public void mouseMoved(MouseEvent evt)
    {
    }

    public void mouseDragged(MouseEvent evt)
    {
        //bob.setCoords(new int[]{evt.getX(), evt.getY()});
        xFrog = evt.getX();
        yFrog = evt.getY();
        repaint();
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
    {}

    public int getBobX()
    {
        return bob.getX();
    }

    public int getBobY()
    {
        return bob.getY();
    }
}