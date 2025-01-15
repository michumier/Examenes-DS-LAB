package buttons.concretecommands;

import buttons.Command;
import console.GameAndWatch;
import games.GameState;
import games.menu.MenuState;

public class DownCommand implements Command {
    private GameAndWatch gameAndWatch;

    public DownCommand(GameAndWatch gameAndWatch) {
        this.gameAndWatch = gameAndWatch;
    }

    @Override
    public void execute() {
        if (gameAndWatch.getCurrentState() instanceof MenuState) {
            gameAndWatch.incrementSelectedGame();
        } else if (gameAndWatch.getCurrentState() instanceof GameState) {
            gameAndWatch.getCurrentState().downPressed();
        }
    }
}
