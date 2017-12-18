package Client;

/* I have this builder class for the threads as I thought abstracting each job from a builder class would
 * make it much easier to pass them to a thread pool later on */

public interface WorkerPlan extends Runnable {

	public void run();
	
	public String getServerResult();
	
	public String getWord();
	
	public String getJobName();
}
