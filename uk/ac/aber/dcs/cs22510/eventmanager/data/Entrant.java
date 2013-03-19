package uk.ac.aber.dcs.cs22510.eventmanager.data;

public class Entrant
{
	private int entrantNo;
	private Course course;
	private String name;
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
}
