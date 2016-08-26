package dfs;


import java.util.ArrayList;

/**
 * @author JesusAntonio
 *
 */
public class DfsCode {
	private ArrayList<CodeEdge> codeEdges;
	
	public DfsCode()
	{
		this.codeEdges = new ArrayList<CodeEdge>();
	}
	
	public void AddCodeEdge(int fromid, int toid, String fromlabel, String tolabel, String edgelabel, String direction)
	{
		CodeEdge myCodeEdge = new CodeEdge(fromid, toid, fromlabel, tolabel, edgelabel, direction);
		this.codeEdges.add(myCodeEdge);
	}
	
	public void PrintCode()
	{
		for(int i = 0; i < this.codeEdges.size(); i++)
		{
			System.out.println(this.codeEdges.get(i).GetFromId() + " " + this.codeEdges.get(i).GetToId() + " " +
				this.codeEdges.get(i).GetFromLabel() + " " + this.codeEdges.get(i).GetEdgeLabel() + " " +
				this.codeEdges.get(i).GetToLabel() + " " + this.codeEdges.get(i).GetEdgeDirection());
		}
	}
}
