package uk.ac.aber.dcs.cs22510.eventmanager.data;

import java.util.ArrayList;
import java.util.Date;

public class Event
{
	private String eventTitle;
	private Time eventTime;
	private Date eventDate;
	private ArrayList<Track> tracks;
	private ArrayList<Course> courses;
	private ArrayList<Entrant> entrants;
	public Event(String eventTitle, Time eventTime, Date eventDate, ArrayList<Track> tracks, ArrayList<Course> courses, ArrayList<Entrant> entrants)
	{
		this.eventTitle = eventTitle;
		this.eventTime = eventTime;
		this.eventDate = eventDate;
		this.tracks = tracks;
		this.courses = courses;
		this.entrants = entrants;
	}
	public String getEventTitle()
	{
		return eventTitle;
	}
	public Time getEventTime()
	{
		return eventTime;
	}
	public Date getEventDate()
	{
		return eventDate;
	}
	public ArrayList<Track> getTracks()
	{
		return tracks;
	}
	public ArrayList<Course> getCourses()
	{
		return courses;
	}
	public ArrayList<Entrant> getEntrants()
	{
		return entrants;
	}
	
}
