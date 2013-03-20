package uk.ac.aber.dcs.cs22510.eventmanager.data;
/**
 * Data class for a track objects 
 * @author THR2
 */
public class Track
{
	private int trackNo, expected, start, end;
	public Track(int trackNo,int start,int end,int expected)
	{
		this.trackNo = trackNo;
		this.start = start;
		this.end = end;
		this.expected = expected;
	}
        /*Getters and Setters*/
	public int getTrackNo()
	{
		return trackNo;
	}
	public int getExpected()
	{
		return expected;
	}
	public int getStart()
	{
		return start;
	}
	public int getEnd()
	{
		return end;
	}
	
}
