/* Ethan Liao and Lorence Tsai
 * Cover page
 * Done by Ethan Liao
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

class Cover extends JPanel
{
	BobHolder bh2;
	public Cover(BobHolder bhIn)
	{
		bh2 = bhIn;
		setBackground(Color.BLUE);
		setLayout(null);
		
		JLabel titleLabel = new JLabel("<html><center>Bob the Frog Explores the Deep Sea of Chinese Literacy</center></html>");
		titleLabel.setFont(new Font("serif", Font.BOLD, 36));
		add(titleLabel);
		titleLabel.setBounds(350, 0, 580, 150);
		
		CoverButtonHandler cbh = new CoverButtonHandler();
		
		JButton next = new JButton("Play");
		next.setFont(new Font("serif", Font.BOLD, 24));
		next.setBounds(568, 200, 144, 40);
		next.addActionListener(cbh);
		add(next);
		
		JButton quit = new JButton("Quit");
		quit.setFont(new Font("serif", Font.BOLD, 24));
		quit.setBounds(568, 275, 144, 40);
		quit.addActionListener(cbh);
		add(quit);
	}
	
	class CoverButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if (command.equals("Play"))
			{
				bh2.getCards().show(bh2, "home");
			}
			else if (command.equals("Quit"))
			{
				System.exit(0);
			}
		}
	}
}
