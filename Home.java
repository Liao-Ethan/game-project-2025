/* Ethan Liao and Lorence Tsai
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.GridLayout;

import javax.swing.JButton;

class Home extends BasePanel
{
	public Home(BobHolder bhIn3)
	{
		super(bhIn3, "Select Game Mode");
		
		JButton instructButton = new JButton("How to Play");
		add(instructButton);
		ButtonHandler bHandler = new ButtonHandler();
		instructButton.addActionListener(bHandler);
		getPanel("right").add(instructButton);

		getPanel("center").setLayout(new GridLayout(1, 3));

		JButton[] menuButtons = new JButton[3];
		String[] buttonNames = {"Flashcards", "Word Identification", "Stroke Order"};
		for (int i=0; i<3; i++) {
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
			else if (command.equals("Flashcards"))
			{
				bh3.getCards().show(bh3, "cards");
			}
			else if (command.equals("Word Identification"))
			{
				bh3.getCards().show(bh3, "game");
				bh3.setStroke(false);
			}
			else if (command.equals("Stroke Order"))
			{
				bh3.getCards().show(bh3, "game");
				bh3.setStroke(true);
			}
		}
	}
}
