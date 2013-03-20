package uk.ac.aber.dcs.cs22510.eventmanager.data;

import java.util.ArrayList;

public class Entrant
{
	private int entrantNo;
	private Course course;
	private String name;
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
        public void addTime(Time ttime)
        {
            times.add(ttime);
        }
}
