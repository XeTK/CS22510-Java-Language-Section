package uk.ac.aber.dcs.cs22510.eventmanager.data;

import java.util.ArrayList;

public class Course implements Cloneable
{
	private ArrayList<Node> nodes;
	private char ident;
	public Course(char ident,ArrayList<Node> nodes)
	{
		this.nodes = nodes;
		this.ident = ident;
	}
	public ArrayList<Node> getNodes()
	{
		return nodes;
	}
	public char getIdent()
	{
		return ident;
	}
        public Course getClone() throws CloneNotSupportedException
        {
            return (Course)super.clone();
        }
}
