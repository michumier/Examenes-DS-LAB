package games;

import console.GameAndWatch;
import games.menu.MenuState;

public class GameState implements State {
    private GameAndWatch gameAndWatch;
    private State game;

    public GameState(GameAndWatch gameAndWatch, State game) {
        this.gameAndWatch = gameAndWatch;
        this.game = game;
    }

    @Override
    public void initialize() {
        game.initialize();
    }

    @Override
    public void checkFinished() {
        game.checkFinished();
    }

    @Override
    public void draw() {
        game.draw();
    }

    @Override
    public void gameFinished() {
        game.draw(); // Necesario para dibujar el último frame
        gameAndWatch.setScreen(new MenuState(gameAndWatch));
    }

    @Override
    public void restart() {
        game.restart();
    }
}
