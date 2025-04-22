/* Ethan Liao and Lorence Tsai
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


class BobGame // Written by Torence Lsai
{
	public static void main(String[] bob)
	{
		BobGame bg = new BobGame();
		bg.runGame();
	}
	
	public void runGame()
	{
		JFrame frame = new JFrame("Bob the Frog Explores the Deep Sea of Chinese Literacy");
		frame.setSize( 1280, 720);				
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); 
		frame.setLocation(0,0);
		frame.setResizable(false);
		BobHolder bh = new BobHolder(); 		
		frame.getContentPane().add(bh);		
		frame.setVisible(true);		
	}
}

