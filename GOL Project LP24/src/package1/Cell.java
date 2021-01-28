package package1;

import java.awt.Color;

 /* 
  * This class's purpose is to deal with the current generation (isAlive(), setAlive()) but also to generate the next generation of cells
  * (isWillbealive(), setWillbealive())
*/

public class Cell {

	private boolean alive;
	private boolean willbealive;
	public CellButton button;
	
	public Cell()
	{
		alive = false;
		willbealive = false;
		button = null;
	}
	
	public void setButton(CellButton button)
	{
		this.button = button;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isWillbealive() {
		return willbealive;
	}

	public void setWillbealive(boolean willbealive) {
		this.willbealive = willbealive;
	}
	
	public boolean getWillbealive() {
		return willbealive;
	}
	
	public Cell nextState(int n)
	{
		/*
		 * This method calculates the next generation with the rules of the game of life
		 */
		
		if(alive)
		{
			if(n<2 || n>3)
			{
				willbealive = false;
			}
			else
			{
				willbealive = true;
			}
		}
		else
		{
			if(n==3)
			{
				willbealive = true;
			}
			else
			{
				willbealive = false;
			}
		}
		return this;
	}
	
	public void refresh(boolean b)
	{
		/*
		 * I sometimes needed this method with only the loop and sometimes with the instruction before, depending if I only wanted to refresh the button's colors
		 * or if I wanted to proceed to the next generation as well. In order to not create two methods with only one line changing, I added a boolean as an attribute.
		 */
		alive = willbealive;
		if (!b)
		{
			if(alive)
			{
				button.setBackground(Color.BLACK);
			}
			else
			{
				button.setBackground(Color.WHITE);
			}
		}
	}
}