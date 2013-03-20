package uk.ac.aber.dcs.cs22510.eventmanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Date;
import uk.ac.aber.dcs.cs22510.eventmanager.cli.Menu;
import uk.ac.aber.dcs.cs22510.eventmanager.io.MyFileReader;
/**
 * This is the start point and execution point of the Manager program
 * @author THR2
 */

public class Start
{
        //Define are static variables to refer back to later
        //We keep the log file that will be refranced from most classes
        static private ArrayList<String> log = new ArrayList<String>();
        static private ArrayList<FileLock> fileLocks = new ArrayList<FileLock>();
	public static void main(String[] args) throws IOException
	{
            //declare the log file
            File logf = new File("log.txt");
            //if the file exists then we reload it into the log arraylist
            if (logf.exists())
                log = new MyFileReader().getFile(new File("log.txt"));
            
            //check if there is enough arguments to load the application
            if (args.length < 7)
            {
                //if not then we print out a message to the console to tell the user how to run the program
                log("Invalid Arguments");
                System.out.println("Application : name.txt nodes.txt courses.txt entrants.txt nodes.txt tracks.txt cp_times_*.txt");
            }
            else
            {
                log("Program Started ");
                //load the menu, while passing in the file locations 
                new Menu(new MyFileReader().getEvent(new File(args[0]), new File(args[1]), new File(args[2]), new File(args[3]), new File(args[4]), new File(args[5]), new File(args[6])));
            }
            log("Program Exited");
            //when the program has finished execution, we unlock the files for the next application to use them 
            for (int i = 0;i <fileLocks.size();i++)
                fileLocks.get(i).release();
            
            //write the log file out for people to read later
            BufferedWriter fw = new BufferedWriter(new FileWriter(logf));
            for (int i = 0;i< log.size();i++)
                fw.write(log.get(i) + "\n");
            fw.close();
        }
        //we have a static method to add the new log message to the file
        public static void log(String message)
        {
            log.add(new Date().toString() + " | " +message);
        }
        //static method to add a new log line to the log file
        public static void addLock(FileLock fl)
        {
            fileLocks.add(fl);
        }
}
