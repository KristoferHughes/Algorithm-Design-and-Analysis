/* this is minheap used as priority queue of vertices,
 * stores triples (see README.txt) represented as DVertex
 */

// Vertex = (current vertex, previous vertex, total distance)
class DVertex {
	String v;
	String prev;
	int dist;		// -1 means infinity
	static final int INF = -1;
	
	DVertex(String v, String p, int d)
	{
		this.v = v;
		prev = p;
		dist = d;
	}
	
	public String toString()
	{
		return "(" + v + "," + prev + "," + (dist == INF ? "<><>" : dist) + ")";
	}
	
	// less or equal:
	// less (anything, INF) = true
	// less (2, 3) = true
	public boolean closer(DVertex DV)
	{
		return DV.dist==INF || (dist!=INF && dist<DV.dist);
	}
	
	// same but for DV.dist and not DV (used externally)
	public boolean closer(int dist)
	{
		return dist == INF || (this.dist != INF && this.dist < dist);
	}
}

public class DHeap {
	private int max_size;
	private DVertex heap[];
	private int size;
	
	DHeap(int max_size)
	{
		this.max_size = max_size;
		heap = new DVertex[max_size];
		size = 0;
	}
	
	/* links in heap */
	private int parent(int node) { return (node - 1) / 2; }
	private int left(int node) { return 2*node + 1; }
	private int right(int node) { return 2*node + 2; }
	
	/* keeping it consistent */
	private void swap(int i, int j)
	{
		DVertex t = heap[i];
		heap[i] = heap[j];
		heap[j] = t;
	}
	
	private void heapify(int node)
	{
		if (node >= size/2)	// cannot be leaf node
			return;
		
		/* check if it's Okay */
		if (heap[node].closer(heap[left(node)]) && //definitely has left,
			(right(node)>=size ||                       // but not sure for right
			 heap[node].closer(heap[right(node)]))) {
           
			return;
		}
		
		/* determine which one is the smaller to put it here */
		if (right(node)>=size ||                // can be without right
			heap[left(node)].closer(heap[right(node)])) {

			swap(node, left(node));
			heapify(left(node));
		} else {
			swap(node, right(node));
			heapify(right(node));
		}
		
	}
	
	/* external operations */
	// check if heap is empty
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	// null if empty, removed root otherwise
	public DVertex extractMin()
	{
		if (size == 0)
			return null;
		
		DVertex ret = heap[0];
		heap[0] = heap[--size];
		heapify(0);
		
		return ret;
	}
	
	// -1 if filled, 0 otherwise
	public int insert(DVertex val)
	{
		if (size == max_size)
			return -1;
		
		int cur = size++;
		heap[cur] = val;
		while (cur > 0 && heap[cur].closer(heap[parent(cur)])) {
			swap(cur, parent(cur));
			cur = parent(cur);
		}
		
		return 0;
	}
	
	// null if not found, removed node otherwise
	public DVertex extractVal(String v)
	{
		int node;
		DVertex ret;
		
		if (size == 0)		// empty
			return null;
		
		node = getValNode(v, 0); // try to search
		if (node == -1)       // not found
			return null;
		
		// found
		ret = heap[node];
		heap[node] = heap[--size]; // remove this el.
		heapify(node);
		
		return ret;
	}
	
	private int getValNode(String v, int node)
	{
		/* this is O(N) search, but it is OK */
		int from_left, from_right;
				
		if (heap[node].v.equals(v)) // here -- success
			return node;
		
		// -- not here ----
		if (node >= size/2) // leaf -- fail
			return -1;
		
		from_left = getValNode(v, left(node));
		if (from_left != -1)    // found in left -- success
			return from_left;
		
		if (right(node) >= size)  // no right part -- fail
			return -1;
		
		from_right = getValNode(v, right(node));
		if (from_right != -1)   // found in right -- success
			return from_right;
		
		// not found anywhere -- fail
		return -1;
	}
	
	/* debugging */
	public String toString()
	{
		String res = "";
		
		for (int i = 0; i < size; i++) {
			res += heap[i];
			if (left(i) < size)
				res += ": left " + heap[left(i)];
			if (right(i) < size)
				res += ", right " + heap[right(i)];
			res += "\n";
		}
		
		return res;
	}
}
