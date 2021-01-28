package package1;

import java.util.Timer;
import java.util.TimerTask;

/*
 * This class deals with the simulation aspect of the grids upate, especially the timing between 2 generations.
 */

public class Simulation
{
	private Map map;
	
	Simulation(Map map)
	{
		this.map = map;
	}
	
	public void start(int n)
	{
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask()
		{
			int i=0;
			public void run()
			{
				map.nextGeneration();
				map.refresh(false);
				i++;
				if (i==n)
				{
					timer.cancel();
					timer.purge();
				}
			}
		};
		timer.scheduleAtFixedRate(task,1000,2000);
	}
}
