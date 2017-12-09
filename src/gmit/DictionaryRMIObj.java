package gmit;
import java.rmi.*;
import java.rmi.server.*;

public class DictionaryRMIObj extends UnicastRemoteObject implements DictionaryInterface {

	private static final long serialVersionUID = 1L;

	public DictionaryRMIObj() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String findWord(String w) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
