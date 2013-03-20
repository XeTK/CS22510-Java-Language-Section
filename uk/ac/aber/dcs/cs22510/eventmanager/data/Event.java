package uk.ac.aber.dcs.cs22510.eventmanager.data;
/**
 * Encapulation class for all the objects that define a event
 * @author THR2
 */
import java.util.ArrayList;

public class Event
{
	private String eventTitle, eventTime, eventDate;
	private ArrayList<Track> tracks;
	private ArrayList<Entrant> entrants;
        private ArrayList<Node> nodes;
	public Event(String eventTitle, String eventTime, String eventDate,ArrayList<Track> tracks, ArrayList<Entrant> entrants, ArrayList<Node> nodes)
	{
		this.eventTitle = eventTitle;
		this.eventTime = eventTime;
		this.eventDate = eventDate;
		this.tracks = tracks;
		this.entrants = entrants;
                this.nodes = nodes;
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
        public ArrayList<Node> getNodes() 
        {
            return nodes;
        }
	
}
