/* Ethan Liao and Lorence Tsai
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

class Home extends BasePanel
{
	private BobHolder bh3; // Instance of bobHolder so information can be passed into Home
	public Home(BobHolder bhIn3)
	{
		super(bhIn3, "Select Game Mode"); // Extends BasePanel
		bh3 = bhIn3;
		// Adding the components to their respective panels
		// Button to switch to Instructions panel
		JButton instructButton = new JButton("How to Play"); // How to play button
		ButtonHandler bHandler = new ButtonHandler(); // Instantiating handler for the button
		instructButton.addActionListener(bHandler);
		getPanel("right").add(instructButton);

		// Log out button
		JButton lOButton = new JButton("Log Out");
		lOButton.addActionListener(bHandler);
		getPanel("right").add(lOButton);

		// GridLayout to show all important buttons
		getPanel("center").setLayout(new GridLayout(1, 3));

		// Array that all buttons and their respective names
		JButton[] menuButtons = new JButton[3];
		String[] buttonNames = {"Flashcards", "Word Identification", "Definition"};
		for (int i=0; i<3; i++) // Drawing all the buttons from an array
		{
			menuButtons[i] = new JButton(buttonNames[i]);
			menuButtons[i].addActionListener(bHandler);
			getPanel("center").add(menuButtons[i]);
		}
		add(getPanel("center")); // Adding the panel onto basePanel

		// Set up radio buttons
		// Button group for switching between simplified and traditional
		ButtonGroup bg = new ButtonGroup();
		RBHandler rbHandler = new RBHandler(); // Radio button handler
		getPanel("left").setLayout(new FlowLayout(FlowLayout.CENTER, 100, 30)); // New layout for left panel

		Font languageFont = new Font("Serif", Font.PLAIN, 30);
		// Radio button for traditional
		JRadioButton trad = new JRadioButton("Traditional");
		trad.setFont(languageFont);
		trad.addActionListener(rbHandler);
		getPanel("left").add(trad);
		// Radio button for simplified
		JRadioButton simp = new JRadioButton("Simplified");
		simp.setFont(languageFont);
		simp.addActionListener(rbHandler);
		getPanel("left").add(simp);

		// Adding radio buttons
		bg.add(trad);
		trad.setSelected(true);
		bg.add(simp);
	}

	// Radio button handler
	class RBHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if (command.equals("Traditional"))
			{
				bh3.setSimplified(false);
			}
			if (command.equals("Simplified"))
			{
				bh3.setSimplified(true);
			}
		}
	}
	
	// Button handler
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
				bh3.getCover().reset();
				bh3.getCards().show(bh3, "cover");
			}
			else if (command.equals("Flashcards"))
			{
				bh3.getCards().show(bh3, "cards");
				bh3.getFlashcards().resetCards();
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
