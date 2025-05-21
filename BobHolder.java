/* Ethan Liao and Lorence Tsai
 * Written mostly by Lorence Tsai
 */
import java.awt.CardLayout;
import javax.swing.JPanel;

class BobHolder extends JPanel
{
	private CardLayout cards; // CardLayout variable
	private boolean isDef; // used to see if game mode "definitions" is chosen)
	private boolean simplified; // Shows whether the game running with simplified text or no.

	// All the individual cards
	private Instructions instructions; // Instructions panel
	private Cover cover; // Cover panel
	private Home home; // Home panel
	private GamePanel game; // Game panel
	private Flashcards fcards; // Flashcards panel
	private LevelSelector lSelect; // Level selecting panel

	private PlayerInfo pInfo; // Stores all the player information.

	public BobHolder()
	{
		isDef = false;
		simplified = false;
		cards = new CardLayout(); // card layout to hold everything
		setLayout(cards);

		// Initialize all panels which will be cards
		instructions = new Instructions(this); // new instances of classes to use here
		cover = new Cover(this);
		home = new Home(this);
		game = new GamePanel(this);
		pInfo = new PlayerInfo();
		fcards = new Flashcards(this);
		lSelect = new LevelSelector(this);


		add(cover, "cover"); // add the other classes to the cardPanelHolder
		add(home, "home");
		add(instructions, "instructions");
		add(game, "game");
		add(fcards, "cards");
		add(lSelect, "select");
	}

	// Getter setters for important panels
	public PlayerInfo getPlayerInfo()
	{
		return pInfo;
	}

	public Flashcards getFlashcards()
	{
		return fcards;
	}

	public GamePanel getGamePage()
	{
		return game;
	}

	public Cover getCover()
	{
		return cover;
	}
	
	// Getter setters for is it in the game mode "definitions"
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

	// Getter setter for is it in simplified or not
	public void setSimplified(boolean isSimplified)
	{
		simplified = isSimplified;
	}

	public boolean getSimplified()
	{
		return simplified;
	}
}
