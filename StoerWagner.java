import java.util.*;

public class StoerWagner
  {
    public static int minCut(int[][] graph)
    {
      int n = graph.length;
      int[] potential = new int[n];
      boolean[] visited = new boolean[n];

      int minCut = Integer.MAX_VALUE;
      for(int phase = 0; phase < n - 1; phase++)
        {
          Arrays.fill(potential, 0);
          Arrays.fill(visited, false);

          int prev = -1; 
          for(int i = 0; i < n; i++)
            {
              int maxWeight = -1;
              int maxIndex = -1;
              for(int j = 0; j < n; j++)
                {
                  if(!visited[j] && potential[j] > maxWeight) {
                    maxWeight = potential[j];
                    maxIndex = j;
                  }
                }
                visited[maxIndex] = true;
                if(prev != -1) {
                  minCut = Math.min(minCut, graph[prev][maxIndex]);
                }
                prev = maxIndex;
                for(int j = 0; j < n; j++)
                  {
                    if(!visited[j])
                    {
                      potential[j] += graph[maxIndex][j];
                    }
                  }
            }
        }
        return minCut;

    }
    public static void main(String[] args)
    {
      // example graph (adjacency matrix representation)
      int[][] graph = {
        {0, 16, 13, 0, 0, 0},
        {16, 0, 10, 12, 0, 0},
        {13, 10, 0, 11, 0, 0},
        {0, 12, 11, 0, 14, 6},
        {0, 0, 0, 14, 0, 7},
        {0, 0, 0, 6, 7, 0}
      };
      int minCut = minCut(graph);
      System.out.println("minimum cut: " + minCut);
    }
  }
