package games.f1;

import java.util.List;

import games.State;

public class F1Game implements State{

	private static final char GOAL = 'X';
    private static final char PATH = '*';
    private static final int ROAD_WIDTH = 6;

    private static final int ROWS_ABOVE = 6;
    private static final int ROWS_BELOW = 2;

    int carX;
	int carY;

    private List<String> road = List.of(
            "  " + GOAL,
            "  " + PATH,
            "    " + PATH,
            "     " + PATH,
            "      " + PATH,
            "      " + PATH,
            "      " + PATH,
            "    " + PATH,
            "  " + PATH,
            " " + PATH,
            " " + PATH);
    
    public void initialize() {
        carX = 5;
        carY = road.size() - 1;
    }

    public void checkFinished() {
        if (carY == 0) {
            gameFinished();
            System.out.println("Congratulations! You won!");
            return;
        }

        var row = road.get(carY);
        int leftLimit = row.indexOf(PATH);
        int rightLimit = leftLimit + ROAD_WIDTH + 1;

        if (carX <= leftLimit || carX >= rightLimit) {
            System.out.println("You crashed!. Start again");
            initialize();
        }
    }

    public void draw() {
        for (int row = carY - ROWS_ABOVE; row <= carY + ROWS_BELOW; row++)
            drawRow(row);
    }

    private void drawRow(int row) {

        if (row < 0 || row >= road.size()) {
            System.out.println();
            return;
        }

        var line = new StringBuilder();

        if (row == 0)
            line.append(" ########");

        else {
            int padding = road.get(row).indexOf(PATH);
            line.append(" ".repeat(padding));

            char border = '|';
            if (row < road.size() - 1) {
                var rowBelow = road.get(row + 1);
                int paddingBelow = rowBelow.indexOf(PATH);
                if (paddingBelow > padding)
                    border = '\\';
                else if (paddingBelow < padding)
                    border = '/';
            }

            line.append(border);
            line.append(" ".repeat(ROAD_WIDTH));
            line.append(border);
        }

        if (carY == row)
            line.setCharAt(carX, '█');

        System.out.println(line);
    }
    
    public void gameFinished() {
        draw(); // Necesario para dibujar el último frame
        //setScreen("menu");
    }
    
    public void restart() {
    	System.out.println("----------------------------");
        System.out.println("F1 Grand Prix!!!");
        System.out.println("(A/D -> left/right)");
        System.out.println("(C -> menu)");

        initialize();
    }

}
