package org.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * The ShapeDrawing class is a JPanel responsible for drawing the nodes and edges of the graph.
 */
public class ShapeDrawing extends JPanel {
    public int count;
    private Node[] nodes;
    private int[][] Adjacency;

    /**
     * Constructs the drawing panel with the graph data.
     * @param n An array of Node objects.
     * @param A The adjacency matrix.
     */
    public ShapeDrawing(Node[] n, int[][] A) {
        super();
        nodes = n;
        Adjacency = A;
    }

    /**
     * The main paint method, called by JSwing to render the components.
     * @param g The Graphics object used for drawing.
     */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.pink);
        // Iterate through all nodes and draw them and their connections.
        for (int i = 0; i < nodes.length; i++) {
            drawNode(i, g2);
            for (int j = i + 1; j < nodes.length; j++) {
                if (Adjacency[i][j] > 0) {
                    drawLine(nodes[i], nodes[j], g2);
                }
            }
        }
    }

    public void changeCount() {
        count += 1;
    }

    /**
     * Draws a single node with its corresponding color based on its state.
     * @param n The index of the node to draw.
     * @param g The Graphics2D object.
     */
    private void drawNode(int n, Graphics2D g) {
        nodes[n].getColor(g);
        g.fillOval(nodes[n].x, nodes[n].y, 30, 30);
    }

    /**
     * Draws a line between two nodes, with color determined by the nodes' states.
     * @param one The first node.
     * @param two The second node.
     * @param g The Graphics2D object.
     */
    private void drawLine(Node one, Node two, Graphics2D g) {
        // The logic here determines the color of the line based on the states of the connected nodes.
        if ((one.currentState == Node.STATE.Current || two.currentState == Node.STATE.Current) && (one.currentState == Node.STATE.Target || two.currentState == Node.STATE.Target)) {
            g.setColor(Color.green);
        } else if ((one.currentState == Node.STATE.Current || two.currentState == Node.STATE.Current) && (one.currentState == Node.STATE.Visited || two.currentState == Node.STATE.Visited)) {
            g.setColor(Color.yellow);
        } else if (one.currentState == two.currentState) {
            one.getColor(g);
        } else if (one.currentState == null || two.currentState == null) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.yellow);
        }
        g.drawLine(one.x + 15, one.y + 15, two.x + 15, two.y + 15);
    }
}