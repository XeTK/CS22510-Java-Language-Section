package uk.ac.aber.dcs.cs22510.eventmanager;

import java.io.File;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Event;
import uk.ac.aber.dcs.cs22510.eventmanager.io.MyFileReader;

public class Start
{
	public static void main(String[] args)
	{
            Event tevent = new MyFileReader().getEvent(new File("Data/name.txt"), new File("Data/nodes.txt"), new File("Data/courses.txt"), new File("Data/entrants.txt"), new File("Data/nodes.txt"), new File("Data/tracks.txt"), new File("Data/cp_times_2.txt"));
            System.out.println();
            /*CopyNodes for entrants, add flags to them to see if correct, add method to check they are done in correct order and time...*/
        }
}
