package Client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WorkerPool {
	
	Integer threadCounter = 0;
    BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(50);

    DictionaryThreadPoolExecutor executor = new DictionaryThreadPoolExecutor(10,
                                        20, 5000, TimeUnit.MILLISECONDS, blockingQueue);

	public WorkerPool() {
		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
	        @Override
	        public void rejectedExecution(Runnable r,
	                ThreadPoolExecutor executor) {
	            System.out.println("Job Rejected : "
	                    + ((WorkerPlan) r).toString());
	            System.out.println("Waiting for a second !!");
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("Lets add another time : "
	                    + ((WorkerPlan) r).toString());
	            executor.execute(r);
	        }
	    });
	}
	
	public void addLookup(LookupWorker job) {
		threadCounter++;
        // Adding new lookup job
        System.out.println("Adding a Lookup job to the queue. Searching for: " + job.getWord() + " Job number: " + threadCounter);
        executor.execute(job);
	}
}
