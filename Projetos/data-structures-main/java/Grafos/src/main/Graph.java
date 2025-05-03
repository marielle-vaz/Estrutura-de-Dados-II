package main;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;
    private boolean digraph;

    public Graph(boolean digraph) {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.digraph = digraph;
    }

    public void addVertex(Vertex v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
        }
    }

    public void addEdge(Vertex origin, Vertex destination, int weight, String label) {
        if (!vertices.contains(origin) || !vertices.contains(destination)) {
            throw new IllegalArgumentException("Both vertices must be in the graph");
        }

        edges.add(new Edge(origin, destination, weight, label));

        if (!digraph && !origin.equals(destination)) {
            // Para grafos não-direcionados, adiciona aresta reversa (exceto laços)
            edges.add(new Edge(destination, origin, weight, label));
        }
    }

    public int countLoops() {
        int count = 0;
        for (Edge e : edges) {
            if (e.getOrigin().equals(e.getDestination())) {
                count++;
            }
        }
        return count;
    }
    

    public boolean isComplete() {
        int n = vertices.size();
    
        if (n <= 1) return false;
    
        for (Vertex v1 : vertices) {
            for (Vertex v2 : vertices) {
                if (v1.equals(v2)) continue; // ignora laços
    
                boolean hasEdge = edges.stream()
                    .anyMatch(e -> e.getOrigin().equals(v1) && e.getDestination().equals(v2));
    
                if (!hasEdge) return false;
    
                if (!digraph) {
                    // para grafos não direcionados, garante que (v2,v1) também não exista em duplicidade
                    boolean hasReverseEdge = edges.stream()
                        .anyMatch(e -> e.getOrigin().equals(v2) && e.getDestination().equals(v1));
                    if (!hasReverseEdge) return false;
                }
            }
        }
        return true;
    }
    

    public int degree(Vertex v) {
        int degree = 0;
        for (Edge e : edges) {
            if (digraph) {
                if (e.getOrigin().equals(v) || e.getDestination().equals(v)) {
                    degree++;
                }
            } else {
                if (e.getOrigin().equals(v)) {
                    degree++;
                }
            }
        }
        return degree;
    }

    public List<Vertex> findPath(Vertex from, Vertex to) {
        List<Vertex> path = new ArrayList<>();
        boolean found = dfs(from, to, path, new ArrayList<>());
        return found ? path : null;
    }

    private boolean dfs(Vertex current, Vertex target, List<Vertex> path, List<Vertex> visited) {
        visited.add(current);
        path.add(current);

        if (current.equals(target)) return true;

        for (Edge e : edges) {
            if (e.getOrigin().equals(current) && !visited.contains(e.getDestination())) {
                if (dfs(e.getDestination(), target, path, visited)) {
                    return true;
                }
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    public String toDOT() {
        StringBuilder sb = new StringBuilder();
        String connector = digraph ? " -> " : " -- ";
        sb.append(digraph ? "digraph G {\n" : "graph G {\n");

        for (Vertex v : vertices) {
            sb.append("  ").append(v.getId()).append(" [label=\"").append(v.getLabel()).append("\"];\n");
        }

        for (Edge e : edges) {
            // Para grafos não-dirigidos, desenha apenas uma vez a aresta (evita duplicidade)
            if (!digraph && vertices.indexOf(e.getOrigin()) > vertices.indexOf(e.getDestination())) continue;

            sb.append("  ")
              .append(e.getOrigin().getId())
              .append(connector)
              .append(e.getDestination().getId())
              .append(" [label=\"").append(e.getLabel()).append("(").append(e.getWeight()).append(")\"];\n");
        }

        sb.append("}");
        return sb.toString();
    }

    // Getters para testes
    public List<Vertex> getVertices() { return vertices; }
    public List<Edge> getEdges() { return edges; }
    public boolean isDigraph() { return digraph; }
}
