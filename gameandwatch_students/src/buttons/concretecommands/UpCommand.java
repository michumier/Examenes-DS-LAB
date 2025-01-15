package buttons.concretecommands;

import buttons.Command;
import console.GameAndWatch;
import games.GameState;
import games.menu.MenuScreen;

public class UpCommand implements Command {
    private GameAndWatch gameAndWatch;

    public UpCommand(GameAndWatch gameAndWatch) {
        this.gameAndWatch = gameAndWatch;
    }

    @Override
    public void execute() {
        if (gameAndWatch.getCurrentState() instanceof MenuScreen) {
            gameAndWatch.decrementSelectedGame();
        } else if (gameAndWatch.getCurrentState() instanceof GameState) {
            gameAndWatch.getCurrentState().upPressed();
        }
    }
}
