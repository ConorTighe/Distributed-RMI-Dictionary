package Client;

import java.rmi.Naming;
import DictionaryInterface.DictionaryInterface;

public class Client {

	public static void main(String[] args) throws Exception {
		
		DictionaryInterface Dictonary = (DictionaryInterface) Naming.lookup("rmi://127.0.0.1:1099/DictionaryService");

	}

}