/* LevelSelector.java
 */

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 public class LevelSelector extends BasePanel
 {
    private int levelChosen;
    private BobHolder bobLevel;

    public LevelSelector(BobHolder bobLevelIn)
    {
        super(bobLevelIn, "Level Select");
        bobLevel = bobLevelIn;
        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new GridLayout(0, 3));

        LevelListener levelListener = new LevelListener();

        JButton level1 = new JButton("Level 1");
        JButton level2 = new JButton("Level 2");
        JButton level3 = new JButton("Level 3");

        level1.addActionListener(levelListener);
        level2.addActionListener(levelListener);
        level3.addActionListener(levelListener);

        levelPanel.add(level1);
        levelPanel.add(level2);
        levelPanel.add(level3);

        getPanel("Center").add(levelPanel);
    }

    class LevelListener implements ActionListener
    {
       public void actionPerformed(ActionEvent evt)
       {
           String buttonPressed = evt.getActionCommand();
   
           if (buttonPressed.equals("Level 1"))
           {
                levelChosen = 1;
           }
           else if (buttonPressed.equals("Level 2"))
           {
                levelChosen = 2;
           }
           else if (buttonPressed.equals("Level 3"))
           {
                levelChosen = 3;
           }
           setLevel();
       }
    }

    public void setLevel()
    {
          bobLevel.getGamePage().newQuestions(levelChosen);
          bobLevel.getGamePage().proceedQuestion(false);
          bobLevel.getCards().show(bobLevel, "game");
     }
 }