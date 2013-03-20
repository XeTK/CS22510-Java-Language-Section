package uk.ac.aber.dcs.cs22510.eventmanager.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Entrant;
import uk.ac.aber.dcs.cs22510.eventmanager.data.Event;

public class Menu 
{
    public Menu(Event event) throws IOException
    {
        boolean exit = false;
        while (true)
        {
            printMenu();
            String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
            switch (Integer.valueOf(s))
            {
                case 1:
                case 2:
                case 3:
                case 4:
                    ArrayList<Entrant> tent = event.getEntrants();
                    for (int i = 0;i < tent.size();i++)
                        tent.get(i).validateTimes();
                    break;
                case 0:
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
        System.out.println("1: Add Medical Checkpoint Time\n"
                         + "2: Print & Log Entrants excluded for wrong course\n"
                         + "3: Print & Log Entrants excluded at medical checkpoints\n"
                         + "4: Print & Log Entrants that successfully Completed\n" 
                         + "0: Exit\n"
                         + "Please Enter your selection : ");
    }
    
}
