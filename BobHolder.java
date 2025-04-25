/* Ethan Liao and Lorence Tsai
 * Written mostly by Lorence Tsai
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

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
		add(cover, "cover");
		add(home, "home");
		add(instructions, "instructions");
		add(game, "game");
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
