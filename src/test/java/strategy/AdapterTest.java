package strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import strategies.BetrayStrategy;
import strategies.CooperateStrategy;

class AdapterTest {
    Strategy test;

    @Test
    void testAskActionCollaborateLastActionTrahir() {
	test = new Adapter(new CooperateStrategy());
	assertEquals(Action.COLLABORER, test.askAction(Action.TRAHIR));
    }

    @Test
    void testAskActionCollaborateLastActionCollaborer() {
	test = new Adapter(new CooperateStrategy());
	assertEquals(Action.COLLABORER, test.askAction(Action.COLLABORER));
    }

    @Test
    void testAskActionTrahirLastActionTrahir() {
	test = new Adapter(new BetrayStrategy());
	assertEquals(Action.TRAHIR, test.askAction(Action.TRAHIR));
    }

    @Test
    void testAskActionTrahirLastActionCollaborer() {
	test = new Adapter(new BetrayStrategy());
	assertEquals(Action.TRAHIR, test.askAction(Action.COLLABORER));
    }
}
