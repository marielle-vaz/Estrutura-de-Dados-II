package main;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(false);
        Vertex a = new Vertex(1, "A");
        Vertex b = new Vertex(2, "B");
        Vertex c = new Vertex(3, "C");
        Vertex d = new Vertex(4, "D");

        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);
        g.addVertex(d);

        g.addEdge(a, b, 1, "ab");
        g.addEdge(b, c, 1, "bc");
        g.addEdge(a, c, 1, "ac");
        g.addEdge(c, d, 1, "cd");
        g.addEdge(a, a, 1, "loop");

        System.out.println("Laços: " + g.countLoops());
        System.out.println("Grafo completo? " + g.isComplete());
        System.out.println("Grau de B: " + g.degree(b));
        System.out.println("Caminho de A para D: " + g.findPath(a, d));
        System.out.println("Formato DOT:\n" + g.toDOT());

    }
}

