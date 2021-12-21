package game;

import strategies.Action;

public class Turn {
    // Définition des constantes de scores
    static final int C = 3;
    static final int D = 0;
    static final int P = 1;
    static final int T = 5;

    private Player p1;
    private Player p2;

    private Action actionP1;
    private Action actionP2;

    public Turn(Player p1, Player p2) {
	this.p1 = p1;
	this.p2 = p2;
    }

    public void resultRound() {
	actionP1 = p1.askAction();
	actionP2 = p2.askAction();

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

    public Action getAction(Player player) {
	if (player.getNumber() == p1.getNumber()) {
	    return actionP1;
	} else {
	    return actionP2;
	}
    }

    public Action getOpponentLastAction(Player player) {
	if (actionP1 != null && actionP2 != null) {
	    if (player.getNumber() == this.p1.getNumber()) {
		return actionP2;
	    } else {
		return actionP1;
	    }
	} else {
	    return null;
	}
    }

    public void setActionP1(Action actionP1) {
	this.actionP1 = actionP1;
    }

    public void setActionP2(Action actionP2) {
	this.actionP2 = actionP2;
    }

    private void p1BetrayP2Collaborate() {
	p1.addTurn(this);
	p2.addTurn(this);
	p1.addScore(T);
	p2.addScore(D);
    }

    private void p1CollaborateP2Betray() {
	p1.addTurn(this);
	p2.addTurn(this);
	p1.addScore(D);
	p2.addScore(T);
    }

    private void bothBetray() {
	p1.addTurn(this);
	p2.addTurn(this);
	p1.addScore(P);
	p2.addScore(P);
    }

    private void bothCollaborate() {
	p1.addTurn(this);
	p2.addTurn(this);
	p1.addScore(C);
	p2.addScore(C);
    }

}
