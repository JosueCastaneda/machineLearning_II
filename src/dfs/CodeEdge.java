package dfs;


/**
 * @author JesusAntonio
 *
 */
public class CodeEdge {
	private int fromid;
	private int toid;
	private String fromlabel;
	private String tolabel;
	private String edgelabel;
	private String direction;
	
	public CodeEdge(int fromid, int toid, String flabel, String tlabel, String elabel, String direction)
	{
		this.fromid = fromid;
		this.toid = toid;
		this.fromlabel = flabel;
		this.tolabel = tlabel;
		this.edgelabel = elabel;
		this.direction = direction;
	}
	


	public int GetFromId()
	{
		return this.fromid;
	}
	
	public int GetToId()
	{
		return this.toid;
	}
	
	public String GetFromLabel()
	{
		return this.fromlabel;
	}
	
	public String GetToLabel()
	{
		return this.tolabel;
	}
	
	public String GetEdgeLabel()
	{
		return this.edgelabel;
	}
	
	public String GetEdgeDirection()
	{
		return this.direction;
	}
	
	public void PrintCodeEdge()
	{
		System.out.println(this.fromid + " " + this.toid + " " + this.fromlabel + " " + this.edgelabel + " " + this.tolabel);
	}
}
