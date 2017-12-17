package Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import DictionaryInterface.DictionaryInterface;

public class LookupWorker implements WorkerPlan {

	private String word;
	private String serverResult;
	private long threadId;
	
	public LookupWorker(String w) {
		this.word = w;
	}

	public void run(){
		DictionaryInterface Dictonary = null;
		try {
			Dictonary = (DictionaryInterface) Naming.lookup("rmi://127.0.0.1:1099/DictionaryService");
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			serverResult = Dictonary.findWord(word);
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

	@Override
	public String toString() {
		return "WordWorker [Id= " + threadId + "word= " + word + ", serverResult= " + serverResult + "]";
	}	
	
}
