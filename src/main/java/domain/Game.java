package domain;

import java.util.ArrayList;
import java.util.List;

import strategy.Action;

public class Game implements GameInterface {

    private Player p1;
    private Player p2;
    private List<Turn> turnList;
    private int numberOfTurns;

    public Game() {
	this.p1 = new Player(1);
	this.p2 = new Player(2);
	turnList = new ArrayList<>();
    }

    @Override
    public List<Turn> getTurnList() {
	return turnList;
    }

    @Override
    public int getNumberOfTurns() {
	return numberOfTurns;
    }

    @Override
    public void setNumberOfTurns(int numberOfTurns) {
	this.numberOfTurns = numberOfTurns;
    }

    @Override
    public Player getP1() {
	return p1;
    }

    @Override
    public Player getP2() {
	return p2;
    }

    @Override
    public Action getOpponentLastAction(Player player) {
	int i = turnList.size();
	if (player == p1 && i != 0) {
	    return turnList.get(i - 1).getActionP2();
	} else if (player == p2 && i != 0) {
	    return turnList.get(i - 1).getActionP1();
	} else {
	    return null;
	}
    }

    @Override
    public String getOpponentLastActionToString(Player player) {
	String lastAction;
	if (turnList.isEmpty()) {
	    lastAction = "Pas d'action precedente";
	} else {
	    lastAction = getOpponentLastAction(player).toString();
	}
	return lastAction;
    }

    public void resetGame() {
	turnList = new ArrayList<>();
	p1.reset();
	p2.reset();
    }
}
