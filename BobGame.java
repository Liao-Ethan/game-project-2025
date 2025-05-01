/* Ethan Liao and Lorence Tsai
 */
import javax.swing.JFrame;


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
		System.out.println("eddie = dum");	
	}
}

