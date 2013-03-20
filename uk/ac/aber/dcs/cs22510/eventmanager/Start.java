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

public class Start
{
        static private ArrayList<String> log = new ArrayList<String>();
        static private ArrayList<FileLock> fileLocks = new ArrayList<FileLock>();
	public static void main(String[] args) throws IOException
	{
            File logf = new File("log.txt");
            if (logf.exists())
                log = new MyFileReader().getFile(new File("log.txt"));
            if (args.length < 7)
            {
                log("Invalid Arguments");
                System.out.println("Application : name.txt nodes.txt courses.txt entrants.txt nodes.txt tracks.txt cp_times_*.txt");
            }
            else
            {
                log("Program Started ");
                new Menu(new MyFileReader().getEvent(new File(args[0]), new File(args[1]), new File(args[2]), new File(args[3]), new File(args[4]), new File(args[5]), new File(args[6])));
            }
            log("Program Exited");
            for (int i = 0;i <fileLocks.size();i++)
                fileLocks.get(i).release();
            BufferedWriter fw = new BufferedWriter(new FileWriter(logf));
            for (int i = 0;i< log.size();i++)
                fw.write(log.get(i) + "\n");
            fw.close();
        }
        public static void log(String message)
        {
            log.add(new Date().toString() + " | " +message);
        }
        public static void addLock(FileLock fl)
        {
            fileLocks.add(fl);
        }
}
