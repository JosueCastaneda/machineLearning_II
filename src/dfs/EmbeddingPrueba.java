package dfs;

import java.util.ArrayList;

import josue.Embedding;

public class EmbeddingPrueba {

	/**
	 * We test the correct embedding of the subgraph-1 of any graph
	 * @param args
	 */

	static ArrayList<Graph> subgrafos = new ArrayList<Graph>();
	static ArrayList<Embedding> embeddings = new ArrayList<Embedding>(); //Embeddings subgrafo 1

	public static void main(String[] args) {
		//String fName = "dat/g3.txt";
		String fName = "dat/g8_3.txt";
		Graph g = new Graph();
		g.ReadGraph(fName);
		System.out.println("Input Graph:");
        g.PrintGraph();
        long startTime = System.currentTimeMillis();
		g.setEmbeddings();
		System.out.println(", size: "+g.printEmbeddingSize());
		System.out.println("--------------------");
		
		for(int i=0; i<10;i++) {
			Graph g2 = new Graph();
			g2 = g.extensionG();
			System.out.println("i="+(i+1)+", size: "+g2.printEmbeddingSize());
			
			g2.printEmbeddings();
			//g2.printEmbeddingSize();
			g = g2;
			
		}
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total time:"+totalTime);
	}


}
