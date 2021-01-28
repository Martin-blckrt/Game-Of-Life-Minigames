package package1;

import java.io.IOException;
import javax.swing.JOptionPane;

public class Main
{
	public static void main (String[] args) throws IOException
	{
		String username = JOptionPane.showInputDialog(null, "Type in your username.");
		if (username == null)
			System.exit(0);
		//If the user doesn't type in his name, the project isn't launched.
		
		Game game = new Game(username);
		Menu menu = new Menu(game);
	}
}