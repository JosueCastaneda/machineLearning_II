package dfs;

import java.util.Comparator;

/**
 * @author JesusAntonio
 *
 */
public class AdjOrder {
	int vertexIndex;
	int dfcIndex;
	int vertexDegree;
	
	public AdjOrder(int vi, int ci, int vd)
	{
		this.vertexIndex = vi;
		this.dfcIndex = ci;
		this.vertexDegree = vd;
	}
	
	public int getVertexIndex()
	{
		return this.vertexIndex;
	}
	
	public int getDfcIndex()
	{
		return this.dfcIndex;
	}
	
	public int getVertexDegree()
	{
		return this.vertexDegree;
	}
	
	public static Comparator<AdjOrder> AdjOrderComparator
		= new Comparator<AdjOrder>()
	{
		public int compare(AdjOrder code1, AdjOrder code2)
		{
			int c1 = code1.getDfcIndex();
			int c2 = code2.getDfcIndex();
			int d1 = code1.getVertexDegree();
			int d2 = code2.getVertexDegree();

			if(c1 != c2)
			{
				//ascending order
				return c2 - c1;
			}
			else if(d1 != d2)
			{
				return d2 - d1;
			}
			return 0;
		}
	};
}
