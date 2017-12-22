package Client;

/* I have this builder class for the threads as I thought abstracting each job from a builder class would
 * make it much easier to pass them to a thread pool later on */

public interface WorkerPlan extends Runnable {

	// For threads to run the code
	public void run();
	
	// All threads will need to return a result to user
	public String getServerResult();
	
	// For when we need the threads word input
	public String getWord();
	
	// Let us know what the thread is working on
	public String getJobName();
}
