package DictionaryInterface;

import java.rmi.*;

public interface DictionaryInterface extends Remote {
	
	public String findWord(String w) throws RemoteException;
	
}
