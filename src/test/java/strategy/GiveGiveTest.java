package strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import strategy.Action;
import strategy.GiveGive;
import strategy.Strategy;

class GiveGiveTest {

    private Strategy test;

    @BeforeEach
    void setUp() {
	test = new GiveGive();
    }

    @Test
    void testAskActionFirstRound() {
	assertEquals(Action.COLLABORER, test.askAction(null));
    }

    @Test
    void testAskActionTrahir() {
	assertEquals(Action.TRAHIR, test.askAction(Action.TRAHIR));
    }

    @Test
    void testAskActionCollaborate() {
	assertEquals(Action.COLLABORER, test.askAction(Action.COLLABORER));
    }
}
