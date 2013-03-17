package uk.ac.aber.dcs.cs22510.eventmanager.data;

public class Node
{
	private int node;
	private String nodeType;
	public Node(int node, String nodeType)
	{
		this.node = node;
		this.nodeType = nodeType;
	}
	public int getNode()
	{
		return node;
	}
	public String getNodeType()
	{
		return nodeType;
	}
	
}
