package Client;
/* This class controls the polling of the requests by using the pool to execute threads until we get a response */
public class ClientService {

	public ClientService() {
		// TODO Auto-generated constructor stub
	}
	
	public String LookupService(String word) {
		String res = null;
		boolean pollServer = true;
        // While loop for polling server
        while(pollServer) {
        	// Get handle on our pool
        	WorkerPool pool = WorkerPool.getInstance( );
        	// Create Job
        	LookupWorker wordWork = new LookupWorker(word.toLowerCase());
        	// Add job to pool
        	res = pool.addJob(wordWork);
        	if(res == null) {
        		try {
        			// Sleep for 10 seconds
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}else {
        		// Stop polling the server
        		pollServer = false;
        	}
        }
        
		return res;
	}

	public String DeleteService(String word) {
		String res = null;
		boolean pollServer = true;
        // While loop for polling server
        while(pollServer) {
        	// Get handle on our pool
        	WorkerPool pool = WorkerPool.getInstance( );
        	// Create Job
        	DeleteWorker wordWork = new DeleteWorker(word.toLowerCase());
        	// Add job to pool
        	res = pool.addJob(wordWork);
        	if(res == null) {
        		try {
        			// Sleep for 10 seconds
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}else {
        		// Stop polling the server
        		pollServer = false;
        	}
        }
        
		return res;
	}
	
	public String WordService(String word, String newWord) {
		String res = null;
		boolean pollServer = true;
        // While loop for polling server
        while(pollServer) {
        	// Get handle on our pool
        	WorkerPool pool = WorkerPool.getInstance( );
        	// Create Job
        	EditWordWorker editWork = new EditWordWorker(word, newWord);
        	// Add job to pool
        	res = pool.addJob(editWork);
        	if(res == null) {
        		try {
        			// Sleep for 10 seconds
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}else {
        		// Stop polling the server
        		pollServer = false;
        	}
        }
        
		return res;
	}
	
	public String DefinitionService(String def, String newDef) {
		String res = null;
		boolean pollServer = true;
        // While loop for polling server
        while(pollServer) {
        	// Get handle on our pool
        	WorkerPool pool = WorkerPool.getInstance( );
        	// Create Job
        	EditDefinitionWorker editDefWork = new EditDefinitionWorker(def, newDef);
        	// Add job to pool
        	res = pool.addJob(editDefWork);
        	if(res == null) {
        		try {
        			// Sleep for 10 seconds
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}else {
        		// Stop polling the server
        		pollServer = false;
        	}
        }
        
		return res;
	}
}
