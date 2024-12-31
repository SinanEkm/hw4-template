package given;

/*
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this list of 
 * conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of 
 * conditions and the following disclaimer in the documentation and/or other materials provided 
 * with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to 
 * endorse or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Most importantly, this software is provided for educational purposes and should not be used for
 * anything else.
 * 
 *
 * */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import autograder.Autograder;
import code.*;

/*
 * NOTE: This testing is not comprehensive at all.
 * We will probably use other graphs to test your algorithms so do not trust this by itself!
 */

public class AlgTesting {
  
  public static <T> boolean compareListAndArray(List<T> l, T objs[]) {
    if(l.size() != objs.length)
      return false;
    
    for(int i = 0; i < objs.length; i++) {
      if(objs[i] != l.get(i))
        return false;
    }
    return true;
  }
  
  public static void testDFS(GraphAlgorithms<Character> algs, BaseGraph<Character> G) {
    G.insertVertex('A');
    G.insertVertex('B');
    G.insertVertex('C');
    G.insertVertex('D');
    G.insertVertex('E');
    G.insertVertex('F');
    G.insertVertex('G');
    G.insertVertex('H');

    G.insertEdge('A', 'B', 6.9f);
    G.insertEdge('A', 'F', 4.8f);
    G.insertEdge('A', 'H', 1.0f);
    G.insertEdge('B', 'F', 8.4f);
    G.insertEdge('B', 'E', 3.6f);
    G.insertEdge('B', 'G', 2.4f);
    G.insertEdge('C', 'G', 9.7f); 
    G.insertEdge('C', 'F', 0.2f);
    G.insertEdge('D', 'G', 5.8f);
    G.insertEdge('E', 'F', 5.0f);

    List<Character> dfsSequence =  algs.DFS(G, 'A');
    Character[] desiredRecursiveSequence = {'A','B','E','F','G','H'};
    Character[] desiredIterativeSequence = {'A','H','F','B','G','E'};
    Character[] desiredIterativeSequence2 = {'A','H','F','E','B','G','D','C'};
    Character[] desiredRecursiveSequence2 = {'A','B','E','F','C','G','D','H'};

    if(compareListAndArray(dfsSequence, desiredRecursiveSequence) ||  compareListAndArray(dfsSequence, desiredRecursiveSequence2)|| compareListAndArray(dfsSequence, desiredIterativeSequence) || compareListAndArray(dfsSequence, desiredIterativeSequence2)) {
      Autograder.addGrade(17.5f);
    }
    else {
      Autograder.Log("DFS failed for " + G);
    }
  }
  
  public static void testBFS(GraphAlgorithms<Character> algs, BaseGraph<Character> G) {
    G.insertVertex('A');
    G.insertVertex('B');
    G.insertVertex('C');
    G.insertVertex('D');
    G.insertVertex('E');
    G.insertVertex('F');
    G.insertVertex('G');
    G.insertVertex('H');
    
    G.insertEdge('A', 'B', 0f);
    G.insertEdge('A', 'F', 0f);
    G.insertEdge('A', 'H', 0f);
    G.insertEdge('B', 'F', 0f);
    G.insertEdge('B', 'E', 0f);
    G.insertEdge('B', 'G', 0f);
    G.insertEdge('C', 'G', 0f);
    G.insertEdge('C', 'F', 0f);
    G.insertEdge('D', 'G', 0f);
    G.insertEdge('E', 'F', 0f);
    
    List<Character> bfsSequence =  algs.BFS(G, 'A');
    Character[] desiredSequence = {'A','B','E','F','H','G','C','D'};
    Character[] desiredSequence2 = {'A','B','H','E','F','G','C','D'};
    Character[] desiredSequence3 = {'A','B','F','H','E','G','C','D'};

    if(compareListAndArray(bfsSequence, desiredSequence) || compareListAndArray(bfsSequence, desiredSequence2) || compareListAndArray(bfsSequence, desiredSequence3)) {
      Autograder.addGrade(17.5f);
    }
    else {
      Autograder.Log("BFS failed for " + G);
    }
  }
  
