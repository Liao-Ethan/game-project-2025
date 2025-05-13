/* Ethan Liao and Lorence Tsai
 * Written mostly by Lorence Tsai
 */
import java.awt.CardLayout;
import javax.swing.JPanel;

class BobHolder extends JPanel
{
	private CardLayout cards; // CardLayout variable
	private boolean isStrokes; // Will define later(used to see if strokes level is chosen)
	public BobHolder()
	{
		isStrokes = false;
		cards = new CardLayout(); // card layout to hold everything
		setLayout(cards);
		Instructions instructions = new Instructions(this); // new instances of classes to use here
		Cover cover = new Cover(this);
		Home home = new Home(this);
		GamePanel game = new GamePanel(this);
		Flashcards fcards = new Flashcards(this);

		add(cover, "cover"); // add the other classes to the cardPanelHolder
		add(home, "home");
		add(instructions, "instructions");
		add(game, "game");
		add(fcards, "cards");
	}
	
	public CardLayout getCards() // get cards because CardHolder is private
	{
		return cards;
	}

	public void setStroke(boolean isStrokeIn)
	{
		isStrokes = isStrokeIn;
	}
}
