package package1;

import java.util.Random;
import java.applet.Applet;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * This class is the first level of my game. It contains all the level (GUI and code for the level).
 */

public class Lvl1 extends Applet implements ActionListener
{
	private String username;
	private int points, n, j, i, playagain, score;
	Random rand;
	JPanel panel, p;
	Game game;
	GridBagLayout gridbag;
	CardLayout cl;
	GridBagConstraints c;
	JButton gridA, gridB, gridC, gridD, menubutton, cluebutton;
	
	public Lvl1 (Game game, CardLayout cl, JPanel p)
	{
    	this.p=p;
    	this.cl=cl;
		this.game = game;
		this.score=0;
		this.rand = new Random();
    	this.panel = new JPanel(gridbag);
    	this.game = new Game(username);
    	this.gridbag = new GridBagLayout();
    	this.c = new GridBagConstraints();
    	this.points=3;
    	this.n=0;
    	this.j=0;
    	this.i=0;
    	this.playagain=JOptionPane.NO_OPTION;
	}
	
	protected JButton makeButton(String name, GridBagLayout gridbag, GridBagConstraints c)
    {
        JButton button = new JButton(name);
        button.addActionListener(this);
        button.setBackground(new Color (0, 200, 138));
        button.setForeground(Color.WHITE);
        gridbag.setConstraints(button, c);
        panel.add(button);
        return button;
    }
    
    protected void makeLabel(String name, GridBagLayout gridbag, GridBagConstraints c)
    {
        JLabel label = new JLabel(name);
        gridbag.setConstraints(label, c);
        label.setForeground(Color.DARK_GRAY);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Level", Font.BOLD, 12));
        panel.add(label);
    }
    
    protected void displayMap (GamePanel gp, GridBagLayout gridbag, GridBagConstraints c)
    {
    	gridbag.setConstraints(gp, c);
    	panel.add(gp);
    }
    
