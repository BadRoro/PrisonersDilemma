package system;

import domain.Game;
import domain.GameInterface;
import domain.Player;
import domain.Point;
import domain.Turn;
import strategy.Action;

public class GameSystem {

    private Player player1;

    private Player player2;
    private GameInterface game;
    private InteractionConsole interactionConsole;

    public GameSystem() {
	this.game = new Game();
	this.player1 = game.getP1();
	this.player2 = game.getP2();
    }

    private void askNumberOfTurn() {
	this.game.setNumberOfTurns(interactionConsole.askNbTurn(player1));
    }

    private void playRound() {
	Action actionP1;
	Action actionP2;

	if (player1.getIsPlaying()) {
	    actionP1 = interactionConsole.askAction(game, player1);
	} else {
	    actionP1 = player1.getMyStrategy().askAction(game.getOpponentLastAction(player1));
	}

	if (player2.getIsPlaying()) {
	    actionP2 = interactionConsole.askAction(game, player2);
	} else {
	    actionP2 = player2.getMyStrategy().askAction(game.getOpponentLastAction(player2));
	}

	this.game.getTurnList().add(new Turn(actionP1, actionP2));
	assignPoint(actionP1, actionP2);

    }

    public void playGame() {
	askNumberOfTurn();
	for (int i = 0; i < game.getNumberOfTurns(); i++) {
	    playRound();
	}
	interactionConsole.showWinner(game);
	playAgain();
    }

    private void playAgain() {
	boolean player1Choice = interactionConsole.playAgain(player1);
	boolean player2Choice = interactionConsole.playAgain(player2);
	if (continueGame(player1Choice, player2Choice)) {
	    game.resetGame();
	    playGame();
	}

    }

    private boolean continueGame(boolean player1Choice, boolean player2Choice) {
	if (player1Choice) {
	    return player2Choice;
	} else {
	    return false;
	}
    }

    private void assignPoint(Action actionP1, Action actionP2) {
	if (actionP1 == actionP2) {
	    if (actionP1 == Action.COLLABORER) {
		bothCollaborate();
	    } else {
		bothBetray();
	    }
	} else {
	    if (actionP1 == Action.COLLABORER) {
		p1CollaborateP2Betray();
	    } else {
		p1BetrayP2Collaborate();
	    }
	}
    }

    private void p1BetrayP2Collaborate() {
	player1.addScore(Point.T);
	player2.addScore(Point.D);
    }

    private void p1CollaborateP2Betray() {
	player1.addScore(Point.D);
	player2.addScore(Point.T);
    }

    private void bothBetray() {
	player1.addScore(Point.P);
	player2.addScore(Point.P);
    }

    private void bothCollaborate() {
	player1.addScore(Point.C);
	player2.addScore(Point.C);
    }

    public void setInteraction(InteractionConsole interactionConsole) {
	this.interactionConsole = interactionConsole;
    }

    public Player getPlayer1() {
	return player1;
    }

    public Player getPlayer2() {
	return player2;
    }

    public GameInterface getGame() {
	return game;
    }
}
