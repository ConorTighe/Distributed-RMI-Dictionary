package Server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import DictionaryInterface.DictionaryInterface;

public class DictionaryWebServer {
	static String ip = "127.0.0.1";
	private static final int SERVER_PORT = 1099;  
	
	//The boolean value keepRunning is used to control the while loop in the inner class called
	//Listener. The volatile keyword tells the JVM not to cache the value of keepRunning during
	//optimisation, but to check it's value in memory before using it.
	private volatile boolean keepRunning = true;
	
	//A null constructor for the WebServer class
	private DictionaryWebServer(){
		Thread server = new Thread(new Listener(), "Web Server Listener"); //We can also name threads
		server.setPriority(Thread.MAX_PRIORITY); //Ask the Thread Scheduler to run this thread as a priority
		server.start(); //The Hollywood Principle - Don't call us, we'll call you
		System.out.println("Server started and listening on: " + ip + ":" + SERVER_PORT);
	}
	
	//A main method is required to start a standard Java application
	public static void main(String[] args) {
		
			new DictionaryWebServer(); // start web server
		 
	}
	
	
	
	/* The inner class Listener is a Runnable, i.e. a job that can be given to a Thread. The job that
	 * the class has been given is to intercept incoming client requests and farm them out to other
	 * threads. Each client request is in the form of a socket and will be handled by a separate new thread.
	 */
	private class Listener implements Runnable{ //A Listener IS-A Runnable
		
		private BlockingQueue<Thread> queue = new ArrayBlockingQueue<Thread>(10);
		
		//The interface Runnable declare the method "public void run();" that must be implemented
		public void run() {
			int counter = 0; //A counter to track the number of requests
			
			while (keepRunning){ //Loop will keepRunning is true. Note that keepRunning is "volatile"
				try { //Try the following. If anything goes wrong, the error will be passed to the catch block
					
					DictionaryInterface Dictionary = new DictionaryRMIObj();

				    Thread job = new Thread(new RMIRequest(Dictionary), "T-" + counter); //Give the new job to the new worker and tell it to start work

				    //Print to say servers ready to start jobs..
					System.out.println("Server ready.");
				    do{
				    	// Handle the client requests in order of FCFS
					    queue.put(job); // add to blocking queue
					    counter++; //Increment counter
						queue.poll(); // taking from blocking queue
						job.start(); //start last jobs taken out
					  }while(!queue.isEmpty()); // while something is in queue
					
				} catch (IOException e) { //Something nasty happened. We should handle error gracefully, i.e. not like this...
					System.out.println("Error handling incoming request..." + e.getMessage());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}//End of inner class Listener
	
	private class RMIRequest implements Runnable{
		private DictionaryInterface Dictionary; //A specific socket connection between some computer on a network and this programme
	
		
		private RMIRequest(DictionaryInterface request) { //Taking the client socket as a constructor enables the Listener class above to farm out the request quickly
			this.Dictionary = request; //Assign to the instance variable sock the value passed to the constructor. 
		}
		
		
		//The interface Runnable declare the method "public void run();" that must be implemented
        public void run() {
        	
        	//Start the RMI registry on port 1099
			try {
				LocateRegistry.createRegistry(SERVER_PORT);

				//Bind our remote object to the registry with the human-readable name "fileService"
				Naming.rebind("DictionaryService", Dictionary);

			} catch (RemoteException | MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
            
	}//End of inner class RMIRequest
}//End of class WebServer