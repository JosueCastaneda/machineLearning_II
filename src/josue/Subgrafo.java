package josue;

import java.util.ArrayList;
import dfs.Vertex;

/**
 * 
 * @author josue
 * This class represents a subgraph.
 */

public class Subgrafo {
	
	/* Los vertices que componen el subgrafo. Estan ordenados*/
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	
	/* La etiqueta de los grafos */
	String etiquetas = "";

	/* Codigo DFS */
	
	/* Embeddings */
	ArrayList<Embedding> embeddings = new ArrayList<Embedding>();
	
	public Subgrafo(ArrayList<Vertex> vertices, String etiquetas) {
		super();
		this.vertices = vertices;
		this.etiquetas = etiquetas;
	}
	
	
	
	
	

}
