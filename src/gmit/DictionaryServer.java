package gmit;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class DictionaryServer {

	public static void main(String[] args) throws RemoteException {
		
		DictionaryInterface Dictionary = new DictionaryRMIObj();

		try {
		//Start the RMI registry on port 1099
		LocateRegistry.createRegistry(1099);

		//Bind our remote object to the registry with the human-readable name "fileService"
		Naming.rebind("DictionaryService", Dictionary);

		//Print a message to standard output
		System.out.println("Server ready.");
		
		}catch (Exception e) {
			   System.out.println("Dictionary Server Failed: " + e);
		}
	}

}
