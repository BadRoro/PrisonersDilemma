package system;

import java.util.Scanner;

import domain.GameInterface;
import domain.Player;
import strategies.BetrayStrategy;
import strategies.CooperateStrategy;
import strategy.Action;
import strategy.Adapter;
import strategy.AlwaysBetray;
import strategy.AlwaysCollaborate;
import strategy.GiveGive;
import strategy.Strategy;

public class InteractionConsole {

    private Scanner keyboardScanner;

    public InteractionConsole() {
	this.keyboardScanner = new Scanner(System.in);
    }

    public Action askAction(GameInterface game, Player player) {

	System.out.println("[Joueur " + player.getNumber() + "] : Votre scrore est " + player.getScore()
		+ " la derniere action de l'adversaire est : " + game.getOpponentLastActionToString(player)
		+ "\nQue voulez vous faire? 1 - Collaborer / 2 - Trahir / 3 - Abandonner");

	int action = readInt();

	switch (action) {
	case 1:
	    return Action.COLLABORER;
	case 2:
	    return Action.TRAHIR;
	case 3:
	    player.setMyStrategy(chooseStrategy(player));
	    player.leave();
	    return player.getMyStrategy().askAction(game.getOpponentLastAction(player));
	default:
	    System.out.println("probleme de choix");
	    return null;
	}
    }

    int readInt() {
	if (keyboardScanner.hasNextInt()) {
	    return keyboardScanner.nextInt();
	} else {
	    System.out.println("Veuillez saisir un entier");
	    return -1;
	}
    }

    private Strategy chooseStrategy(Player player) {
	System.out.println("[Joueur " + player.getNumber() + "] : Quel strategie de remplacement voulez vous?");
	System.out.println(
		"1 - Donnant-Donnant\n2 - Toujours trahir\n3 - Toujours collaborer\n4 - Autres strategies importees");
	int choose = readInt();
	switch (choose) {
	case 1:
	    System.out.println("Vous avez choisi Donnant-Donnant\n");
	    return new GiveGive();
	case 2:
	    System.out.println("Vous avez choisi de toujours trahir\n");
	    return new AlwaysBetray();
	case 3:
	    System.out.println("Vous avez choisi de toujours collabore\n");
	    return new AlwaysCollaborate();
	case 4:
	    return chooseOtherStrategy(player);
	default:
	    return new GiveGive();
	}
    }

    private Strategy chooseOtherStrategy(Player player) {

	System.out.println(
		"Les nouvelles strategies importees sont :\n1 - Donnant-Donnant (Not Yet Implemented)\n2 - Toujours trahir\n3 - Toujours collaborer");
	int choose = readInt();
	switch (choose) {
	case 2:
	    System.out.println("Vous avez choisi de toujours trahir Import\n");
	    return new Adapter(new BetrayStrategy());
	case 3:
	    System.out.println("Vous avez choisi de toujours collabore Import\n");
	    return new Adapter(new CooperateStrategy());
	default:
	    return new GiveGive();
	}
    }

    public boolean playAgain(Player player) {
	System.out.println("[Joueur " + player.getNumber() + "] : Voulez vous rejouer une partie ? 1 - Oui / 2 - Non");
	int playAgain = readInt();
	return (playAgain == 1);
    }

    public int askNbTurn(Player player) {
	System.out.println("[Joueur " + player.getNumber() + "] : Combien de tour souhaitez vous faire?");
	return readInt();
    }

    public void showWinner(GameInterface game) {

	if (game.getP1().getScore() > game.getP2().getScore()) {
	    System.out
		    .println("Gagnant Joueur 1 avec " + game.getP1().getScore() + " contre " + game.getP2().getScore());
	} else if (game.getP1().getScore() < game.getP2().getScore()) {
	    System.out
		    .println("Gagnant Joueur 2 avec " + game.getP2().getScore() + " contre " + game.getP1().getScore());
	} else {
	    System.out.println("Egalité avec " + game.getP1().getScore());
	}
    }
}