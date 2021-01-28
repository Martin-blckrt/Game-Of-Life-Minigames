package package1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/*
 * This is the class that ties the whole project together. Game is called once in the main and is then passed as an attribute of every level.
 * It contains the scores of the current user and the methods that make the leaderboard.
 */

public class Game
{
	static int totalscore, bestscore1, bestscore2, bestscore3;
	private String username, name, buffer;
	private TreeSet<User> tree;
	static String[] users;
	static int[] scores;
	
	public Game (String username)
	{
		this.username=username;
		Game.bestscore1=0;
		Game.bestscore2=0;
		Game.bestscore3=0;
		Game.users = new String[10];
		Game.scores = new int[10];
		this.name=null;
		Game.totalscore=0;
	}
	
	void saveScore() throws IOException
	{
		/*
		 * This method writes in the "Leaderboard.txt" file the username and score of the user under the existing names and scores.
		 */
		
		Scanner reader = new Scanner(new FileInputStream("Leaderboard.txt"));
		StringBuilder t = new StringBuilder();
		int registeredScore = 0;
		while(reader.hasNextLine())
		{
			if(!reader.hasNext(username))
			{
				t.append(reader.nextLine() + "\n");
			}
			else
			{
				reader.next();
				reader.next();
				registeredScore = reader.nextInt();
				if(totalscore < registeredScore)
					totalscore = registeredScore;
				reader.nextLine();
			}
		}
		FileWriter f = new FileWriter("Leaderboard.txt");
		f.write(t.toString());
		f.write(username + ":" + totalscore + "\n");
		f.close();
	}
	
	public void sort() throws FileNotFoundException, IOException
	{
		/*
		 * This method does two things : it adds the users and scores to the TreeSet that implements the comparator, then fills the two arrays with the top 10 players
		 * and scores.
		 */
		
		tree = new TreeSet<User>(new ScoreComparator());
		int score=0, r=0, i=0;
		User user;
		
		BufferedReader br = new BufferedReader(new FileReader("Leaderboard.txt"));
		while ((buffer = br.readLine()) !=null)
		{ 
			/*
			 * This loop fills the TreeSet. It puts the String before the ':' in as the name and converts to an integer (so that the comparator works)
			 * then puts the String after in as the score.
			*/
			
			r = buffer.indexOf(':');
			name = buffer.substring(0, r);
			score = Integer.parseInt(buffer.substring(r+1));
			user = new User(name, score);
			tree.add(user);
		}
		br.close();
		for(User u:tree)
		{   
			// The set is automatically sorted out by score according to the rules used by the comparator. It then fills the 10 spots in the arrays with the top 10.
			
			users[i]=u.getUsername();
			scores[i]=u.getScore();
			if (++i==10) //The "spelling" "++1" first increments the variable, then compares it. 
				break;
		}
	}
	
	public int getTotalscore() {
		return totalscore;
	}

	public int getBestscore1() {
		return bestscore1;
	}

	public int getBestscore2() {
		return bestscore2;
	}

	public int getBestscore3() {
		return bestscore3;
	}

	public String getUsername() {
		return username;
	}

	public void setTotalscore(int totalscore) {
		Game.totalscore = totalscore;
	}

	public void setBestscore1(int bestscore1) {
		Game.bestscore1 = bestscore1;
	}

	public void setBestscore2(int bestscore2) {
		Game.bestscore2 = bestscore2;
	}

	public void setBestscore3(int bestscore3) {
		Game.bestscore3 = bestscore3;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getUsers() {
		return users;
	}

	public int[] getScores() {
		return scores;
	}
}