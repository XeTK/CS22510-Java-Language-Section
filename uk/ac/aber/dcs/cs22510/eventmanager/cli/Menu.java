package uk.ac.aber.dcs.cs22510.eventmanager.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import uk.ac.aber.dcs.cs22510.eventmanager.data.CheckPointType;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Entrant;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Event;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Node;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Status;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Time;
import uk.ac.aber.dcs.cs22510.eventmanager.io.MyFileReader;

public class Menu 
{
    public Menu(Event event) throws IOException
    {
        ArrayList<Entrant> entrants = event.getEntrants();
        
        boolean exit = false;
        while (true)
        {
            int no = 0;
            for (int i = 0;i < entrants.size();i++)
                entrants.get(i).validateTimes();
            printMenu();
            char s = new BufferedReader(new InputStreamReader(System.in)).readLine().charAt(0);
            switch (s)
            {
                case '1':
                    System.out.println("1: add Checkpoint time | 2: add Medical arival time | 3: add Medical depart time :");
                    char c = new BufferedReader(new InputStreamReader(System.in)).readLine().charAt(0);
                    switch (c)
                    {
                        case '1':
                            addTime(event,CheckPointType.T);
                            break;
                        case '2':
                            addTime(event,CheckPointType.A);
                            break;
                        case '3':
                            addTime(event,CheckPointType.D);
                            break;
                        default:
                            break;
                    }
                case '2':     
                    for (int i =0; i < entrants.size();i++)
                        if (entrants.get(i).getStatus() == Status.FailedCP)
                            System.out.println("Entrant : " + entrants.get(i).getName() + " has made a error on their course");
                    break;
                case '3':
                     for (int i =0; i < entrants.size();i++)
                        if (entrants.get(i).getStatus() == Status.FailedMed)
                            System.out.println("Entrant : " + entrants.get(i).getName() + " has failed there medical check");
                    break;
                case '4':
                    for (int i =0; i < entrants.size();i++)
                        if (entrants.get(i).getStatus() == Status.Completed)
                            System.out.println("Entrant : " + entrants.get(i).getName() + " has completed their course");
                    break;
                case '5':
                    no = 0;
                    for (int i =0; i < entrants.size();i++)
                        if (entrants.get(i).getStatus() == Status.NotStarted)
                            no++;
                    System.out.println(no + " This many entrants have not started");
                    break;
                case '6':
                    no = 0;
                    for (int i =0; i < entrants.size();i++)
                        if (entrants.get(i).getStatus() == Status.Incomplete)
                            no++;
                    System.out.println(no + " This many entrants have not completed their course");
                    break;
                case '7':
                    System.out.println("Please enter entrants number: ");
                    int entno = Integer.valueOf(new BufferedReader(new InputStreamReader(System.in)).readLine());
                    for (int i = 0; i < entrants.size();i++)
                    {
                        if (entrants.get(i).getEntrantNo() == entno)
                        {
                            Status state = entrants.get(i).getStatus();
                            if (state == Status.Completed)
                                System.out.println("Entrant has finished the event");
                            else if (state == Status.NotStarted)
                                System.out.println("Entrant has not started the event");
                            else if (state == Status.FailedCP||state == Status.FailedMed)
                                System.out.println("Entrant has failed the event");
                            else if (state == Status.Incomplete)
                                System.out.println("Entrant was last seen at : " + entrants.get(i).getLastLocation().getNode().getNode() + " at this time : " + entrants.get(i).getLastLocation().getTime());
                            break;
                        }    
                    }
                    break;
                case '8':

                    break;
                case '9':
                    System.out.println("Please enter the path for the times file you would like to import");
                    new MyFileReader().addTimes(new File(new BufferedReader(new InputStreamReader(System.in)).readLine()), event);
                    break;
                case '0':
                    System.out.println("Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
            if (exit)
                break;
            
        }
        
    }
    private void printMenu()
    {
        System.out.println("\n1: Add new checkpoint time\n"
                         + "2: Print & Log Entrants excluded for wrong course\n"
                         + "3: Print & Log Entrants excluded at medical checkpoints\n"
                         + "4: Print & Log Entrants that successfully Completed\n" 
                         + "5: Display number of entrants that have not started\n"
                         + "6: Display number of entrants that have not completed there course\n"
                         + "7: Query entrants current location\n"
                         + "8: Display LeaderBoard\n"
                         + "9: Import new time file\n"
                         + "0: Exit\n"
                         + "Please Enter your selection : ");
    }
    private void addTime(Event event,CheckPointType cpt) throws IOException 
    {
        ArrayList<Entrant> entrants = event.getEntrants();
        System.out.println("Please enter entrants number: ");
        int entno = Integer.valueOf(new BufferedReader(new InputStreamReader(System.in)).readLine());
        for (int i = 0; i < entrants.size();i++)
        {
            if (entrants.get(i).getEntrantNo() == entno)
            {
                System.out.println("Please enter node number: ");
                int cpn = Integer.valueOf(new BufferedReader(new InputStreamReader(System.in)).readLine());
                ArrayList<Node> nodes = event.getNodes();
                Node an = null;
                for (int j = 0;j < nodes.size();j++)
                    if (nodes.get(j).getNode() == cpn)
                        an = nodes.get(j);
                System.out.println("Please enter time");
                String time = new BufferedReader(new InputStreamReader(System.in)).readLine();
                entrants.get(i).addTime(new Time(cpt,an,time));
                break;
            }
        }
    }
}
