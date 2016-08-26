package dfs;



import java.io.BufferedReader;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Stack;

import josue.Embedding;

/**
 * @author JesusAntonio, Josue Castaneda
 * @date 03-03-2015
 * @modified 10-07-2015
 */

public class Graph {
    public int numVertices;
    public int numEdges;
	private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private ArrayList<Dfc> dfc;
    private DfsCode dfscode;
    private int time = 0;
    
    /* Possible Embeddings of the graph */
    ArrayList<Embedding> embeddings = new ArrayList<Embedding>();
    
    /* Subgrafos frecuentes of the graph */
    ArrayList<Graph> subgrafos = new ArrayList<Graph>();
    
    /* Labels of the graph */
	String etiquetas = "";
    

    public Graph()
    {
	    this.numVertices = 0;
	    this.numEdges = 0;
	    this.vertices = new ArrayList<Vertex>();
	    this.edges = new ArrayList<Edge>();
	    this.dfscode = new DfsCode();
	    this.dfc = new ArrayList<Dfc>();
    }
    
    /**
     * Add a list of subgraphs to a given graph G
     * @param List of supgraphs s
     */
    
    public void addSubgraphs( ArrayList<Graph> s){
    	this.subgrafos.addAll(s);
    }
    
    /**
     * Add one subgraph
     * @param Subgraph s
     */
    
    public void addSubgraph( Graph s){
    	this.subgrafos.add(s);
    }
    
    /**
     * Print subgraphs of this node
     */
    public void printSubgrafos(){
    	for(int i=0; i<subgrafos.size(); i++) {
    		subgrafos.get(i).PrintGraph();
    		System.out.println("-----");
    	}
    }
    
    
    public DfsCode getDfs(){
    	return this.dfscode;
    }

    public void AddVertex(int id, String label)
    {
    	Vertex myVertex = new Vertex(id, label);
    	this.vertices.add(myVertex);
    	this.numVertices++;
    }
    
    public void AddEdge(int from, int to, String label)
    {
    	System.out.println(from+","+to);
    	Edge myEdge = new Edge(from, to, label);
    	this.edges.add(myEdge);
    	this.numEdges++;
    	if(from == 0) {
    		this.vertices.get(from).incrementDegree();
    	}
    	else {
    		this.vertices.get(from - 1).incrementDegree();
    	}
    	if (to == 0) {
    		this.vertices.get(to).incrementDegree();
    	
    	}
    	else {
    		this.vertices.get(to - 1).incrementDegree();
    	}
    	
    	
    }
    
    public void AddDfc(String la, String le, String lb)
    {
    	Dfc myDfc = new Dfc(la, le, lb);
    	this.dfc.add(myDfc);
    }
    
    /**
     * Adds an embedding to a graph.
     * @param Embedding e
     */
    public void addEmbedding (Embedding e) {
    	this.embeddings.add(e);
    }
    
    /**
     * Creates the label information of the graph
     * 
     * @param String s
     */
    public void changeLabel(String s) {
    	this.etiquetas = s;
    }
    
    /**
     * 
     * @return Number of vertices
     */
    public int getNumEdges() {
    	return this.numEdges;
    }
    
    /**
     * 
     * @return The edges of the graph
     */
    public ArrayList<Edge> getEdges(){
    	return this.edges;
    }
    
    /**
     * 
     * @return The vertices of the graph
     */
    public ArrayList<Vertex> getVertices() {
    	return this.vertices;
    }
    
    /**
     * 
     * @param edgesNueva
     */
    
    public void setEdges(ArrayList<Edge>edgesNueva) {
    	this.edges = edgesNueva;
    }
    
    /**
     * Set the first embeddings of a graph, wich are the edges, each subgraph has
     * an embedding associated to him
     */
    
