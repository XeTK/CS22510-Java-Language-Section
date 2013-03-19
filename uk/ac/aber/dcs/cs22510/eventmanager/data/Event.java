package uk.ac.aber.dcs.cs22510.eventmanager.data;

import java.util.ArrayList;

public class Event
{
	private String eventTitle, eventTime, eventDate;
	private ArrayList<Track> tracks;
	private ArrayList<Course> courses;
	private ArrayList<Entrant> entrants;
        private ArrayList<Node> nodes;
        private ArrayList<Time> times;
	public Event(String eventTitle, String eventTime, String eventDate,ArrayList<Node> nodes, ArrayList<Track> tracks, ArrayList<Course> courses, ArrayList<Entrant> entrants,ArrayList<Time> times)
	{
		this.eventTitle = eventTitle;
		this.eventTime = eventTime;
		this.eventDate = eventDate;
                this.nodes = nodes;
		this.tracks = tracks;
		this.courses = courses;
		this.entrants = entrants;
                this.times = times;
	}
	public String getEventTitle()
	{
		return eventTitle;
	}
	public String getEventTime()
	{
		return eventTime;
	}
	public String getEventDate()
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
