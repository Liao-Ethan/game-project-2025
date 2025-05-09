/* GamePanel.java
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
    private FileReader fReader;

    private int whichPad;
    private int level;
    private int idx;

    private String[] questions;

    public GamePanel(BobHolder bh5In)
    {
        super(bh5In, "game");
        bh5 = bh5In;
        submit = new JButton("Submit");
        paint = new Paint(this);
        add(paint, BorderLayout.CENTER);

        correctLabel = new JLabel(); // label on the side showing if correct or not

        correctPanel = new JPanel(); // panel that should hold the label

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

        fReader = new FileReader("words");
        level = 1; // initialize the level
        questions = new String[fReader.getLevelLengths(level)];
        questions = fReader.shuffle(level);
        whichPad = -1;
        idx = 0;
        setQuestion(questions[idx]);
    }

    public void checkCorrect()
    {
        int xCoord = paint.getBobX(); // get coords of the frog(aka bob)
        int yCoord = paint.getBobY();
        Font font = new Font("Serif", Font.PLAIN, 25);
        correctLabel.setFont(font);
        correctLabel.setOpaque(true);

        // check which lilypad the frog is on
        boolean isFirstPad = (xCoord >= 50 && xCoord <= 290 && yCoord >= 140 && yCoord <= 380);
        boolean isSecondPad = (xCoord >= 410 && xCoord <= 620 && yCoord >= 140 && yCoord <= 380);
        boolean isThirdPad = (xCoord >= 50 && xCoord <= 290 && yCoord >= 380 && yCoord <= 620);
        boolean isFourthPad = (xCoord >= 410 && xCoord <= 620 && yCoord >= 380 && yCoord <= 620);

        if (isFirstPad && whichPad == 1) // if the frog is on the first pad, it is correct(for now)
        {
            correctLabel.setForeground(Color.GREEN);
            correctLabel.setText("Correct");
        }
        else if (isSecondPad && whichPad == 2)
        {
            correctLabel.setForeground(Color.GREEN);
            correctLabel.setText("Correct");
        }
        else if (isThirdPad && whichPad == 3)
        {
            correctLabel.setForeground(Color.GREEN);
            correctLabel.setText("Correct");
        }
        else if (isFourthPad && whichPad == 4)
        {
            correctLabel.setForeground(Color.GREEN);
            correctLabel.setText("Correct");
        }
        else if (!isFirstPad && !isSecondPad && !isThirdPad && !isFourthPad) // if the frog isn't on any pad
        // give the user a second chance
        {
            correctLabel.setForeground(Color.RED);
            correctLabel.setText("Try Again");
        }
        else
        {
            correctLabel.setForeground(Color.RED);
            correctLabel.setText("Incorrect");
        }
        paint.resetBob(); // reset frog(bob) to original location
        paint.repaint();
    }

    class HomeButtonListener implements ActionListener // button handler to check which button is pressed
    // and perform corresponding action
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equals("home"))
            {
                paint.resetBob();
                paint.repaint();
                bh5.getCards().show(bh5, "home");
                correctLabel.setForeground(Color.WHITE);
            }
            else if (command.equals("Submit"))
            {
                checkCorrect();
            }
        }
    }

    public String[] getQuestions()
    {
        return questions;
    }

    public int getIdx()
    {
        return idx;
    }

    public void setQuestion(String question)
    {
        paint.setWhichPad((int)(Math.random() * 4));
    }
}

class Paint extends JPanel implements MouseMotionListener, MouseListener
{
    private LilyPad[] pads;
    private BobFrog bob;
    private int xFrog;
    private int yFrog;
    private int whichPad;

    private GamePanel gp;


    public Paint(GamePanel gpIn)
    {
        gp = gpIn;
        pads = new LilyPad[4];  // Initialize the pads array with 4 elements
        whichPad = 0;
        // Initialize each LilyPad object
        for (int i = 0; i < pads.length; i++) {
            pads[i] = new LilyPad(this, 50 + (i % 2) * 360, 140 + (i / 2) * 240);
        }

        xFrog = 600;
        yFrog = 0;
        
        bob = new BobFrog(this, xFrog, yFrog);
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    /*public Paint()
    {
        pads = new LilyPad[4];

        xFrog = 600;
        yFrog = 0;

        bob = new BobFrog(this, xFrog, yFrog);
        addMouseMotionListener(this);
        addMouseListener(this);
    }*/

    public void resetBob()
    {
        bob.killTimer();
        bob = new BobFrog(this, 600, 0);
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

    public void setWhichPad(int newPad)
    {
        whichPad = newPad;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //drawRects(g);
        // bob = new BobFrog(this, xFrog, yFrog);

        for (int i=0; i<2; i++)
        {
            for (int j=0; j<2; j++)
            {
                pads[i+j] = new LilyPad(this, 50 + (j*360), 140 + (i*240));
                pads[i+j].drawImage(g);
                // pads[i+j].drawText(g, );
                if (i+j == whichPad)
                {
                    pads[i+j].drawText(g, gp.getQuestions()[gp.getIdx()]);
                }
                else
                {
                    int randomWordIdx = (int)(Math.random() * gp.getQuestions().length);
                    if (!gp.getQuestions()[randomWordIdx].equals(gp.getQuestions()[gp.getIdx()]))
                    {
                        pads[i+j].drawText(g, gp.getQuestions()[randomWordIdx]);
                    }
                    // pads[i+j].drawText(g, TOOL_TIP_TEXT_KEY);
                }
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
        bob.setCoords(new int[]{evt.getX(), evt.getY()});
        xFrog = evt.getX();
        yFrog = evt.getY();
        repaint();
    }

    public void mousePressed(MouseEvent evt)
    {
        bob.setFrameBounds(5, 6);
    }

    public void mouseClicked(MouseEvent evt)
    {}

    public void mouseReleased(MouseEvent evt)
    {
        bob.setFrameBounds(0, 3);
    }

    public void mouseEntered(MouseEvent evt)
    {}

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

    public LilyPad[] getPads()
    {
        return pads;
    }
}