    public void play()
	{
    	//This method is the one called in the menu. It contains a pop-up that asks for the number of generations then calls the level.
    	Integer[] options = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        n = (Integer)JOptionPane.showOptionDialog(null, "Welcome to level 1 !\nIn this level, you are provided with 5 grids. This level works as a multiple" + 
            		" choice test.\nYou have to pick which one of the first 4 grids is going to evolve into the last one after a set number of generations that"
            		+ " you pick.\nWatch out ! The number of generations you pick is related to the maximum score you can get on the level, but the more "
            		+ "generations, the harder it is !\nYour best score on level 1 is : "+ game.getBestscore1() + " .\nHow many generations do you want ?",
                    "How many generations do you want", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
        levelDisplay();
	}
    
    public void levelDisplay()
    {    	
    	j=0;
    	points=3;
    	score=0;
    	
    	Map map1 = new Map(10,10);
     	Map map2 = new Map(10,10);
     	Map map3 = new Map(10,10);
     	Map map4 = new Map(10,10);
     	Map fmap = new Map(10,10);
     	
     	map1.makeMap(25);
     	map2.makeMap(25);
     	map3.makeMap(25);
     	map4.makeMap(25);
     	
     	i=rand.nextInt(4);
     	//This random ensures that the correct grid is not always the same.
     	switch (i)
     	{
     		case 0 : fmap = map1.updateMap(n);
     		break;
     		
     		case 1 : fmap = map2.updateMap(n);
     		break;
     		
     		case 2 : fmap = map3.updateMap(n);
     		break;
     		
     		case 3 : fmap = map4.updateMap(n);
     		break;   			
     	}
     	
     	GamePanel pan1 = new GamePanel(map1.getXSize(),map1.getYSize(),map1, true);   
     	GamePanel pan2 = new GamePanel(map2.getXSize(),map2.getYSize(),map2, true);    
     	GamePanel pan3 = new GamePanel(map3.getXSize(),map3.getYSize(),map3, true);    
     	GamePanel pan4 = new GamePanel(map4.getXSize(),map4.getYSize(),map4, true);    
     	GamePanel fpan = new GamePanel(fmap.getXSize(),fmap.getYSize(),fmap, true);
     	
    	panel.setLayout(gridbag);
    	panel.setBackground(new Color (0, 255, 176));
    	
    	c.insets = new Insets(10,10,10,10);
    	c.fill = GridBagConstraints.BOTH;
    	
     	c.weightx=0;
     	c.gridx=0;
     	c.weighty=0;
     	c.gridwidth=GridBagConstraints.REMAINDER;
     	c.gridheight = 2;
     	menubutton = new JButton("BACK TO MENU");
     	menubutton.setBackground(Color.WHITE);
     	menubutton.addActionListener(new ActionListener()
	     	{
				public void actionPerformed(ActionEvent e) 
				{
					cl.show(p, "Menu");
				}
	     	});
        gridbag.setConstraints(menubutton, c);
        panel.add(menubutton);
     	
     	
     	c.weightx=1;
     	c.weighty=0;
     	c.gridwidth=11;
     	c.gridheight=2;
     	makeLabel("<html>Welcome to level 1 ! In this level, you are provided with 5 grids. This level works as a multiple choice test.<br>You have to"
     			+ " pick which one of the first 4 grids is going to evolve into the last one after a set number of generations that you pick.<br>"
     			+ "Watch out ! The number of generations you pick is related to the maximum score you can get"
     			+ " on the level, but the more generations, the harder it is !<br>"
     			+ "Watch out ! If you ask for a clue it will deduct points from your score.<br>"
     			+ "Your best score on level 1 is : " + game.getBestscore1() + "</html>", gridbag, c);
 		
     	c.weightx=0;
     	c.weighty=0;
     	c.gridx=15;
     	c.gridwidth= GridBagConstraints.REMAINDER;
     	cluebutton = new JButton("ASK FOR CLUE");
     	cluebutton.setBackground(Color.WHITE);
     	cluebutton.addActionListener(new ActionListener ()
	     	{
	     		public void actionPerformed(ActionEvent e)
	     		{
	     			j+=1;
	     			if (j==1)
	     			{
	     				if (points>1)
	         			{
	         				if (i==0 || i==1)
	         				{
	         					JOptionPane.showMessageDialog(new JFrame("New Clue"), "The correct grid is located in the top half.");
	         					gridC.setEnabled(false);
	         					gridD.setEnabled(false);
	         				}	
	         				else
	         				{
	         					JOptionPane.showMessageDialog(new JFrame("New Clue"), "The correct grid is located in the bottom half.");
	         					gridA.setEnabled(false);
	         					gridB.setEnabled(false);
	         				}
	         				cluebutton.setBackground(Color.GRAY);
	         				points-=1;
	         			}
	         			else
	         			{
	         				JOptionPane.showMessageDialog(new JFrame(), "You don't have enough points for a clue.", "Impossible",  JOptionPane.ERROR_MESSAGE);
	         			}	
	     			}
	     			else
	     				JOptionPane.showMessageDialog(new JFrame(), "You have already used your clue for the round", "Impossible",  JOptionPane.ERROR_MESSAGE);
	     		}
	     	});
        gridbag.setConstraints(cluebutton, c);
        panel.add(cluebutton);
        
     	makeLabel("<html>The grid right below is the final grid.<br>Pick the grid to the left you think originated it in " + n +" generations.<br>To select"
     			+ " one, just click on the button under it.</html>", gridbag, c);
     	
     	c.weightx=0.2;
     	c.weighty=1;
     	c.gridwidth = 5;
     	c.gridheight = 30;
     	c.gridx=0;
     	map1.plzMakeItWork();
     	map2.plzMakeItWork();
     	displayMap(pan1, gridbag, c);
     	c.gridheight=2;
     	c.weighty=0;
     	gridA = makeButton("Grid A", gridbag, c);
     	c.weighty=1;
     	c.gridx=5;
     	c.gridheight = 30;
     	displayMap(pan2, gridbag, c);
     	c.weighty=0;
     	c.gridheight=2;
     	gridB = makeButton("Grid B", gridbag, c);
     	
     	c.gridx= 0;
     	c.weighty=1;
     	c.gridheight = 30;
     	map3.plzMakeItWork();
     	displayMap(pan3, gridbag, c);
     	c.weighty=0;
     	c.gridheight=2;
     	gridC = makeButton("Grid C", gridbag, c);
     	c.gridx=5;
     	c.weighty=1;
     	c.gridheight = 30;
     	map4.plzMakeItWork();
     	displayMap(pan4, gridbag, c);
     	c.weighty=0;
     	c.gridheight=2;
     	gridD = makeButton("Grid D", gridbag, c);
     	
     	c.weightx=2;
     	c.weighty=1;
     	c.gridwidth = GridBagConstraints.REMAINDER;
     	c.gridheight = c.gridwidth;
     	c.gridx = 10;
     	fmap.plzMakeItWork();
     	displayMap(fpan, gridbag, c);
    }
    
    public void gameEnd()
    {
    	gridA.setEnabled(false);
    	gridB.setEnabled(false);
    	gridC.setEnabled(false);
    	gridD.setEnabled(false);
    	cluebutton.setEnabled(false);
    	if (score>game.getBestscore1())
    		game.setBestscore1(score);
    	playagain = JOptionPane.showConfirmDialog(null, "Your score this round was " + score + ". Do you wish to return to the menu ?", "Back to menu ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	if (playagain==0)
    		cl.show(p, "Menu");
    }
    
    
    public void actionPerformed(ActionEvent e)
	{
    	switch (i)
    	{
	 		case 0 :if (e.getSource()==gridA)
			 		{
			    		score=points*(n+1);
			    		JOptionPane.showMessageDialog(new JFrame(), "You figured it out ! Your score is "+ score +".", "Congratulations !",JOptionPane.INFORMATION_MESSAGE);
			    		gameEnd();
			 		}
			 		else
			    	{
			    		points-=1;
			        	JOptionPane.showMessageDialog(new JFrame(), "Wrong answer ! Try again.", "Oh no !",JOptionPane.INFORMATION_MESSAGE);
			        	if (points==0)
			        	{
			        		JOptionPane.showMessageDialog(new JFrame(), "You ran out of tries. Your score is 0.", "Oh no !",JOptionPane.INFORMATION_MESSAGE);
			        		gameEnd();
			        	}
			    	}
	 		break;
	 		
	 		case 1 :if (e.getSource()==gridB)
			 		{
	 					score=points*(n+1);
			    		JOptionPane.showMessageDialog(new JFrame(), "You figured it out ! Your score is "+ score +".", "Congratulations !",JOptionPane.INFORMATION_MESSAGE);
			    		gameEnd();
			 		}
			 		else
			    	{
			    		points-=1;
			        	JOptionPane.showMessageDialog(new JFrame(), "Wrong answer ! Try again.", "Oh no !",JOptionPane.INFORMATION_MESSAGE);
			        	if (points==0)
			        	{
			        		JOptionPane.showMessageDialog(new JFrame(), "You ran out of tries. Your score is 0.", "Oh no !",JOptionPane.INFORMATION_MESSAGE);
			        		gameEnd();
			        	}
			    	}
	 		break;
	 		
	 		case 2 :if (e.getSource()==gridC)
			 		{
	 					score=points*(n+1);
			    		JOptionPane.showMessageDialog(new JFrame(), "You figured it out ! Your score is "+ score +".", "Congratulations !",JOptionPane.INFORMATION_MESSAGE);
			    		gameEnd();
			 		}
			    	else
			    	{
			    		points-=1;
			        	JOptionPane.showMessageDialog(new JFrame(), "Wrong answer ! Try again.", "Oh no !",JOptionPane.INFORMATION_MESSAGE);
			        	if (points==0)
			        	{
			        		JOptionPane.showMessageDialog(new JFrame(), "You ran out of tries. Your score is 0.", "Oh no !",JOptionPane.INFORMATION_MESSAGE);
			        		gameEnd();
			        	}
			    	}
	 		break;
	 		
	 		case 3 :if (e.getSource()==gridD)
			 		{
	 					score=points*(n+1);
			    		JOptionPane.showMessageDialog(new JFrame(), "You figured it out ! Your score is "+ score +".", "Congratulations !",JOptionPane.INFORMATION_MESSAGE);
			    		gameEnd();
			 		}
			 		else
			    	{
			    		points-=1;
			        	JOptionPane.showMessageDialog(new JFrame(), "Wrong answer ! Try again.", "Oh no !",JOptionPane.INFORMATION_MESSAGE);
			        	if (points==0)
			        	{
			        		JOptionPane.showMessageDialog(new JFrame(), "You ran out of tries. Your score is 0.", "Oh no !",JOptionPane.INFORMATION_MESSAGE);
			        		gameEnd();
			        	}
			    	}
			        
	 		break;
    	}
	}
}