package uk.ac.aber.dcs.cs22510.eventmanager.data;
/**
 * This is the data class for entrant, it also contains methods to add new times and repopulate the status of being a entrant
 * @Author THR2
 */
import java.util.ArrayList;

public class Entrant
{
	private int entrantNo;
	private Course course;
	private String name;
        private Status status;
        private ArrayList<Time> times = new ArrayList<Time>();
	public Entrant(int entrantNo,Course course, String name)
	{
		this.entrantNo = entrantNo;
		this.course = course;
		this.name = name;
	}
	public int getEntrantNo()
	{
		return entrantNo;
	}
	public Course getCourse()
	{
		return course;
	}
	public String getName()
	{
		return name;
	}
        public Status getStatus()
        {
            return status;
        }
        //add new time to the end of the times list
        public void addTime(Time ttime)
        {
            times.add(ttime);
        }
        //get the last time from the times list so we can get the difference between the first and last node
        public Time getLastTime()
        {
            return times.get(times.size() -1);
        }
        //get the first node to help work out the difference from the start and end
        public Time getFirstTime()
        {
            return times.get(0);
        }
        //revalidate the times to see how far the competitor is through the event and get there status
        public void validateTimes()
        { 
            ArrayList<Node> checkPoints = new ArrayList<Node>(), medicalPoints = new ArrayList<Node>(), nodes = course.getNodes();
            //convert the nodes to lists to work on later
            for (int i = 0; i < nodes.size();i++)
                if (nodes.get(i).getNodeType() == CPType.CP)
                    checkPoints.add(nodes.get(i));
                else if (nodes.get(i).getNodeType() == CPType.MC)
                    medicalPoints.add(nodes.get(i));
            
            ArrayList<Time> checkPointTimes = new ArrayList<Time>();
            
            //pharse are nodes to get the relivant nodes that we want to work on
            for (int i = 0; i < times.size();i++)
                if (times.get(i).getNode().getNodeType() == CPType.CP)
                    checkPointTimes.add(times.get(i));
                
            //check if the list of nodes we have size matches before we check deeper
            if (checkPointTimes.size() == checkPoints.size() - 1)
            {
                for (int i = 0; i < checkPoints.size() -1; i++)
                {
                    //if a node does not match then we change the status of the entrant
                    if (checkPointTimes.get(i).getNode() != checkPoints.get(i))
                    {
                        status = Status.FailedCP;
                        break;
                    }
                }
            }
            else
            {
                //if there are some times then we set the status of the entrant
                if (times.size() > 0)
                    status = Status.Incomplete;
                else 
                    status = Status.NotStarted;
            }
            //lastly we check for any failed medical checks and set the status for that state
            for (int i = 0; i < times.size();i++)
                if (times.get(i).getNode().getNodeType() == CPType.MC)
                    if (times.get(i).getCheckPointType() == CheckPointType.I)
                        status = Status.FailedMed;
            
            //if we havent set anything at the end of the method then we set it so we have completed all the nodes
            if (status == null)
                status = Status.Completed;   
        }
}
