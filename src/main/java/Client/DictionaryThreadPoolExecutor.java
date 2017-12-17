package Client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* Customizing the thread pool will make it easier to track whos started, finished and failed using the 
 * before and after execution methods
 */
 
public class DictionaryThreadPoolExecutor extends ThreadPoolExecutor {
 
    public DictionaryThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
            long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
 
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println("Thread " + t + " is attepting a new job");
    }
 
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t != null) {
            System.out.println("Thread didnt finish its job properly, error: " + t);
        }
        System.out.println("Thread is finished its job");
    }
 
}