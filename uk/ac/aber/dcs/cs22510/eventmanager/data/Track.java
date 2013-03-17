package uk.ac.aber.dcs.cs22510.eventmanager.data;

public class Track
{
	private int trackNo, expected;
	private Time start, end;
	public Track(int trackNo,Time start,Time end,int expected)
	{
		this.trackNo = trackNo;
		this.start = start;
		this.end = end;
		this.expected = expected;
	}
	public int getTrackNo()
	{
		return trackNo;
	}
	public int getExpected()
	{
		return expected;
	}
	public Time getStart()
	{
		return start;
	}
	public Time getEnd()
	{
		return end;
	}
	
}
