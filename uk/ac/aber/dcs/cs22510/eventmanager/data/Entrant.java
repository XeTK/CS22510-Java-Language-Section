package uk.ac.aber.dcs.cs22510.eventmanager.data;

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
        public void addTime(Time ttime)
        {
            times.add(ttime);
        }
        public Time getLastLocation()
        {
            return times.get(times.size() -1);
        }
        public void validateTimes()
        { 
            ArrayList<Node> checkPoints = new ArrayList<Node>(), medicalPoints = new ArrayList<Node>(), nodes = course.getNodes();
            for (int i = 0; i < nodes.size();i++)
                if (nodes.get(i).getNodeType() == CPType.CP)
                    checkPoints.add(nodes.get(i));
                else if (nodes.get(i).getNodeType() == CPType.MC)
                    medicalPoints.add(nodes.get(i));
            
            ArrayList<Time> checkPointTimes = new ArrayList<Time>();
            
            for (int i = 0; i < times.size();i++)
                if (times.get(i).getNode().getNodeType() == CPType.CP)
                    checkPointTimes.add(times.get(i));
                
            if (checkPointTimes.size() == checkPoints.size() - 1)
            {
                for (int i = 0; i < checkPoints.size() -1; i++)
                {
                    if (checkPointTimes.get(i).getNode() != checkPoints.get(i))
                    {
                        status = Status.FailedCP;
                        break;
                    }
                }
            }
            else
            {
                if (times.size() > 0)
                    status = Status.Incomplete;
                else 
                    status = Status.NotStarted;
            }
            
            for (int i = 0; i < times.size();i++)
                if (times.get(i).getNode().getNodeType() == CPType.MC)
                    if (times.get(i).getCheckPointType() == CheckPointType.I)
                        status = Status.FailedMed;
            
            if (status == null)
                status = Status.Completed;   
        }
}
