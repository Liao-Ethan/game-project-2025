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

    private JPanel allCards;
    private JPanel[] cards;
    private FileReader fReader;
    public Flashcards(BobHolder bhIn6)
    {
        super(bhIn6, "Review Your Vocab"); // call constructor and pass in "Review Your Vocab"
        bh7 = bhIn6;
        JButton home2 = new JButton("home");
        HomeButtonListener2 hbl2 = new HomeButtonListener2();
        
        home2.addActionListener(hbl2);
        getPanel("right").add(home2); // use polymorphism to quickly draw the empty panel for 
        // buttons on the side
        allCards = new JPanel();
        allCards.setLayout(new GridLayout(20, 3));

        fReader = bh7.getGamePage().getFReader();
        int sum = fReader.getLevelLengths(1) + fReader.getLevelLengths(2) + fReader.getLevelLengths(3);
        cards = new JPanel[sum];
        for (int i=0; i< sum; i++)
        {
            JPanel card = new JPanel();
            card.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
            String fullText = fReader.getWords()[i];
            fullText = fullText.substring(fullText.indexOf(" ")+1);
            if (bh7.getSimplified() == true)
            {
                fullText = fullText.substring(fullText.indexOf(" ")+1);
            }
            //System.out.println("Fulltext: " + fullText);
            
            JLabel chineseText = new JLabel();
            chineseText.setText(fullText.substring(0, fullText.indexOf(" ")));
            JLabel def = new JLabel(fullText.substring(fullText.lastIndexOf(" ")));
            card.add(chineseText);
            card.add(def);
            allCards.add(card);
            cards[i] = card;
        }
        resetCards();
        

        JScrollPane scroller = new JScrollPane(allCards);
        scroller.setPreferredSize(new Dimension(700, 400));
        getPanel("center").add(scroller);
    }

    public void resetCards()
    {
        for (int i=0; i<cards.length; i++)
        {
            boolean isCorrect = bh7.getPlayerInfo().getCorrect(i);
            // System.out.println(fReader.getWords()[i] + " = " + isCorrect);
            if (isCorrect == false)
            {
                cards[i].setBackground(Color.RED);
            }
            else
            {
                cards[i].setBackground(Color.GREEN);
            }
        }
    }
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
