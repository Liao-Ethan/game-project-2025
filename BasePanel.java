/* Lorence Tsai
 * BasePanel.java
 * Classes inherit this for good formatting
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

class BasePanel extends JPanel
{
	private BobHolder bh3;

	// Three panels: left, right, center.
	private JPanel empty1 = new JPanel(); // empty panels on the side for buttons
	private JPanel empty2 = new JPanel();
	private JPanel centerPanel = new JPanel();
	public BasePanel(BobHolder bhIn2, String titleIn)
	{
		bh3 = bhIn2;
		
		setLayout(new BorderLayout(10, 10)); // set to borderLayout with 10 as v&h gaps
		
		JLabel title = new JLabel(titleIn, JLabel.CENTER); // add a JLabel with text in the center
		title.setFont(new Font("serif", Font.BOLD, 36));
		add(title, BorderLayout.NORTH);
		
		empty1.setPreferredSize(new Dimension(250, 100));
		empty1.setBackground(Color.BLUE);
		empty1.setLayout(new BorderLayout(10, 10));
		add(empty1, BorderLayout.WEST);
		
		empty2.setPreferredSize(new Dimension(250, 100));
		empty2.setBackground(Color.BLUE);
		empty2.setLayout(new GridLayout(3, 1));
		add(empty2, BorderLayout.EAST);
		
		add(centerPanel, BorderLayout.CENTER);
	}
	
	/* For accessing either the left or right side panels (center is simply just adding)
	 * Useful for having more customization of the side panels per big card.
	 */
	public JPanel getPanel(String panelName)
	{
		if (panelName.equals("left"))
		{
			return empty1;
		}
		else if (panelName.equals("right"))
		{
			return empty2;
		}
		
		return centerPanel;
	}
}

