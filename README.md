# Maze Generation, Solving and Visualisation

A Java Swing app that builds a random maze and then finds the shortest way out of it. You can watch the whole thing happen on the screen: first the maze carves itself out, then the solver spreads through it, and at the end the shortest path lights up in green.

## What it does

1. **Builds a maze** using randomised depth-first search (DFS) and a stack. The result is a "perfect maze": no loops, and exactly one route between any two cells.
2. **Solves the maze** using breadth-first search (BFS) and a queue, from the top-left corner to the bottom-right corner. BFS always finds the shortest path.
3. **Shows every step** in a window, with a small delay so you can follow what the algorithms are doing.

## How to run

### In Eclipse
1. Open the project in Eclipse.
2. Open `MazeFrame.java`.
3. Right-click it and choose **Run As → Java Application**.

### From the command line
From the folder with the `.java` files:

```
javac *.java
java MazeFrame
```

### Changing the maze size
The size is set in `main` inside `MazeFrame.java`:

```java
MazeFrame f = new MazeFrame(10, 10);   // rows, columns
```

Change the two numbers and run again. Rows and columns can each be from **1 to 100**, and they don't have to be the same (for example `5, 15` works). If you give a size outside that range, the program prints a message instead of crashing.

To make the animation faster or slower, change `delay` (in milliseconds) at the top of `MazeFrame`.

## Colours

| Colour | Meaning |
|---|---|
| White | Cell not visited yet |
| Grey | Cell visited (carved by the generator, or explored by the solver) |
| Green | Cell on the final shortest path |
| Black lines | Walls |

## Class structure

| Class | What it does |
|---|---|
| `Cell` | One square of the maze. Stores its row and column, its four walls (`top`, `bottom`, `left`, `right`), whether it was visited, its `parent` (used by BFS) and whether it's on the final `path`. |
| `MazeFrame` | The main window. Holds the grid, runs the DFS generator and the BFS solver, and has `main`. |
| `MazeCanvas` | A `JPanel` that draws the maze. It fills each cell with its colour and then draws its walls. |
| `StackReferenceBased`, `StackInterface`, `StackException`, `Node` | The linked stack from the labs. Used by the DFS generator. |
| `QueueArrayBased`, `QueueInterface`, `QueueException` | The circular array queue from the labs. Used by the BFS solver. |

## Design choices

**Grid and coordinates.** The maze is a 2D array, `Cell[][] grid`, used as `grid[row][col]`. Rows go down the screen and columns go across. When drawing, the row decides how far down a cell is (`top = row * cell size`) and the column decides how far across it is (`left = col * cell size`).

**Walls.** Each cell has four booleans: `true` means the wall is there, `false` means it has been removed. Two cells next to each other share a wall, so removing it always changes both cells. For example, if a cell's `right` wall goes, its right neighbour's `left` wall goes too.

**Boundaries.** The outer walls are never removed:
- The generator only picks neighbours that are inside the grid, so it never tries to break an outer wall.
- Because the outer walls are always there, the solver can never walk outside the grid. It always checks the wall first before looking at the neighbour cell.

**Generator (randomised DFS).** It starts at the top-left cell and pushes it onto the stack. In a loop, it **peeks** at the top cell (not pops, because it might still need that cell later), picks a random unvisited neighbour, removes the wall between them, marks the neighbour visited and pushes it. At a dead end it **pops** to go back one step. It stops when the stack is empty, which means every cell has been visited.

**Solver (BFS).** It starts at the top-left cell and enqueues it. Each time it takes a cell from the queue, it looks in the four directions and only moves where there is no wall. Every new cell gets marked visited, remembers which cell it came from (`parent`), and goes into the queue. When the exit is reached, it follows the parents back from the exit to the start and marks those cells as the path.

**Reusing `visited`.** Both algorithms use the same `visited` flag. After generating, every cell is visited, so all of them get reset to `false` before solving.

**Changes to the lab code.** The lab queue had a fixed size of 50, which is too small for big mazes. I added a constructor that takes the size, and the solver creates the queue with `rows × columns` so it can never get full. The stack is linked, so it has no size limit and didn't need changes.

**Animation.** After each step, the program calls `repaint()` and then `Thread.sleep(delay)`. The algorithms run from `main` (the main thread), not on Swing's drawing thread, so the window doesn't freeze while it waits.

**Size limit.** Sizes are limited to 1–100. With a 600×600 window, a much bigger grid would make each cell less than one pixel, so nothing would be visible.

Every run makes a different random maze, so the path length changes each time.

## Credits

- The `Cell` class and the grid drawing are based on the lecture demo by Dr. Simon McLoughlin.
- The stack and queue classes come from the DSA labs.

