import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.Graph;
import com.example.Vertex;

public class GraphTest {

    @Test
    public void testAdicionarVerticesEArestas() {
        Graph g = new Graph(false);
        Vertex a = new Vertex(1, "A");
        Vertex b = new Vertex(2, "B");

        g.addVertex(a);
        g.addVertex(b);
        g.addEdge(a, b, 1, "A-B");

        assertEquals(2, g.getVertices().size());
        assertEquals(2, g.getEdges().size()); // Grafo não direcionado → A-B e B-A
    }

    @Test
    public void testContarLacos() {
        Graph g = new Graph(false);
        Vertex a = new Vertex(1, "A");

        g.addVertex(a);
        g.addEdge(a, a, 1, "loop");

        assertEquals(1, g.countLoops());
    }

    @Test
    public void testGrafoCompleto() {
        Graph g = new Graph(false);
        Vertex a = new Vertex(1, "A");
        Vertex b = new Vertex(2, "B");
        Vertex c = new Vertex(3, "C");

        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);

        g.addEdge(a, b, 1, "ab");
        g.addEdge(a, c, 1, "ac");
        g.addEdge(b, c, 1, "bc");

        assertTrue(g.isComplete());
    }

    @Test
    public void testGrauVertice() {
        Graph g = new Graph(false);
        Vertex a = new Vertex(1, "A");
        Vertex b = new Vertex(2, "B");

        g.addVertex(a);
        g.addVertex(b);
        g.addEdge(a, b, 1, "ab");

        assertEquals(1, g.degree(a));
        assertEquals(1, g.degree(b));
    }

    @Test
    public void testCaminhoExistente() {
        Graph g = new Graph(false);
        Vertex a = new Vertex(1, "A");
        Vertex b = new Vertex(2, "B");
        Vertex c = new Vertex(3, "C");

        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);

        g.addEdge(a, b, 1, "ab");
        g.addEdge(b, c, 1, "bc");

        List<Vertex> caminho = g.findPath(a, c);
        assertNotNull(caminho);
        assertEquals(List.of(a, b, c), caminho);
    }

    @Test
    public void testCaminhoInexistente() {
        Graph g = new Graph(false);
        Vertex a = new Vertex(1, "A");
        Vertex b = new Vertex(2, "B");
        Vertex c = new Vertex(3, "C");

        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);

        g.addEdge(a, b, 1, "ab");

        List<Vertex> caminho = g.findPath(a, c);
        assertNull(caminho);
    }
}
