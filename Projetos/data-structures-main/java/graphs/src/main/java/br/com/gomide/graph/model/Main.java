package br.com.gomide.graph.model;

import java.util.Arrays;

import br.com.gomide.graph.service.GraphService;
import br.com.gomide.graph.service.IGraphService;

public class Main {

    public static void main(String[] args) {
        IGraphService graphService = new GraphService();

        // Testando com grafo não direcionado
        Graph graph = new NonDirectedGraph();

        graphService.addNodes(Arrays.asList("A", "B", "C", "D"), graph);

        graphService.connectNode("A", "B", graph);
        graphService.connectNode("B", "C", graph);
        graphService.connectNode("C", "D", graph);
        graphService.connectNode("D", "A", graph);
        graphService.connectNode("A", "C", graph); // Aresta diagonal

        System.out.println("Grafo no formato DOT:");
        System.out.println(graphService.toString(graph));

        System.out.println("É completo? " + graphService.isComplete(graph));
        System.out.println("É conexo? " + graphService.isConnected(graph));
        System.out.println("Grau de C: " + graphService.nodeDegree("C", graph));
        System.out.println("Número de laços: " + graphService.countLoops(graph));
        System.out.println("Múltiplas ligações: " + graphService.countMultipleLink(graph));
        System.out.println("Caminho de A até D: " + graphService.showPath("A", "D", (NonDirectedGraph) graph));

        // Você pode testar um grafo dirigido trocando para DirectedGraph
    }
}
