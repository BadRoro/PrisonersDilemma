package domain;

import strategy.Strategy;

public class Player {
    private int number;
    private int scoreTotal = 0;
    private boolean isPlaying = true;
    private Strategy myStrategy = null;

    public Player(int number) {
	this.number = number;
	this.scoreTotal = 0;
    }

    public void addScore(int scoreOfTurn) {
	this.scoreTotal += scoreOfTurn;
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
    }

    public void setMyStrategy(Strategy myStrategy) {
	this.myStrategy = myStrategy;
    }
}
