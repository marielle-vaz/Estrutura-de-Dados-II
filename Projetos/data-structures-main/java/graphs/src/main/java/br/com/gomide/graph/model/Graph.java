package br.com.gomide.graph.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph {
    protected final List<Vertex> vertices;
    protected final List<Edge> edges;
    protected final boolean digraph;
    private long nextId = 1;

    public Graph(boolean digraph) {
        this.digraph = digraph;
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public boolean isDigraph() {
        return digraph;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        if (!digraph) {
            edges.add(new Edge(edge.getDestination(), edge.getOrigin(), edge.getWeight(), edge.getLabel()));
        }
    }

    public Vertex getVertexByLabel(String label) {
        return vertices.stream()
                .filter(v -> v.getLabel().equals(label))
                .findFirst()
                .orElse(null);
    }

    public long getNextVertexId() {
        return nextId++;
    }

    public String toDot() {
        StringBuilder sb = new StringBuilder();
        sb.append(digraph ? "digraph G {\n" : "graph G {\n");

        for (Vertex v : vertices) {
            sb.append("  ").append(v.getId())
              .append(" [label=\"").append(v.getLabel()).append("\"];").append("\n");
        }

        String connector = digraph ? " -> " : " -- ";
        List<String> rendered = new ArrayList<>();

        for (Edge e : edges) {
            String from = String.valueOf(e.getOrigin().getId());
            String to = String.valueOf(e.getDestination().getId());
            String edgeKey = digraph ? (from + "->" + to) : (Math.min(Long.parseLong(from), Long.parseLong(to)) + "--" + Math.max(Long.parseLong(from), Long.parseLong(to)));
            if (!rendered.contains(edgeKey)) {
                sb.append("  ").append(from)
                  .append(connector).append(to)
                  .append(" [label=\"").append(e.getLabel())
                  .append("(").append(e.getWeight()).append(")\"];").append("\n");
                rendered.add(edgeKey);
            }
        }

        sb.append("}");
        return sb.toString();
    }
}
