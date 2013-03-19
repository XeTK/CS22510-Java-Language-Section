package uk.ac.aber.dcs.cs22510.eventmanager.data;

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
