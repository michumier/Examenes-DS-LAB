package console;

import java.util.*;

import buttons.Command;
import buttons.concretecommands.DownCommand;
import buttons.concretecommands.LeftCommand;
import buttons.concretecommands.RightCommand;
import buttons.concretecommands.UpCommand;
import games.State;
import games.f1.F1Game;
import games.menu.MenuState;
import games.mortal.MortalMazeGame;
import games.space.SpaceInvadersGame;

public class GameAndWatch {
    private State currentState;
    private List<String> games;
    private int selectedGame;
    private boolean exit;

    private Command upCommand;
    private Command downCommand;
    private Command leftCommand;
    private Command rightCommand;

    public GameAndWatch() {
        this.games = List.of("Mortal Maze", "F1 Grand Prix", "Space Invaders");
        this.selectedGame = 0;
        this.currentState = new MenuState(this);

        this.upCommand = new UpCommand(this);
        this.downCommand = new DownCommand(this);
        this.leftCommand = new LeftCommand(this);
        this.rightCommand = new RightCommand(this);
    }

    public void setScreen(State state) {
        this.currentState = state;
        currentState.initialize();
    }

    public State getCurrentState() {
        return currentState;
    }

    public void draw() {
        currentState.draw();
    }

    public void gameFinished() {
        currentState.gameFinished();
    }

    public void drawMenu() {
        System.out.println("\n--------------------");
        System.out.println("Select a game: (W/S -> up/down. D -> play game)");
        for (int i = 0; i < games.size(); i++) {
            System.out.print((i == selectedGame) ? " >> " : "    ");
            System.out.println(games.get(i));
        }
        System.out.println("(C -> turn off)");
    }

    public boolean exit() {
        return exit;
    }

    public void upPressed() {
        upCommand.execute();
    }

    public void downPressed() {
        downCommand.execute();
    }

    public void leftPressed() {
        leftCommand.execute();
    }

    public void rightPressed() {
        rightCommand.execute();
    }

    public void backPressed() {
        if (currentState instanceof MenuState) {
            exit = true;
        } else {
            setScreen(new MenuState(this));
        }
    }

    public void incrementSelectedGame() {
        if (selectedGame < games.size() - 1) {
            selectedGame++;
        }
    }

    public void decrementSelectedGame() {
        if (selectedGame > 0) {
            selectedGame--;
        }
    }

    public State getSelectedGame() {
        // Return the selected game instance based on selectedGame index
        switch (selectedGame) {
            case 0:
                return new MortalMazeGame();
            case 1:
                return new F1Game();
            case 2:
                return new SpaceInvadersGame();
            default:
                return null;
        }
    }
}