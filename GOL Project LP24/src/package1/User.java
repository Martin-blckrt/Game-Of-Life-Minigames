package package1;

/*
 * This class contains the users and their scores. 
 */

public class User
{
	private int score;
	private String username;
	
	public User (String username, int score)
	{
		this.score=score;
		this.username=username;
	}
	
	public int getScore ()
	{
		return score;
	}
	
	public String getUsername ()
	{
		return username; 
	}
}
