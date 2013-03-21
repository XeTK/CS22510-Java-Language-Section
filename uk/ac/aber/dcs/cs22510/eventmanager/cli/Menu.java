package uk.ac.aber.dcs.cs22510.eventmanager.cli;

//Import most of the things java uses....
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import uk.ac.aber.dcs.cs22510.eventmanager.Start;
import uk.ac.aber.dcs.cs22510.eventmanager.data.CheckPointType;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Entrant;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Event;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Node;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Status;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Time;
import uk.ac.aber.dcs.cs22510.eventmanager.io.MyFileReader;
/**
 * This class is the main gutts of the program and runs the main loop where all the execution is carried out
 * @author THR2
 */
public class Menu 
{
    //Main Execution point of the program, this is where everything happens
    public Menu(Event event) throws IOException
    {
        //keep are entrants close so we can work with them
        ArrayList<Entrant> entrants = event.getEntrants();
        //need this boolean for when we are exiting the program
        boolean exit = false;
        while (true)
        {
            //keep for tottaling the numbers of entrants in a given section
            int no = 0;
            //reload the entrants status after a menu action has taken place
            Start.log("Repopulating Entrants status");
            for (int i = 0;i < entrants.size();i++)
                entrants.get(i).validateTimes();
            //print are menu for the end user to know how to select different functions
            printMenu();
            Start.log("Taking user input for menu");
            //get are user's input to get to the next stage of execution
            char s = new BufferedReader(new InputStreamReader(System.in)).readLine().charAt(0);
            switch (s)
            {
                case '1':
                    //print the info to add a new checkpoint and choose what type of checkpoint we are adding to the entrant
                    System.out.println("1: add Checkpoint time | 2: add Medical arival time | 3: add Medical depart time :");
                    char c = new BufferedReader(new InputStreamReader(System.in)).readLine().charAt(0);
                    //simple switch to move between different modes
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
                    //basicly just list all entrants that have failed to get to the correct checkpoints in the correct order
                    Start.log("Listing Entrants that failed to complete successfully");
                    for (int i =0; i < entrants.size();i++)
                        if (entrants.get(i).getStatus() == Status.FailedCP)
                            System.out.println("Entrant : " + entrants.get(i).getName() + " has made a error on their course");
                    break;
                case '3':
                    //Same again just for the failed medical checkpoint entrants now
                    Start.log("Listing Entrants that have failed medical checks");
                     for (int i =0; i < entrants.size();i++)
                        if (entrants.get(i).getStatus() == Status.FailedMed)
                            System.out.println("Entrant : " + entrants.get(i).getName() + " has failed there medical check");
                    break;
                case '4':
                    //same again just for completed members, yawn its all very much the same
                    Start.log("Listing number of Entrants that have completed");
                    for (int i =0; i < entrants.size();i++)
                        if (entrants.get(i).getStatus() == Status.Completed)
                            System.out.println("Entrant : " + entrants.get(i).getName() + " has completed their course");
                    break;
                case '5':
                    //same method again as above but instead of printing out the entrant we just increment a counter to get the total of entrants that have not started
                    Start.log("Listing number of Entrants that have not started");
                    no = 0;
                    for (int i =0; i < entrants.size();i++)
                        if (entrants.get(i).getStatus() == Status.NotStarted)
                            no++;
                    System.out.println(no + " This many entrants have not started");
                    break;  
                case '6':
                    //same as above just for incomplete entrants, i did say there was going to be alot of loops
                    Start.log("Listing number of Entrants that have not completed there course yet");
                    no = 0;
                    for (int i =0; i < entrants.size();i++)
                        if (entrants.get(i).getStatus() == Status.Incomplete)
                            no++;
                    System.out.println(no + " This many entrants have not completed their course");
                    break;
                case '7':
                    //oww something new, in this method we are going to query where the entrant is,
                    //log that we have done this
                    Start.log("Quering location of a entrant");
                    //ask the user for what entrant they want to query
                    System.out.println("Please enter entrants number: ");
                    int entno = Integer.valueOf(new BufferedReader(new InputStreamReader(System.in)).readLine());
                    for (int i = 0; i < entrants.size();i++)
                    {
                        if (entrants.get(i).getEntrantNo() == entno)
                        {
                            //once have found the entrant then we check on the state of the entrant and print a message out that fits there current status
                            Start.log("Entrant found checking further...");
                            Status state = entrants.get(i).getStatus();
                            if (state == Status.Completed)
                                System.out.println("Entrant has finished the event");
                            else if (state == Status.NotStarted)
                                System.out.println("Entrant has not started the event");
                            else if (state == Status.FailedCP||state == Status.FailedMed)
                                System.out.println("Entrant has failed the event");
                            else if (state == Status.Incomplete)
                                System.out.println("Entrant was last seen at : " + entrants.get(i).getLastTime().getNode().getNode() + " at this time : " + entrants.get(i).getLastTime().getTime());
                            //we have done are work here lets escape
                            break;
                        }    
                    }
                    break;
                case '8':
                    //now we sort are entrants to get who has completed there route in the quickest time
                    Start.log("Populating Leaderboard");
                    ArrayList<Entrant> leaderboard = new ArrayList<Entrant>();
                    //find all are entrants that have completed so we can work on them
                    for (int i =0; i < entrants.size();i++)
                        if (entrants.get(i).getStatus() == Status.Completed)
                            leaderboard.add(entrants.get(i));
                    Start.log("Start Sorting Results");
                    //start sorting are results so we no who wins
                    while (true)
                    {
                        //we need a flag to know when its time to exit this horible sort
                        boolean sorted = true;
                        //go through all are completed entrants and start to sort them
                        for (int i = 0; i < leaderboard.size() - 1;i++)
                        {
                            //get the time the entrants have done there courses in
                            int diff = diffTime(leaderboard.get(i).getFirstTime().getTime(),leaderboard.get(i).getLastTime().getTime()),
                                diff2 = diffTime(leaderboard.get(i+1).getFirstTime().getTime(),leaderboard.get(i+1).getLastTime().getTime());
                            //compare the 2 times to know if we have to swap them
                            if (diff > diff2)
                            {
                                //switcharoo
                                Entrant te = leaderboard.get(i);
                                leaderboard.set(i, leaderboard.get(i+1));
                                leaderboard.set(i+1,te);
                                //set are flag to false as we have had to swap things about
                                sorted = false;
                            }
                        }
                        //if we have gone a whole loop and not moved things about then we break are bind from the while loop
                        if (sorted) 
                            break;
                    }
                    //log that we have got away from that evil sort
                    Start.log("Finish Sorting Results, Display Results to the screen");
                    //print out are leaderboard for the end user to see
                    for (int i = 0;i < leaderboard.size();i++)
                        System.out.println(i+1 + ": " +leaderboard.get(i).getName() + " Time in mins : " + diffTime(leaderboard.get(i).getFirstTime().getTime(),leaderboard.get(i).getLastTime().getTime()));
                    break;
                case '9':
                    //import another time file for us todo work on
                    Start.log("Importing new set of times");
                    //ask the user for the path of the file so we can import it
                    System.out.println("Please enter the path for the times file you would like to import");
                    //lets get this done, lets add the new times file to the entrants
                    new MyFileReader().addTimes(new File(new BufferedReader(new InputStreamReader(System.in)).readLine()), event);
                    break;
                case '0':
                    //this is called to exit the program
                    Start.log("Exiting Program");
                    System.out.println("Goodbye!");
                    //we have a boolean to tell the main while that its time to exit
                    exit = true;
                    break;
                default:
                    //if the selection does not exist then print a error and let the user try again
                    Start.log("Invalid Menu selection made");
                    System.out.println("Invalid Selection");
                    break;
            }
            //if its time to exit then we break the loop
            if (exit)
                break;
            
        }
        
    }
    //Simple method to print out the menu to screen.
    private void printMenu()
    {
        Start.log("Displaying Menu");
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
    //Add new time method, it is kept seperate as its used for all 3 types of node rather than having a custom one for each method
    private void addTime(Event event,CheckPointType cpt) throws IOException 
    {
        //Log we are getting a new time
        Start.log("Adding New Time Manualy");
        //get the list of entrants to help us work on them
        ArrayList<Entrant> entrants = event.getEntrants();
        //get some dialog about the entrant we are working on
        System.out.println("Please enter entrants number: ");
        int entno = Integer.valueOf(new BufferedReader(new InputStreamReader(System.in)).readLine());
        for (int i = 0; i < entrants.size();i++)
        {
            
            if (entrants.get(i).getEntrantNo() == entno)
            {
                //once we have found the entrant then we can add the new info for times
                System.out.println("Please enter node number: ");
                int cpn = Integer.valueOf(new BufferedReader(new InputStreamReader(System.in)).readLine());
                //find the node for the time to help us tie the whole thing together
                ArrayList<Node> nodes = event.getNodes();
                Node an = null;
                for (int j = 0;j < nodes.size();j++)
                    if (nodes.get(j).getNode() == cpn)
                        an = nodes.get(j);
                System.out.println("Please enter time");
                String time = new BufferedReader(new InputStreamReader(System.in)).readLine();
                //Finaly we add the new time to the entrants list of times and when we reload to menu we will have repopulated the entrants status
                entrants.get(i).addTime(new Time(cpt,an,time));
                break;
            }
        }
    }
    //convert are time string into a int value we can use for some maths
    private int convertTime(String inTime)
    {
        int hour = Integer.valueOf(inTime.charAt(0) + inTime.charAt(1)), min = Integer.valueOf(inTime.charAt(3) + inTime.charAt(4)); 
        return (hour * 60) + min;
    }
    //get the differents between 2 times to work out the total time for a competitor
    private int diffTime(String startTime, String endTime)
    {
        return convertTime(endTime) - convertTime(startTime);
    }
}
