package games.mortal;

import games.State;

public class MortalMazeGame implements State {
    static final int WIDTH = 14;
    static final int HEIGHT = 7;

    int playerX;
    int playerY;
    int exitX, exitY;

    @Override
    public void initialize() {
        playerX = playerY = 2;
        exitX = exitY = 4;
    }

    @Override
    public void checkFinished() {
        if (playerX == exitX && playerY == exitY) {
            gameFinished();
            System.out.println("You won!");
        }
    }

    @Override
    public void draw() {
        var map = new char[HEIGHT][WIDTH];

        // Fill first and last column with '#'
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = '#';
            map[map.length - 1][i] = '#';
        }

        // Fill inside lines with a border around and spaces inside
        for (int i = 1; i < map.length - 1; i++) {
            map[i][0] = '#';
            map[i][map[i].length - 1] = '#';
            for (int j = 1; j < map[i].length - 1; j++)
                map[i][j] = ' ';
        }

        // Put exit and player
        map[exitY][exitX] = 'X';
        map[playerY][playerX] = 'O';

        // draw all
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++)
                System.out.print(map[i][j]);
            System.out.println();
        }

        System.out.println();
    }

    @Override
    public void gameFinished() {
        // Implement game finished logic if needed
    }

    @Override
    public void restart() {
        initialize();
    }
}