package DictionaryInterface;

import java.rmi.*;

public interface DictionaryInterface extends Remote {
	
	public String lookup(String s) throws RemoteException;
	
	public String deleteWord(String w) throws RemoteException;
	
	public String EditWord(String w, String newWord) throws RemoteException;
	
	public String EditDefinition(String w , String newDesc) throws RemoteException;
	
}
