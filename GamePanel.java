/* GamePanel.java
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    private BobHolder bh5; // BobHolder instance
    private Paint paint; // instance of the Paint class, all drawing is done here
    private JPanel correctPanel; // Panel holding correctLabel
    private JLabel correctLabel; // Text label to show if the answer was correct or incorrrect
    private FileReader fReader; // FileReader instance, used to get the random (totalqs) words
    private JTextArea questionLabel; // Must be field variable, as it exists in another panel and text will be changed frequently
    private JSlider fontSlider; // Slider to change the font size of the characters

    private int whichPad; // The index of which lily pad is correct.
    private int level; // the level selected on previous page
    private int idx; // Index of the randomly sorted questions.
    private int correctIdx; // Counts how many questions are correct
    private float fontSize; // Size of the font of the Chinese text
    private int totalQs; // Total amount of questions that will be asked

    private String[] questions; // List of all the questions

    public GamePanel(BobHolder bh5In)
    {
        super(bh5In, "game");
        // Initialization of some field variables
        bh5 = bh5In;
        paint = new Paint(this);
        add(paint, BorderLayout.CENTER);
        totalQs = 10;
        
        Font buttonFont = new Font("Dialog", Font.PLAIN, 30); // fonts for buttons
        Font labelFont = new Font("Serif", Font.PLAIN, 36); // fonts for labels

        // Initializing correctLabel and correctPanel
        correctLabel = new JLabel(); // label on the side showing if correct or not
        correctPanel = new JPanel(); // panel that should hold the label

        // Panel for the home button
        JPanel homeButtonPanel = new JPanel(); // JPanel where buttons are placed
        homeButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 75));
        homeButtonPanel.setBackground(Color.BLUE);

        //Initializing and adding the home buton
        JButton home = new JButton("Home");
        HomeButtonListener hbl = new HomeButtonListener();
        home.setFont(buttonFont); // setting fonts for the buttons
        homeButtonPanel.add(home);
        
        // Panel for the submit button
        JPanel secondButtonPanel = new JPanel();
        secondButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 75));
        secondButtonPanel.setBackground(Color.BLUE);
        
        // Initializing and adding submit button
        JButton submit = new JButton("Submit");
        submit.setFont(buttonFont);
        secondButtonPanel.add(submit);

        // Setting up left panel
        getPanel("left").setLayout(new GridLayout(2, 1));

        // Initializing and adding the question label, which will be part of the JScrollPane labelScroller
        questionLabel = new JTextArea();
        questionLabel.setFont(labelFont);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBackground(Color.BLUE);
        questionLabel.setEditable(false);
        questionLabel.setLineWrap(true);
        questionLabel.setWrapStyleWord(true);

        // ScrollPane for the questionLabel
        JScrollPane labelScroller = new JScrollPane(questionLabel);
        getPanel("left").add(labelScroller);

        // Adding button action listeners
        home.addActionListener(hbl);
        submit.addActionListener(hbl);

        // Setting up panel to show whether the question was answered correctly or not
        correctPanel.setBackground(Color.WHITE);
        correctLabel.setBackground(Color.WHITE);
        correctPanel.add(correctLabel);
        getPanel("right").add(homeButtonPanel);
        getPanel("right").add(secondButtonPanel);
        getPanel("right").add(correctPanel);
        paint.repaint();

        // FileReader instance
        fReader = new FileReader("words");
        
        // Setting up the questions for this level
        newQuestions(1);
        whichPad = -1; // Random init number
        proceedQuestion(false);
        // Font dize
        fontSize = 50f;

        // Setting up and adding the fontSlider, for changing chinese character font size
        SliderHandler sHandler = new SliderHandler();
        fontSlider = new JSlider(10, 100, 50);
        fontSlider.setMajorTickSpacing(10);	// create tick marks on slider every 5 units
        fontSlider.setPaintTicks(true);
        fontSlider.setLabelTable(fontSlider.createStandardLabels(20)); // create labels on tick marks
        fontSlider.setPaintLabels(true);
        fontSlider.addChangeListener(sHandler);
        fontSlider.setBackground(Color.BLUE);
        getPanel("left").add(fontSlider);
    }

    // Resetting all the questions for a new run of the game
    public void newQuestions(int levelIn)
    {
        level = levelIn; // initialize the level
        idx = 0;
        correctIdx = 0;
        questions = new String[fReader.getLevelLengths(level)];
        questions = fReader.shuffle(level);
    }

    // Loading the font
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

    // Checking if the answer was done correctly
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
        else // Boohoo it was all incorrect, come back for another run to get it correct
        {
            correctLabel.setForeground(Color.RED);
            correctLabel.setText("Incorrect");
            proceedQuestion(true);
        }
        paint.resetBob(); // reset frog(bob) to original location
        paint.repaint();
    }

    // It is indeed the correct answer, change the text and color for correctLabel and proceed to the next question
    public void isCorrect()
    {
        correctLabel.setForeground(Color.GREEN);
        correctLabel.setText("Correct");
        bh5.getPlayerInfo().switchCorrect(level, questions[idx]);
        correctIdx++;
        proceedQuestion(true);
    }

    // Handler for the slider
    class SliderHandler implements ChangeListener
    {

        public void stateChanged(ChangeEvent evt) 
        {
            fontSize = (float)fontSlider.getValue();
            paint.repaint();
        }
    }

    // Handler for home button
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

    // Getter setters for some field variables
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

    // Switch to the next question, or prepare for congratulation screen
    public void proceedQuestion(boolean nextQ)
    {
        if (nextQ) // If it isn't just for initializing game panel
        {
            idx++;
        }
        if (idx < totalQs) // If there are still words left
        {
            setQuestion(questions[idx]);
        }
        else // No more words, completion screen
        {
            setQuestion("");
            bh5.getPlayerInfo().setScore();
        }
    }

    // Setting the text for all the lily pads and the question label
    public void setQuestion(String question)
    {
        whichPad = ((int)(Math.random() * 4)) + 1; // 1 to 4, pick a random lily pad to be correct
        question = question.substring(question.indexOf(" ")+1); // Remove the number prefixing the text
        String labelText = "";
        if (bh5.getSimplified() == true) // Remove more text if it is in simplified mode
        {
            question = question.substring(question.indexOf(" ")+1);
        }
        if (idx < totalQs) // Are there words left
        {
            if (bh5.getDef() == false) // For word identification gameplay
            {
                labelText = "Which character means:\n" + question.substring(question.lastIndexOf(" "));
            }
            else // For definition gameplay
            {
                labelText = "What does this mean: " + question.substring(0, question.indexOf(" "));
            }
        }
        else // Level completed
        {
            labelText = "You have completed level " + level + ". Please return to the home page.";
        }

        labelText += "\n\nScore: " + correctIdx + " / " + idx; // Show score
        questionLabel.setText(labelText); // Changing questionLabel

        // Array for all answer choice text
        String[] sentList = new String[4];

        // Decide all questions
        for (int i = 0; i < sentList.length; i++)
        {
            if (idx < totalQs) // There are questions left
            {    
                if (i == whichPad - 1) // put the correct answer at correct index
                {
                    // Do some string manipulation to only show the relevant text, add to sentList array
                    if (bh5.getDef() == false) // Word identification mode
                    {
                        sentList[i] = question.substring(0, question.indexOf(" "));
                    }
                    else
                    {
                        sentList[i] = question.substring(question.lastIndexOf(" ") + 1);
                    }
                }
                else // All incorrect answers
                {
                    String alt;
                    int randomWordIdx;
                    do 
                    {
                        randomWordIdx = (int)(Math.random() * questions.length); // Pick random word (this and next line)
                        alt = questions[randomWordIdx].substring(questions[randomWordIdx].indexOf(" ")+1);
                        if (bh5.getSimplified() == true) // Cut some of the string off to show relevant parts
                        {
                            alt = alt.substring(alt.indexOf(" ") + 1);
                        }

                        for (int j=0; j<sentList.length; j++) // Remove duplicate answer choices
                        {
                            if (!alt.equals(question)) // Used if we need to exit the for loop immediately
                            {
                                if (bh5.getDef() == false) // Check for certain parts of the string depending on game mode
                                {
                                    if (alt.substring(0, alt.indexOf(" ")).equals(sentList[i]))
                                    {
                                        alt = question; // Redos the while loop
                                        j = sentList.length; // Exits for loop, for efficiency
                                    }
                                }
                                else
                                {
                                    if (alt.substring(alt.lastIndexOf(" ")).equals(sentList[i]))
                                    {
                                        alt = question; // Redos while loop
                                        j = sentList.length; // Exits for loop,for efficiency
                                    }
                                }
                            }
                        }
                    } while (alt.equals(question));
                    
                    // Add only relevant parts of the string depending on the game mode
                    if (bh5.getDef() == false)
                    {
                        sentList[i] = alt.substring(0, alt.indexOf(" "));
                    }
                    else
                    {
                        sentList[i] = alt.substring(alt.lastIndexOf(" "));
                    }
                }
            }
            else
            {
                sentList[i] = ""; // No text, used for completion scren
            }
        }
        paint.setQStrings(sentList); // Set the text for every lily pad
    }

    // Getter for the font size, used for for the lily pads
    public float getFontSize()
    {
        return fontSize;
    }

}

class Paint extends JPanel implements MouseMotionListener, MouseListener
{
    private LilyPad[] pads; // Array of all the lily pads
    private BobFrog bob; // Our beloved frog, used to answer questions
    private int xFrog; // X coordinate of bob
    private int yFrog; // Y coordinate of bob
    private String[] qStrings; // String array of all the possible question choices 

    private GamePanel gp; // Instance of game panel, used to get information from the parent
    private Water water;

    public Paint(GamePanel gpIn)
    {
        gp = gpIn;
        qStrings = new String[4];
        pads = new LilyPad[4];  // Initialize the pads array with 4 elements
        // Initialize each LilyPad object
        for (int i = 0; i < pads.length; i++) {
            pads[i] = new LilyPad(this, 50 + (i % 2) * 360, 140 + (i / 2) * 240);
        }

        // Default position of bob
        xFrog = 600;
        yFrog = 0;
        
        // Initializing bob and giving him listeners
        bob = new BobFrog(this, xFrog, yFrog);
        addMouseMotionListener(this);
        addMouseListener(this);

        water = new Water(this);
    }

    // Reset bob to proceed to the next question or when a new game starts
    public void resetBob()
    {
        bob.killTimer();
        bob = new BobFrog(this, 600, 0);
    }

    // Getter setters for bob's coordinates
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

    // The very famous paintComponent, draws everything
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        water.drawImage(g);
        // Draw lily pads, then gives every lily pad its respective answer choice
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                int padIndex = i * 2 + j;
                pads[padIndex] = new LilyPad(this, 50 + (j * 360), 140 + (i * 240));
                pads[padIndex].drawImage(g);
                pads[padIndex].drawText(g, qStrings[padIndex], gp.getFontSize());
            }
        }

        // Draw bob
        bob.drawImage(g);
    }

    // All methods for abstract class MouseMotionListener
    public void mouseMoved(MouseEvent evt)
    {
    }

    // Moves bob to mouse coordinates once the mouse drags it
    public void mouseDragged(MouseEvent evt)
    {
        bob.setCoords(new int[]{evt.getX(), evt.getY()});
        xFrog = evt.getX();
        yFrog = evt.getY();
        repaint();
    }

    // Changes the animation of bob if the mouse is pressed
    public void mousePressed(MouseEvent evt)
    {
        bob.setFrameBounds(5, 6);
    }

    public void mouseClicked(MouseEvent evt)
    {}

    // Changes the animation of bob if the mouse is released
    public void mouseReleased(MouseEvent evt)
    {
        bob.setFrameBounds(0, 3);
    }

    public void mouseEntered(MouseEvent evt)
    {}

    public void mouseExited(MouseEvent evt)
    {}

    // Getters for bob's coordinates
    public int getBobX()
    {
        return bob.getX();
    }

    public int getBobY()
    {
        return bob.getY();
    }

    // Getting all the lilypads from pads
    public LilyPad[] getPads()
    {
        return pads;
    }

    // Setting qStrings so it shows all the new answer choices
    public void setQStrings(String[] newList)
    {
        qStrings = newList;
    }
}
