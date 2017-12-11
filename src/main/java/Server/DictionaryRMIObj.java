package Server;
import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import DictionaryInterface.DictionaryInterface;

public class DictionaryRMIObj extends UnicastRemoteObject implements DictionaryInterface {

	private static final long serialVersionUID = 1L;
	
	File file = new File("src\\main\\resources\\dictionary.txt");
	
	public DictionaryRMIObj() throws RemoteException {
		super();
	}

	public String findWord(String w) throws RemoteException {
		
		
		String result = null;
		
		try {
		    Scanner scanner = new Scanner(file);
			boolean wordFound = false;
			int lineNum = 0;
		    
		    while (scanner.hasNextLine() && wordFound == false) {
		        String line = scanner.nextLine();
		        if(w.toLowerCase().contains(line.toLowerCase())) { 
		            result = w + " is a word in the dictionary and can be located on line: " + lineNum;
		            wordFound = true;
		        }
		        else {
		        	result = w + " is NOT a word in the dictionary";
		        }
		        lineNum++;
		    }
		    scanner.close();
		} catch(FileNotFoundException e) { 
			result = "Error loading dictionary, Exception: " + e;
		}
		
		return result;
	}
}
