package br.com.gomide.graph.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.gomide.graph.model.DirectedGraph;
import br.com.gomide.graph.model.Edge;
import br.com.gomide.graph.model.Graph;
import br.com.gomide.graph.model.NonDirectedGraph;
import br.com.gomide.graph.model.Vertex;

public class GraphService implements IGraphService {

    @Override
    public void addNodes(List<String> nodes, Graph graph) {
        for (String label : nodes) {
            addNode(label, graph);
        }
    }

    @Override
    public void addNode(String node, Graph graph) {
        if (graph.getVertexByLabel(node) == null) {
            graph.getVertices().add(new Vertex(graph.getNextVertexId(), node));
        }
    }

    @Override
    public void connectNode(String firstNode, String secondNode, Graph graph) {
        connectNode(firstNode, secondNode, 1, graph);
    }

    @Override
    public void connectNode(String firstNode, String secondNode, Integer weight, Graph graph) {
        Vertex v1 = graph.getVertexByLabel(firstNode);
        Vertex v2 = graph.getVertexByLabel(secondNode);

        if (v1 != null && v2 != null) {
            graph.addEdge(new Edge(v1, v2, weight, ""));
        }
    }

    @Override
    public int countLoops(Graph graph) {
        return (int) graph.getEdges().stream()
                .filter(e -> e.getOrigin().equals(e.getDestination()))
                .count();
    }

    @Override
    public int countMultipleLink(Graph graph) {
        Set<String> seen = new HashSet<>();
        int count = 0;

        for (Edge e : graph.getEdges()) {
            String key = e.getOrigin().getLabel() + ":" + e.getDestination().getLabel();
            if (seen.contains(key)) {
                count++;
            } else {
                seen.add(key);
            }
        }

        return count;
    }

    @Override
    public int nodeDegree(String node, Graph graph) {
        Vertex v = graph.getVertexByLabel(node);
        if (v == null) return -1;

        if (graph.isDigraph()) {
            long in = graph.getEdges().stream().filter(e -> e.getDestination().equals(v)).count();
            long out = graph.getEdges().stream().filter(e -> e.getOrigin().equals(v)).count();
            return (int)(in + out);
        } else {
            return (int) graph.getEdges().stream().filter(e -> e.getOrigin().equals(v) || e.getDestination().equals(v)).count();
        }
    }

    @Override
    public boolean isConnected(Graph graph) {
        if (graph.getVertices().isEmpty()) return true;

        Set<Vertex> visited = new HashSet<>();
        dfs(graph.getVertices().get(0), graph, visited);

        return visited.size() == graph.getVertices().size();
    }

    private void dfs(Vertex current, Graph graph, Set<Vertex> visited) {
        visited.add(current);

        for (Edge e : graph.getEdges()) {
            Vertex neighbor = null;
            if (e.getOrigin().equals(current)) {
                neighbor = e.getDestination();
            } else if (!graph.isDigraph() && e.getDestination().equals(current)) {
                neighbor = e.getOrigin();
            }

            if (neighbor != null && !visited.contains(neighbor)) {
                dfs(neighbor, graph, visited);
            }
        }
    }

    @Override
    public boolean isComplete(Graph graph) {
        List<Vertex> vertices = graph.getVertices();

        for (Vertex v1 : vertices) {
            for (Vertex v2 : vertices) {
                if (v1.equals(v2)) continue;

                boolean hasEdge = graph.getEdges().stream()
                        .anyMatch(e -> e.getOrigin().equals(v1) && e.getDestination().equals(v2));

                if (!hasEdge) return false;

                if (!graph.isDigraph()) {
                    boolean reverse = graph.getEdges().stream()
                            .anyMatch(e -> e.getOrigin().equals(v2) && e.getDestination().equals(v1));
                    if (!reverse) return false;
                }
            }
        }

        return true;
    }

    @Override
    public String showPath(String origin, String destination, DirectedGraph graph) {
        return findPath(origin, destination, graph);
    }

    @Override
    public String showPath(String origin, String destination, NonDirectedGraph graph) {
        return findPath(origin, destination, graph);
    }

    private String findPath(String originLabel, String destinationLabel, Graph graph) {
        Vertex origin = graph.getVertexByLabel(originLabel);
        Vertex destination = graph.getVertexByLabel(destinationLabel);
        if (origin == null || destination == null) return "";

        List<Vertex> path = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        if (dfsPath(origin, destination, graph, visited, path)) {
            return path.stream().map(Vertex::getLabel).collect(Collectors.joining(" -> "));
        }
        return "";
    }

    private boolean dfsPath(Vertex current, Vertex target, Graph graph, Set<Vertex> visited, List<Vertex> path) {
        visited.add(current);
        path.add(current);

        if (current.equals(target)) return true;

        for (Edge e : graph.getEdges()) {
            Vertex next = null;
            if (e.getOrigin().equals(current)) {
                next = e.getDestination();
            } else if (!graph.isDigraph() && e.getDestination().equals(current)) {
                next = e.getOrigin();
            }
            if (next != null && !visited.contains(next)) {
                if (dfsPath(next, target, graph, visited, path)) return true;
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    @Override
    public String showShortestPath(String origin, String destination, DirectedGraph graph) {
        return showPath(origin, destination, graph);
    }

    @Override
    public String showShortestPath(String origin, String destination, NonDirectedGraph graph) {
        return showPath(origin, destination, graph);
    }

    @Override
    public String toString(Graph graph) {
        StringBuilder sb = new StringBuilder();
        boolean isDigraph = graph.isDigraph();

        sb.append(isDigraph ? "digraph G {\n" : "graph G {\n");

        // Mapeia vértices por ID
        for (Vertex v : graph.getVertices()) {
            sb.append("  ").append(v.getId())
            .append(" [label=\"").append(v.getLabel()).append("\"];\n");
        }

        // Para evitar duplicatas em grafos não direcionados
        Set<String> rendered = new HashSet<>();

        for (Edge e : graph.getEdges()) {
            long from = e.getOrigin().getId();
            long to = e.getDestination().getId();

            String edgeOp = isDigraph ? "->" : "--";
            String edgeKey = isDigraph ? from + "->" + to : Math.min(from, to) + "--" + Math.max(from, to);

            if (!rendered.contains(edgeKey)) {
                sb.append("  ")
                .append(from).append(" ").append(edgeOp).append(" ").append(to)
                .append(" [label=\"").append(e.getLabel()).append("(").append(e.getWeight()).append(")").append("\"];\n");

                rendered.add(edgeKey);
            }
        }

        sb.append("}");

        return sb.toString();
    }
}
