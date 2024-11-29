// randomized algorithm for finding a minimum cut in an undirected graph 
// works by repeatedly contracting edges until only 2 vertices remain 
// the number of edges between these 2 vertices is a candidate for minimum cut

import java.util.*;
public class KargerMinCut
  {
    public static int minCut(ArrayList<ArrayList<Integer>> graph)
    {
      int n = graph.size();
      while(n > 2) {
        // randomly select an edge
        int v = (int) (Math.random() * n);
        int w = graph.get(v).get((int) (Math.random() * graph.get(v).size()));
        // merge vertices v and w
        mergeVertices(graph, v, w);
        n--;
      }
      // count number of edges between remaining 2 vertices
      return graph.get(0).size();
    }

    private static void mergeVertices(ArrayList<ArrayList<Integer>> graph, int v, int w)
    {
      for(int i : graph.get(w)) {
        if(i != v) {
          graph.get(v).add(i);
          graph.get(i).add(v);
        }
      }
      graph.remove(w);
      for (ArrayList<Integer> list : graph) {
        for(int i = 0; i < list.size(); i++) {
          if(list.get(i) == w) {
            list.set(i,v);
          }
        }
      }
    }
    public static void main(String[] args)
    {
      // example graph (adjacency list representation)
      ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
      graph.addEdge(0, 1);
      graph.addEdge(0, 2);
      graph.addEdge(1, 2);
      graph.addEdge(1, 3);
      graph.addEdge(1, 4);
      graph.addEdge(2, 3);
      graph.addEdge(3, 4);
      int minCut = Integer.MAX_VALUE;

      for(int i = 0; i < 10; i++) // run algorithm multiple times 
        {
          minCut = Math.min(minCut, minCut(new ArrayList<>(graph)));
        }
        System.out.println("minimum cut: " + minCut);
    }
  }}
          
