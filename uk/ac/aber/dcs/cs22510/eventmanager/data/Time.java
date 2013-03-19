package uk.ac.aber.dcs.cs22510.eventmanager.data;

public class Time
{
	private CheckPointType checkPointType;
	private Node node;
        private Entrant entrant;
	private String time;
	public Time(CheckPointType checkPointType,Entrant entrant,Node node,String time)
	{
                this.entrant = entrant;
		this.checkPointType = checkPointType;
		this.node = node;
		this.time = time;
	}
	public CheckPointType getCheckPointType()
	{
		return checkPointType;
	}
	public Node getNode()
	{
		return node;
	}
	public String getTime()
	{
		return time;
	}
	public Entrant getEntrant()
        {
                return entrant;
        }
}
