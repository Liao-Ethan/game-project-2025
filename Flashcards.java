/* Flashcards.java
 * Done by Lorence Tsai
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.processing.Filer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Flashcards extends BasePanel // inherits from BasePanel
{
    private BobHolder bh7;

    private JPanel allCards; // Panel that holds all the little flashcards
    private JPanel[] cards; // Array holding all the little flashcards (all panels)
    private FileReader fReader; // FileReader instance

    public Flashcards(BobHolder bhIn6)
    {
        super(bhIn6, "Review Your Vocab"); // call constructor and pass in "Review Your Vocab"
        bh7 = bhIn6;
        JButton home2 = new JButton("home");
        HomeButtonListener2 hbl2 = new HomeButtonListener2();
        
        // Home button
        home2.addActionListener(hbl2);
        getPanel("right").add(home2); // use polymorphism to quickly draw the empty panel for 
        // buttons on the side
        allCards = new JPanel();
        allCards.setLayout(new GridLayout(20, 3));

        // Initializing fReader, and prepare to set up all of the flashcards
        fReader = bh7.getGamePage().getFReader();
        int sum = fReader.getSum();
        cards = new JPanel[sum];
        for (int i=0; i< sum; i++)
        {
            // Create a JPanel with a flow layout, this will be every little card.
            JPanel card = new JPanel();
            card.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
            String fullText = fReader.getWords()[i];
            fullText = fullText.substring(fullText.indexOf(" ")+1); // Manipulate string so we see what we want
            if (bh7.getSimplified() == true) // Change the text if it's in simplified mode or not
            {
                fullText = fullText.substring(fullText.indexOf(" ")+1);
            }
            
            // Rendering the chinese text
            JLabel chineseText = new JLabel();
            chineseText.setText(fullText.substring(0, fullText.indexOf(" ")));
            // Rendering the definition
            JLabel def = new JLabel(fullText.substring(fullText.lastIndexOf(" ")));
            card.add(chineseText);
            card.add(def);

            // Add the card to the panel and the collective array of every card.
            allCards.add(card);
            cards[i] = card;
        }
        resetCards();
        
        // Scrollpane that holds all the cards, so you can scroll to see all the cards :P
        JScrollPane scroller = new JScrollPane(allCards);
        scroller.setPreferredSize(new Dimension(700, 400));
        getPanel("center").add(scroller);
    }

    // Changes the color of every card depending on the values of firstTry from PlayerInfo
    public void resetCards()
    {
        // System.out.println("Card length: " + cards.length);
        for (int i=0; i<cards.length; i++)
        {
            boolean isCorrect = bh7.getPlayerInfo().getCorrect(i);
            // System.out.print(i + " " + isCorrect + "|");
            if (isCorrect == false)
            {
                cards[i].setBackground(Color.RED);
            }
            else
            {
                cards[i].setBackground(Color.GREEN);
            }
        }
        // System.out.println("");
    }

    // Button listener
    class HomeButtonListener2 implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equals("home"))
            {
                bh7.getCards().show(bh7, "home");
            }
        }
    }
}
