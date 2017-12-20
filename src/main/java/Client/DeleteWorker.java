package Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class DeleteWorker implements WorkerPlan {

	private String word;
	private String serverResult;
	private String jobName;
	private long threadId;
	
	public DeleteWorker(String w) {
		super();
		this.word = w;
		jobName = "Delete Job";
	}

	@Override
	public void run(){
		DictionaryInterface Dictonary = null;
		try {
			Dictonary = (DictionaryInterface) Naming.lookup("rmi://127.0.0.1:1099/DictionaryService");
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			serverResult = Dictonary.deleteWord(word);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(serverResult); 
		threadId = Thread.currentThread().getId();
		try {
			// Sleep for a while to simulate real lookup async
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
