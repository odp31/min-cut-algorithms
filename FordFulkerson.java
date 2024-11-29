// maximum flow-minimum cut: states max amount of flow that can be setn from a source
// node to a sink node in a flow network is equal to the capacity of the 
// minimum cut in the network
// cut = a partition of the vertices into two sets, one containing the source
// and the other containing the sink 
// capacity of a cut= sum of capacieties of the edges crossing the cut 


import java.util.*;

public class FordFulkerson
  {
    public static int fordFulkerson(int[][] graph, int source, int sink)
    {
      int n = graph.length;
      int[][] residualGraph = new int[n][n];

      for(int i = 0; i < n; i ++)
        {
          for(int j = 0; j < n; j++)
            {
              residualGraph[i][j] = graph[i][j];
            }
        }
      int maxFlow = 0;
      while(bfs(residualGraph, source, sink, new boolean[n]))
        {
          int pathFlow = Integer.MAX_VALUE;
          for(int v = sink; v != source; v = parent[v])
            {
              int u = parent[v];
              pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }
          for(int v = sink; v != source; v = parent[v])
            {
              int u = parent[v];
              residualGraph[u][v] -= pathFlow;
              residualGraph[v][u] += pathFlow;
            }
          maxFlow += pathFlow;
        }
      return maxFlow;
    }
    private static boolean bfs(int[][] residualGraph, int source, int sink, boolean[] visited)
    {
      Arrays.fill(visited, false);
      Queue<Integer> queue = new LinkedList<>();
      queue.add(source);
      visited[source] = true;
      int[] parent = new int[residualGraph.length];

      while(!queue.isEmpty())
        {
          int u = queue.poll();
          for(int v = 0; v < residualGraph.length; v++)
            {
              if(!visited[v] && residualGraph[u][v] > 0)
              {
                queue.add(v);
                visited[v] = true;
                parent[v] = u;
              }
            }
        }
      return visited[sink];
    }
    public static void main(String[] args)
    {
      int[][] graph = {
        {0, 16, 13, 0, 0, 0},
        {0, 0, 10, 12, 0, 0},
        {0, 0, 0, 11, 0, 0},
        {0, 0, 0, 0, 14, 6},
        {0, 0, 0, 0, 0, 7},
        {0, 0, 0, 0, 0, 0}
      };
      int source = 0;
      int sink = 5;
      int maxFlow = fordFulkerson(graph, source, sink);
      System.out.println("maximum possible flow is " + maxFlow);
    }
  }
