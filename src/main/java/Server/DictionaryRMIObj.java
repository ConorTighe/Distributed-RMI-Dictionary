package Server;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Map;

// Extend UnicastRemoteObject to make available to network and use interface as middleware
public class DictionaryRMIObj extends UnicastRemoteObject implements DictionaryInterface {

	private static final long serialVersionUID = 1L;
	
	// Hash map for storing values from text file
	Map<String, String> dictionary = new HashMap<String, String>();
	// Change this to the appropriate location in project when testing the server
	FileReader file = new FileReader("\\src\\main\\resources\\dictionary");
	
	public DictionaryRMIObj() throws IOException {
		super();
		// I throw all the words and definitions into a HashMap to make them easier to deal with and manipulate
        BufferedReader in = new BufferedReader(file);
        String line = "";
        while ((line = in.readLine()) != null) {
            String parts[] = line.split("  ");
            dictionary.put(parts[0].toLowerCase(), parts[1]);
        }
        in.close();
	}

	public String lookup(String s) throws RemoteException {
		
		String result;
		// Get clients word from map
	    result = dictionary.get(s);
	    
	    if(result == null) {
	    	result = "String not found";
	    }
		
		return result;
	}

	public String deleteWord(String w) throws RemoteException {
		
		String result;
		try {
			// Delete clients word from map
			dictionary.remove(w);
			result = "String removed from dictionary";
		}catch(Exception e) {
			result = "Problem removing string: " + e;
		}
		return result;
	}
	
	public String EditWord(String w, String newWord) throws RemoteException {
		String result;
		try {
			String definition = dictionary.get(w);
			// Remove old word and replace with new one
			dictionary.remove(w);
			dictionary.put(newWord, definition);
			result = w + " changed to " + newWord;
		}catch(Exception e) {
			result = "Problem changing string: " + e;
		}
		return result;
	}

	public String EditDefinition(String w, String newDesc) throws RemoteException {
		String result;
		try {
			// Change definition in map
			dictionary.put(w, newDesc);
			result = w + " description changed to " + newDesc;
		}catch(Exception e) {
			result = "Problem changing strings description: " + e;
		}
		return result;
	}
	
}