    public void setEmbeddings() {
    	for(int i=0; i<edges.size(); i++) {
    		Edge e = edges.get(i);
    		if (!e.getTraversed())
    			//System.out.println("Arco actual: "+e);
    		if (!e.getTraversed()){
    			/* Generate new subgraph*/
    			Graph subgrafo = new Graph();
    			//Vertex from = vertices.get(e.getFrom()-1);
    			//Vertex to = vertices.get(e.getTo()-1);
    			//Vertex from = vertices.get(e.getFrom());
    			//Vertex to = vertices.get(e.getTo());
    			
    			Vertex from = new Vertex();
				Vertex to = new Vertex();
				
				if(e.getFrom() == 0){
					from = vertices.get(e.getFrom());
				}else {
					from = vertices.get(e.getFrom()-1);
				}
				
				if(e.getTo() == 0 ) {
					to = vertices.get(e.getTo());
				}
				else {
					to = vertices.get(e.getTo()-1);
				}
    			
    			
    			subgrafo.AddVertex(from.getId(), from.getLabel());
    			subgrafo.AddVertex(to.getId(), to.getLabel());
    			subgrafo.setEdge(e.getFrom(), e.getTo(), e.getLabel());
    			subgrafo.edges.get(0).addVertex(from);
    			subgrafo.edges.get(0).addVertex(to);
    			ArrayList<String> labels = new ArrayList<String>();
				labels.add(e.getLabel());
    			Embedding em =new Embedding(Integer.toString(from.getId())+Integer.toString(to.getId()),labels);
    			em.addVertex(from);
    			em.addVertex(to);
    			subgrafo.addEmbedding(em);
    			e.setTraversed(true);
    			
    			/*Check the rest of the edges */
    			for (int j=0; j<edges.size(); j++) {
    				Edge e2 = edges.get(j);
    				if (!e2.getTraversed() && e.getLabel().equals(e2.getLabel())){ // Si no la he recorrido y tienen el mismo label
    					Vertex from2 = new Vertex();
    					Vertex to2 = new Vertex();
    					
    					if(e2.getFrom() == 0){
    						from2 = vertices.get(e2.getFrom());
    					}else {
    						from2 = vertices.get(e2.getFrom()-1);
    					}
    					
    					if(e2.getTo() == 0 ) {
    						to2 = vertices.get(e2.getTo());
    					}
    					else {
    						to2 = vertices.get(e2.getTo()-1);
    					}
    					
    					//
    					//
    					
    					
    					
    					if(from2.getLabel().equals(from.getLabel()) && to2.getLabel().equals(to.getLabel()) ) {
    						ArrayList<String> labels2 = new ArrayList<String>();
							labels2.add(e2.getLabel());
    						Embedding em2 =new Embedding(Integer.toString(from2.getId())+Integer.toString(to2.getId()),labels2);
    						em2.addVertex(from2);
    						em2.addVertex(to2);
    						subgrafo.addEmbedding(em2);
    				        e2.setTraversed(true);
    					}
    					else {
    						if(from2.getLabel().equals(to.getLabel()) && to2.getLabel().equals(from.getLabel()) ) {
    							ArrayList<String> labels2 = new ArrayList<String>();
    							labels2.add(e2.getLabel());
    							Embedding em2 =new Embedding(Integer.toString(to2.getId())+Integer.toString(from2.getId()),labels2);
    							em2.addVertex(to2);
    							em2.addVertex(from2);
    							subgrafo.addEmbedding(em2);
    					        e2.setTraversed(true);
    						}
    					}
    				}
    				
    			}
    			subgrafos.add(subgrafo);
    		}
    	}
    	
    }
    
    /**
     * Puts an edge in the graph and increments the vertex size
     * @param Verted id from
     * @param Vertex id to
     * @param Edge label
     */
    
    public void setEdge(int from, int to, String label) {
    	Edge myEdge = new Edge(from, to, label);
    	this.edges.add(myEdge);
    	this.numEdges++;
    	for(int i=0; i<vertices.size(); i++) {
    		if(vertices.get(i).getId() == from) {
    			vertices.get(i).incrementDegree();
    		}
    		if(vertices.get(i).getId() == to) {
    			vertices.get(i).incrementDegree();
    		}
    	}
    }
    
     
    /**
     * Imprime los embeddings por subgrafo
     */
    public void printEmbeddings() {
    	for(int i=0; i<subgrafos.size(); i++) {
    		Graph sg = subgrafos.get(i);
    		String subgrafo = "Subgrafo ("+i+") ";
    		for(int j=0; j<sg.embeddings.size(); j++) {
    			//subgrafo += " Embeddings: "+ sg.getEmbeddings().get(j).getEmbedding()+","+"Vertices:"+sg.getEmbeddings().get(j).printVertices()+", size:"+sg.getVertices().size() +",Labels "+sg.getEmbeddings().get(j).getLabels()+". ";
    			subgrafo += " Embeddings: "+ sg.getEmbeddings().get(j).getEmbedding()+","+",Labels "+sg.getEmbeddings().get(j).getLabels()+". ";
    		}
    		System.out.println(subgrafo);
    	}
    }
    
    public String printEmbeddingSize() {
    	
    	int contador=0;
    	
    	for(int i=0; i<subgrafos.size(); i++) {
    		Graph sg = subgrafos.get(i);
    		contador = contador + sg.embeddings.size();
    		
    	}
    	
    	return Integer.toString(contador);
    }
    
    public void printEmbeddingsGrafo(Graph g) {
    	for(int i=0; i<g.subgrafos.size(); i++) {
    		Graph sg = subgrafos.get(i);
    		String subgrafo = "Subrafo ("+i+")  Embeddings: ";
    		for(int j=0; j<sg.embeddings.size(); j++) {
    			subgrafo += sg.getEmbeddings().get(j).getEmbedding()+","+"Vertices:"+sg.getEmbeddings().get(j).printVertices()+",Labels "+sg.getEmbeddings().get(j).getLabels()+". ";
    		}
    		System.out.println(subgrafo);
    	}
    }
    
    /**
     * 
     * @return Returns embeddings of a graph 
     */
    
    public ArrayList<Embedding> getEmbeddingSubGraph(){
    	ArrayList<Embedding> embeddings = new ArrayList<Embedding>();
    	for(int i=0; i<subgrafos.size(); i++) {
    		Graph g = subgrafos.get(i);
    		for(int j=0; j<g.getEmbeddingsSize(); j++) {
    			//System.out.println(g.getEmbeddings().get(j));
    			embeddings.add(g.getEmbeddings().get(j));
    		}
    	}
    	return embeddings;
    }
    
    
    public ArrayList<Graph> getSubGrafos() {
    	return this.subgrafos;
    }
    
    public void setSubGrafos(ArrayList<Graph> subgrafosFrecuentes) {
    	this.subgrafos  = subgrafosFrecuentes;
    }
    
    /**
     * Order the embeddings of a graph
     * 
     * @return List of strings
     */

