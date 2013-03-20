package uk.ac.aber.dcs.cs22510.eventmanager.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileReader;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.aber.dcs.cs22510.eventmanager.Start;

import uk.ac.aber.dcs.cs22510.eventmanager.data.*;

public class MyFileReader
{
	public static ArrayList<String> getFile(File path)
	{
            Start.log("getFile Loaded " + path.toString());
            ArrayList<String> temp = new ArrayList<String>();
            try 
            {
                    FileOutputStream fos = new FileOutputStream(path, true);
                    FileLock fl = fos.getChannel().tryLock();
                    if (fl != null) 
                    {
                        Start.addLock(fl);
                        Start.log("File Locked");
                    }
                    else
                    {
                        Start.log("File Failed to lock");
                    }
                    BufferedReader reader = new BufferedReader(new FileReader(path));
                    String tempWord = "";
                    while ((tempWord = reader.readLine()) != null)
                            temp.add(tempWord);
                    reader.close();
            } 
            catch (IOException x) 
            {
                System.err.println(x);
            }
            return temp;
	}
        private static ArrayList<Node> getNodes(File path)
        {
            Start.log("Getting Nodes");
            ArrayList<Node> tempNodes = new ArrayList<Node>();
            ArrayList<String> file = getFile(path);
            for (int i =0; i < file.size();i++)
            {
                Scanner parse = new Scanner(file.get(i));
                tempNodes.add(new Node(parse.nextInt(),CPType.valueOf(parse.next())));
            }
            return tempNodes;
        }
        private ArrayList<Course> getCourses(File coursePath,ArrayList<Node> nodes)
        {
            Start.log("Getting Courses");
            ArrayList<Course> tempCourses = new ArrayList<Course>();
            ArrayList<String> file = getFile(coursePath);
            for (int i = 0; i < file.size();i++)
            {
                Scanner scan = new Scanner(file.get(i));
                ArrayList<Node> tnodes = new ArrayList<Node>();
                char ident = scan.next().charAt(0);
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
                tempCourses.add(new Course(ident,tnodes));
            }
            return tempCourses;
        }
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
        private ArrayList<Entrant> getEntrants(File path,ArrayList<Course> courses)
        {
            Start.log("Getting Entrants");
            ArrayList<Entrant> tempEntrants = new ArrayList<Entrant>();
            ArrayList<String> file = getFile(path);
            for (int i = 0; i < file.size();i++)
            {
                Scanner scan = new Scanner(file.get(i));
                int entrantNo = scan.nextInt();
                char course = scan.next().charAt(0);
                String name = scan.next() + " " + scan.next();
                Course tcourse = null;
                for (int j = 0;j < courses.size();j++)
                {
                    if (courses.get(j).getIdent() == course)
                    {
                        try 
                        {
                            tcourse = (Course) courses.get(j).getClone();
                            break;
                        } 
                        catch (CloneNotSupportedException ex) 
                        {
                            Logger.getLogger(MyFileReader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }             
                tempEntrants.add(new Entrant(entrantNo,tcourse,name));
            }
            
            return tempEntrants;
        }
        public void addTimes(File path, Event event)
        {
            addTimes(path, event.getNodes(), event.getEntrants());
        }
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
                for (int j = 0; j < nodes.size();j++)
                    if (nodes.get(j).getNode() == nodeno)
                        node = nodes.get(j);
                for (int j = 0; j < entrants.size();j++)
                    if (entrants.get(j).getEntrantNo() == entrant)
                        entrants.get(j).addTime(new Time(cpt,node,scan.next()));
            }
        }
        public Event getEvent(File eventPath,File nodesPath, File coursePath, File entrantPath, File nodePath, File trackPath, File timesPath)
        {
            Start.log("Populating Event");
            ArrayList<String> nameFile = getFile(eventPath);
            ArrayList<Node> nodes = getNodes(nodesPath);
            ArrayList<Entrant> entrants = getEntrants(entrantPath,getCourses(coursePath,nodes));
            addTimes(timesPath,nodes,entrants);
            return new Event(nameFile.get(0),nameFile.get(1),nameFile.get(2),getTrack(trackPath),entrants,nodes);
        }
}

