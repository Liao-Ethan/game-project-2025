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

class Home extends JPanel
{
	BobHolder bh3;
	public Home(BobHolder bhIn2)
	{
		bh3 = bhIn2;
		
		setLayout(new BorderLayout(10, 10));
		
		JLabel title = new JLabel("Select Game Mode", JLabel.CENTER);
		title.setFont(new Font("serif", Font.BOLD, 36));
		add(title, BorderLayout.NORTH);
		
		JPanel empty1 = new JPanel();
		empty1.setPreferredSize(new Dimension(250, 100));
		empty1.setBackground(Color.BLUE);
		add(empty1, BorderLayout.EAST);
		
		JPanel empty2 = new JPanel();
		empty2.setPreferredSize(new Dimension(250, 100));
		empty2.setBackground(Color.BLUE);
		add(empty2, BorderLayout.WEST);
		
		JPanel buttonPanel = new JPanel();
	}
}
