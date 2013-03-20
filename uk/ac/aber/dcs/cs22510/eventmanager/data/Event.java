package uk.ac.aber.dcs.cs22510.eventmanager.data;

import java.util.ArrayList;

public class Event
{
	private String eventTitle, eventTime, eventDate;
	private ArrayList<Track> tracks;
	private ArrayList<Entrant> entrants;
	public Event(String eventTitle, String eventTime, String eventDate,ArrayList<Track> tracks, ArrayList<Entrant> entrants)
	{
		this.eventTitle = eventTitle;
		this.eventTime = eventTime;
		this.eventDate = eventDate;
		this.tracks = tracks;
		this.entrants = entrants;
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
	public ArrayList<Entrant> getEntrants()
	{
		return entrants;
	}
	
}
