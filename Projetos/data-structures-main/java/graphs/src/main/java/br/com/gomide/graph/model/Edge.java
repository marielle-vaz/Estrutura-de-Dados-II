package br.com.gomide.graph.model;

public class Edge {
    private final Vertex origin;
    private final Vertex destination;
    private final int weight;
    private final String label;

    public Edge(Vertex origin, Vertex destination, int weight, String label) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
        this.label = label;
    }

    public Vertex getOrigin() {
        return origin;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public String getLabel() {
        return label;
    }
}
