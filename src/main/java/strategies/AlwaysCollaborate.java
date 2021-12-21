package strategies;

public class AlwaysCollaborate implements Strategy {

    public Action askAction(Action lastAction) {
	return Action.COLLABORER;
    }

}
