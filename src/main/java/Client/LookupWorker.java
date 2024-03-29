package Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
/* Thread that looks up words on the server using RMI naming service */
public class LookupWorker implements WorkerPlan {

	private String word;
	private String jobName;
	private String serverResult;
	private long threadId;
	
	public LookupWorker(String w) {
		super();
		this.word = w;
		jobName = "Lookup Job";
	}

	@Override
	public void run(){
		DictionaryInterface Dictonary = null;
		try {
			// Connect to Naming Service
			Dictonary = (DictionaryInterface) Naming.lookup("rmi://127.0.0.1:1099/DictionaryService");
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// Get lookup word results from remote
			serverResult = Dictonary.lookup(word);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println(serverResult); 
			threadId = Thread.currentThread().getId();
	}

	public String getServerResult() {
		return serverResult;
	}

	public String getWord() {
		return word;
	}

	public String getJobName() {
		return jobName;
	}

	@Override
	public String toString() {
		return jobName + " [Id= " + threadId + "word= " + word + ", serverResult= " + serverResult + "]";
	}	
	
}
