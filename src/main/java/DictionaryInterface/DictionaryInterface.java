package DictionaryInterface;

import java.rmi.*;

public interface DictionaryInterface extends Remote {
	
	public String findWord(String w) throws RemoteException;
	/*
	public String DeleteWord(String w) throws RemoteException;
	
	public String EditWord(String w) throws RemoteException;
	
	public String PrintPage(String w) throws RemoteException;
	*/
}
