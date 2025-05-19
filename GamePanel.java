/* GamePanel.java
 * Done by Lorence Tsai
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

class GamePanel extends BasePanel
{
    private BobHolder bh5;
    private JButton submit;
    private Paint paint;
    private JPanel correctPanel;
    private JLabel correctLabel;
    private FileReader fReader;
    private JTextArea questionLabel; // Must be field variable, as it exists in another panel and text will be changed frequently

    private int whichPad;
    private int level; // the level selected on previous page
    private int idx;
    private int correctIdx;

    private String[] questions;

    public GamePanel(BobHolder bh5In)
    {
        super(bh5In, "game");
        bh5 = bh5In;
        submit = new JButton("Submit");
        paint = new Paint(this);
        add(paint, BorderLayout.CENTER);
        
        Font buttonFont = new Font("Dialog", Font.PLAIN, 30); // fonts for buttons
        Font labelFont = new Font("Serif", Font.PLAIN, 36); // fonts for labels

        correctLabel = new JLabel(); // label on the side showing if correct or not

        correctPanel = new JPanel(); // panel that should hold the label

        JButton home = new JButton("Home");
        HomeButtonListener hbl = new HomeButtonListener();
        
        JPanel homeButtonPanel = new JPanel(); // JPanel where buttons are placed
        homeButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 75));
        homeButtonPanel.setBackground(Color.BLUE);
        
        JPanel secondButtonPanel = new JPanel();
        secondButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 75));
        secondButtonPanel.setBackground(Color.BLUE);
        
        home.setFont(buttonFont); // setting fonts for the buttons
        submit.setFont(buttonFont);
        
        homeButtonPanel.add(home);
        secondButtonPanel.add(submit);

        questionLabel = new JTextArea();
        questionLabel.setFont(labelFont);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBackground(Color.BLUE);
        questionLabel.setEditable(false);
        questionLabel.setLineWrap(true);
        questionLabel.setWrapStyleWord(true);
        getPanel("left").add(questionLabel);

        home.addActionListener(hbl);
        submit.addActionListener(hbl);

        correctPanel.setBackground(Color.WHITE);
        correctLabel.setBackground(Color.WHITE);
        correctPanel.add(correctLabel);
        getPanel("right").add(homeButtonPanel);
        getPanel("right").add(secondButtonPanel);
        getPanel("right").add(correctPanel);
        paint.repaint();

        fReader = new FileReader("words");
        
        newQuestions(1);
        whichPad = -1;
        proceedQuestion(false);
    }

    public void newQuestions(int levelIn)
    {
        level = levelIn; // initialize the level
        idx = 0;
        correctIdx = 0;
        questions = new String[fReader.getLevelLengths(level)];
        questions = fReader.shuffle(level);
    }

    public Font loadFont()
    {
        Font chineseFont = null;
        //String fileName = "assets/fonts/YRDZST Medium/YRDZST Medium.ttf";
        String fileName = "assets/fonts/chinese.msyh.ttf";
        try 
        {
            //create the font to use. Specify the size!
            chineseFont = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(chineseFont);
        } 
        catch (IOException e)
        {
            System.err.println("File \"YRDZST Medium.ttf\" does not exist.");
            e.printStackTrace();
            System.exit(2);
        } 
        catch(FontFormatException e) 
        {
            System.err.println("File \"YRDZST Medium.ttf\" does not exist.");
            e.printStackTrace();
            System.exit(3);
        }

        return chineseFont;
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
            isCorrect();
        }
        else if (isSecondPad && whichPad == 2)
        {
            isCorrect();
        }
        else if (isThirdPad && whichPad == 3)
        {
            isCorrect();
        }
        else if (isFourthPad && whichPad == 4)
        {
            isCorrect();
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
            proceedQuestion(true);
        }
        paint.resetBob(); // reset frog(bob) to original location
        paint.repaint();
    }

    public void isCorrect()
    {
        correctLabel.setForeground(Color.GREEN);
        correctLabel.setText("Correct");
        bh5.getPlayerInfo().switchCorrect(level, questions[idx]);
        correctIdx++;
        proceedQuestion(true);
    }

    class HomeButtonListener implements ActionListener // button handler to check which button is pressed
    // and perform corresponding action
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equals("Home"))
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

    public FileReader getFReader()
    {
        return fReader;
    }

    public String[] getQuestions()
    {
        return questions;
    }

    public int getIdx()
    {
        return idx;
    }

    public void proceedQuestion(boolean nextQ)
    {
        if (nextQ)
        {
            idx++;
        }
        if (idx < questions.length)
        {
            setQuestion(questions[idx]);
        }
        else
        {
            setQuestion("");
            bh5.getPlayerInfo().setScore(level, correctIdx);
        }
    }
    public void setQuestion(String question)
    {
        whichPad = ((int)(Math.random() * 4)) + 1; // 1 to 4
        
        String labelText = "";
        if (idx < questions.length)
        {
            if (bh5.getDef() == false)
            {
                labelText = "Which character means:\n" + question.substring(question.lastIndexOf(" "));
            }
            else
            {
				String firstWord = question.substring(question.indexOf(" ")+1);
                labelText = "What does this mean: " + firstWord.substring(0, firstWord.indexOf(" "));
            }
        }
        else
        {
            labelText = "You have completed level " + level + ". Please return to the home page.";
        }

        labelText += "\n\nScore: " + correctIdx + " / " + idx;

        questionLabel.setText(labelText);

        String[] sentList = new String[4];
        for (int i = 0; i < sentList.length; i++)
        {
            if (idx < questions.length)
            {    
                if (i == whichPad - 1) // put the correct answer at correct index
                {
                    if (bh5.getDef() == false)
                    {
						String questionSub = question.substring(question.indexOf(" ")+ 1);
                        sentList[i] = questionSub.substring(0, questionSub.indexOf(" "));
                        // System.out.println("SentList: " + sentList[i]);
                    }
                    else
                    {
                        sentList[i] = question.substring(question.lastIndexOf(" ") + 1);
                    }
                    // System.out.print(question + " | ");
                }
                else
                {
                    String alt;
                    int randomWordIdx;
                    do {
                        randomWordIdx = (int)(Math.random() * questions.length);
                        alt = questions[randomWordIdx].substring(questions[randomWordIdx].indexOf(" ")+1);
                    } while (alt.equals(question));
                    if (bh5.getDef() == false)
                    {
                        sentList[i] = alt.substring(0, alt.indexOf(" "));
                    }
                    else
                    {
                        sentList[i] = alt.substring(alt.lastIndexOf(" "));
                    }
                    //System.out.print(questions[randomWordIdx] + " | ");
                }
            }
            else
            {
                sentList[i] = "";
            }
        }
        //System.out.println("");
        paint.setQStrings(sentList);
    }

}

class Paint extends JPanel implements MouseMotionListener, MouseListener
{
    private LilyPad[] pads;
    private BobFrog bob;
    private int xFrog;
    private int yFrog;
    private String[] qStrings;

    public Paint(GamePanel gpIn)
    {
        qStrings = new String[4];
        pads = new LilyPad[4];  // Initialize the pads array with 4 elements
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

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                int padIndex = i * 2 + j;
                pads[padIndex] = new LilyPad(this, 50 + (j * 360), 140 + (i * 240));
                pads[padIndex].drawImage(g);
                pads[padIndex].drawText(g, qStrings[padIndex]);
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

    public void setQStrings(String[] newList)
    {
        qStrings = newList;
    }
}
