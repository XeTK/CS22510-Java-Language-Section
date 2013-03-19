package uk.ac.aber.dcs.cs22510.eventmanager.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class FileReader
{
	private ArrayList<String> getFile(File path)
	{
		ArrayList<String> temp = new ArrayList<String>();
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String tempWord = "";
			while ((tempWord = reader.readLine()) != null)
				temp.put(i++,tempWord.toLowerCase());
			reader.close();
		} 
		catch (IOException x) 
		{
		    System.err.println(x);
		}
	}
}
