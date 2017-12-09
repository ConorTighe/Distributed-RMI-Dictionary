package Server;
import java.rmi.*;
import java.rmi.server.*;
import DictionaryInterface.DictionaryInterface;

public class DictionaryRMIObj extends UnicastRemoteObject implements DictionaryInterface {

	private static final long serialVersionUID = 1L;

	public DictionaryRMIObj() throws RemoteException {
		super();
	}

	public String findWord(String w) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
