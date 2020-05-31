Hughes
HW 4 program
(covers undirected and directed graphs)


Project organization
- Graph.java stores graph representation (independently of algorithm), and also edge class used both in it and in Dijkstra
- DHeap.java stores Dijkstra-specific data structure -- min heap storing triples <current vertex, previous vertex, distance from start> based on <distance from start>. It is used to get unvisited vertex with minimum path
- Dijkstra.java stores implementation of algorithm and entry-point

II. Usage

To compile simply do
> javac *java

Then
> Dijkstra <filename> <u/U or d/D> <source vertex> <dest. vertex>
here u/d means undirected or directed graph

Examples:
> Dijkstra CaseA.txt d A B
> Dijkstra CaseB.txt U A A
(vertices can be non-existent -- error will be shown, or same -- path of length 0 will be shown)
