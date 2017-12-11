package Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import DictionaryInterface.DictionaryInterface;

public class WordWorker {

	String word;
	String serverResult;
	long threadId;
	
	public WordWorker(String w) {
		this.word = w;
	}

	public void run() throws MalformedURLException, RemoteException, NotBoundException{
		DictionaryInterface Dictonary = (DictionaryInterface) Naming.lookup("rmi://127.0.0.1:1099/DictionaryService");
		serverResult = Dictonary.findWord(word);
		System.out.println(serverResult); 
		threadId = Thread.currentThread().getId();
	}

	public String getServerResult() {
		return serverResult;
	}

	@Override
	public String toString() {
		return "WordWorker [Id= " + threadId + "word= " + word + ", serverResult= " + serverResult + "]";
	}	
	
}
