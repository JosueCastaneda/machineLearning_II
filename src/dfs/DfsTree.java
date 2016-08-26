package dfs;


import java.util.ArrayList;

/**
 * @author JesusAntonio
 *
 */
public class DfsTree {
	private ArrayList<DfsNode> dfsNodes;
	private int numNodes = 0;
	
	public DfsTree()
	{
		this.dfsNodes = new ArrayList<DfsNode>();
		this.numNodes = 0;
	}
	
	public int AddDfsNode(DfsNode myDfsNode)
	{
		this.numNodes++;
		this.dfsNodes.add(myDfsNode);
		return this.dfsNodes.lastIndexOf(myDfsNode);
	}
	
	public int GetNumNodes()
	{
		return this.numNodes;
	}
}
