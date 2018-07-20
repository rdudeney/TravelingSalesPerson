package java2.project2;

/* 
 * Graph.java
 *
 * Program Class receives a matrix of distances and cities from the TSPBackend class
 * and confirms the shortest route from the starting city to all the other cities
 * visiting each city only once.
 * 
 * Computer Science E-22 Fall 2016, Harvard University
 * @author David Sullivan
 * @modified by Stephen Dudeney, stephen.dudeney@gmail.com
 * @version: 05/05/2017
 *
 *
 */


import java.util.*;

/**
 * An implementation of a Graph ADT.
 */
public class Graph {

    /* A linked list of the vertices in the graph. */
    private Vertex vertices;
    private Vertex tail;
    private double leastCost;
    private String leastRoute;
    TSPBackend workingCopy;
    double[][] matrix;
    double travCost;
    int routes;
    
    /*
    * graph constructor takes a TSPBackend object as a paramenter
    *
    * @param        TSPBacked 
    */
    
    public Graph(TSPBackend n) {
        
        workingCopy = n;
        matrix = workingCopy.getMatrix();
        leastRoute = "";
        leastCost = 0;
        travCost = 0;
        
    }     

    /*
     * Vertex - a private inner class for representing a vertex.
     */
    private class Vertex {
        private int id;
        private Edge edges;           // adjacency list, sorted by edge cost
        private Vertex next;          // next Vertex in linked list
        private boolean encountered;
        private boolean done;
        private Vertex parent;        // preceding vertex in path from root
        private double cost;          // cost of shortest known path
        
        private Vertex(int id) {
            this.id = id;
            //cost = Double.POSITIVE_INFINITY;
        }
        
        /*
         * reinit - reset the starting values of the fields used by
         * the various algorithms, removing any values set by previous
         * uses of the algorithms.
         */
        private void reinit() {
            done = false;
            encountered = false;
            parent = null;
            cost = Double.POSITIVE_INFINITY;
        }
        
        /*
         * addToAdjacencyList - add the specified edge to the
         * adjacency list for this vertex.
         */
        private void addToAdjacencyList(Edge e) {
            /* Add to the front of the list. */
            e.next = edges;
            edges = e;
        }
        
         /*
         * pathString - returns a string that specifies the path from
         * the root of the current spanning tree (if there is one) to
         * this vertex.  If this method is called after performing
         * Dijkstra's algorithm, the returned string will specify the
         * shortest path.
         */
        private String pathString() {
            String str = "";
            Edge e = edges;
            
            if (parent == null) {
                str += id;/* base case: this vertex is the root */
                travCost = 0;
            }    
            else {
                str = parent.pathString() + id;
                
                while (e.end.id != parent.id) {
                    e = e.next;
                }
                
                travCost += e.cost;
            }
            
            return str;
        }
        
        /*
         * toString - returns a string representation of the vertex 
         * of the following form:
         *    vertex v:
         *            edge to vi (cost = c1i)
         *            edge to vj (cost = c1j)
         *            ...
         */
        public String toString() {
            String str = "vertex " + id + ":\n";
            
            /* 
             * Iterate over the edges, adding a line to the string for
             * each of them.
             */
            Edge e = edges;
            while (e != null) {
                // Note: we have to use just the id field of the 
                // end vertex, or else we'll get infinite recursion!
                str += "\tedge to " + e.end.id;
                str += " (cost = " + e.cost + ")\n"; 
                
                e = e.next;
            }
            
            return str;
        }
    }
    /*
     * Edge - a private inner class for representing an edge.
     */
    private class Edge {
        private Vertex start;
        private Vertex end;
        private double cost;
        private Edge next;            // next Edge in adjacency list
        
