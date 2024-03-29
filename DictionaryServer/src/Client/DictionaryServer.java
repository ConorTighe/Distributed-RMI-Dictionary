package Client; // package name is the same as client side package as it will throw errors if its not

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class DictionaryServer {

	public static void main(String[] args) throws RemoteException {

		try {
		// 	Create remote object	
		DictionaryInterface Dictionary = new DictionaryRMIObj();
		
		//Start the RMI registry on port 1099
		LocateRegistry.createRegistry(1099);

		//Bind our remote object to the registry with the human-readable name "DictionaryService"
		Naming.rebind("DictionaryService", Dictionary);

		//Print a message to standard output
		System.out.println("Server ready.");
		
		}catch (Exception e) {
			   System.out.println("Dictionary Server Failed: " + e);
		}
	}

}
