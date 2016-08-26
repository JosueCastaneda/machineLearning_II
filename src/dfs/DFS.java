package dfs;


/**
 * @author JesusAntonio
 *
 */
public class DFS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fName = "dat/g2.txt";
        Graph g = new Graph();
        g.ReadGraph(fName);
        System.out.println("Input Graph:");
        g.PrintGraph();
       // System.out.println("\n\nHola:");
        g.BuildDfsTree(1);
        //Graph g2 = g;
       // System.out.println("\n\nLola:");
        System.out.println("\n\nDFSTree:");
        g.PrintGraph();
        System.out.println("\n\nDFSCode:");
        g.PrintDfsCode();
        String workingdirectory = System.getProperty("user.dir");
        System.out.println(workingdirectory);
        
        fName = "dat/g3.txt";
        Graph g2 = new Graph();
        g2.ReadGraph(fName);
        System.out.println("Input Graph:");
        g2.PrintGraph();
       // System.out.println("\n\nHola:");
        g2.BuildDfsTree(1);
        //Graph g2 = g;
       // System.out.println("\n\nLola:");
        System.out.println("\n\nDFSTree:");
        g2.PrintGraph();
        System.out.println("\n\nDFSCode:");
        g2.PrintDfsCode();
        
       System.out.println(g2.getDfs().equals(g.getDfs()) );
	}
}
