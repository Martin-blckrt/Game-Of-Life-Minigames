package package1;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

/*
 * This class is the GUI for the grids. The buttons are disposed in a GridLayout to represent a grid. The boolean blocked is the instigator of what happens in CellButton.
 */

public class GamePanel extends JPanel
{
	CellButton[][] b_array;
	Map map;
	private boolean blocked;
	
	GamePanel(int x,int y, Map map, boolean blocked)
	{
		super(new GridLayout(x,y));
		this.map = map;
		this.blocked=blocked;
		setCells(x,y);
	}
	
	private void setCells(int x,int y)
	{
		b_array = new CellButton[x][y];
		
		Border blackline = BorderFactory.createLineBorder(Color.black,1);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridheight = 1;
		c.gridwidth = 1;
		
	    for(int i=0 ;i<x ;i++)
	    {
	    	c.gridx = i;
	    	for(int j=0; j<y; j++)
	    	{
	    		c.gridy = j;
	    		if (blocked)
	    			b_array[i][j] = new CellButton(map.cell[i][j], true);
	    		else
	    			b_array[i][j] = new CellButton(map.cell[i][j], false);
	        	map.cell[i][j].setButton(b_array[i][j]);
	        	b_array[i][j].setBackground(Color.WHITE);
	        	b_array[i][j].setOpaque(true);
	            b_array[i][j].setBorder(blackline);
	    		b_array[i][j].setPreferredSize(new Dimension(10, 10));
	    		
	    		this.add(b_array[i][j],c);
	    	}
	    }
	}
}