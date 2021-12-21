package game;

import java.util.Scanner;

import strategies.Action;
import strategies.Adapter;
import strategies.AlwaysBetray;
import strategies.AlwaysCollaborate;
import strategies.BetrayStrategy;
import strategies.CooperateStrategy;
import strategies.GiveGive;
import strategies.Strategy;

public class Interaction {

    private Scanner keyboardScanner;
    private Player player;

    public Interaction(Player player) {
	this.player = player;
	this.keyboardScanner = new Scanner(System.in);
    }

    public Action askAction() {
	int action = readInt();

	switch (action) {
	case 1:
	    return Action.COLLABORER;
	case 2:
	    return Action.TRAHIR;
	case 3:
	    player.setMyStrategy(chooseStrategy());
	    player.leave();
	    return this.player.getMyStrategy().askAction(player.getOpponentLastAction());
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

    private Strategy chooseStrategy() {
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
	    return chooseOtherStrategy();
	default:
	    return new GiveGive();
	}
    }

    private Strategy chooseOtherStrategy() {

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

    public boolean playAgain() {
	System.out.println("[Joueur " + player.getNumber() + "] : Voulez vous rejouer une partie ? 1 - Oui / 2 - Non");
	int playAgain = readInt();
	return (playAgain == 1);
    }

    public int askNbTurn() {
	System.out.println("[Joueur " + player.getNumber() + "] : Combien de tour souhaitez vous faire?");
	return readInt();
    }
}