/* Ethan Liao and Lorence Tsai
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.GridLayout;

import javax.swing.JButton;

class Home extends BasePanel
{
	private BobHolder bh3;
	public Home(BobHolder bhIn3)
	{
		super(bhIn3, "Select Game Mode"); // Extends BasePanel
		bh3 = bhIn3;
		// Adding the components to their respective panels
		JButton instructButton = new JButton("How to Play"); // How to play button
		ButtonHandler bHandler = new ButtonHandler(); // Instantiating handler for the button
		instructButton.addActionListener(bHandler);
		getPanel("right").add(instructButton);

		JButton lOButton = new JButton("Log Out");
		lOButton.addActionListener(bHandler);
		getPanel("right").add(lOButton);

		getPanel("center").setLayout(new GridLayout(1, 4));

		JButton[] menuButtons = new JButton[3];
		String[] buttonNames = {"Flashcards", "Word Identification", "Definition"};
		for (int i=0; i<3; i++) // Drawing all the buttons from an array
		{
			menuButtons[i] = new JButton(buttonNames[i]);
			menuButtons[i].addActionListener(bHandler);
			getPanel("center").add(menuButtons[i]);
		}
		add(getPanel("center"));
	}
	
	class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if (command.equals("How to Play"))
			{
				bh3.getCards().show(bh3, "instructions");
			}
			else if (command.equals("Log Out"))
			{
				bh3.getPlayerInfo().writeSave();
				bh3.getPlayerInfo().reset();
				bh3.getCards().show(bh3, "cover");
			}
			else if (command.equals("Flashcards"))
			{
				bh3.getCards().show(bh3, "cards");
			}
			else if (command.equals("Word Identification"))
			{
				bh3.getCards().show(bh3, "select");
				bh3.setDef(false);
			}
			else if (command.equals("Definition"))
			{
				bh3.getCards().show(bh3, "select");
				bh3.setDef(true);
			}
		}
	}
}
