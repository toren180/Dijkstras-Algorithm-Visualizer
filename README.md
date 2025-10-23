Dijkstra's Algorithm Visualizer - Toren Snyder

Description:
Implements Dijkstra's algorithm in Java and visualizes it using JSwing.
As the algorithm runs and the shortest paths to vertices are calculated, the display is updated.
Nodes change color based on their state: yellow for visited, green for current, and blue for the final path.
Also contains implementations of other pathfinding algorithms for comparison, including Breadth-First Search (BFS), Depth-First Search (DFS), and a Random Walk.

Files:

PathFinding.java: Contains the implementations of the pathfinding algorithms (Dijkstra's, BFS, DFS, Random Walk) and graph setup.

GUI.java: Handles the JSwing GUI, including buttons and the main window.

ShapeDrawing.java: Contains the logic for drawing the nodes, edges, and their colors.

Node.java: Represents a node in the graph and tracks its state.

To Run:

Run the main class (PathFinding.java).

A window will open displaying the graph and a set of buttons.

Click the "Dijkstra's" button to start the algorithm.

The visualization will show the algorithm's progress in real-time.

You can also click other buttons to run different algorithms or switch between the two predefined graph networks.

Notes:

The provided code is for visualization and demonstration purposes.

The BFS and DFS methods are currently placeholders and do not have full implementations.
