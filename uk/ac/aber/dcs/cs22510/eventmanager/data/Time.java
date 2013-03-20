package uk.ac.aber.dcs.cs22510.eventmanager.data;
/**
 * Data class for Time objects, with its appropriate node 
 * @author THR2
 */
public class Time
{
	private CheckPointType checkPointType;
	private Node node;
	private String time;
	public Time(CheckPointType checkPointType,Node node,String time)
	{;
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
}
