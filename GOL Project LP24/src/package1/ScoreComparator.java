package package1;

import java.util.Comparator;

/*
 * This class is the comparator that sorts the users by their score. It helps with the leaderboard.
 */

public class ScoreComparator implements Comparator<User>
{
	public int compare(User u1, User u2)
	{
		return (int) (u2.getScore()-u1.getScore());
	}
}