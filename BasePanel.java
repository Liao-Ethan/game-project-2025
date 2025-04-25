/* Ethan Liao and Lorence Tsai
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

class BasePanel extends JPanel
{
	BobHolder bh3;
	private JPanel empty1 = new JPanel();
	private JPanel empty2 = new JPanel();
	private JPanel centerPanel = new JPanel();
	public BasePanel(BobHolder bhIn2, String titleIn)
	{
		bh3 = bhIn2;
		
		setLayout(new BorderLayout(10, 10));
		
		JLabel title = new JLabel(titleIn, JLabel.CENTER);
		title.setFont(new Font("serif", Font.BOLD, 36));
		add(title, BorderLayout.NORTH);
		
		empty1.setPreferredSize(new Dimension(250, 100));
		empty1.setBackground(Color.BLUE);
		empty1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		add(empty1, BorderLayout.WEST);
		
		empty2.setPreferredSize(new Dimension(250, 100));
		empty2.setBackground(Color.BLUE);
		empty2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		add(empty2, BorderLayout.EAST);
		
		add(centerPanel, BorderLayout.CENTER);
	}
	
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

