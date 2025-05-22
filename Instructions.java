/* Instructions.java
 * Done by Lorence Tsai
 */

import java.awt.Font;

import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;

class Instructions extends BasePanel
{
    private BobHolder bh4; // BobHolder instance
    public Instructions(BobHolder bhIn4)
    {
        super(bhIn4, "How to Play");
        bh4 = bhIn4;
        
        getPanel("center").setLayout(new FlowLayout(FlowLayout.CENTER, 1280, 10));

        String instructions = "In this game, \"Bob the Frog Explores the Deep Sea of Chinese Literacy\", " +
            "you are playing as the frog, and you will be given 4 lilypads with answer choices on them. " +
            "Your job is to choose the lilypad with the correct answer choice that correctly answers the " +
            "question prompt on the side. There are 3 levels of the game, and each level gets progressively" +
            " harder, with just simple words in the fist level and in the third level, all more complicated " +
            "phrases. You must get 100% accuracy in each level in order to progress to the next level. If you "+
            "need help, you can navigate to the flashcards panel in order to review your vocab. If at any point" +
            "you feel like you want to go back to the home page, you can just press the home button. Al the " +
            "buttons have clear instructions on where they lead, so navigation of the game is really easy and " +
            "straight forward. Have fun exploring Chinese!";
        
        // Add TextArea to display instructions, set it up
        JTextArea instructionsText = new JTextArea(instructions);
        JButton homeButton = new JButton("Home");
        instructionsText.setFont(new Font("dialog", Font.PLAIN, 24));
        instructionsText.setEditable(false);
        instructionsText.setLineWrap(true);
        instructionsText.setWrapStyleWord(true);

        // Set up the home button
        HomeListener hl = new HomeListener();
        homeButton.addActionListener(hl);
        
        // JScrollPane to display the entirety of instructionsText
        JScrollPane scroller = new JScrollPane(instructionsText);
        scroller.setPreferredSize(new Dimension(480, 200));
        getPanel("center").add(scroller);
        getPanel("center").add(homeButton);
    }

    // Button handler
    class HomeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equals("Home"))
            {
                bh4.getCards().show(bh4, "home");
            }
        }
    }
}