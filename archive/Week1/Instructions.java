/* Ethan Liao and Lorence Tsai
 * Instructions.java
 * Done by Lorence Tsai
 */

import java.awt.Font;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;

class Instructions extends BasePanel
{
    BobHolder bh4;
    public Instructions(BobHolder bhIn4)
    {
        super(bhIn4, "How to Play");
        bh4 = bhIn4;
        
        getPanel("center").setLayout(new FlowLayout(FlowLayout.CENTER, 1280, 10));
        String instructions = "In the game panel, you have a frog, which is what you are " + 
            "controlling. You move the frog either by dragging it or using the arrow keys" +
            " to the square with the right answer to the question. There will be 4 answer" +
            " choices. After you moved the frog to the desired position, you press submit" +
            " and the computer will grade your answer.";
        JTextArea instructionsText = new JTextArea(instructions);
        JButton homeButton = new JButton("Home");
        instructionsText.setFont(new Font("dialog", Font.PLAIN, 24));
        
        instructionsText.setEditable(false);
        instructionsText.setLineWrap(true);
        instructionsText.setWrapStyleWord(true);

        HomeListener hl = new HomeListener();

        homeButton.addActionListener(hl);
        
        JScrollPane scroller = new JScrollPane(instructionsText);
        scroller.setPreferredSize(new Dimension(480, 200));
        getPanel("center").add(scroller);
        getPanel("center").add(homeButton);
    }

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