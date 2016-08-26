package dfs;

/**
 * @author JesusAntonio
 *
 */
public class DfsNode {
	private int parent;
	private int discovery;
	private int finish;
	private String color;
	
	public DfsNode()
	{
		this.parent = -1;
		this.discovery = -1;
		this.finish = -1;
		this.color = "";
	}
	
	public void SetParent(int parent)
	{
		this.parent = parent;
	}
	
	public void SetDiscovery(int discovery)
	{
		this.discovery = discovery;
	}
	
	public void SetFinish(int finish)
	{
		this.finish = finish;
	}
	
	public void SetColor(String color)
	{
		this.color = color;
	}

	public int GetParent()
	{
		return this.parent;
	}
	
	public int GetDiscovery()
	{
		return this.discovery;
	}
	
	public int GetFinish()
	{
		return this.finish;
	}
	
	public String GetColor()
	{
		return this.color;
	}
}