	public ArrayList<String> ordenaEmbeddings() {
		ArrayList<Embedding> embeddings = getEmbeddingSubGraph();
		ArrayList<String> embeddingsOrdenados = new ArrayList<String>();
		/* Ordeno los embeddings */
		for(int i=0; i<embeddings.size(); i++) {
			char[] arr = embeddings.get(i).getEmbedding().toCharArray();
			Arrays.sort(arr);
			String str = new String(arr);//.toString();
			embeddingsOrdenados.add(str);
		}
		return embeddingsOrdenados;
	}
    
    /**
     * 
     * @return The embeddings of the graph
     */
    public ArrayList<Embedding> getEmbeddings() {
   
   	return embeddings;
    }
    
    /**
     * 
     * @return size of the embeddings
     */
    public int getEmbeddingsSize() {
    	return this.embeddings.size();
    }
    
    public boolean FindDfc(String la, String le, String lb)
    {
    	boolean found = false;
    	for(int i = 0; i < dfc.size(); i++)
    	{
    		if(dfc.get(i).getLa().equals(la) && dfc.get(i).getLe().equals(le) && dfc.get(i).getLb().equals(lb))
    		{
    			found = true;
    			break;
    		}
    		else if (dfc.get(i).getLa().equals(lb) && dfc.get(i).getLe().equals(le) && dfc.get(i).getLb().equals(la))
    		{
    			found = true;
    			break;
    		}
    	}
    	return found;
    }
    
