/*
 * graph implementation outside of the dijikstra
 */

import java.io.*;
import java.util.*;

class Edge {
	public String v;
	public int weight;
	
	Edge(String v, int w) { this.v = v;	this.weight = w;	}
	
	public String toString() { return "(" + v + "," + weight + ")"; }
}

public class Graph {
	private HashMap<String, ArrayList<Edge>> G;
	
	
	Graph(String fname, boolean directed)
	{
		Scanner f = null;
		int n;
		

		try {
			f = new Scanner(new File(fname));
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot open file");
			System.exit(1);
		}
		
		
		G = new HashMap<>();
		n = f.nextInt();
		for (String v = "A"; n != 0; n--) {
			G.put(v, new ArrayList<Edge>());
			
			v = String.valueOf((char)(v.charAt(0)+1));
		}
		
		
		while (f.hasNext()) {
			String from = f.next();
			String to = f.next();
			int weight = f.nextInt();
			
			addEdge(from, to, weight);
			if (!directed)
				addEdge(to, from, weight);
		}
		
		
		f.close();
	}
	
	
	private void addEdge(String from, String to, int w)
	{
		G.get(from).add(new Edge(to, w));
	}
	
	
	public boolean isEmpty()
	{
		return G.size() == 0;
	}
	
	
	public ArrayList<String> getVertices()
	{
		return new ArrayList<String>(G.keySet());
	}
	
	
	public ArrayList<Edge> getNeighbors(String v)
	{
		return G.get(v);
	}
	
	
	public int size()
	{
		return G.size();
	}
	
	
	public String toString()
	{
		String res = "";
		
		for (String v : G.keySet()) {
			res += (v + ": ");
			for (Edge e : G.get(v))
				res += (e + " ");
			res += "\n";
		}
		
		return res;
	}
}
