package Client;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* Customizing the thread pool will make it easier to track whos started, finished and failed using the 
 * before and after execution methods, I added the queue map here to track the job number and thread doing that job
 */
 
public class DictionaryThreadPoolExecutor extends ThreadPoolExecutor {
 
	// Mapping Thread and Job Number for queuing 
	private int jobNum;
	private Map<Integer, Thread> queue = new HashMap<Integer, Thread>();
	
    public DictionaryThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
            long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
 
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println("Executing a new dictionary job");
        jobNum++;
        queue.put(jobNum,t);
    }
 
    @Override
    protected void afterExecute(Runnable r, Throwable er) {
        super.afterExecute(r, er);
        if (er != null) {
            System.out.println("Thread didnt finish its job properly, error: " + er);
            
        }
        System.out.println("Thread " + queue.get(jobNum).getName() + " is finished executing its dictionary job");
    }
 
}