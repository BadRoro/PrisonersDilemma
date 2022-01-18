package strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import strategy.Action;
import strategy.AlwaysBetray;

class AlwaysBetrayTest {

    private AlwaysBetray test;

    @BeforeEach
    void setUp() throws Exception {
	test = new AlwaysBetray();
    }

    @Test
    void testAskActionTrahir() {
	assertEquals(Action.TRAHIR, test.askAction(Action.TRAHIR));
    }

    @Test
    void testAskActionCollaborer() {
	assertEquals(Action.TRAHIR, test.askAction(Action.COLLABORER));
    }
}
