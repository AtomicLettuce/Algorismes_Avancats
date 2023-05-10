package P4.Controlador;
import java.util.*;

public class Controlador {


    public class DijkstraShortestPath {
        private final int INF = Integer.MAX_VALUE; // infinity distance
        private int n; // number of vertices
        private int[] dist; // shortest distance from source to each vertex
        private int[] prev; // previous vertex on the shortest path
        private Map<Integer, List<Edge>> graph; // adjacency list representation of graph

        public DijkstraShortestPath(int n) {
            this.n = n;
            dist = new int[n];
            prev = new int[n];
            Arrays.fill(dist, INF);
            Arrays.fill(prev, -1);
            graph = new HashMap<>();
            for (int i = 0; i < n; i++) {
                graph.put(i, new ArrayList<>());
            }
        }

        public void addEdge(int u, int v, int w) {
            graph.get(u).add(new Edge(v, w));
        }

        public int[] shortestPath(int source, int target) {
            PriorityQueue<Node> queue = new PriorityQueue<>();
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(i, INF);
            }
            nodes[source].dist = 0;
            queue.offer(nodes[source]);

            while (!queue.isEmpty()) {
                Node u = queue.poll();
                if (u.index == target) {
                    break;
                }
                for (Edge e : graph.get(u.index)) {
                    Node v = nodes[e.to];
                    int newDist = u.dist + e.weight;
                    if (newDist < v.dist) {
                        queue.remove(v); // remove old copy of v from the queue
                        v.dist = newDist;
                        prev[v.index] = u.index;
                        queue.offer(v); // insert new copy of v with updated distance
                    }
                }
            }

            return dist;
        }

        private static class Node implements Comparable<Node> {
            private int index;
            private int dist;

            public Node(int index, int dist) {
                this.index = index;
                this.dist = dist;
            }

            @Override
            public int compareTo(Node other) {
                return Integer.compare(dist, other.dist);
            }
        }

        private static class Edge {
            private int to;
            private int weight;

            public Edge(int to, int weight) {
                this.to = to;
                this.weight = weight;
            }
        }
    }

}
