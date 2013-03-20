package uk.ac.aber.dcs.cs22510.eventmanager;

import java.io.File;
import java.io.IOException;
import uk.ac.aber.dcs.cs22510.eventmanager.cli.Menu;
import uk.ac.aber.dcs.cs22510.eventmanager.io.MyFileReader;

public class Start
{
	public static void main(String[] args) throws IOException
	{
            if (args.length < 7)
                System.out.println("Application : name.txt nodes.txt courses.txt entrants.txt nodes.txt tracks.txt cp_times_*.txt");
            else
                new Menu(new MyFileReader().getEvent(new File(args[0]), new File(args[1]), new File(args[2]), new File(args[3]), new File(args[4]), new File(args[5]), new File(args[6])));
        }
}
