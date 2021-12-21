package game;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Player p1;
    private Player p2;
    private List<Turn> turnList;

    public Game() {
	this.p1 = new Player(1);
	this.p2 = new Player(2);
	turnList = new ArrayList<>();
    }

    public void playGame() {
	boolean playAgainP1;
	boolean playAgainP2;
	int n;
	do {
	    n = this.p1.askNbTurn();
	    playMatch(n);

	    playAgainP1 = this.p1.playAgain();
	    playAgainP2 = this.p2.playAgain();

	    this.p1.reset();
	    this.p2.reset();

	} while (continueGame(playAgainP1, playAgainP2));
	System.out.println("A bientot!");
    }

    public List<Turn> getTurnList() {
	return turnList;
    }

    public void setP1(Player p1) {
	this.p1 = p1;
    }

    public void setP2(Player p2) {
	this.p2 = p2;
    }

    private void playMatch(int n) {
	for (int i = 0; i < n; i++) {
	    Turn currentTurn = new Turn(p1, p2);
	    currentTurn.resultRound();
	    turnList.add(currentTurn);
	}
	String result = getWinner();
	System.out.println(result); // NOPMD
    }

    private String getWinner() {

	if (p1.getScore() > p2.getScore()) {
	    return "Gagnant Joueur 1 avec " + p1.getScore() + " contre " + p2.getScore();
	} else if (p1.getScore() < p2.getScore()) {
	    return "Gagnant Joueur 2 avec " + p2.getScore() + " contre " + p1.getScore();
	} else {
	    return "Egalité avec " + p1.getScore();
	}
    }

    private boolean continueGame(boolean player1Choice, boolean player2Choice) {
	if (player1Choice) {
	    return player2Choice; // NOPMD by Utilisateur3 on 22/11/2021 23:20
	} else {
	    return false;
	}
    }

    public static void main(String[] args) {
	Game game = new Game();
	game.playGame();
    }

}
