package Client; // package name is the same as client side package as it will throw errors if its not

import java.rmi.*;
// middleware
public interface DictionaryInterface extends Remote {
	
	// For Looking up words one the server side
	public String lookup(String s) throws RemoteException;
	
	// For Deleting words one the server side
	public String deleteWord(String w) throws RemoteException;
	
	// For Editing words one the server side
	public String EditWord(String w, String newWord) throws RemoteException;
	
	// For Editing definitions one the server side
	public String EditDefinition(String w , String newDesc) throws RemoteException;
	
}
