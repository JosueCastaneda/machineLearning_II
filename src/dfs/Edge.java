package dfs;

import java.util.ArrayList;


/**
 * @author JesusAntonio
 * @created 03-03-2015
 * @modified 03-03-2015
 *
 */
public class Edge {

	//public int id;
	private int from;
	private int to;
	private String label;
	private boolean traversed;


	//private Vertex fromV; //= new Vertex();
	//private Vertex toV;// = new Vertex();
	
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	
	
	public Edge(int from, int to, String label)
	{
		//this.id = id;
		this.from = from;
		this.to = to;
		this.label = label;
		this.traversed = false;
	}
	
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
	
	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	public Vertex getFromV() {
		return vertices.get(0);
	}
	
	public Vertex getToV() {
		return vertices.get(1);
	}
	
	
	
	public Edge()
	{
		this.from = -1;
		this.to = -1;
		this.label = "";
	}
	
	public int getFrom()
	{
		return this.from;
	}
	
	public int getTo()
	{
		return this.to;
	}
	
	public String getLabel()
	{
		return this.label;
	}
	
	public boolean getTraversed()
	{
		return this.traversed;
	}
	
	/* public void setId(int id)
	{
		this.id = id;
	} */
	
	public void setFrom(int from)
	{
		this.from = from;
	}
	
	public void setTo(int to)
	{
		this.to = to;
	}
	
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	public void setTraversed(boolean traversed)
	{
		this.traversed = traversed;
	}
	
	public void printEdge()
	{
		System.out.println(this.from + " " + this.to + " " + this.label);
	}
	
	public String toString() {
		return this.from + " " + this.to + " " + this.label;
	}
	
	public String getEdgeString() {
		return ""+this.from+this.to;
	}
}
