package domain;

import strategy.Action;

public class Turn {

    private Action actionP1;
    private Action actionP2;

    public Turn(Action actionP1, Action actionP2) {
	this.actionP1 = actionP1;
	this.actionP2 = actionP2;
    }

    public Action getActionP1() {
	return actionP1;
    }

    public Action getActionP2() {
	return actionP2;
    }

}