  public static void testDijk(GraphAlgorithms<Character> algs, BaseGraph<Character> G) {
    G.insertVertex('A');
    G.insertVertex('B');
    G.insertVertex('C');
    G.insertVertex('D');
    G.insertVertex('E');
    G.insertVertex('F');
    G.insertVertex('G');
    G.insertVertex('H');
    
    G.insertEdge('A', 'B', 6.9f);
    G.insertEdge('A', 'F', 4.8f);
    G.insertEdge('A', 'H', 1.0f);
    G.insertEdge('B', 'F', 8.4f);
    G.insertEdge('B', 'E', 3.6f);
    G.insertEdge('B', 'G', 2.4f);
    G.insertEdge('C', 'G', 9.7f); 
    G.insertEdge('C', 'F', 0.2f);
    G.insertEdge('D', 'G', 5.8f);
    G.insertEdge('E', 'F', 5.0f);
    
    G.insertEdge('A', 'E', 10.6f);
    if(G.isDirected())
      G.insertEdge('F', 'H', 0.1f);
    
    HashMap<Character,Float> costs = algs.Dijkstras(G, 'A');
    HashMap<Character,Float> desiredCosts = new HashMap<Character,Float>();
    if(G.isDirected()) {
      desiredCosts.put('A', 0f);
      desiredCosts.put('B', 6.9f);
      desiredCosts.put('E', 10.5f);
      desiredCosts.put('F', 4.8f);
      desiredCosts.put('G', 9.3f);
      desiredCosts.put('H', 1.0f);
    }
    else {
      desiredCosts.put('A', 0f);
      desiredCosts.put('B', 6.9f);
      desiredCosts.put('C', 5.0f);
      desiredCosts.put('D', 15.1f);
      desiredCosts.put('E', 9.8f);
      desiredCosts.put('F', 4.8f);
      desiredCosts.put('G', 9.3f);
      desiredCosts.put('H', 1.0f);
    }
    
    if(costs.equals(desiredCosts)) {
      Autograder.addGrade(20f);
    }
    else {
      for(Character c : costs.keySet())
        System.out.println(c + " " + costs.get(c) );
      Autograder.Log("Dijkstra failed for " + G);
    }
  }

  public static void testCyclic(GraphAlgorithms<Character> algs, BaseGraph<Character> G) {

    // Test empty graph
    if(!algs.isCyclic(G))
      Autograder.addGrade(1f);
    else
      Autograder.Log("Graph with no edges cannot be cyclic (AlgTesting.java)");

    // Insert vertices
    G.insertVertex('A');
    G.insertVertex('B');
    G.insertVertex('C');
    G.insertVertex('D');

    G.insertVertex('X');
    G.insertVertex('Y');
    G.insertVertex('Z');

    // Create a tree structure with weights
    G.insertEdge('A', 'B', 1.0f);
    G.insertEdge('A', 'C', 1.0f);

    if(!algs.isCyclic(G))
      Autograder.addGrade(1f);
    else {
      Autograder.Log("A tree structure is not cyclic (AlgTesting.java)");
      Autograder.Log("Check your handling of undirected edges - don't count the reverse path as a cycle");
    }

    // Add edges to create a cycle
    G.insertEdge('B', 'D', 1.0f);
    G.insertEdge('C', 'B', 1.0f);

    boolean tmpB = algs.isCyclic(G);

    // In an undirected graph, this creates a cycle A-B-C-A
    if(tmpB)
      Autograder.addGrade(1.5f);
    else
      Autograder.Log("This undirected graph contains a cycle A-B-C-A (AlgTesting.java)");

    // Remove edge to break the cycle
    G.removeEdge('A', 'B');
    tmpB = algs.isCyclic(G);
    if(!tmpB)
      Autograder.addGrade(1f);
    else
      Autograder.Log("This graph should not be cyclic after edge removal (AlgTesting.java)");

    // Create a separate cycle with weights
    G.insertEdge('X', 'Y', 1.0f);
    G.insertEdge('Y', 'Z', 1.0f);
    G.insertEdge('Z', 'X', 1.0f);

    tmpB = algs.isCyclic(G);
    if(tmpB)
      Autograder.addGrade(5f);
    else
      Autograder.Log("This graph contains a cycle X-Y-Z-X (AlgTesting.java)");
  }

  public static void main(String args[]) {
    boolean iDidIt = false;
    if (!Autograder.initializedOnce) {
      Autograder.init();
      iDidIt = true;
    }
    
    Autograder.Log("Starting algorithm testing");
    
    GraphAlgorithms<Character> algs = new GraphAlgorithms<Character>();

    //Test Dijktra with undirected weighted
    UndirectedWeightedGraph<Character> uwG = new UndirectedWeightedGraph<Character>();
    testDijk(algs, uwG);
    UndirectedWeightedGraph<Character> uwGBFS = new UndirectedWeightedGraph<Character>();
    testBFS(algs, uwGBFS);
    UndirectedWeightedGraph<Character> uwGDFS = new UndirectedWeightedGraph<Character>();
    testDFS(algs, uwGDFS);

    UndirectedWeightedGraph<Character> uwGCyclic = new UndirectedWeightedGraph<Character>();
    //Test cyclic 
    testCyclic(algs, uwGCyclic);
    
    Autograder.Log("Finished algorithm testing");
    
    if (iDidIt) {
      Autograder.printLog();
      Autograder.printGrade(65);
    }
  }

}
