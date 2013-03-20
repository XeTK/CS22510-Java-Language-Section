package uk.ac.aber.dcs.cs22510.eventmanager.data;
/**
 * This class holds all the information relevant to course
 * @Author THR2
 */
import java.util.ArrayList;

public class Course
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
}
