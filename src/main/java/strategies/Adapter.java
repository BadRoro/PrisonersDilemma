package strategies;

import object.Decision;
import object.Player;

public class Adapter implements Strategy {

    StrategyHediAndPierre adapterStrategy;
    Player player = new Player();

    public Adapter(StrategyHediAndPierre strategy) {
	adapterStrategy = strategy;
    }

    @Override
    public Action askAction(Action lastAction) {
	adapterStrategy.action(player);
	return adapterAction();
    }

    private Action adapterAction() {
	if (this.player.getCurrentDecision().equals(Decision.COOPERATE)) {
	    return Action.COLLABORER;
	} else {
	    return Action.TRAHIR;
	}
    }
}
