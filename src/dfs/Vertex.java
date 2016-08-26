package dfs;


/**
 * @author Jesus A. Gonzalez
 * @created 03-03-2015
 * @modified 03-03-2015
 */
public class Vertex {
	public int id;
	public String label;
	private String color;
	private int discovery;
	private int finish;
	private int parent;
	private int degree;
	
    public Vertex(int id, String label)
    {
    	this.id = id;
    	this.label = label;
    	this.color = "white";
    	this.parent = -1;
    	this.discovery = -1;
    	this.finish = -1;
    	this.degree = 0;
    }
    
    public Vertex()
    {
    	this.id = -1;
    	this.label = "";
    	this.color = "";
    	this.parent = -1;
    	this.discovery = -1;
    	this.finish = -1;
    	this.degree = 0;
    }
    
    public int getId()
    {
    	return this.id;
    }
    
    public String getLabel()
    {
    	return this.label;
    }
    
    
    public String getColor()
    {
    	return this.color;
    }
    
    public int getDegree()
    {
    	return this.degree;
    }
    
    public void setId(int id)
    {
    	this.id = id;
    }

    public void setLabel(String label)
    {
    	this.label = label;
    }
    
    public void setColor(String color)
    {
    	this.color = color;
    }
    
    public void setParent(int parent)
    {
    	this.parent = parent;
    }
    
    public void setDiscovery(int discovery)
    {
    	this.discovery = discovery;
    }
    
    public void setFinish(int finish)
    {
    	this.finish = finish;
    }
    
    public void incrementDegree()
    {
    	this.degree++;
    }
    public void printVertex()
    {
        System.out.println(this.id + " " + this.label + " " + this.color + " " + this.parent + " " + this.discovery + " " + this.finish);
    }
    
    public String toString() {
    	return this.label+"("+this.id+")";
    }
}

