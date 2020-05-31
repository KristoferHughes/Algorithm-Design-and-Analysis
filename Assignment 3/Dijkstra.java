
import java.util.*;

/*
 * this class implements Dijkstra algorithm itself.
 * Works with directed and undirected graphs, unconnected graphs, paths to same 
 * vertex -- covers all possible cases, except when directed and undirected paths are mixed
 */
public class Dijkstra {
	/*
	 * MAIN CORE -- implementation of algorithm
	 * 0) check that vertices exist in graph and input is correct
	 * 1) put all vertices into unvisited, except starting one, which is visited
	 * 
	 * 2) INFINITE LOOP:
	 * - if it is destination vertice -- path FOUND
	 * - for current vertice -- get all neighbours, and if another neighbor is visited,
	 *    check if it needs be updated with shorter value and do it
	 * - after all are covered -- find the minimum (can be one from them or not) vertex
	 *   from heap
	 * - if it is infinitely far -- there is no direct way to it, path DOESNOT EXIST
	 * - otherwise switch to it and return to 2
	 * 
	 * 3) if FOUND -- add length to output and then 
	 *    RETURN the resulting string
	 */
	private static String dijkstra(Graph G, String source, String dest)
	{
		ArrayList<String> V;
		DHeap UnVisited;
		HashMap<String, DVertex> Visited;
		DVertex v_cur;
		StringBuilder path;
		int total_dist;
		
		if (G == null || source == null || dest == null)
			return "ERROR: NULL values";
			
		V = G.getVertices();
		UnVisited = new DHeap(G.size());
		Visited = new HashMap<>();
		
		if (!V.contains(source) || !V.contains(dest))
			return "ERROR: Does not have both vertices";
				
		for (String v : V)
			if (!v.equals(source))
				UnVisited.insert(new DVertex(v, null, DVertex.INF));
		
		// visit source first
		v_cur = new DVertex(source, null, 0);
		Visited.put(source, v_cur);
		
		// will break when either it has been found or no result
		while (true) {
			// check if just visited the end
			if (v_cur.v.equals(dest))
				break;
			
			/* updates paths that are neighboring */
			// get neighbors
			ArrayList<Edge> nearby = G.getNeighbors(v_cur.v);
			
			// simplify their paths if unvisited and closer
			for (Edge e : nearby) {
				DVertex v_near = UnVisited.extractVal(e.v);
				if (v_near == null)   // visited
					continue;
				                      // need shorten path
				if (!v_near.closer(v_cur.dist + e.weight)) {
					v_near.dist = v_cur.dist + e.weight;
					v_near.prev = v_cur.v;
				}
				                     // return on correct place
				UnVisited.insert(v_near);
			}
			
			/* find next min */
			DVertex v_min = UnVisited.extractMin();
			
			if (v_min.dist == DVertex.INF) { // no direct path
				return "No direct path";
			}
			
			// visit min vertex
			Visited.put(v_min.v, v_min);
			v_cur = v_min;
		}
		
		/* backtrack */
		total_dist = v_cur.dist;
		for (path = new StringBuilder(""); v_cur != null; v_cur = Visited.get(v_cur.prev))
			path.append(" " + v_cur.v);
		
		return total_dist + "\n" + path.reverse().toString();
	}
	
	public static void main(String[] args)
	{
		if (args.length != 4) {
			System.err.println("Usage: Dijkstra <graph filename> <directed/undirected> "
					+ "<source> <destination>");
			System.exit(1);
		}
		
		boolean directed = false;
		
		// check whether graph is directed or undirected
		switch (args[1]) {
		case "d": case "D":
			directed = true;
			break;
		case "u": case "U":
			directed = false;
			break;
		default:
			System.err.println("2nd parameter must be d/D or u/U");
			System.exit(1);
		}
		
		Graph G = new Graph(args[0], directed); // file for G, directed or not
		// G, from, to
		System.out.println(dijkstra(G, args[2], args[3]));
	}
}
