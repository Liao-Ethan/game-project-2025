/* LevelSelector.java
 */

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

 public class LevelSelector extends BasePanel
 {
    private int levelChosen;
    private BobHolder bobLevel;

    public LevelSelector(BobHolder bobLevelIn)
    {
        super(bobLevelIn, "Level Select");
        levelChosen = 0;
        bobLevel = bobLevelIn;
        JPanel levelPanel = new JPanel();
    }
 }