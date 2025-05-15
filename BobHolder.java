/* Ethan Liao and Lorence Tsai
 * Written mostly by Lorence Tsai
 */
import java.awt.CardLayout;
import javax.swing.JPanel;

class BobHolder extends JPanel
{
	private CardLayout cards; // CardLayout variable
	private boolean isDef; // Will define later(used to see if game mode "definitions" is chosen)

	private Instructions instructions;
	private Cover cover;
	private Home home;
	private GamePanel game;
	private Flashcards fcards;
	private LevelSelector lSelect;

	private PlayerInfo pInfo;

	public BobHolder()
	{
		isDef = false;
		cards = new CardLayout(); // card layout to hold everything
		setLayout(cards);
		instructions = new Instructions(this); // new instances of classes to use here
		cover = new Cover(this);
		home = new Home(this);
		game = new GamePanel(this);
		fcards = new Flashcards(this);
		pInfo = new PlayerInfo();
		lSelect = new LevelSelector(this);


		add(cover, "cover"); // add the other classes to the cardPanelHolder
		add(home, "home");
		add(instructions, "instructions");
		add(game, "game");
		add(fcards, "cards");
		add(lSelect, "select");
	}

	public PlayerInfo getPlayerInfo()
	{
		return pInfo;
	}

	public GamePanel getGamePage()
	{
		return game;
	}

	public void setDef(boolean defIn)
	{
		isDef = defIn;
	}

	public boolean getDef()
	{
		return isDef;
	}
	
	public CardLayout getCards() // get cards because CardHolder is private
	{
		return cards;
	}
}
