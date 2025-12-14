import static org.junit.Assert.*;

import org.junit.Test;

import java.net.URL;
import java.beans.Transient;
import java.io.FileNotFoundException;

import java.util.LinkedList;

public class ShortestPathsTest {


    /* Returns the Graph loaded from the file with filename fn. */
    private Graph loadBasicGraph(String fn) {
        Graph result = null;
        try {
          result = ShortestPaths.parseGraph("basic", fn);
        } catch (FileNotFoundException e) {
          fail("Could not find graph " + fn);
        }
        return result;
    }

    /** Dummy test case demonstrating syntax to create a graph from scratch.
     * TODO Write your own tests below. */
    @Test
    public void test00Nothing() {
        Graph g = new Graph();
        Node a = g.getNode("A");
        Node b = g.getNode("B");
        g.addEdge(a, b, 1);

        // sample assertion statements:
        assertTrue(true);
        assertEquals(2+2, 4);
    }

    /** Minimal test case to check the path from A to B in Simple0.txt */
    @Test
    public void test01Simple0() {
        Graph g = loadBasicGraph("data/Simple0.txt");
        g.report();
        ShortestPaths sp = new ShortestPaths();
        Node a = g.getNode("A");
        sp.compute(a);
        Node b = g.getNode("B");
        LinkedList<Node> abPath = sp.shortestPath(b);
        assertEquals(abPath.size(), 2);
        assertEquals(abPath.getFirst(), a);
        assertEquals(abPath.getLast(),  b);
        assertEquals(sp.shortestPathLength(b), 1.0, 1e-6);
    }

    @Test 
    public void test02Simple1() {
      Graph g = loadBasicGraph("data/Simple1.txt");
      g.report();
      ShortestPaths sp = new ShortestPaths();
      Node a = g.getNode("A");
      sp.compute(a);
      Node b = g.getNode("B");
      LinkedList<Node> abPath = sp.shortestPath(b);
      assertEquals(abPath.size(), 2);
      assertEquals(abPath.getFirst(), a);
      assertEquals(abPath.getLast(), b);
      assertEquals(sp.shortestPathLength(b), 1.0, 1e-6);
    }

    @Test 
    public void noPath(){
      Graph g = loadBasicGraph("data/Simple2.txt");
      g.report();
      ShortestPaths sp = new ShortestPaths();
      Node a = g.getNode("A");
      sp.compute(a);
      Node i = g.getNode("I");
      LinkedList<Node> aiPath = sp.shortestPath(i);
      assertEquals(aiPath.isEmpty());
    }

    @Test 
    public void oneNode(){
      Graph g = loadBasicGraph("data/Simple2.txt");
      g.report();
      ShortestPaths sp = new ShortestPaths();
      Node a = g.getNode("A");
      sp.compute(a);
      LinkedList<Node> apath = sp.shortestPath(a);
      assertEquals(apath.size(), 1);
      asserEquals(apath.getFirst(), a);
      assertEquals(sp.shortestPathLength(a), 0.0, 1e-6);
    }

    @Test 
    public void dToE(){
      Graph g = loadBasicGraph("data/Simple2.txt");
      g.report();
      ShortestPaths sp = new ShortestPaths();
      Node d = g.getNode("D");
      sp.compute(d);
      Node e = g.getNode("E");
      LinkedList<Node> dePath = sp.shortestPath(e);
      assertEquals(3,dePath.size());
      assertEquals(sp.shortestPathLength(e), 5.0, 1e-6);
    }

    @Test
    public void dToGBigPath(){
      Graph gr = loadBasicGraph("data/simple2.txt");
      gr.report();
      ShortestPaths sp = new ShortestPaths();
      Node d = gr.getNode("D");
      sp.compute(d);
      Node g = gr.getNode("G");
      sp.compute(g);
      LinkedList<Node> dgPath = sp.shortestPath(g);
      assertEquals(dgPath.getFirst(), d);
      assertEquals(dgPath.getLast(), g);
      assertEquals(sp.shortestPathLength(g), 12.0, 1e-6);
    }
    /* Pro tip: unless you include @Test on the line above your method header,
     * JUnit will not run it! This gets me every time. */
}
