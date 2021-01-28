package package1;

import java.applet.Applet;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * This class is purely esthetics for the leaderboard.
 */

public class Leaderboard extends Applet implements ActionListener
{
	private String username;
	Game game;
	JPanel panel, p;
	GridBagLayout gridbag;
	CardLayout cl;
	GridBagConstraints c;
	JButton menubutton;
	JLabel ldb, n1, n2, n3;
	
	public Leaderboard (Game game, CardLayout cl, JPanel p)
	{
		this.p=p;
    	this.cl=cl;
		this.game = game;
		this.panel = new JPanel(gridbag);
    	this.game = new Game(username);
    	this.gridbag = new GridBagLayout();
    	this.c = new GridBagConstraints();
	}
	
	protected JButton makeButton(String name, GridBagLayout gridbag, GridBagConstraints c)
    {
		 JButton button = new JButton(name);
	     button.addActionListener(this);
	     button.setBackground(new Color(255, 240, 250));
	     gridbag.setConstraints(button, c);
	     panel.add(button);
	     return button;
    }
    
    protected JLabel makeLabel(String name, GridBagLayout gridbag, GridBagConstraints c, Color col, boolean b)
    {
        JLabel label = new JLabel(name);
        label.setBackground(col);
        label.setOpaque(true);
        label.setFont(new Font("ldblabel", Font.BOLD, 25));
        gridbag.setConstraints(label, c);
        if (b)
        	label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);
        return label;
    }
	
	
	public void init()
	{
		panel.setLayout(gridbag);
     	panel.setBackground(Color.WHITE);
     	
     	
     	c.fill = GridBagConstraints.BOTH;
     	c.insets = new Insets(0,0,0,0);
     	c.weightx=1;
     	c.gridx=0;
     	c.weighty=1;
     	c.gridwidth=GridBagConstraints.REMAINDER;
     	c.gridheight = 2;
     	menubutton = makeButton("BACK TO MENU", gridbag, c);
     	c.gridheight = 20;
     	ldb = makeLabel("LEADERBOARD", gridbag, c, null, true);
     	ldb.setFont(new Font ("ldb", Font.BOLD, 50));
     	
     	c.gridheight=10;
     	c.gridwidth=5;
     	makeLabel("", gridbag, c, null, false);
     	c.gridx=5;
     	makeLabel(game.getUsers()[0] + " : " + game.getScores()[0] + " points.", gridbag, c, null, false);
     	c.gridx=10;
     	makeLabel("", gridbag, c, null, false);
     	c.gridx=0;
     	makeLabel(game.getUsers()[1] + " : " + game.getScores()[1] + " points.", gridbag, c, null, false);
     	c.gridx=5;
     	n1 = makeLabel("", gridbag, c, new Color (255, 217, 90), false);
     	c.gridx=10;
     	makeLabel("", gridbag, c, null, false);
     	c.gridx=0;
     	n2 = makeLabel("", gridbag, c, new Color (192, 192, 192), false);
     	c.gridx=5;
     	n1 = makeLabel("", gridbag, c, new Color (255, 217, 90), false);
     	c.gridx=10;
     	n3 = makeLabel(game.getUsers()[2] + " : " + game.getScores()[2] + " points.", gridbag, c, null, false);
     	c.gridx=0;
     	makeLabel("#2", gridbag, c, new Color (192, 192, 192), false);
     	c.gridx=5;
     	makeLabel("#1", gridbag, c, new Color (255, 217, 90), false);
     	c.gridx=10;
     	n3 = makeLabel("#3", gridbag, c, new Color(205, 127, 50), false);
     	
     	c.gridx=0;
     	c.weighty=0;
     	c.gridwidth=GridBagConstraints.REMAINDER;
     	c.insets = new Insets(10,10,10,10);
     	makeLabel("#4 : " + game.getUsers()[3] + " with " + game.getScores()[3] + " points.", gridbag, c, null, true);
     	makeLabel("#5 : " + game.getUsers()[4] + " with " + game.getScores()[4] + " points.", gridbag, c, null, true);
     	makeLabel("#6 : " + game.getUsers()[5] + " with " + game.getScores()[5] + " points.", gridbag, c, null, true);
     	makeLabel("#7 : " + game.getUsers()[6] + " with " + game.getScores()[6] + " points.", gridbag, c, null, true);
     	makeLabel("#8 : " + game.getUsers()[7] + " with " + game.getScores()[7] + " points.", gridbag, c, null, true);
     	makeLabel("#9 : " + game.getUsers()[8] + " with " + game.getScores()[8] + " points.", gridbag, c, null, true);
     	makeLabel("#10 : " + game.getUsers()[9] + " with " + game.getScores()[9] + " points.", gridbag, c, null, true);
	}

	public void actionPerformed(ActionEvent e) {
		cl.show(p, "Menu");
	}
}
