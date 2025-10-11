package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Main class for the pathfinding visualization application.
 * It contains the graph data, sets up the GUI, and
 *  implements the pathfinding algorithms.
 */
public class PathFinding {
    // Adjacency matrix to represent the graph and its edge weights.
    public static int[][] adjacency;
    // An array of Node objects representing the vertices of the graph.
    static Node[] nodes;
    // The main GUI object for the visualization.
    static GUI visuals;
    // An integer to track which graph structure is currently in use (1 or 2).
    public static int NodeStructure;

    public static void main(String[] args) throws InterruptedException {
        // Initializes the first default graph structure.
        firstNodeSetup();
        System.out.println();
    }

    /**
     * Sets up the first graph structure with its adjacency matrix and node locations.
     */
    public static void firstNodeSetup() {
        NodeStructure = 1;
        adjacency = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 0, 0, 0, 0, 6, 7, 0}};
        int[][] locations = new int[][]{{100, 400},
                {250, 300},
                {400, 300},
                {550, 300},
                {700, 400},
                {550, 500},
                {400, 500},
                {250, 500},
                {400, 400}};
        nodes = new Node[adjacency.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(locations[i][0], locations[i][1]);
        }
        // Dispose of the old frame if it exists before creating a new one.
        if (visuals != null) {
            visuals.frame.dispose();
        }
        visuals = new GUI(nodes, adjacency);
    }

    /**
     * Sets up the second graph structure.
     */
    public static void secondNodeSetup() {
        NodeStructure = 2;
        adjacency = new int[][]{{0, 0, 7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 5, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {7, 5, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {5, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 5, 0},
                {0, 0, 3, 0, 2, 0, 0, 7, 6, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 2, 4, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 7, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 6, 9, 0, 0, 0, 0, 2, 0, 0, 0, 0},
                {0, 0, 0, 0, 2, 0, 0, 8, 0, 0, 0, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 2, 4, 0, 0, 0, 5, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 5, 0, 0, 3, 0},
                {0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 4, 3, 0, 9},
                {0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0}
        };
        int[][] locations = new int[][]{{100, 100},
                {200, 100},
                {150, 200},
                {50, 200},
                {300, 200},
                {225, 300},
                {100, 300},
                {300, 300},
                {225, 400},
                {400, 300},
                {500, 500},
                {400, 500},
                {500, 200},
                {450, 300},
                {400, 100},
                {300, 50}};
        nodes = new Node[adjacency.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(locations[i][0], locations[i][1]);
        }
        visuals.frame.dispose();
        visuals = new GUI(nodes, adjacency);

    }

    /**
     * Pauses the execution to allow the user to see the visualization updates.
     * @throws InterruptedException if the thread is interrupted while sleeping.
     */
    public static void pause() throws InterruptedException {
        Thread.sleep(500);
        visuals.updateScreen();
    }

    /**
     * Implements Dijkstra's algorithm to find the shortest path between a start and target node.
     * @param start The starting node's index.
     * @param target The target node's index.
     * @return An ArrayList of integers representing the shortest path.
     * @throws InterruptedException if the thread is interrupted while sleeping.
     */
    public static ArrayList<Integer> dijkstras(int start, int target) throws InterruptedException {
        // Reset the graph visualization to its initial state.
        reset();
        nodes[0].currentState = Node.STATE.Current;
        int[] pathArr = new int[adjacency.length];
        int[] sptSet = new int[adjacency.length];
        int[] fullSptSet = new int[adjacency.length];
        int[] distanceArr = new int[adjacency.length];
        ArrayList<Integer> finalPath = new ArrayList<Integer>();
        distanceArr[start] = 0;
        sptSet[start] = 0;

        // Initialize distances to infinity and the SPT set to -1 (not in set).
        for (int i = 0; i < distanceArr.length; i++) {
            if (i != start) {
                distanceArr[i] = Integer.MAX_VALUE;
                sptSet[i] = -1;
                fullSptSet[i] = 0;
            }
        }

        int currNode = start;

        // Main loop to find the shortest path for all nodes.
        for (int j = 0; j < adjacency.length; j++) {
            nodes[currNode].currentState = Node.STATE.Current;
            pause();
            int minDistance = Integer.MAX_VALUE;
            int minNode = -1;

            // Update distances of adjacent nodes.
            for (int cols = 0; cols < adjacency[0].length; cols++) {
                int childDist = distanceArr[cols];
                int currDist = distanceArr[currNode];
                int currToChildDist = adjacency[currNode][cols];
                // Check if there is a path and if it's a shorter path than the current known distance.
                if (currToChildDist > 0 && sptSet[cols] == -1 && ((currDist + currToChildDist) < childDist)) {
                    distanceArr[cols] = currDist + currToChildDist;
                    pathArr[cols] = currNode;
                }
            }

            // Find the node with the minimum distance that hasn't been added to the SPT set.
            for (int currChild = 0; currChild < distanceArr.length; currChild++) {
                int currDistance = distanceArr[currChild];
                if (currDistance != -1) {
                    if (currDistance < minDistance && sptSet[currChild] == -1) {
                        nodes[currChild].currentState = Node.STATE.Visited;
                        pause();
                        minDistance = currDistance;
                        minNode = currChild;
                    }
                }
            }

            // Update node states for visualization.
            for (int k = 0; k < sptSet.length; k++) {
                if (sptSet[k] == 0) {
                    nodes[k].currentState = Node.STATE.Done;
                    pause();
                }
            }

            // Add the found minimum-distance node to the shortest path tree (SPT) set.
            if (minNode > -1) {
                sptSet[minNode] = 0;
            }
            currNode = minNode;
        }

        // Reconstruct the final path from the target to the start.
        int index = target;
        while (pathArr[index] != 0) {
            finalPath.add(0, pathArr[index]);
            index = pathArr[index];
        }
        finalPath.add(target);
        if (start == 0) {
            finalPath.add(0, 0);
        }

        // Highlight the final path for visualization.
        for (int num : finalPath) {
            nodes[num].currentState = Node.STATE.Path;
            pause();
        }
        System.out.println("Dijkstras Called");
        return finalPath;
    }

    /**
     * Placeholder for the Depth-First Search (DFS) algorithm.
     * @param start The starting node.
     * @param end The target node.
     * @throws InterruptedException
     */
    public static void DFS(int start, int end) throws InterruptedException {
        reset();
        System.out.println("DFS called");
    }

    /**
     * Placeholder for the Breadth-First Search (BFS) algorithm.
     * @param start The starting node.
     * @param end The target node.
     * @throws InterruptedException
     */
    public static void BFS(int start, int end) throws InterruptedException {
        reset();
        System.out.println("BFS called");
    }

    /**
     * Implements a random walk algorithm to find a path from a start to an end node.
     * @param start The starting node's index.
     * @param end The target node's index.
     * @throws InterruptedException if the thread is interrupted while sleeping.
     */
    public static void randomWalk(int start, int end) throws InterruptedException {
        reset();
        System.out.println("Random walk called");
        int current = start;
        ArrayList<Integer> order = new ArrayList<Integer>();
        order.add(start);
        nodes[start].currentState = Node.STATE.Current;
        while (current != end) {
            ArrayList<Integer> choices = new ArrayList<Integer>();
            // Find all unvisited neighbors.
            for (int i = 0; i < adjacency.length; i++) {
                if (adjacency[current][i] != 0 && !order.contains(i)) {
                    choices.add(i);
                    nodes[i].currentState = Node.STATE.Visited;
                    pause();
                }
            }
            // If no unvisited neighbors are found, reset the walk and try again.
            if (choices.size() == 0) {
                reset();
                order = new ArrayList<Integer>();
                order.add(start);
                current = start;
                continue;
            }
            // Randomly select a neighbor to move to.
            int choice = (int) (Math.random() * choices.size());
            nodes[current].currentState = Node.STATE.Done;
            current = choices.get(choice);
            nodes[current].currentState = Node.STATE.Current;
            order.add(current);
            // Reset the state of unchosen neighbors.
            for (int i : choices) {
                if (!order.contains(i)) {
                    nodes[i].currentState = null;
                }
            }
            pause();
        }
        // Highlight the final path.
        for (int i : order) {
            nodes[i].currentState = Node.STATE.Path;
            pause();
        }
        System.out.println("end found");
        visuals.updateScreen();

    }

    /**
     * Resets the state of all nodes in the graph to null.
     */
    public static void reset() {
        for (Node n : nodes) {
            n.currentState = null;
        }
        visuals.updateScreen();
    }
}