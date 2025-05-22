/* Ethan Liao and Lorence Tsai
 * Cover page
 * Done by Ethan Liao
 */
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

class Cover extends JPanel
{
	private BobHolder bh2;
	public Cover(BobHolder bhIn)
	{
		bh2 = bhIn;
		setBackground(Color.BLUE);
		setLayout(null);
		
		JLabel titleLabel = new JLabel("<html><center>Bob the Frog Explores the Deep Sea of Chinese Literacy</center></html>");
		// use html to center text in JLabel
		titleLabel.setFont(new Font("serif", Font.BOLD, 36)); // setting the font
		add(titleLabel);
		titleLabel.setBounds(350, 0, 580, 150); // setting location in the nullLayout
		
		CoverButtonHandler cbh = new CoverButtonHandler(); // instantiate a new buttonHandler class
		CoverNameHandler cnh = new CoverNameHandler();

		// Textfield for the name
		JTextField name = new JTextField("Enter your name");
		name.setFont(new Font("serif", Font.BOLD, 16));
		name.setBounds(560, 200, 160, 40);
		name.addActionListener(cnh);
		add(name);

		// Button to move to the next page
		JButton next = new JButton("Play");
		next.setFont(new Font("serif", Font.BOLD, 24));
		next.setBounds(568, 275, 144, 40);
		next.addActionListener(cbh);
		add(next);
		
		// Button to quit the program
		JButton quit = new JButton("Quit");
		quit.setFont(new Font("serif", Font.BOLD, 24));
		quit.setBounds(568, 350, 144, 40);
		quit.addActionListener(cbh);
		add(quit);
	}
	
	class CoverButtonHandler implements ActionListener // button handler to check which button is clicked
	// and do corresponding actions
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if (command.equals("Play"))
			{
				if (!bh2.getPlayerInfo().getName().equals(""))
				{
					bh2.getCards().show(bh2, "home");
				}
			}
			else if (command.equals("Quit"))
			{
				System.exit(0);
			}
		}
	}

	class CoverNameHandler implements ActionListener // button handler to check which button is clicked
	// and do corresponding actions
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			bh2.getPlayerInfo().setName(command);
			bh2.getPlayerInfo().checkForFile();
		}
	}
}
