import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GamePanel extends BasePanel
{
    BobHolder bh5;
    public GamePanel(BobHolder bh5In)
    {
        super(bh5In, "game");
        bh5 = bh5In;
        Paint paint = new Paint();
        add(paint, BorderLayout.CENTER);

        JButton home = new JButton("home");
        HomeButtonListener hbl = new HomeButtonListener();

        home.addActionListener(hbl);
        add(home, BorderLayout.EAST);
    }

    class HomeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equals("home"))
            {
                System.out.println("home");
                bh5.getCards().show(bh5, "home");
            }
        }
    }
}

class Paint extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawRects(g);
    }

    public void drawRects(Graphics g)
    {
        g.setColor(Color.PINK);
        g.fillRect(0, 140, 380, 240);
        g.fillRect(380, 140, 380, 240);
        g.fillRect(0, 390, 380, 240);
        g.fillRect(380, 390, 380, 240);

        g.setColor(Color.BLACK);
        g.drawRect(0, 140, 380, 240);
        g.drawRect(380, 140, 380, 240);
        g.drawRect(0, 390, 380, 240);
        g.drawRect(380, 390, 380, 240);
    }
}