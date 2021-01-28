package package1;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/*
 * This class is dedicated to the button that make the grids. Everything is pretty explicit, except for the boolean "blocked" that is an attribute of the constructor.
 * If blocked is false, then the MouseListener is added. This enables to have grids that the user cannot change.
 */

public class CellButton extends JButton implements MouseListener
{
	Cell c;
	
	CellButton(Cell c, boolean blocked)
	{
		super();
		this.c = c;
		if (!blocked)
			this.addMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(this.getBackground()==Color.GRAY)
		{
			this.setBackground(Color.BLACK);
			c.setAlive(true);
		}
		else
		{
			this.setBackground(Color.WHITE);
			c.setAlive(false);
		}
	}

	public void mousePressed(MouseEvent e)
	{
		
	}

	public void mouseReleased(MouseEvent e)
	{
		
	}

	public void mouseEntered(MouseEvent e)
	{
		if(this.getBackground()==Color.WHITE)
		{
			this.setBackground(Color.GRAY);
		}
		else
		{
			this.setBackground(Color.DARK_GRAY);
		}
	}

	public void mouseExited(MouseEvent e)
	{
		if(this.getBackground()==Color.GRAY || this.getBackground()==Color.WHITE)
		{
			this.setBackground(Color.WHITE);
		}
		else
		{
			this.setBackground(Color.BLACK);
		}
	}
}
