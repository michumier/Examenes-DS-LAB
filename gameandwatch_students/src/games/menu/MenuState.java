package games.menu;


import console.GameAndWatch;
import games.State;


public class MenuState implements State {
    private GameAndWatch gameAndWatch;

    public MenuState(GameAndWatch gameAndWatch) {
        this.gameAndWatch = gameAndWatch;
    }

    @Override
    public void initialize() {
        // Initialize menu state if needed
    }

    @Override
    public void checkFinished() {
        // No action needed for menu state
    }

    @Override
    public void draw() {
        gameAndWatch.drawMenu();
    }

    @Override
    public void gameFinished() {
        // No action needed for menu state
    }

    @Override
    public void restart() {
        // No action needed for menu state
    }
}