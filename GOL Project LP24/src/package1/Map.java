package package1;

import java.awt.Color;
import java.util.Random;

public class Map {
	
	private int xsize;
	private int ysize;
	private int[] coord;
	public Cell[][] cell, initialcells;
	
	Map(int xsize ,int ysize)
	{
		this.xsize = xsize;
		this.ysize = ysize;
		this.coord = new int[100];
		cell = new Cell[this.xsize][this.ysize];
		initialcells = new Cell[this.xsize][this.ysize];
		fillMap();
	}

	public int getXSize() {
		return xsize;
	}

	public int getYSize()
	{
		return ysize;
	}
	
	public void setXSize(int xsize)
	{
		this.xsize = xsize;
	}

	public void setYSize(int ysize) {
		this.ysize = ysize;
	}
	
	public int getCoord (int index) {
		return coord[index];
	}
	
	private void fillMap()
	{
		//This method fills the map with the cells and the buttons, and also keeps the initial cells the map had before the user tampered with it.
		for(int i = 0;i<xsize ;i++)
		{
			for(int j = 0 ;j<ysize ;j++)
			{
				cell[i][j] = new Cell();
				cell[i][j].setButton(new CellButton(cell[i][j],false));
				initialcells[i][j] = new Cell();
				initialcells[i][j].setButton(new CellButton(initialcells[i][j],false));
			}
		}
	}
	
	public void nextGeneration()
	{
		//This method generates the next generation.
		for(int i = 0;i<xsize ;i++)
		{
			for(int j = 0 ;j<ysize ;j++)
			{
				cell[i][j].nextState(computeNeighbors(i,j));
			}
		}
	}
	
	private int computeNeighbors(int i, int j)
	{
		//This method calculates the number of neighbors around the cell in question.
		int n = 0;
		for(int k = i-1 ; k<=i+1 ;k++)
		{
			for(int l = j-1; l<=j+1 ;l++)
			{
				if(k!=i || l!=j)
				{
					if(isNeighborAlive(k,l))
					{
						n += 1;
					}
				}
			}
		}
		return n;
	}
	
	private boolean isNeighborAlive(int k, int l)
	{
		try
		{
			return cell[k][l].isAlive();
		}
		catch(java.lang.ArrayIndexOutOfBoundsException er)
		{
			return false;
		}
	}
	
	public void refresh(boolean b)
	{
		for(int i = 0;i<xsize ;i++)
		{
			for(int j = 0 ;j<ysize ;j++)
			{
				cell[i][j].refresh(b);
			}
		}
	}
	
	public void makeMap(int n)
	{
		//This method creates a grid with n numbers of alive cells.
		Random rand = new Random();
	    int x,y;
	    for (int j=0; j<n; j++)
		{
			do
			{
				x = rand.nextInt(xsize);
				y = rand.nextInt(ysize);
				coord[2*j]=x;
				coord[2*j+1]=y;
			}
			while (cell[x][y].isAlive());
			cell[x][y].setAlive(true);
			initialcells[x][y].setAlive(true);
		}
	}
	
	public boolean isEqual (Map map)
	{
		for(int row = 0; row < this.xsize; row++)
        {
           for(int col = 0; col < this.ysize; col++)
           {
	        	if (this.cell[row][col].isAlive()!=map.cell[row][col].isAlive())
	        		return false;
           }
        }
		return true;
	}
	
	public void addRandomCell (int n)
	{
		//This method adds n alive random cells.
		int x,y;
		Random rand = new Random();
	    for (int j=0; j<n; j++)
		{
			do
			{
				x = rand.nextInt(xsize);
				y = rand.nextInt(ysize);
				coord[2*j]=x;
				coord[2*j+1]=y;
			}
			while (cell[x][y].isAlive());
			cell[x][y].setAlive(true);
		}
	}
	
	public void plzMakeItWork ()
	{
		//This method updates the color of the cells. It is used when the map is created "artificially".
		for(int row = 0; row < this.xsize; row++)
        {
           for(int col = 0; col < this.ysize; col++)
           {
        	    if(this.cell[row][col].isAlive())
	       		{
        	    	this.cell[row][col].button.setBackground(Color.BLACK);
	       		}
	       		else
	       		{
	       			this.cell[row][col].button.setBackground(Color.WHITE);
	       		}
           }
        }
	}
	
	public void clearMap()
	{
		//This method is used to set a map to all dead cells for the user to dictate which ones shall be alive.
		int row, col;
		for(row = 0; row < this.xsize; row++)
        {
           for(col = 0; col < this.ysize; col++)
           {
        	   this.cell[row][col].setAlive(false);
        	   this.cell[row][col].setWillbealive(false);
           }
        }
	}
	
	public void returnInitial ()
	{
		//This method sets the cells back to their original state (before the user modifications).
		int row, col;
		for(row = 0; row < this.xsize; row++)
        {
           for(col = 0; col < this.ysize; col++)
           {
        	   cell[row][col].setAlive((initialcells[row][col]).isAlive());
        	   cell[row][col].setWillbealive((initialcells[row][col]).isWillbealive());
           }
        }
	}
	
	public Map copyMap()
	{
		int row, col;
		Map maptemp = new Map(this.xsize, this.ysize);
		maptemp.fillMap();
		for(row = 0; row < this.xsize; row++)
        {
           for(col = 0; col < this.ysize; col++)
           {
        	   maptemp.cell[row][col].setAlive((this.cell[row][col]).isAlive());
        	   maptemp.cell[row][col].setWillbealive((this.cell[row][col]).isWillbealive());
           }
        }
		return maptemp;
	}
	
	public Map updateMap(int gen)
	{
		//This method generates a new map which is the original one updated after gen generations.
		int i;
		Map maptemp = new Map(this.xsize, this.ysize);
		maptemp.fillMap();
		maptemp=this.copyMap();
		
		for (i=0; i<gen; i++)
        {
			maptemp.nextGeneration();
			maptemp.refresh(true);
        }    
        return maptemp;
	}
}
