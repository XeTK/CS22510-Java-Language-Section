package uk.ac.aber.dcs.cs22510.eventmanager.data;

public class Node
{
	private int node;
	private CPType nodeType;
	public Node(int node, CPType nodeType)
	{
		this.node = node;
		this.nodeType = nodeType;
	}
	public int getNode()
	{
		return node;
	}
	public CPType getNodeType()
	{
		return nodeType;
	}
	
}
