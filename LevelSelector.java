/* LevelSelector.java
 */

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 public class LevelSelector extends BasePanel
 {
    private int levelChosen;
    private BobHolder bobLevel;
    
    private JCheckBox level1; // checkboxes to choose level
	private JCheckBox level2;
	private JCheckBox level3;

    public LevelSelector(BobHolder bobLevelIn)
    {
        super(bobLevelIn, "Level Select");
        bobLevel = bobLevelIn;
        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new GridLayout(0, 3));

        LevelListener levelListener = new LevelListener();

        level1 = new JCheckBox("Level 1");
        level2 = new JCheckBox("Level 2");
        level3 = new JCheckBox("Level 3");

        level1.addActionListener(levelListener);
        level2.addActionListener(levelListener);
        level3.addActionListener(levelListener);

        levelPanel.add(level1);
        levelPanel.add(level2);
        levelPanel.add(level3);

        getPanel("center").add(levelPanel);
    }

    class LevelListener implements ActionListener
    {
       public void actionPerformed(ActionEvent evt)
       {
           String buttonPressed = evt.getActionCommand();
   
           if (buttonPressed.equals("Level 1"))
           {
                levelChosen = 1;
                bobLevel.getCards().show(bobLevel, "game");
                level1.setSelected(false);
           }
           else if (buttonPressed.equals("Level 2"))
           {
                levelChosen = 2;
                bobLevel.getCards().show(bobLevel, "game");
                level2.setSelected(false);
           }
           else
           {
                levelChosen = 3;
                bobLevel.getCards().show(bobLevel, "game");
                level3.setSelected(false);
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
