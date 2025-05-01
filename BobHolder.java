/* Ethan Liao and Lorence Tsai
 * Written mostly by Lorence Tsai
 */
import java.awt.CardLayout;
import javax.swing.JPanel;

class BobHolder extends JPanel
{
	CardLayout cards;
	private boolean isStrokes;
	public BobHolder()
	{
		isStrokes = false;
		cards = new CardLayout();
		setLayout(cards);
		Instructions instructions = new Instructions(this);

		Cover cover = new Cover(this);
		Home home = new Home(this);
		GamePanel game = new GamePanel(this);
		Flashcards fcards = new Flashcards(this);
		add(cover, "cover");
		add(home, "home");
		add(instructions, "instructions");
		add(game, "game");
		add(fcards, "cards");
	}
	
	public CardLayout getCards()
	{
		return cards;
	}

	public void setStroke(boolean isStrokeIn)
	{
		isStrokes = isStrokeIn;
	}
}
