/*Ethan Liao and Lorence Tsai
 * Flashcards.java
 * Done by Ethan Liao
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Flashcards extends BasePanel // inherits from BasePanel
{
    private BobHolder bh7;
    public Flashcards(BobHolder bhIn6)
    {
        super(bhIn6, "Review Your Vocab"); // call constructor and pass in "Review Your Vocab"
        bh7 = bhIn6;
        JButton home2 = new JButton("home");
        HomeButtonListener2 hbl2 = new HomeButtonListener2();

        home2.addActionListener(hbl2);
        getPanel("right").add(home2); // use polymorphism to quickly draw the empty panel for 
        // buttons on the side
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
