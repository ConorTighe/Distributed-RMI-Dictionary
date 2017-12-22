package Client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* Thread pool that takes care of executing the rmi requests, some error handling added incase the pool its self fails*/
public class WorkerPool {
	
	// Singleton pool and Blocking queue
	private static WorkerPool pool = new WorkerPool();
    private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(50);

    // This project only needs a few threads as its only a replication but for examples sake I used
    // 15 threads with 40 max threads, I choose this amount just to make sure we can get jobs done 
    // before the browser time, wed need more threads when working with a large amount of clients
    private DictionaryThreadPoolExecutor executor = new DictionaryThreadPoolExecutor(15,
                                        50, 5000, TimeUnit.MILLISECONDS, blockingQueue);

    // Set up error handling for when pool itself fails
	private WorkerPool() {
		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
	        @Override
	        public void rejectedExecution(Runnable r,
	                ThreadPoolExecutor executor) {
	            System.out.println("Dictionary Job Rejected : "
	                    + ((WorkerPlan) r).toString());
	            System.out.println("Waiting for a second !!");
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("Wait until queue frees up : "
	                    + ((WorkerPlan) r).toString());
	            executor.execute(r);
	        }
	    });
	}
	
	// Get out clients pool instance
	public static WorkerPool getInstance( ) {
	      return pool;
	}
	
	// For adding one of our Jobs to the Pool for execution by the pool threads
	public String addJob(WorkerPlan job) {
		String result;
        // Adding new job to our queue
        System.out.println("Adding a " + job.getJobName() + " to the queue. Searching for: " + job.getWord());
        executor.execute(job);
        try {
        	// generating computation time
			Thread.sleep(1000); 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println(job.toString()); for debugging
        result = job.getServerResult();
        return result;
	}
}