    public void ReadGraph(String name)
    {
    	BufferedReader br = null;
    	String nextItem = "";
    	int id = 0;
    	String label = "";
    	int from = 0;
    	int to = 0;
		try {
			br = new BufferedReader(new FileReader(name));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String line;
    	try {
			while((line = br.readLine()) != null)
			{
				StringTokenizer tok = new StringTokenizer(line," ");
				nextItem = tok.nextToken();
				if(nextItem.contentEquals("v"))
				{
				    while(tok.hasMoreTokens())
				    {
					    id = Integer.parseInt(tok.nextToken());
					    label = tok.nextToken();
					    AddVertex(id, label);
				    }
				}
				else if(nextItem.equals("e") || nextItem.equals("d") || nextItem.equals("u"))
				{
					while(tok.hasMoreTokens())
					{
					    from = Integer.parseInt(tok.nextToken());
					    to = Integer.parseInt(tok.nextToken());
					    label = tok.nextToken();
					    AddEdge(from, to, label);
					    if(from == 0 || to ==0 )
					    	AddDfc(vertices.get(from).getLabel(), label, vertices.get(to).getLabel());
					    else
					    	AddDfc(vertices.get(from - 1).getLabel(), label, vertices.get(to - 1).getLabel());
					    	
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Collections.sort(dfc,Dfc.DfcLaComparator);
    	//this.printDfcList();
    }
    
    public void PrintDfsCode()
    {
    	this.dfscode.PrintCode();
    }
    
    public String GetEdgeLabel(int from, int to)
    {
    	int edgeCount = 0;
    	String myLabel = "";
    	
    	while(edgeCount < this.edges.size())
    	{
    		if((this.edges.get(edgeCount).getFrom() == from && this.edges.get(edgeCount).getTo() == to) || (this.edges.get(edgeCount).getFrom() == to && this.edges.get(edgeCount).getTo() == from))
    		{
    			myLabel = this.edges.get(edgeCount).getLabel();
    			break;
    		}
    		edgeCount++;
    	}
    	return myLabel;
    }
    
    public void printDfcList()
    {
    	for(int i = 0; i < dfc.size(); i++)
    	{
    		dfc.get(i).printDfc();
    	}
    }
    
    public void SetTraversed(int from, int to)
    {
    	int edgeCount = 0;
    	
    	while(edgeCount < this.edges.size())
    	{
    		if((this.edges.get(edgeCount).getFrom() == from && this.edges.get(edgeCount).getTo() == to) || (this.edges.get(edgeCount).getFrom() == to && this.edges.get(edgeCount).getTo() == from))
    		{
    			this.edges.get(edgeCount).setTraversed(true);
    			break;
    		}
    		edgeCount++;
    	}
    }

    public boolean GetTraversed(int from, int to)
    {
    	int edgeCount = 0;
    	boolean traversed = false;
    	
    	while(edgeCount < this.edges.size())
    	{
    		if((this.edges.get(edgeCount).getFrom() == from && this.edges.get(edgeCount).getTo() == to) || (this.edges.get(edgeCount).getFrom() == to && this.edges.get(edgeCount).getTo() == from))
    		{
    			traversed = this.edges.get(edgeCount).getTraversed();
    			break;
    		}
    		edgeCount++;
    	}
    	return traversed;
    }
    
    public void PrintGraph()
    {
    	for(int i = 0; i < this.vertices.size(); i++)
    	{
    	    this.vertices.get(i).printVertex();	
    	}
    	for(int i = 0; i < this.edges.size(); i++)
    	{
    		this.edges.get(i).printEdge();
    	}
    }
    
    public void IncrementDfcCount(String la, String le, String lb)
    {
    	for(int i = 0; i < this.dfc.size(); i++)
    	{
    		if((dfc.get(i).la.equals(la) && dfc.get(i).le.equals(le) && dfc.get(i).lb.equals(lb)) || (dfc.get(i).la.equals(lb) && dfc.get(i).le.equals(le) && dfc.get(i).lb.equals(la)))
    		{
    			dfc.get(i).count++;
    			break;
    		}
    	}
    	return;
    }
    
    public void printEdges() {
    	for(int i =0; i<edges.size(); i++){
    		System.out.println(edges.get(i));
    	}
    }
    
    public void CreateDfc()
    {
    	Edge myedge = new Edge();
    	int lcomp = 0;
    	boolean foundDfc = false;

    	for(int i = 0; i < this.edges.size(); i++)
    	{
    		myedge = edges.get(i);
    		foundDfc = FindDfc(vertices.get(myedge.getFrom()).getLabel(), edges.get(i).getLabel(), vertices.get(myedge.getTo()).getLabel());
    		lcomp = vertices.get(myedge.getFrom()).getLabel().compareTo(vertices.get(myedge.getTo()).getLabel());
    		
    		if(foundDfc)
    		{
    			IncrementDfcCount(vertices.get(myedge.getFrom()).getLabel(), edges.get(i).getLabel(), vertices.get(myedge.getTo()).getLabel());
    			break;
            }
    		else
    		{
    		    if(lcomp < 0)
    		    {
    		        Dfc mydfc = new Dfc(vertices.get(myedge.getFrom()).getLabel(), myedge.getLabel(), vertices.get(myedge.getTo()).getLabel());
        		    dfc.add(mydfc);
    		    }
    		    else
    		    {
    			    Dfc mydfc = new Dfc(vertices.get(myedge.getTo()).getLabel(), myedge.getLabel(), vertices.get(myedge.getFrom()).getLabel());
        		    dfc.add(mydfc);
    		    }
    		}
    	}
    }
    
    
    public Vertex GetNextVertex()
    {
    	for(int i = 0; i < vertices.size(); i++)
    	{
    		if(vertices.get(i).getColor() == "white")
    		{
    			return vertices.get(i);
    		}
    	}
    	return null;
    }
    
    // Use index of array-1 for vertex index!!!!
    // Array starts counting at 0 while vertices indexes start at 1
    public void BuildDfsTree(int root)
    {
    	Vertex u = new Vertex();
    	int index = root;
    	System.out.println("Root: "+root);
    	
    	//Cambio...para que funcione este sistema
    	for(int i=0; i<this.vertices.size(); i++) {
    		if(root == this.vertices.get(i).getId()) {
    			index = i;
    			System.out.println("Size: "+vertices.size() +" vertices ("+i+")"+this.vertices.get(i)+" ID: "+this.vertices.get(i).getId()+" root: "+index	);
    		}
    		
    	}
    	//u = this.vertices.get(root -1);
    	u = this.vertices.get(index);
    	System.out.println("Root: "+root+", Vertice: "+ u);
    	//System.out.println("HOLA2");
    	//System.out.println(u.getLabel());
    	//System.out.println(GetNextVertex().getLabel());
    	//System.out.println("LOLA");
    	while(u != null)
    	{
    		if(u.getColor().equals("white"))
    		{
    		    DFSVisit(u);
    		}
    		u = GetNextVertex();
    	}
    }
    
    
    public int FindBackward(ArrayList<Vertex> adj)
    {
    	int b = -1;
    	int deg = -1;
    	for(int i = 0; i < adj.size(); i++)
    	{
    		if(adj.get(i).getColor().equals("gray") && adj.get(i).getDegree() > deg)
    		{
    			b = i;
    		}
    	}
    	return b;
    }

    
	public int getCodeIndex(String la, String le, String lb)
	{
		int codeIndex = -1;
		for(int i = 0; i < dfc.size(); i++)
		{
			if((dfc.get(i).getLa().equals(la) && dfc.get(i).getLe().equals(le) && dfc.get(i).getLb().equals(lb)) || (dfc.get(i).getLa().equals(lb) && dfc.get(i).getLe().equals(le) && dfc.get(i).getLb().equals(la)))
			{
				codeIndex = i;
				break;
			}
		}
		return codeIndex;
	}
	
    
    public int FindForward2(int from, ArrayList<Vertex> adj)
    {
    	ArrayList<AdjOrder> adjOrd = new ArrayList<AdjOrder>();
    	System.out.println("From: "+from+", adj size: "+adj.size()+", Aqui es el problema");
    	
    	for(int i = 0; i < adj.size(); i++)
    	{
    		AdjOrder myAdjOrd = new AdjOrder(i, getCodeIndex(vertices.get(from).getLabel(), GetEdgeLabel(from, adj.get(i).getId()), adj.get(i).getLabel()), adj.get(i).getDegree());
    		adjOrd.add(myAdjOrd);
    	}
    	Collections.sort(adjOrd,AdjOrder.AdjOrderComparator);
    	System.out.println("ERROR 5*: "+adjOrd);
    	if(adj.size() < 1) {
    		return -1;
    	}else {
    	return adjOrd.get(0).getVertexIndex();
    	}
    }
    
    
    public int FindForward(ArrayList<Vertex> adj)
    {
    	int f = -1;
    	int deg = -1;
    	
    	for(int i = 0; i < adj.size(); i++)
    	{
    		if(adj.get(i).getDegree() > deg)
    		{
    			f = i;
    			deg = adj.get(i).getDegree();
    		}
    	}
    	return f;
    }
    
    
    public int ChooseNextVertex(int from, ArrayList<Vertex> adj)
    {
    	int bIndex = -1;
    	int myIndex = -1;
    	// First return b edges by highest degree
    	// choose smallest LVEV b or
    	// choose highest degree or
    	// choose highest LVEV counter
    	bIndex = FindBackward(adj);
        if(bIndex != -1)
        {
        	myIndex = bIndex;
        }
        else
        {
        	System.out.println("CHoose forward :");
        	
        	myIndex = FindForward2(from, adj);
        }
        return myIndex;	
    }
    
    
    public void DFSVisit(Vertex u)
    {
    	System.out.println("Vertice VISIT :"+u);
    	
    	u.setColor("gray");
    	this.time++;
    	u.setDiscovery(time);
    	ArrayList<Vertex> adj = GetAdjVerticesList(u);    	
    	
    	int vIndex = ChooseNextVertex(u.getId(), adj);
    	System.out.println(vIndex);
    	Vertex v = new Vertex();
    	if(vIndex != -1){
    		System.out.println("Entro aqui xD");
    		 v = adj.get(vIndex);
    		 adj.remove(vIndex);
    	}
    	else {
    		 v  = null;
    	}
    	
    	System.out.println(v);

    	while(v != null)
    	{
    		if(v.getColor().equals("white"))
    		{
    			v.setParent(u.getId());
    	    	// Create and store new f edge
    	    	this.dfscode.AddCodeEdge(u.getId(), v.getId(), u.getLabel(), v.getLabel(), this.GetEdgeLabel(u.getId(),v.getId()), "f");
    	    	this.SetTraversed(u.getId(), v.getId());
    			DFSVisit(v);
    		}
    		else
    		{
    			// Create and store new b edge
    			if(!GetTraversed(u.getId(), v.getId()))
    			{
    			    this.dfscode.AddCodeEdge(u.getId(), v.getId(), u.getLabel(), v.getLabel(), this.GetEdgeLabel(u.getId(),v.getId()), "b");
    			    this.SetTraversed(u.getId(), v.getId());
    			}
    		}
    		if(adj.isEmpty() == false)
    		{
    			vIndex = ChooseNextVertex(u.getId(), adj);
    			v = adj.get(vIndex);
    			adj.remove(vIndex);
    		}
    		else
    		{
    			break;
    		}
    	}
    	u.setColor("black");
    	this.time++;
    	u.setFinish(time);
    
    }
   
    
    // Get adjacent vertices for the implementation of DFS in an array
    // Gives more freedom to choose the next vertex to visit
    public ArrayList<Vertex> GetAdjVerticesList(Vertex u)
    {
    	System.out.println("Vertice ADJACENT: "+u);
    	ArrayList<Vertex> adj = new ArrayList<Vertex>();
    	Edge e = new Edge();
    	
    	for(int i = 0; i < this.edges.size(); i++)
    	{
    		e = edges.get(i);
    		//System.out.println("Edges:"+e);
    		if(u.getId() == (e.getFrom()))
    		{
    			//System.out.println("ERROR3");
    			//Cambio
    			int index = e.getTo();
    			for(int p=0; p<this.vertices.size(); p++) {
    	    		if(index == this.vertices.get(p).getId()) {
    	    			index = p;
    	    			//System.out.println("Size: "+vertices.size() +" vertices ("+p+")"+this.vertices.get(p)+" ID: "+this.vertices.get(p).getId()+" INDEX: "+index	);
    	    		}
    	    		
    	    	}
    			
    			//adj.add(this.vertices.get(e.getTo() - 1));
    			adj.add(this.vertices.get(index));
    			//System.out.println("ERROR4");
    		}
    		else if(u.getId() == e.getTo())
    		{
    			
    			//Cambio
    			int index = e.getFrom();
    			System.out.println("ERROR1, indice: "+index);
    			for(int p=0; p<this.vertices.size(); p++) {
    	    		if(index == this.vertices.get(p).getId()) {
    	    			index = p;
    	    			//System.out.println("Size: "+vertices.size() +" vertices ("+p+")"+this.vertices.get(p)+" ID: "+this.vertices.get(p).getId()+" INDEX: "+index	);
    	    		}
    	    		
    	    	}
    			//adj.add(this.vertices.get(e.getFrom() - 1));
    			System.out.println("Indice:"+index+" vertice: "+this.vertices.get(index));
    			adj.add(this.vertices.get(index));
    			System.out.println("ERROR2");
    		}
    	}
    	System.out.println("FIN AD");
    	return adj;
    }
    
    
    // Get adjacent vertices for the implementation of DFS in a stack
    public Stack<Vertex> GetAdjVertices(Vertex u)
    {
    	Stack<Vertex> st = new Stack<Vertex>();
    	Edge e = new Edge();

    	for(int i = 0; i < this.edges.size(); i++)
    	{
    		e = edges.get(i);	
    		if(u.getId() == (e.getFrom()))
    		{
    			st.push(this.vertices.get(e.getTo() - 1));
    		}
    		else if(u.getId() == e.getTo())
    		{
    			st.push(this.vertices.get(e.getFrom() - 1));
    		}
    	}
    	return st;
    }

    
    /**
     * @return Frequent subgraphs of size i+1
     */
    
    public Graph extensionG() {
    	
    	ArrayList<Edge> edges = getEdges();
    	ArrayList<Graph> pruebaGrafo = new ArrayList<Graph>();
    	ArrayList<Embedding> nuevosEmbeddings = new ArrayList<Embedding>();
    	
    	for(int i=0; i<subgrafos.size(); i++) {
    		Graph subgrafo = subgrafos.get(i);
    		for(int j=0; j<subgrafo.getEmbeddings().size(); j++) {
    			Embedding e = subgrafo.getEmbeddings().get(j);
    				int k=0;
    				int m =e.getEmbedding().length()-1;
    				String e_first = ""+e.getEmbedding().charAt(k);
    				String e_last = ""+e.getEmbedding().charAt(m);
    				for(int l=0; l<edges.size(); l++) {
    					
    					String edge_first = ""+edges.get(l).getEdgeString().charAt(0);
    					String edge_last = ""+edges.get(l).getEdgeString().charAt(1);
    					
    						if(e_last.equals(edge_first)) { //Caso A 12 25 => 125
    							if(!e_first.equals(edge_last)) {
	    							Graph nuevoSubGrafo = new Graph();
	    							Vertex v_first = e.getVertices().get(k);
	    							nuevoSubGrafo.AddVertex(v_first.getId(), v_first.getLabel());
	    							
	    							for(int h=1; h<e.getEmbedding().length(); h++) { //Caso A 512 23 = 5123
	    								Vertex vFirst = subgrafo.getVertices().get(h-1);
	    								Vertex vNext = subgrafo.getVertices().get(h);
	    								nuevoSubGrafo.AddVertex(vNext.getId(),vNext.getLabel());
	    								nuevoSubGrafo.setEdge(vFirst.getId(), vNext.getId(),e.getLabelsArray().get(h-1));
	    							}
	    							
	    							Vertex v_last = e.getVertices().get(m);
	    							Vertex v_to = new Vertex( edges.get(l).getTo(), edges.get(l).getLabel());
	    							nuevoSubGrafo.AddVertex(v_to.getId(), v_to.getLabel());
	    							nuevoSubGrafo.setEdge(v_last.getId(), v_to.getId(),edges.get(l).getLabel());
	    							
	    							ArrayList<String> labels = new ArrayList<String>();
	    							for(int p=0; p<e.getLabelsArray().size(); p++) {
	    								labels.add(e.getLabelsArray().get(p));
	    							}
	    							
	    							labels.add(edges.get(l).getLabel());
	    							Embedding em = new Embedding(e.getEmbedding()+edge_last, labels);
	    							//System.out.println("CASO A "+"Embedding viejo: "+e.getEmbedding()+" Nuevo embedding: "+em.getEmbedding()+" Vertices: "+nuevoSubGrafo.getVertices());
	    							//System.out.println("CASO A "+"Embedding viejo: "+e.getEmbedding()+", edge:"+edges.get(l).getEdgeString()+" Nuevo embedding: "+em.getEmbedding()+" Vertices: "+nuevoSubGrafo.getVertices());
	    							
	    							for(int t=0; t<nuevoSubGrafo.getVertices().size(); t++) {
	    								em.addVertex(nuevoSubGrafo.getVertices().get(t));
	    							}
	    							
	    							if(checkRedundant(em) == true) {
	    							
		    							if(reduce(em, nuevosEmbeddings)){
		    								nuevoSubGrafo.addEmbedding(em);
		    								//nuevoSubGrafo.BuildDfsTree(1);
		    								//System.out.println("\n\nDFSCode:");
		    								//nuevoSubGrafo.PrintDfsCode();
		    								//System.out.println("Input Graph:");
		    						        //nuevoSubGrafo.PrintGraph();
		    								//System.out.println("CASO A "+"Embedding viejo: "+e.getEmbedding()+", edge:"+edges.get(l).getEdgeString()+" Nuevo embedding: "+em.getEmbedding()+" Vertices: "+nuevoSubGrafo.getVertices());
		    						        //nuevoSubGrafo.BuildDfsTree(0);
		    						        //nuevoSubGrafo.PrintDfsCode();
		    						        //System.out.println("Termina aca");
		    								subgrafos.get(i).addSubgraph(nuevoSubGrafo);
		    								pruebaGrafo.add(nuevoSubGrafo);
		    								nuevosEmbeddings.add(em);
		    								//System.out.println("CASO A "+"Embedding viejo: "+e.getEmbedding()+", edge:"+edges.get(l).getEdgeString()+" Nuevo embedding: "+em.getEmbedding()+" Vertices: "+nuevoSubGrafo.getVertices());
		    							}
	    							}
    							}
    						}
    						
    						if(e_last.equals(edge_last)) { //Caso B 15 25 => 152
    							if(!e_first.equals(edge_first)) { //Caso extendido 126 46
	    							Graph nuevoSubGrafo = new Graph();	
	    							Vertex v_first = e.getVertices().get(k);
	    							nuevoSubGrafo.AddVertex(v_first.getId(), v_first.getLabel());
	    							
	    							for(int h=1; h<e.getEmbedding().length(); h++) { //Caso B extendido 126 46
	    								Vertex vFirst = nuevoSubGrafo.getVertices().get(h-1);
	    								Vertex vNext = e.getVertices().get(h);
	    								nuevoSubGrafo.AddVertex(vNext.getId(),vNext.getLabel());
	    								nuevoSubGrafo.setEdge(vFirst.getId(), vNext.getId(),e.getLabelsArray().get(h-1));
	    							}
	    							
	    							Vertex v_last = e.getVertices().get(m);
	    							Vertex v_to = new Vertex( edges.get(l).getFrom(), edges.get(l).getLabel());
	    							nuevoSubGrafo.AddVertex(v_to.getId(), v_to.getLabel());
	    							nuevoSubGrafo.setEdge(v_last.getId(), v_to.getId(),edges.get(l).getLabel());
	    							
	    							ArrayList<String> labels = new ArrayList<String>();
	    							for(int p=0; p<e.getLabelsArray().size(); p++) {
	    								labels.add(e.getLabelsArray().get(p));
	    							}
	    							
	    							labels.add(edges.get(l).getLabel());
	    							Embedding em = new Embedding(e.getEmbedding()+edge_first, labels);
	    						
	    							for(int t=0; t<nuevoSubGrafo.getVertices().size(); t++) {
	    								em.addVertex(nuevoSubGrafo.getVertices().get(t));
	    							}
	    							
	    							if(checkRedundant(em) == true) {
	    								if(reduce(em, nuevosEmbeddings)){		    							
		    								nuevoSubGrafo.addEmbedding(em);
		    								subgrafos.get(i).addSubgraph(nuevoSubGrafo);
		    								pruebaGrafo.add(nuevoSubGrafo);
		    								nuevosEmbeddings.add(em);
		    								//System.out.println("CASO B "+"Embedding viejo: "+e.getEmbedding()+", edge:"+edges.get(l).getEdgeString()+" Nuevo embedding: "+em.getEmbedding()+" Vertices: "+nuevoSubGrafo.getVertices());
		    							}
	    							}
    							}
    						}
    						
    						if(e_first.equals(edge_first)) { //Caso C 25 23 => 325
    							if(!e_last.equals(edge_last)){ //Caso C extendido 264 25 => 5264
    								Graph nuevoSubGrafo = new Graph();	    							
	    							Vertex v_first = new Vertex(edges.get(l).getTo(), edges.get(l).getLabel());
	    							Vertex v_second = e.getVertices().get(k);
	    							nuevoSubGrafo.AddVertex(v_first.getId(),v_first.getLabel());	    							
	    							nuevoSubGrafo.AddVertex(v_second.getId(),v_second.getLabel());
	    							nuevoSubGrafo.setEdge(v_first.getId(), v_second.getId(),e.getLabelsArray().get(k) );
	    				
	    							//System.out.println("CASO C "+"Embedding viejo: "+e.getEmbedding()+", edge:"+edges.get(l).getEdgeString()+" Vertices: "+nuevoSubGrafo.getVertices());
	    							
	    							for(int h=1; h<e.getEmbedding().length(); h++) { //Caso C extendido 264 25 => 5264
	    								Vertex vFirst = nuevoSubGrafo.getVertices().get(h-1);
	    								Vertex vNext = e.getVertices().get(h);
	    								nuevoSubGrafo.AddVertex(vNext.getId(),vNext.getLabel());
	    								nuevoSubGrafo.setEdge(vFirst.getId(), vNext.getId(),e.getLabelsArray().get(h-1));
    								}
	    							
	    							ArrayList<String> labels = new ArrayList<String>();
	    							labels.add(edges.get(l).getLabel());
	    							for(int p=0; p<e.getLabelsArray().size(); p++) {
	    								labels.add(e.getLabelsArray().get(p));
	    							}
	    						
	    							Embedding em = new Embedding(edge_last+e.getEmbedding(), labels);
	    							
	    							for(int t=0; t<nuevoSubGrafo.getVertices().size(); t++) {
	    								em.addVertex(nuevoSubGrafo.getVertices().get(t));
	    							}
	    							
	    							if(checkRedundant(em) == true) {
		    							if(reduce(em, nuevosEmbeddings)){
		    								nuevoSubGrafo.addEmbedding(em);
		    								subgrafos.get(i).addSubgraph(nuevoSubGrafo);
		    								pruebaGrafo.add(nuevoSubGrafo);
		    								nuevosEmbeddings.add(em);
		    								//System.out.println("CASO C "+"Embedding viejo: "+e.getEmbedding()+", edge:"+edges.get(l).getEdgeString()+" Nuevo embedding: "+em.getEmbedding()+" Vertices: "+nuevoSubGrafo.getVertices());
		    							}
	    							}
	    							
	    						}
    							
    						}
    						
    						if(e_first.equals(edge_last)) { //Caso D 62 46 => 264 
    							if(!e_last.equals(edge_first)) { //Caso D extendido 234 12 => 1234
	    							Graph nuevoSubGrafo = new Graph();
	    							Vertex v_first = new Vertex(edges.get(l).getFrom(), edges.get(l).getLabel());
	    							Vertex v_second = e.getVertices().get(k);
	    							nuevoSubGrafo.AddVertex(v_first.getId(), v_first.getLabel());
	    							nuevoSubGrafo.AddVertex(v_second.getId(),v_second.getLabel());
	    							nuevoSubGrafo.setEdge(v_first.getId(), v_second.getId(),e.getLabelsArray().get(k) );
	    							
	    							for(int h=1; h<e.getEmbedding().length(); h++) { //Caso C extendido 264 25 => 5264
	    								Vertex vFirst = nuevoSubGrafo.getVertices().get(h-1);
	    								Vertex vNext = e.getVertices().get(h);
	    								nuevoSubGrafo.AddVertex(vNext.getId(),vNext.getLabel());
	    								nuevoSubGrafo.setEdge(vFirst.getId(), vNext.getId(),e.getLabelsArray().get(h-1));
    								}
	    							
	    							ArrayList<String> labels = new ArrayList<String>();
	    							labels.add(edges.get(l).getLabel());
	    							for(int p=0; p<e.getLabelsArray().size(); p++) {
	    								labels.add(e.getLabelsArray().get(p));
	    							}
	    							
	    							Embedding em = new Embedding(edge_first+e.getEmbedding(), labels);
	    							for(int t=0; t<nuevoSubGrafo.getVertices().size(); t++) {
	    								em.addVertex(nuevoSubGrafo.getVertices().get(t));
	    							}
	    							
	    							if(checkRedundant(em) == true) {
	    							
		    							if(reduce(em, nuevosEmbeddings)){
			    							
		    								nuevoSubGrafo.addEmbedding(em);
		    								subgrafos.get(i).addSubgraph(nuevoSubGrafo);
		    								pruebaGrafo.add(nuevoSubGrafo);
		    								nuevosEmbeddings.add(em);
		    								//System.out.println("CASO D "+"Embedding viejo: "+e.getEmbedding()+", edge:"+edges.get(l).getEdgeString()+" Nuevo embedding: "+em.getEmbedding()+" Vertices: "+nuevoSubGrafo.getVertices());
		    							}
	    							}
	    						}
    						}
    					//}//FIN IGUAL
    				}
    			//}//Termina K
    		}
    		//subgrafo.printEmbeddings();
    		
    	}
    	
    	Graph subgrafoFrecuente = new Graph();
    	subgrafoFrecuente.setSubGrafos(pruebaGrafo);
    	subgrafoFrecuente.setEdges(edges);
    	
    	return subgrafoFrecuente;
    }
    
    /**
     * Checks and embedding em if its repeated, 
     * true = em is not repeated
     * false = em is repeated
     * @param em
     * @return
     */
    
    public boolean checkRedundant(Embedding em) {
    	for(int i=0; i<em.getEmbedding().length();i++) {
    		String first = String.valueOf(em.getEmbedding().charAt(i));
    		for(int j=0; j<em.getEmbedding().length(); j++) {
    			String second = String.valueOf(em.getEmbedding().charAt(j));
    			if(i!= j) {
    				
    				if(first.equals(second)) {
    					return false;
    				}
    			}
    		}
    	}
    	return true;
    }

        
    /**
     * 
     * @param Embedding e
     * @return indice de subgrafo
     */
    public int encuentraSubgrafo(Embedding e) {
    	int index = -1;
    	for(int i =0; i<subgrafos.size(); i++) {
    		Graph sb = subgrafos.get(i);
    		for (int j=0; j<sb.getEmbeddings().size(); j++) {
    			if (sb.getEmbeddings().get(j).equals(e)){
    				index = j;
    			}
    		}
    	}
    	return index;
    }
    
    /**
	 * Revisa si un embedding no esta repetido para un grafo dado
	 * @param e
	 * @param g
	 * @return
	 */

	public  boolean revisaUnico(Embedding e, Graph g, ArrayList<Embedding> lista_e) {
		ArrayList<String> embeddingOrdenados = new ArrayList<String>();
		char[] arr = e.getEmbedding().toCharArray();
		
		for(int i=0; i<lista_e.size(); i++) {	
			
			char[] arr2 = lista_e.get(i).getEmbedding().toCharArray();
			Arrays.sort(arr2);
			String str = new String(arr2);
			embeddingOrdenados.add(str);
		}

		Arrays.sort(arr);
		String str = new String(arr);

		for(int i=0; i<embeddingOrdenados.size(); i++) {
			if(str.equals(embeddingOrdenados.get(i))) {
				return false;
			}
		}

		return true;
	}

	public  boolean reduce(Embedding e, ArrayList<Embedding> lista_e) {
		ArrayList<String> embeddingOrdenados = new ArrayList<String>();
		char[] arr = e.getEmbedding().toCharArray();
		
		for(int i=0; i<lista_e.size(); i++) {	
			
			char[] arr2 = lista_e.get(i).getEmbedding().toCharArray();
			Arrays.sort(arr2);
			String str = new String(arr2);
			embeddingOrdenados.add(str);
		}

		Arrays.sort(arr);
		String str = new String(arr);

		for(int i=0; i<embeddingOrdenados.size(); i++) {
			if(str.equals(embeddingOrdenados.get(i))) {
				return false;
			}
		}

		return true;
	}

	
	/**
	 * Espejo de string
	 * @param String de entrada  s 123
	 * @return String volteada 321
	 */
	
	public String shiftString(String s) {
		String sh_str = "";
		for(int i = s.length()-1; i>-1; i--) {
			char c = s.charAt(i);
			sh_str= sh_str + c;
		}
		return sh_str;
	}
	
	/**
	 * Regresa una lista de embeddings no 
	 * repetidos
	 * 
	 * @param Lista de embeddings e
	 * @return Lista de embeddings sin redundantes
	 */
	
	public ArrayList<Embedding> removeRedundant(ArrayList<Embedding> e) {
		ArrayList<Embedding> nonRedundantEmbedding= new ArrayList<Embedding>();
		for(int i=0; i<e.size(); i++) {
			String e_r = e.get(i).getEmbedding();
			boolean repetido = checkRepeated(e_r);
			if(!repetido) {
				nonRedundantEmbedding.add(e.get(i));
			}
			
		}
		return nonRedundantEmbedding;
	}
	
	/**
	 * Revisa si una cadena tiene caracteres
	 * repetidos
	 * 
	 * @param String s
	 * @return True si la cadena tiene caracteres repetidos
	 */
	public boolean checkRepeated(String s) {
		for(int i=0; i<s.length(); i++) {
			for(int j=0; j<s.length(); j++) {
				if(i!= j) {
				if(s.charAt(i) == s.charAt(j)){
					return true;
				}
				}
			}
		}		
		return false;
	}
  
}
