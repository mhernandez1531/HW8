/******************************************************************
 *
 *   Mariana Hernandez / COMP 272: Data Structures II (Fall 2024)
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 *  Graph traversal exercise
 *
 *  The Graph class is a representing an oversimplified Directed Graph of vertices
 *  (nodes) and edges. The graph is stored in an adjacency list
 */

public class Graph {
  int numVertices;                  // vertices in graph
  LinkedList<Integer>[] adjListArr; // Adjacency list
  List<Integer> vertexValues;       // vertex values

  // Constructor 
  public Graph(int numV) {
    numVertices = numV;
    adjListArr = new LinkedList[numVertices];
    vertexValues = new ArrayList<>(numVertices);

    for (int i = 0; i < numVertices; i++) {
      adjListArr[i] = new LinkedList<>();
      vertexValues.add(0);
    }
  }

  /*
   * method setValue
   *
   * Sets a vertex's (node's) value.
   */

  public void setValue(int vertexIndex, int value) {
    if (vertexIndex >= 0 && vertexIndex < numVertices) {
      vertexValues.set(vertexIndex, value);
    } else {
      throw new IllegalArgumentException(
              "Invalid vertex index: " + vertexIndex);
    }
  }


  public void addEdge(int src, int dest) {
    adjListArr[src].add(dest);
  }

  /*
   * method printGraph
   *
   * Prints the graph as an adjacency matrix
   */

  public void printGraph() {
    System.out.println(
            "\nAdjacency Matrix Representation:\n");
    int[][] matrix = new int[numVertices][numVertices];

    for (int i = 0; i < numVertices; i++) {
      for (Integer dest : adjListArr[i]) {
        matrix[i][dest] = 1;
      }
    }

    System.out.print("  ");
    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
    }
    System.out.println();

    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < numVertices; j++) {
        if (matrix[i][j] == 1) {
          System.out.print("| ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }


  /**
   * method findRoot
   * <p>
   * This method returns the value of the root vertex, where root is defined in
   * this case as a node that has no incoming edges. If no root vertex is found
   * and/or more than one root vertex, then return -1.
   */

  public int findRoot() {

    // Step 1: Track vertices with incoming edges
    // We use this array to check if a vertex has any incoming edges from other vertices
    boolean[] hasIncomingEdge = new boolean[numVertices];

    // Step 2: Mark vertices that have incoming edges
    // Loop through all vertices (source) and their adjacent vertices (destination)
    // If a destination has an incoming edge, we mark it as true in the hasIncomingEdge array
    for (int src = 0; src < numVertices; src++) {
      for (int dest : adjListArr[src]) {
        hasIncomingEdge[dest] = true; // Mark destination as having an incoming edge
      }
    }

    // Step 3: Identify vertices with no incoming edges
    // The root vertex should not have any incoming edges, so we look for such vertices
    int rootCandidate = -1; // Initialize rootCandidate to -1 (indicating no root found yet)
    for (int i = 0; i < numVertices; i++) {
      if (!hasIncomingEdge[i]) { // If a vertex has no incoming edge, it's a candidate for the root
        if (rootCandidate == -1) {
          rootCandidate = i; // First root found, assign rootCandidate
        } else {
          // More than one root found, return -1 since there can't be multiple roots
          return -1;
        }
      }
    }

    // Step 4: If no root is found (rootCandidate is still -1), return -1
    if (rootCandidate == -1) {
      return -1;  // No root vertex found
    }

    // Step 5: Return the value of the root vertex
    // If we found a valid root, return its value using the vertexValues list
    return vertexValues.get(rootCandidate);  // Return the value of the root vertex
  }
}