        private Edge(Vertex start, Vertex end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    
    /******* Graph instance variables and method start here. *********/
      
    
    /*
     * reinitVertices - private helper method that resets the starting
     * state of all of the vertices in the graph, removing any values
     * set by previous uses of the algorithms.
     */
    private void reinitVertices() {
        Vertex v = vertices;
        while (v != null) {
            v.reinit();
            v = v.next;
        }
    }
    
    /*
     * getVertex - private helper method that returns a reference to
     * the vertex with the specified id.  If the vertex isn't
     * in the graph, it returns null.
     */
    private Vertex getVertex(int id) {
        Vertex v = vertices;
        while (v != null && !(v.id == id))
            v = v.next;
        
        return v;
    }
    
    /*
     * addVertex - private helper method that adds a vertex with
     * the specified id and returns a reference to it.
     */
    private Vertex addVertex(int id) {
        Vertex v = new Vertex(id);
        
        if (id == 0) {
            tail = v;
        }
        
        /* Add to the front of the list. */
        v.next = vertices;
        vertices = v;
        
        return v;
    }
    
    /**
     * addEdge - add an edge with the specified cost, and with start
     * and end vertices that have the specified IDs.  The edge will
     * be stored in the adjacency list of the start vertex.  If a
     * specified vertex isn't already part of the graph, it will be
     * added, too.
     */
    public void addEdge(int startVertexID, int endVertexID,
                        double cost) {
        Vertex start = getVertex(startVertexID);
        if (start == null)
            start = addVertex(startVertexID);
        Vertex end = getVertex(endVertexID);
        if (end == null)
            end = addVertex(endVertexID);
        
        Edge e = new Edge(start, end, cost);
        start.addToAdjacencyList(e);
    }
    
    /**
     * toString - returns a concatenation of the string
     * representations of all of the vertices in the graph.
     */
    public String toString() {
        String str = "";
        
        Vertex v = vertices;
        while (v != null) {
            str += v;
            v = v.next;
        }
        
        return str;
    }
    
    /**
     * initFromFile - initialize a graph from the matrix passed
     * from TSPBacked to the graph class. 
     */
    public void initFromMatrix() {
        
        int length = workingCopy.getLength();
        double[][] matrix = workingCopy.getMatrix();
        
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                
                if (i != j) {    
                    int startID = i;
                    int endID = j;
                    double cost = matrix[i][j];
                    addEdge(startID, endID, cost);
                }
            }
        }   
    }
    
    /**
     * depthFirstTrav - perform a depth-first traversal starting from
     * the vertex with the specified ID.  After making sure that the
     * start vertex exists, it makes an initial call to the recursive
     * method dfTrav, which does the actual traversal.
     */
    public void depthFirstTrav() {
        
        /* Get the specified start vertex. */
        Vertex start = tail;//getVertex(originID);
        if (start == null)
            throw new IllegalArgumentException("no such vertex: " +
                                               vertices.id);
        dfTrav(start, null);
    }
    
    /*
     * dfTrav - a recursive method used to perform a depth-first
     * traversal, starting from the specified vertex v.  The parent
     * parameter specifies v's parent in the depth-first spanning
     * tree.  In the initial invocation, the value of parent should be
     * null, because the starting vertex is the root of the spanning
     * tree.
     */
   private void dfTrav(Vertex v, Vertex parent) {
        /* Visit v. */
            
        v.done = true;
        v.parent = parent;
        
        Edge e = v.edges;
        while (e != null) {
            Vertex w = e.end;
            if (!w.done) {
                dfTrav(w, v);
            }
            e = e.next;
        }
        
        v.done = false;
        String currentRoute = v.pathString();
        
        /*if all the vertices have been visited, programs confirms if the 
        * route is the least route to visit all vertices 
        */
        if (currentRoute.length() == workingCopy.getLength()) {
            
            
            if (leastCost == 0) {
                leastCost = travCost;
                leastRoute = currentRoute;
            }
            
            else {
                
                if (leastCost > travCost) {
                    leastCost = travCost;
                    leastRoute = currentRoute;
                }
            }
                
            travCost = 0;
        }
        
    }
   
   /*
   * public getter method returns the String of cities for the least route
   */
   
   public String returnRoute () {
       return leastRoute;
   }
   
   /*
   * public getter method returns the double value of the least route
   */
   
   public double returnCost() {
       return leastCost;
   }
     
}