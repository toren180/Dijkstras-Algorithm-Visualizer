package org.example;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represents a single node (vertex) in the graph.
 * It stores the node's position and its current state for visualization purposes.
 */
public class Node {
    int x;
    int y;
    STATE currentState;

    /**
     * Constructs a new Node with specified coordinates.
     * @param x The x-coordinate of the node.
     * @param y The y-coordinate of the node.
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * An enum to define the possible states of a node during the algorithm's execution.
     * Each state corresponds to a different color for visualization.
     */
    public enum STATE {
        Target,    // The target or destination node.
        Current,   // The node currently being processed by the algorithm.
        Visited,   // A node that has been visited but not yet finalized.
        Done,      // A node for which the shortest path has been finalized.
        Path;      // A node that is part of the final shortest path.
    }

    /**
     * Gets the color corresponding to the node's current state.
     * @param g The Graphics2D object to set the color on.
     */
    public void getColor(Graphics2D g) {
        if (currentState == STATE.Path) {
            g.setColor(Color.blue);
        } else if (currentState == STATE.Visited || currentState == STATE.Target) {
            g.setColor(Color.yellow);
        } else if (currentState == STATE.Current) {
            g.setColor(Color.green);
        } else if (currentState == STATE.Done) {
            g.setColor(Color.pink);
        } else {
            g.setColor(Color.red);
        }
    }
}