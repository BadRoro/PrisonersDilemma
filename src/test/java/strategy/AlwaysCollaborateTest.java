package strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import strategy.Action;
import strategy.AlwaysCollaborate;
import strategy.Strategy;

class AlwaysCollaborateTest {

    Strategy test;

    @BeforeEach
    void setUp() throws Exception {
	test = new AlwaysCollaborate();
    }

    @Test
    void testAskActionTrahir() {
	assertEquals(Action.COLLABORER, test.askAction(Action.TRAHIR));
    }

    @Test
    void testAskActionCollaborer() {
	assertEquals(Action.COLLABORER, test.askAction(Action.COLLABORER));
    }
}
