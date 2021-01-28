package package1;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*
 * This class is the link between everything. From the menu you can access the whole of the project.
 */

public class Menu extends Applet implements ActionListener
{
	JFrame f;
	JPanel menupanel, p;
	CardLayout cl;
	GridBagLayout gridbag;
	GridBagConstraints c;
	Game game;
	JButton lvl1_but, lvl2_but, lvl3_but, ldb_but, save_but, exit_but;
	JLabel label0, label1, label2, label3, label4;
	Lvl1 lvl1;
	Lvl2 lvl2;
	Lvl3 lvl3;
	Leaderboard ldb;
	
	public Menu (Game game)
	{
		this.f=new JFrame("Conway's Game Of Life Mini-games");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setSize(1700, 1000);
		this.game = game;
		this.gridbag = new GridBagLayout();
		this.menupanel = new JPanel(gridbag);
		this.cl = new CardLayout();
		this.p = new JPanel(cl);
		p.add(menupanel, "Menu");
		this.lvl1 = new Lvl1(game, cl, p);
		this.lvl2 = new Lvl2(game, cl, p);
		this.lvl3 = new Lvl3(game, cl, p);
		this.ldb = new Leaderboard(game, cl, p);
		p.add(lvl1.panel, "Level 1");
		p.add(lvl2.panel, "Level 2");
		p.add(lvl3.panel, "Level 3");
		p.add(ldb.panel, "Ldb");
		this.c = new GridBagConstraints();
		
		setFont(new Font("Menu", Font.BOLD, 14));
    	menupanel.setLayout(gridbag);
    	menupanel.setBackground(Color.BLACK);
    	
    	c.fill=GridBagConstraints.BOTH;
    	
    	c.weightx=1;
    	c.weighty=2;
    	c.gridwidth = GridBagConstraints.REMAINDER;
    	c.gridheight = 4;
    	makeLabel("Conway's Game Of Life Mini-games", gridbag, c);
    	makeLabel("MAIN MENU", gridbag, c);
    	
    	c.weighty=0;
    	c.gridwidth=5;
    	c.gridx=0;
    	label0 = new JLabel("User : " + game.getUsername());
    	gridbag.setConstraints(label0, c);
    	label0.setFont(new Font("User", Font.ITALIC, 30));
    	label0.setForeground(new Color (200, 200, 200));
    	menupanel.add(label0);
    	
    	label4 = new JLabel("Total score :  " + game.getTotalscore() + " points.");
    	gridbag.setConstraints(label4, c);
    	label4.setFont(new Font("User", Font.ITALIC, 30));
    	label4.setForeground(new Color (200, 200, 200));
    	menupanel.add(label4);
    	
    	c.gridheight=2;
    	c.weighty=0;
    	c.gridx=20;
    	c.gridwidth=GridBagConstraints.REMAINDER;
    	
    	label1 = new JLabel("Level 1 : " + game.getBestscore1() + " points.");
    	gridbag.setConstraints(label1, c);
    	label1.setForeground(new Color (200, 200, 200));
    	label1.setFont(new Font("User", Font.ITALIC, 30));
    	menupanel.add(label1);
    	
    	label2 = new JLabel("Level 2 : " + game.getBestscore2() + " points.");
    	gridbag.setConstraints(label2, c);
    	label2.setFont(new Font("User", Font.ITALIC, 30));
    	label2.setForeground(new Color (200, 200, 200));
    	menupanel.add(label2);
    	
    	label3 = new JLabel("Level 3 : " + game.getBestscore3() + " points.");
    	gridbag.setConstraints(label3, c);
    	label3.setFont(new Font("User", Font.ITALIC, 30));
    	label3.setForeground(new Color (200, 200, 200));
    	menupanel.add(label3);
    	
    	
    	c.weightx=0.5;
    	c.weighty=0.8;
    	c.gridheight=4;
    	c.gridx=0;
    	
		lvl1_but = makeButton("Level 1", gridbag, c);
		lvl2_but = makeButton("Level 2", gridbag, c);
		lvl3_but = makeButton("Level 3", gridbag, c);
		ldb_but = makeButton("Leaderboard", gridbag, c);
		save_but = makeButton("Save Game", gridbag, c);
		exit_but = makeButton("Exit Game", gridbag, c);
		
		cl.show(p, "Menu");
		f.add(p);
		f.setVisible(true);
	}
	
	protected JButton makeButton(String name, GridBagLayout gridbag, GridBagConstraints c)
    {
        JButton button = new JButton(name);
        button.addActionListener(this);
        button.setBackground(Color.WHITE);
        gridbag.setConstraints(button, c);
        button.setFont(new Font("Button", Font.BOLD, 25));
        menupanel.add(button);
        return button;
    }
    
    protected void makeLabel(String name, GridBagLayout gridbag, GridBagConstraints c)
    {
        JLabel label = new JLabel(name);
        gridbag.setConstraints(label, c);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        label.setFont(new Font("Menu", Font.BOLD, 40));
        label.setForeground(new Color (224, 224, 224));
        label.setHorizontalAlignment(JLabel.CENTER);
        menupanel.add(label);
    }

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==lvl1_but)
		{
			lvl1.panel.removeAll();	
			cl.show(p, "Level 1");
			lvl1.play();
		}
		if (e.getSource()==lvl2_but)
		{
			if (game.getTotalscore()<10)
			{
				JOptionPane.showMessageDialog(new JFrame(), "You don't have enough points to unlock level 2. You are missing " + (10 - game.getTotalscore())
						+ " points.", "Impossible",  JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				lvl2.panel.removeAll();	
				cl.show(p, "Level 2");
				lvl2.play();
			}
		}
		if (e.getSource()==lvl3_but)
		{
			if (game.getTotalscore()<35)
			{
				JOptionPane.showMessageDialog(new JFrame(), "You don't have enough points to unlock level 3. You are missing " + (35 - game.getTotalscore())
						+ " points.", "Impossible",  JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				lvl3.panel.removeAll();	
				cl.show(p, "Level 3");
				lvl3.play();
			}
		}
		if (e.getSource()==ldb_but)
		{
			ldb.panel.removeAll();	
			cl.show(p, "Ldb");
			ldb.init();
		}
		if (e.getSource()==save_but)
		{
			label1.setText("Level 1 : " + game.getBestscore1() + " points.");
			label2.setText("Level 2 : " + game.getBestscore2() + " points.");
			label3.setText("Level 3 : " + game.getBestscore3() + " points.");
			game.setTotalscore(game.getBestscore1() + game.getBestscore2() + game.getBestscore3());
			label4.setText("Total score : " + game.getTotalscore() + " points.");
			try
			{
				game.saveScore();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
			try
			{
				game.sort();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(new JFrame("Success"), "Game saved !");
			
		}
		if (e.getSource()==exit_but)
		{
			JOptionPane.showMessageDialog(new JFrame("Goodbye !"), "Thanks for playing !");
			System.exit(0);
		}
		
	}
}