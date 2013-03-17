package uk.ac.aber.dcs.cs22510.eventmanager.data;

public class Time
{
	private CheckPointType checkPointType;
	private Node node;
	private Time time;
	public Time(CheckPointType checkPointType,Node node,Time time)
	{
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
	public Time getTime()
	{
		return time;
	}
	
}
