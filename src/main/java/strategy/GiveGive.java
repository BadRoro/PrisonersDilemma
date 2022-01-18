package strategy;

public class GiveGive implements Strategy {

    public Action askAction(Action lastAction) {
	if (lastAction != null) {
	    return lastAction;
	} else {
	    return Action.COLLABORER;
	}
    }
}
