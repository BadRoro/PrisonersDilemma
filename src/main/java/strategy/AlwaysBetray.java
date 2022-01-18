package strategy;

public class AlwaysBetray implements Strategy {

    public Action askAction(Action lastAction) {
	return Action.TRAHIR;
    }
}
