// works by iteratively pushing flow along augmenting paths and relabeling vertices to maintain a valid flow 
// key concepts
// flow network- directed graph with capacities assigned to each edge
// flow - amount of flow sent along an edge which must not exceed the edge capacity 
// residual graph- a graph representing the remaining capacity on each edge
// excess flow- the amount of flow entering a vertex that exceeds the amount leaving 
// active vertex- a vertex with excess flow 

import java.util.*;
public class PushRelabel
  {
    private final int[][] capacity;      // 2d array to store edge capacities 
    private final int[][] flow;          // 2d array to store current flow on each edge
    private final int[] height;          // array to store height label of each vertex 
    private final Queue<Integer> queue;    // queue to store active vertices 

    public PushRelabel(int[][] capacity) {
      this.capacity = capacity;
      int n = capacity.length;
      flow = new int[n][n];
      height = new int[n];
      queue = new LinkedList<>();
    }
    public void push(int u, int v)
    {
      int delta = Math.min(excess(u), capacity[u][v] - flow[u][v]);
      flow[u][v] += delta;
      flow[u][v] -= delta;
      if(excess(v) > 0 && height[v] == height[u]) {
        queue.offer(v);
      }
    }
    public void relabel(int u) 
    {
      int minHeight = Integer.MAX_VALUE;
      for(int v = 0; v < capacity.length; v++) {
        if(capacity[u][v] - flow[u][v] > 0) {
          minHeight = Math.min(minHeight, height[v]);
        }
      }
      height[u] = minHeight + 1;
      queue.offer(u);
    }
    public int excess(int u)
    {
      int excess = 0;
      for(int v = 0; v < capacity.length; v++) {
        excess += flow[v][u] - flow[u][v];
      }
      return excess;
    }
    public void maxFlow(int source, int sink)
    {
      height[source] = capacity.length;
      for(int v = 0; v < capacity.length; v++) {
        if (v!= source) {
          queue.offer(v);
        }
      }
      while(!queue.isEmpty()) {
        int u = queue.poll();
        if(height[u] < capacity.length) {
          boolean discharged = false;
          for(int v = 0; v < capacity.length && !discharged; v++) {
            if(capacity[u][v] - flow[u][v] > 0 && height[u] == height[v] + 1) {
              push(u, v);
              discharged = true;
            }
          }
          if(!discharged) {
            relabel(u);
          }
        }
      }
    }
  }
