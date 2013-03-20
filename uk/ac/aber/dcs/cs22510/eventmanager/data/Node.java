package uk.ac.aber.dcs.cs22510.eventmanager.data;
/**
 * Data class for a node object, to help link courses together
 * @author THR2
 */
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
