package josue;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import dfs.Vertex;

public class Embedding {
	
	String embeding="";
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	ArrayList<String> labels = new ArrayList<String>();

	public Embedding(String embeding, ArrayList<String> labels) {
		super();
		this.embeding = embeding;
		this.labels = labels;
	}
	
	public String getLabels(){
		String str="";
		for(int i=0; i<labels.size(); i++) {
			str+=labels.get(i);
		}
		return str;
	}
	
	public ArrayList<String> getLabelsArray() {
		return labels;
	}
	
	public void addVertex(Vertex v) {
		vertices.add(v);
	}

	@Override
	public String toString() {
		return "Embeddings [embeding=" + embeding + "]";
	}
	
	public String getEmbedding(){
		return this.embeding;
	}
	
	public void setEmbedding(String s) {
		this.embeding = s;
	}
	
	public void removeDuplicates() {
		char[] chars = embeding.toCharArray();
		Set<Character> charSet = new LinkedHashSet<Character>();
		for (char c : chars) {
		    charSet.add(c);
		}

		StringBuilder sb = new StringBuilder();
		for (Character character : charSet) {
		    sb.append(character);
		}
	}
	
	public String printVertices() {
		String str ="";
		for(int i=0; i<vertices.size(); i++) {
			str += vertices.get(i);
		}
		return str;
	}
	
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
	

}
