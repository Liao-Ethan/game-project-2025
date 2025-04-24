/* Ethan Liao and Lorence Tsai
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

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

class Home extends BasePanel
{
	public Home(BobHolder bhIn3)
	{
		super(bhIn3, "Select Game Mode");
		
		JButton instructButton = new JButton("How to Play");
		add(instructButton);
		InstructHandler iHandler = new InstructHandler();
		instructButton.addActionListener(iHandler);
		getPanel("right").add(instructButton);
	}
	
	class InstructHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if (command.equals("How to Play"))
			{
				bh3.getCards().show(bh3, "instructions");
			}
		}
	}
}


class ModeButton
{
	public ModeButton()
	{}
}
