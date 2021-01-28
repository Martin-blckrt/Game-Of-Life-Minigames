package package1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

/*
 * This class is the second level of my game. It contains all the level (GUI and code for the level).
 */

public class Lvl2 extends Applet implements ActionListener
{
	private String username;
	private int points, n, j, score, playagain;
	Random rand;
	JPanel panel, p;
	Game game;
	GridBagLayout gridbag;
	CardLayout cl;
	GridBagConstraints c;
	JButton menubutton, cluebutton, submitbutton, resetbutton, simulbutton, simresetbutton;
	Simulation simul1, simul2;
	Map map1, map2, fmap, smap;
	GamePanel pan1;
	
	public Lvl2 (Game game, CardLayout cl, JPanel p)
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
    	this.playagain = JOptionPane.NO_OPTION;
    	this.map1 = new Map(7, 7);
    	this.map2 = new Map(7, 7);
    	this.fmap = new Map(7, 7);
    	this.smap = new Map(7,7);
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
	
	public void play()
	{
		//This method is the one called in the menu. It contains a pop-up that asks for the difficulty then calls the level.
		String[] options = {"Easy", "Medium", "Hard"};
        n = (Integer)JOptionPane.showOptionDialog(null, "Welcome to level 2 !\nIn this level, you will be given an initial grid and a final grid after 5 generations.\n" + 
        		"You will have to find which alive cells of the initial board have to be dead in order to end up with the final one.\nYour best score on level 2 is : "+ game.getBestscore2() + " .\nWhich difficulty do you want ?",
                "Select difficulty", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
    	levelDisplay();
	}
	
	public void levelDisplay()
	{
		points=5;
		score=0;
		j=0;
		
		map1.makeMap(13);
		fmap = map1.updateMap(5);
		map2 = map1.copyMap();
		map1.addRandomCell((n+1)*2);
		
		/*
		 * Explanation of the block of code before :
		 * A first map (map1) is created, then from it is created another map (fmap) that is the future of map1. Map1 is then copied to map2 before being added
		 * a number of random alive cells. Map2 is now the ancestor of fmap, and map1 is the ancestor to which has been added alive cells.
		 */
		
		pan1 = new GamePanel(map1.getXSize(),map1.getYSize(),map1, false);
     	GamePanel fpan = new GamePanel(fmap.getXSize(),fmap.getYSize(),fmap, true);
     	GamePanel span = new GamePanel(smap.getXSize(),smap.getYSize(),smap, false);
     	
     	panel.setLayout(gridbag);
     	panel.setBackground(new Color(0,230, 230));
     	
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
     	makeLabel("<html>Welcome to level 2 !<br>In this level, you will be given an initial grid and a final grid after 5 generations.<br>"
     			+ "You will have to find which alive cells of the initial board have to be dead in order to end up with the final one.<br>"
     			+ "There are " + (n+1)*2 +" extra cells ont the original grid you have to find.<br>"
     			+ "Just click on a cell to change its state. When you think you have the right answer, click on \"SUBMIT\".<br>"
     			+ "Watch out ! If you ask for a clue it will deduct points from your score.<br>"
     			+ "Your best score on level 2 is : " + game.getBestscore2() + ".</html>", gridbag, c);
     	
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
			     					JOptionPane.showMessageDialog(new JFrame("New Clue"), "One of the cells is located in " + (map1.getCoord(0)+1) + ", " + (map1.getCoord(1)+1) + ".");
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
			     					JOptionPane.showMessageDialog(new JFrame("New Clue"), "One of the cells is located in " + (map1.getCoord(2)+1) + ", " + (map1.getCoord(3)+1) + ".");
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
     	submitbutton.setBackground(new Color(0, 200, 200));
     	submitbutton.addActionListener(new ActionListener()
	     	{
				public void actionPerformed(ActionEvent e)
				{
					//The map he has just finished to modify will update on 5 generations to reveal if its the right answer.
					simul1 = new Simulation (map1);
					simul1.start(5);
					//Provided the player has found the alive cells to kill, map1 should be equal to map2.
					if (map1.isEqual(map2))
					{
						score = points*(n+1)*4;
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
     	
        resetbutton = new JButton("RESET INITIAL GRID");
     	resetbutton.setBackground(new Color(0, 200, 200));
     	resetbutton.addActionListener(new ActionListener()
	     	{
				public void actionPerformed(ActionEvent e)
				{
					map1.returnInitial();
					for (int i=0; i<(n+1)*2; i++)
					{
						map1.cell[map1.getCoord(2*i)][map1.getCoord(2*i+1)].setAlive(true);
					}
					map1.plzMakeItWork();
					//In order to reset map1, it has to look as it was before the users modifications. We get the initial grid, then add the same cells as before.
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
     	simulbutton.setBackground(new Color(0, 200, 200));
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
     	simresetbutton.setBackground(new Color(0, 200, 200));
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
	    		if (score>game.getBestscore2())
	        		game.setBestscore2(score);
	    		cl.show(p, "Menu");
	    }
	
	public void actionPerformed(ActionEvent e) {
		
		if (score>game.getBestscore2())
    		game.setBestscore2(score);
		cl.show(p, "Menu");
	}
}