package games.space;

import java.util.ArrayList;
import java.util.List;

import games.State;



public class SpaceInvadersGame implements State{

	
	public int shipX;

    enum EnemyCell {
        EMPTY, ENEMY
    }

    public List<EnemyCell> enemiesMap;

    private List<Integer> barriersMap;
    private char[] barriersType = new char[] { ' ', '▒', '▓', '█' };
    
    public void initialize() {
        enemiesMap = new ArrayList<>(List.of(
                EnemyCell.EMPTY,
                EnemyCell.EMPTY,
                EnemyCell.ENEMY,
                EnemyCell.EMPTY,
                EnemyCell.ENEMY,
                EnemyCell.EMPTY,
                EnemyCell.ENEMY,
                EnemyCell.EMPTY,
                EnemyCell.EMPTY));

        barriersMap = new ArrayList<>();
        for (var content : enemiesMap)
            barriersMap.add(content == EnemyCell.ENEMY ? barriersType.length - 1 : 0);

        shipX = 4;
    }

    public void checkFinished() {

        if (barriersMap.get(shipX) > 0) {
            barriersMap.set(shipX, barriersMap.get(shipX) - 1);
            return;
        }

        if (enemiesMap.get(shipX) == EnemyCell.ENEMY) {
            enemiesMap.set(shipX, EnemyCell.EMPTY);

            if (enemiesMap.stream().noneMatch(content -> content == EnemyCell.ENEMY)) {
                gameFinished();
                System.out.println("Congratulations! You won!");
            }
        }
    }

    public void draw() {
        System.out.println();

        var screenSize = enemiesMap.size();

        System.out.println("┌" + "─".repeat(screenSize) + "┐");

        // draw enemies
        System.out.print("│");
        for (var content : enemiesMap)
            System.out.print(content == EnemyCell.EMPTY ? ' ' : '¥');
        System.out.println("│");

        drawBlanLine(screenSize);

        // draw barriers
        System.out.print("│");
        for (var content1 : barriersMap)
            System.out.print(barriersType[content1]);
        System.out.println("│");
        drawBlanLine(screenSize);

        // draw ship
        System.out.println("│" + " ".repeat(shipX) + '^' + " ".repeat(screenSize - shipX - 1) + "│");

        System.out.println("└" + "─".repeat(screenSize) + "┘");
    }

    private void drawBlanLine(int screenSize) {
        System.out.println("│" + " ".repeat(screenSize) + "│");
    }
    
    public void gameFinished() {
        draw(); // Necesario para dibujar el último frame
        //setScreen("menu");
    }
    
    public void restart() {
    	System.out.println("----------------------------");
        System.out.println("Space Invaders!!!");
        System.out.println("(W -> shoot. A/D -> left/right)");
        System.out.println("(C -> menu)");

        initialize();
    }
}
