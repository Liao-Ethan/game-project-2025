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
	public BobHolder()
	{
		cards = new CardLayout();
		setLayout(cards);
		Instructions instructions = new Instructions(this);

		Cover cover = new Cover(this);
		Home home = new Home(this);
		add(cover, "cover");
		add(home, "home");
		add(instructions, "instructions");
	}
	
	public CardLayout getCards()
	{
		return cards;
	}
}
