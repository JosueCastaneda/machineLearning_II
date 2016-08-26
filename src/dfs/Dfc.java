package dfs;

import java.util.Comparator;

/**
 * @author JesusAntonio
 *
 */
public class Dfc{
	String la;
	String le;
	String lb;
	int count;
	
	
	public Dfc(String la, String le, String lb)
	{
		this.la = la;
		this.le = le;
		this.lb = lb;
		this.count = 1;
		return;
	}
	
	
	public Dfc()
	{
		this.la = "";
		this.le = "";
		this.lb = "";
		this.count = 0;
	}
	
	public String getLa()
	{
		return this.la;
	}
	
	public String getLe()
	{
		return this.le;
	}
	
	public String getLb()
	{
		return this.lb;
	}
	
	public void setLa(String la)
	{
		this.la = la;
	}
	
	public void setLb(String lb)
	{
		this.lb = lb;
	}
	
	public void setLe(String le)
	{
		this.le = le;
	}
	
	
	public void printDfc()
	{
		System.out.println(this.la + " " + this.le + " " + this.lb);
	}
	
	public static Comparator<Dfc> DfcLaComparator
					= new Comparator<Dfc>()
    {
		public int compare(Dfc code1, Dfc code2)
		{
		    String la1 = code1.getLa().toUpperCase();
		    String la2 = code2.getLa().toUpperCase();
		    String le1 = code1.getLe().toUpperCase();
		    String le2 = code2.getLe().toUpperCase();
		    String lb1 = code1.getLb().toUpperCase();
		    String lb2 = code2.getLb().toUpperCase();
		    if(la1.compareTo(la2) != 0)
		    {
		        //ascending order
		        return la1.compareTo(la2);
		        //descending order
		        // return la2.compareTo(la1);
		    }
		    else if(le1.compareTo(le2) != 0)
		    {
		    	return le1.compareTo(le2);
		    }
		    else if(lb1.compareTo(lb2) != 0)
		    {
		    	return lb1.compareTo(lb2);
		    } 
		    return 0;
		}
    };

}
