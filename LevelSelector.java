/* LevelSelector.java
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

 public class LevelSelector extends BasePanel
 {
    private int levelChosen; // Stores which level is chosen
    private BobHolder bobLevel; // BobHolder instance
    
    private JCheckBox level1; // checkboxes to choose level
	private JCheckBox level2;
	private JCheckBox level3;

    private JLabel notif; // Text that changes if you enter a level without following requirements

    public LevelSelector(BobHolder bobLevelIn)
    {
        // Setting up the panel
        super(bobLevelIn, "Level Select");
        bobLevel = bobLevelIn;
        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new GridLayout(0, 3));
        Font font = new Font("serif", Font.PLAIN, 48);

        LevelListener levelListener = new LevelListener();

        // Initialize all checkboxes
        level1 = new JCheckBox("Level 1");
        level2 = new JCheckBox("Level 2");
        level3 = new JCheckBox("Level 3");
        
        // Set up notif label
        notif = new JLabel("Please select a level.", JLabel.CENTER);
        notif.setFont(new Font("serif", Font.PLAIN, 36));
        notif.setForeground(Color.BLACK);
        add(notif, BorderLayout.SOUTH);

        // Setting fonts for checkboxes
        level1.setFont(font);
        level2.setFont(font);
        level3.setFont(font);

        // Adds listeners to checkboxes
        level1.addActionListener(levelListener);
        level2.addActionListener(levelListener);
        level3.addActionListener(levelListener);

        // Adds checkboxes to a panel
        levelPanel.add(level1);
        levelPanel.add(level2);
        levelPanel.add(level3);

        // Adds panel holding checkboxes
        getPanel("center").add(levelPanel);
    }

    // Listener for each panel
    class LevelListener implements ActionListener
    {
       public void actionPerformed(ActionEvent evt)
       {
           String buttonPressed = evt.getActionCommand();
   
           if (buttonPressed.equals("Level 1"))
           {
                // Switch to level 1
                levelChosen = 1;
                bobLevel.getCards().show(bobLevel, "game");
                setLevel();
           }
           else if (buttonPressed.equals("Level 2"))
           {
                if (bobLevel.getPlayerInfo().isComplete(0)) // Is level 1 complete
                {
                    // Switch level to level 2
                    levelChosen = 2;
                    bobLevel.getCards().show(bobLevel, "game");
                    setLevel();
                }
                else
                {
                    notif.setText("Please get everything correct in level 1 before proceeding.");
                    notif.setForeground(Color.ORANGE);
                }
           }
           else if (buttonPressed.equals("Level 3"))
           {
                if (bobLevel.getPlayerInfo().isComplete(1)) // Is level 2 complete (2 can't be completed before 1)
                {
                    // Switch level to level 3
                    levelChosen = 3;
                    bobLevel.getCards().show(bobLevel, "game");
                    setLevel();
                }
                else // Change label
                {
                    notif.setText("Please get everything correct in level 2 before proceeding.");
                    notif.setForeground(Color.ORANGE);
                }
           }
           
       }
    }

    public void setLevel()
    {
          bobLevel.getGamePage().newQuestions(levelChosen);
          bobLevel.getGamePage().proceedQuestion(false);
          bobLevel.getCards().show(bobLevel, "game");

          notif.setText("Please select a level.");
          notif.setForeground(Color.BLACK);
          level1.setSelected(false);
          level2.setSelected(false);
          level3.setSelected(false);
     }
 }
