package buttons.concretecommands;

import buttons.Command;
import console.GameAndWatch;
import games.GameState;
import games.menu.MenuScreen;

public class RightCommand implements Command {
    private GameAndWatch gameAndWatch;

    public RightCommand(GameAndWatch gameAndWatch) {
        this.gameAndWatch = gameAndWatch;
    }

    @Override
    public void execute() {
        if (gameAndWatch.getCurrentState() instanceof MenuScreen) {
            gameAndWatch.setScreen(new GameState(gameAndWatch, gameAndWatch.getSelectedGame()));
        } else if (gameAndWatch.getCurrentState() instanceof GameState) {
            gameAndWatch.getCurrentState().rightPressed();
        }
    }
}
