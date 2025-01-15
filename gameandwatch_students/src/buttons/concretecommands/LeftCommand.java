package buttons.concretecommands;

import buttons.Command;
import console.GameAndWatch;
import games.GameState;

public class LeftCommand implements Command {
    private GameAndWatch gameAndWatch;

    public LeftCommand(GameAndWatch gameAndWatch) {
        this.gameAndWatch = gameAndWatch;
    }

    @Override
    public void execute() {
        if (gameAndWatch.getCurrentState() instanceof GameState) {
            gameAndWatch.getCurrentState().leftPressed();
        }
    }
}
