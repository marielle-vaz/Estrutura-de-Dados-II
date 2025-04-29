package main;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(false);
        Vertex a = new Vertex(1, "A");
        Vertex b = new Vertex(2, "B");
        Vertex c = new Vertex(3, "C");

        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);

        g.addEdge(a, b, 1, "ab");
        g.addEdge(b, c, 1, "bc");
        g.addEdge(a, a, 1, "loop");

        System.out.println("Laços: " + g.countLoops());
        System.out.println("Grafo completo? " + g.isComplete());
        System.out.println("Grau de B: " + g.degree(b));
        System.out.println("Caminho de A para C: " + g.findPath(a, c));
        System.out.println("Formato DOT:\n" + g.toDOT());

    }
}

