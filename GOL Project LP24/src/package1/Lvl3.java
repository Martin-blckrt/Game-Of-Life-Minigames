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
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * This class is the second level of my game. It contains all the level (GUI and code for the level).
 */

public class Lvl3 extends Applet implements ActionListener
{
	private String username;
	private int points, n, j, score, size, cells, playagain;
	Random rand;
	Map map1, map2, fmap, smap, amap;
	Simulation simul1, simul2;
	JPanel panel, p;
	Game game;
	GridBagLayout gridbag;
	CardLayout cl;
	GridBagConstraints c;
	JButton menubutton, cluebutton, simulbutton, simresetbutton, submitbutton, resetbutton;
	
	public Lvl3 (Game game, CardLayout cl, JPanel p)
	{
		this.p=p;
    	this.cl=cl;
		this.game = game;
		this.score=0;
		this.rand=new Random();
    	this.panel = new JPanel(gridbag);
    	this.game = new Game(username);
    	this.gridbag = new GridBagLayout();
    	this.c = new GridBagConstraints();
    	this.points=5;
    	this.n=0;
    	this.j=0;
    	this.playagain=JOptionPane.NO_OPTION;
	}
	
	protected JButton makeButton(String name, GridBagLayout gridbag, GridBagConstraints c)
    {
        JButton button = new JButton(name);
        button.addActionListener(this);
        button.setBackground(Color.WHITE);
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
	
	public void play ()
	{
		//This method is the one called in the menu. It contains a pop-up that asks for the number of generations and grid size then calls the level.
		Integer[] options = {2, 3, 4};
        n = (Integer)JOptionPane.showOptionDialog(null, "Welcome to level 3 !\nIn this level, you pick a grid size and a number of generations.\nFrom there, "
        		+ "you will be given a final grid and a number of cells to place. Your aim is to place those cells in order to end up with the final shape."
        		+ "\nWatch out ! The number of generations you pick is related to the maximum score you can get on the level, but the more "
        		+ "generations, the harder it is !\nYour best score on level 1 is : "+ game.getBestscore3() + " .\nHow many generations do you want ?",
                "How many generations do you want", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
        
        Integer[] options2 = {4, 5, 6};
        size = (Integer)JOptionPane.showOptionDialog(null, "Which grid size do you want ?",
                "Pick a grid size", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options2, options2[0]);
    	levelDisplay();
	}
	
	public void levelDisplay()
	{
		points=5;
		score=0;
		j=0;
		
		size=size+4;
		n=n+2;
		
		this.map1 = new Map(size, size);
    	this.map2 = new Map(size, size);
    	this.fmap = new Map(size, size);
    	this.smap = new Map(size, size);
    	this.amap = new Map(size, size);
		
		cells = size*size/3 + rand.nextInt(size);
		map2.makeMap(cells);
		fmap=map2.updateMap(n);
				
		GamePanel pan1 = new GamePanel(map1.getXSize(),map1.getYSize(),map1, false);
     	GamePanel fpan = new GamePanel(fmap.getXSize(),fmap.getYSize(),fmap, true);
     	GamePanel span = new GamePanel(smap.getXSize(),smap.getYSize(),smap, false);
     	
     	panel.setLayout(gridbag);
     	panel.setBackground(new Color(0, 164, 255));
     	
     	c.insets = new Insets(10, 10, 10, 10);
     	c.fill = GridBagConstraints.BOTH;
     	
     	c.weightx=0;
     	c.gridx=0;
     	c.weighty=0;
     	c.gridwidth=GridBagConstraints.REMAINDER;
     	c.gridheight = 2;
     	menubutton = makeButton("BACK TO MENU", gridbag, c);
     	
     	c.weightx=1;
     	c.weighty=0;
     	c.gridwidth=11;
     	c.gridheight=2;
     	makeLabel("<html>Welcome to level 3 !<br>In this level, you picked a grid size and a number of generations.<br>Below is a final grid and a blank grid.<br>"
     			+ "Your job is to find which " + cells + " cells should be alive in the empty grid, in order to end up with the final grid after " + n + " genrations<br>"
     			+ "Just click on a cell to change its state. When you think you have the right answer, click on \"SUBMIT\".<br>"
     			+ "Watch out ! If you ask for a clue it will deduct points from your score.<br>"
     			+ "Your best score on level 3 is : " + game.getBestscore3() + ".</html>", gridbag, c);
     	
     	c.weightx=0;
     	c.weighty=0;
     	c.gridx=50;
     	c.gridwidth= GridBagConstraints.REMAINDER;
     	cluebutton = new JButton("ASK FOR CLUE");
     	cluebutton.setBackground(Color.WHITE);
     	cluebutton.addActionListener(new ActionListener ()
     		{
     			public void actionPerformed(ActionEvent e)
     			{
	     			j+=1;
	     			switch (j)
	     			{
	     				case 1 : if (points>1)
		     					{
			     					JOptionPane.showMessageDialog(new JFrame("New Clue"), "One of the cells is located in " + (map2.getCoord(0)+1) + ", " + (map2.getCoord(1)+1) + ".");
									cluebutton.setBackground(Color.LIGHT_GRAY);
									points-=1;
		     					}
			     				else
			     				{
			     					JOptionPane.showMessageDialog(new JFrame(), "You don't have enough points for a clue.", "Impossible",  JOptionPane.ERROR_MESSAGE);
			     					cluebutton.setBackground(Color.GRAY);
			     				}
	     				break;
	     				
	     				case 2 : if (points>1)
			 					{
			     					JOptionPane.showMessageDialog(new JFrame("New Clue"), "One of the cells is located in " + (map2.getCoord(2)+1) + ", " + (map2.getCoord(3)+1) + ".");
									cluebutton.setBackground(Color.GRAY);
									points-=1;
			 					}
			     				else
			     				{
			     					JOptionPane.showMessageDialog(new JFrame(), "You don't have enough points for a clue.", "Impossible",  JOptionPane.ERROR_MESSAGE);
			     					cluebutton.setBackground(Color.GRAY);
			     				}
	     				break;
	     				
	     				default : JOptionPane.showMessageDialog(new JFrame(), "You have already used your clues for the round", "Impossible",  JOptionPane.ERROR_MESSAGE);
	     				break;
	     			}
     			}
 			});
        gridbag.setConstraints(cluebutton, c);
        panel.add(cluebutton);
	
        makeLabel("<html>The grid right below is the simulation grid.<br>"
        		+ "Since this level is pretty difficult, use it to test out your theories before submitting them.<br>"
        		+ "Just click on a cell to change its state, and when you're all set click the button \"SIMULATE\".<br>"
        		+ "Click \"RESET\" to start another simulation if you need it.</html>", gridbag, c);
     	
     	c.weightx=2;
     	c.weighty=1;
     	c.gridwidth=4;
     	c.gridheight=30;
     	c.gridx=0;
     	map1.plzMakeItWork();
     	displayMap(pan1, gridbag, c);
     	c.gridheight=2;
     	c.weighty=0;
     	makeLabel("Initial Grid", gridbag, c);
     	c.weighty=1;
     	c.gridx=5;
     	c.gridheight = 30;
     	fmap.plzMakeItWork();
     	displayMap(fpan, gridbag, c);
     	c.gridheight=2;
     	c.weighty=0;
     	makeLabel("Final Grid", gridbag, c);
	
     	c.gridx=0;
     	c.gridheight=2;
     	c.weightx=2;
     	c.gridwidth=50;
     	
     	submitbutton = new JButton("SUBMIT");
     	submitbutton.setBackground(new Color(0, 116, 180));
     	submitbutton.setForeground(Color.white);
     	submitbutton.addActionListener(new ActionListener()
     		{
				public void actionPerformed(ActionEvent e)
				{
					simul1 = new Simulation (map1);
					simul1.start(n);
					amap=map1.updateMap(n);
					
					/*
					 * amap is the grid the user created after the n generations. It is compared with the correct final map.
					 */
					
					if (amap.isEqual(fmap))
					{
						score = points*size*n;
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
				}
     		});
     	gridbag.setConstraints(submitbutton, c);
        panel.add(submitbutton);
     	
        resetbutton = new JButton("RESET GRID");
     	resetbutton.setBackground(new Color(0, 116, 180));
     	resetbutton.setForeground(Color.white);
     	resetbutton.addActionListener(new ActionListener()
     		{
				public void actionPerformed(ActionEvent e)
				{
					map1.clearMap();
					map1.refresh(false);
				}
     		});
     	gridbag.setConstraints(resetbutton, c);
        panel.add(resetbutton);
     	
     	c.weightx=1;
     	c.weighty=1;
     	c.gridwidth=GridBagConstraints.REMAINDER;
     	c.gridheight=30;
     	c.gridx=50;
     	smap.plzMakeItWork();
     	displayMap(span, gridbag, c);
     	
     	c.weighty=0;
     	c.gridheight=2;
     	c.gridwidth=5;
     	
     	simulbutton = new JButton("SIMULATE");
     	simulbutton.setBackground(new Color(0, 116, 180));
     	simulbutton.setForeground(Color.white);
     	simulbutton.addActionListener(new ActionListener()
     		{
     			public void actionPerformed(ActionEvent e)
     			{
					simul2 = new Simulation (smap);
					simul2.start(1);
				}
     		});
     	gridbag.setConstraints(simulbutton, c);
        panel.add(simulbutton);
     	
     	c.gridwidth=GridBagConstraints.REMAINDER;
     	c.gridx=50;
     	
     	simresetbutton = new JButton("RESET");
     	simresetbutton.setBackground(new Color(0, 116, 180));
     	simresetbutton.setForeground(Color.white);
     	simresetbutton.addActionListener(new ActionListener()
     		{
				public void actionPerformed(ActionEvent e)
				{
					smap.clearMap();
					smap.refresh(false);
				}
     		});
     	gridbag.setConstraints(simresetbutton, c);
        panel.add(simresetbutton);
	
	}
	
	public void gameEnd()
    {
    	submitbutton.setEnabled(false);
    	resetbutton.setEnabled(false);
    	cluebutton.setEnabled(false);
    	simulbutton.setEnabled(false);
    	simresetbutton.setEnabled(false);
    	playagain = JOptionPane.showConfirmDialog(null, "Your score this round was " + score + ". Do you wish to return to the menu ?", "Back to menu ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	if (playagain==0)
    		if (score>game.getBestscore3())
    			game.setBestscore3(score);
    		cl.show(p, "Menu");
    }

	public void actionPerformed(ActionEvent e) {
	
	if (score>game.getBestscore3())
		game.setBestscore3(score);
	cl.show(p, "Menu");
	}
}