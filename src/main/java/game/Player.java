package game;

import java.util.ArrayList;
import java.util.List;

import strategies.Action;
import strategies.GiveGive;
import strategies.Strategy;

public class Player {
    private int number;
    private int scoreTotal = 0;
    private List<Turn> turnList;
    private boolean isPlaying = true;
    private Strategy myStrategy = new GiveGive();
    Interaction interactionPlayer = new Interaction(this);

    public Player(int number) {
	this.number = number;
	this.scoreTotal = 0;
	this.turnList = new ArrayList<>();
    }

    public Action askAction() {
	if (isPlaying) {
	    printInformation();
	    return interactionPlayer.askAction();
	} else {
	    return this.myStrategy.askAction(getOpponentLastAction());
	}
    }

    public void printInformation() {
	String lastAction = opponenentLastActionToString();
	System.out.println("[Joueur " + number + "] : Votre scrore est " + getScore()
		+ " la derniere action de l'adversaire est : " + lastAction
		+ "\nQue voulez vous faire? 1 - Collaborer / 2 - Trahir / 3 - Abandonner");
    }

    private String opponenentLastActionToString() {
	String lastAction;
	if (turnList.isEmpty()) {
	    lastAction = "Pas d'action precedente";
	} else {
	    lastAction = getOpponentLastAction().toString();
	}
	return lastAction;
    }

    public void addScore(int points) {
	this.scoreTotal += points;
    }

    public void addTurn(Turn lastTurn) {
	turnList.add(lastTurn);
    }

    public Turn getLastTurn() {
	if (!turnList.isEmpty()) {
	    return turnList.get(turnList.size() - 1);
	} else {
	    return null;
	}
    }

    public Action getOpponentLastAction() {
	if (this.getLastTurn() != null) {
	    return this.getLastTurn().getOpponentLastAction(this);
	} else {
	    return null;
	}
    }

    public int getNumber() {
	return number;
    }

    public int getScore() {
	return scoreTotal;
    }

    public boolean getIsPlaying() {
	return isPlaying;
    }

    public Strategy getMyStrategy() {
	return myStrategy;
    }

    public void leave() {
	isPlaying = false;
    }

    public void reset() {
	this.scoreTotal = 0;
	this.myStrategy = null;
	this.isPlaying = true;
	this.turnList.clear();
    }

    public void setMyStrategy(Strategy myStrategy) {
	this.myStrategy = myStrategy;
    }

    public int askNbTurn() {
	return interactionPlayer.askNbTurn();
    }

    public boolean playAgain() {
	return interactionPlayer.playAgain();
    }
}
