package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The GUI class handles the graphical user interface for the pathfinding visualizer.
 * It sets up the main window, buttons, and display panel.
 */
public class GUI implements ActionListener {

    int count = 0;
    JFrame frame;
    ShapeDrawing panel;
    JLabel label;
    JButton button;

    /**
     * Constructs the GUI, initializes all JSwing components, and adds action listeners.
     * @param n An array of Node objects.
     * @param A The adjacency matrix of the graph.
     */
    public GUI(Node[] n, int[][] A) {
        frame = new JFrame();
        panel = new ShapeDrawing(n, A);
        button = new JButton("Djikstra's");
        JButton button2 = new JButton("DFS");
        JButton button3 = new JButton("BFS");
        JButton button4 = new JButton("RandomWalk");
        JButton button5 = new JButton("First Network");
        JButton button6 = new JButton("Second Network");
        label = new JLabel("number of clicks: " + count);

        panel.setBackground(Color.black);
        panel.setBounds(0, 0, 800, 800);

        // Set button bounds and add action listeners for each algorithm and graph setup.
        button.setBounds(800, 0, 200, 20);
        button.addActionListener(new DijkstraButtonListener());

        button2.setBounds(800, 30, 200, 20);
        button2.addActionListener(new DFSButtonListener());

        button3.setBounds(800, 60, 200, 20);
        button3.addActionListener(new BFSButtonListener());

        button4.setBounds(800, 90, 200, 20);
        button4.addActionListener(new RandomButtonListener());

        button5.setBounds(800, 600, 200, 20);
        button5.addActionListener(new FirstButtonListener());

        button6.setBounds(800, 630, 200, 20);
        button6.addActionListener(new SecondButtonListener());

        // Configure and set up the main JFrame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 800);
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);
        frame.add(button);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // This method is a placeholder and not used by the current button listeners.
        // The action logic is handled by the inner classes.
        Thread newThread = new Thread(() -> {
            try {
                PathFinding.dijkstras(0, 4);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        newThread.start();
    }

    /**
     * Calls the repaint method on the panel to update the screen.
     */
    public void updateScreen() {
        panel.repaint();
    }
}

// Inner classes for button action listeners.
class DijkstraButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        System.out.println("Dijkstra Executing");
        // Create a new thread to run the algorithm to prevent the GUI from freezing.
        Thread newThread = new Thread(() -> {
            try {
                if (PathFinding.NodeStructure == 1) {
                    PathFinding.dijkstras(0, 4);
                } else {
                    PathFinding.dijkstras(0, 15);
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        newThread.start();
    }
}

class DFSButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        System.out.println("DFS Executing");
        Thread newThread = new Thread(() -> {
            try {
                if (PathFinding.NodeStructure == 1) {
                    PathFinding.DFS(0, 4);
                } else {
                    PathFinding.DFS(0, 15);
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        newThread.start();
    }
}

class BFSButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        System.out.println("BFS Executing");
        Thread newThread = new Thread(() -> {
            try {
                if (PathFinding.NodeStructure == 1) {
                    PathFinding.BFS(0, 4);
                } else {
                    PathFinding.BFS(0, 15);
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        newThread.start();
    }
}

class RandomButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        System.out.println("Random Executing");
        Thread newThread = new Thread(() -> {
            try {
                if (PathFinding.NodeStructure == 1) {
                    PathFinding.randomWalk(0, 4);
                } else {
                    PathFinding.randomWalk(0, 15);
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        newThread.start();
    }
}

class FirstButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        System.out.println("First Node Structure");
        Thread newThread = new Thread(() -> {
            PathFinding.firstNodeSetup();
        });
        newThread.start();
    }
}

class SecondButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        System.out.println("Second Node Structure");
        Thread newThread = new Thread(() -> {
            PathFinding.secondNodeSetup();
        });
        newThread.start();
    }
}