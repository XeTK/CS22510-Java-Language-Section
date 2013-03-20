package uk.ac.aber.dcs.cs22510.eventmanager.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileReader;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Scanner;

import uk.ac.aber.dcs.cs22510.eventmanager.Start;
import uk.ac.aber.dcs.cs22510.eventmanager.data.*;
/**
 * This class reads in all the relevant files into the data objects they are needed for, along with building the relevant data structures for carring out the management of events
 * @author THR2
 */
public class MyFileReader
{
        //Method to read in a file into a list for later use
	public static ArrayList<String> getFile(File path)
	{
            //add a new entry to the log file
            Start.log("getFile Loaded " + path.toString());
            //declare a temporay space to hold are file while we read it in
            ArrayList<String> temp = new ArrayList<String>();
            try 
            {
                    //now we attempt to lock the file
                    FileOutputStream fos = new FileOutputStream(path, true);
                    FileLock fl = fos.getChannel().tryLock();
                    //if we have lock
                    if (fl != null) 
                    {
                        //we add a record that it has been locked to the static array in start
                        Start.addLock(fl);
                        //and add to the log file
                        Start.log("File Locked");
                    }
                    else
                    {
                        //else we throw a error
                        Start.log("File Failed to lock");
                    }
                    
                    BufferedReader reader = new BufferedReader(new FileReader(path));
                    String tempWord = "";
                    //we read in the file till we hit the end and encounter a null
                    while ((tempWord = reader.readLine()) != null)
                            temp.add(tempWord);
                    //close are file reader so other processe's can access the file
                    reader.close();
            } 
            catch (IOException x) 
            {
                System.err.println(x);
            }
            return temp;
	}
        //read nodes file into a structure that can then be used in other places
        private static ArrayList<Node> getNodes(File path)
        {
            //log the event happining
            Start.log("Getting Nodes");
            ArrayList<Node> tempNodes = new ArrayList<Node>();
            ArrayList<String> file = getFile(path);
            for (int i =0; i < file.size();i++)
            {
                //use scanner to pharse the string into the relivant bits for getting the node data
                Scanner parse = new Scanner(file.get(i));
                tempNodes.add(new Node(parse.nextInt(),CPType.valueOf(parse.next())));
            }
            return tempNodes;
        }
        //read in the course file to a structure so it can be refrenced 
        private ArrayList<Course> getCourses(File coursePath,ArrayList<Node> nodes)
        {
            //add event to the log file
            Start.log("Getting Courses");
            ArrayList<Course> tempCourses = new ArrayList<Course>();
            ArrayList<String> file = getFile(coursePath);
            for (int i = 0; i < file.size();i++)
            {
                //pharse the data into there relivant feilds
                Scanner scan = new Scanner(file.get(i));
                ArrayList<Node> tnodes = new ArrayList<Node>();
                char ident = scan.next().charAt(0);
                //find the nodes that match and add them to the course
                int noNodes = scan.nextInt();
                for (int j = 0; j < noNodes;j++)
                {
                    int nodeNo = scan.nextInt();
                    for (int k = 0; k < nodes.size();k++)
                    {
                        if (nodes.get(k).getNode() == nodeNo)
                        {
                            tnodes.add(nodes.get(k));
                            break;
                        }
                    }
                }
                //finaly add the course to the list
                tempCourses.add(new Course(ident,tnodes));
            }
            return tempCourses;
        }
        //pharse the track file into there relivant objects
        private ArrayList<Track> getTrack(File path)
        {
            Start.log("Getting Tracks");
            ArrayList<Track> tempTimes = new ArrayList<Track>();
            ArrayList<String> file = getFile(path);
            for (int i = 0; i < file.size();i++)
            {
                Scanner scan = new Scanner(file.get(i));
                tempTimes.add(new Track(scan.nextInt(),scan.nextInt(),scan.nextInt(),scan.nextInt()));
            }
            return tempTimes;
        }
        //read in the entrants and add the courses
        private ArrayList<Entrant> getEntrants(File path,ArrayList<Course> courses)
        {
            Start.log("Getting Entrants");
            ArrayList<Entrant> tempEntrants = new ArrayList<Entrant>();
            ArrayList<String> file = getFile(path);
            for (int i = 0; i < file.size();i++)
            {
                //scan and pharse the data into th relivant feilds
                Scanner scan = new Scanner(file.get(i));
                int entrantNo = scan.nextInt();
                char course = scan.next().charAt(0);
                String name = scan.next() + " " + scan.next();
                Course tcourse = null;
                //find the correct course for the entrant and add it in 
                for (int j = 0;j < courses.size();j++)
                {
                    if (courses.get(j).getIdent() == course)
                    {
                            tcourse = courses.get(j);
                            break;                    
                    }
                }             
                tempEntrants.add(new Entrant(entrantNo,tcourse,name));
            }
            
            return tempEntrants;
        }
        //simplefied method to add new times to the already existing objects
        public void addTimes(File path, Event event)
        {
            addTimes(path, event.getNodes(), event.getEntrants());
        }
        //import the times files into the entrants objects so they can be processed later
        private void addTimes(File path,ArrayList<Node> nodes,ArrayList<Entrant> entrants)
        {
            Start.log("Getting Times");
            ArrayList<String> file = getFile(path);
            for (int i = 0; i < file.size();i++)
            {
                Scanner scan = new Scanner(file.get(i));
                CheckPointType cpt = CheckPointType.valueOf(scan.next());
                int nodeno = scan.nextInt(), entrant = scan.nextInt();
                Node node = null;
                //rebuild the time objects
                for (int j = 0; j < nodes.size();j++)
                    if (nodes.get(j).getNode() == nodeno)
                        node = nodes.get(j);
                //add them to the relevant entrants
                for (int j = 0; j < entrants.size();j++)
                    if (entrants.get(j).getEntrantNo() == entrant)
                        entrants.get(j).addTime(new Time(cpt,node,scan.next()));
            }
        }
        //build a superclass to hold and encapsulate the data 
        public Event getEvent(File eventPath,File nodesPath, File coursePath, File entrantPath, File nodePath, File trackPath, File timesPath)
        {
            //log this event
            Start.log("Populating Event");
            //get the relevant info for the event to process the details and make findings on the information 
            ArrayList<String> nameFile = getFile(eventPath);
            ArrayList<Node> nodes = getNodes(nodesPath);
            ArrayList<Entrant> entrants = getEntrants(entrantPath,getCourses(coursePath,nodes));
            addTimes(timesPath,nodes,entrants);
            return new Event(nameFile.get(0),nameFile.get(1),nameFile.get(2),getTrack(trackPath),entrants,nodes);
        }
}

