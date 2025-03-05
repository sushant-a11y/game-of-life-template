import java.util.ArrayList;
import java.util.Arrays;

public class GameOfLife implements Board {

    // Integers: 0 or 1 for alive or dead
    private int[][] board;
    private ArrayList<Point> pointsToRemove = new ArrayList<>();
    private ArrayList<Point> pointsToAdd = new ArrayList<>();

    // Construct a 2d array of the given x and y size.
    public GameOfLife(int x, int y) {

        board = new int[x][y];

    }

    // Set values on the board
    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                board[i + x][j + y] = data[i][j];
            }
        }
    }

    // Run the simulation for a number of turns
    public void run(int turns) {
        // call step the number of times requested
        for (int i = 0; i < turns; i++) {
            step();
        }
    }

    // Step the simulation forward one turn.
    public void step() {
        pointsToAdd.clear();
        pointsToRemove.clear();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    if (countNeighbors(i, j) < 2) {
                        pointsToRemove.add(new Point(i, j));
                    } else if (countNeighbors(i, j) > 3) {

                        pointsToRemove.add(new Point(i, j));
                    }
                }
                if (board[i][j] == 0) {
                    if (countNeighbors(i, j) == 3) {
                        pointsToAdd.add(new Point(i, j));
                    }
                }


            }

        }
        for (Point p : pointsToAdd) {
            board[p.x][p.y] = 1;
        }
        for (Point p : pointsToRemove) {
            board[p.x][p.y] = 0;
        }
        print();
        // Update the game board, store a 1 if the cell is alive and a 0 otherwise.
    }


    public int countNeighbors(int x, int y) {
        int count = 0;
        if (this.get(x - 1, y - 1) == 1) {
            count++;
        }
        if (this.get(x - 1, y) == 1) {
            count++;
        }
        if (this.get(x - 1, y + 1) == 1) {
            count++;
        }
        if (this.get(x, y - 1) == 1) {
            count++;
        }

        if (this.get(x, y + 1) == 1) {
            count++;
        }
        if (this.get(x + 1, y - 1) == 1) {
            count++;
        }
        if (this.get(x + 1, y) == 1) {
            count++;
        }
        if (this.get(x + 1, y + 1) == 1) {
            count++;
        }
        // count the number of neighbors the cell has
        // use the get(x,y) method to read any board state you need.
        return count;
    }

    // Get a value from the board with "wrap around"
    // Locations outside the board will loop back into the board.
    // Ex: -1 will read board.length-1
    public int get(int x, int y) {
        int xLimit = board.length;
        int yLimit = board[0].length;
        return board[(x + xLimit) % xLimit][(y + yLimit) % yLimit];
    }

    // Test helper to get the whole board state
    public int[][] get() {
        return board;
    }

    // Test helper to print the current state
    public void print() {
        // Print the header
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y % 10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x % 10);
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == 1) {
                    System.out.print("⬛");
                } else {
                    System.out.print("⬜");
                }
            }
        }
        System.out.println();
    }
}
