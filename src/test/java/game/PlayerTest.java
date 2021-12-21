package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import strategies.Action;

class PlayerTest {
    @Spy
    Player spy;
    Player test;
    int n = 1;
    Interaction testInteraction;
    @Spy
    Interaction spyInteraction;

    @Mock
    Turn turnMock;

    @BeforeEach
    void setUp() throws Exception {
	test = new Player(n);
	spy = Mockito.spy(test);
	testInteraction = new Interaction(spy);
	spyInteraction = Mockito.spy(testInteraction);
	spy.interactionPlayer = spyInteraction;
    }

    @Test
    void testPlayAgainTrue() {
	Mockito.doReturn(1).when(spyInteraction).readInt();
	assertEquals(true, spy.playAgain());
    }

    @Test
    void testPlayAgainFalse() {
	Mockito.doReturn(2).when(spyInteraction).readInt();
	assertEquals(false, spy.playAgain());
    }

    @Test
    void testAskActionCollaborate() {
	Mockito.doReturn(1).when(spyInteraction).readInt();
	assertEquals(Action.COLLABORER, spy.askAction());
    }

    @Test
    void testAskActionBetray() {
	Mockito.doReturn(2).when(spyInteraction).readInt();
	assertEquals(Action.TRAHIR, spy.askAction());
    }

    @Test
    void testAskActionLeaveGiveGive() {
	Mockito.doReturn(3, 1).when(spyInteraction).readInt();
	assertEquals(Action.COLLABORER, spy.askAction());
	assertTrue(!spy.getIsPlaying());
    }

    @Test
    void testAskActionLeaveAlwaysCollaborate() {
	Mockito.doReturn(3, 3).when(spyInteraction).readInt();
	assertEquals(Action.COLLABORER, spy.askAction());
	assertTrue(!spy.getIsPlaying());
    }

    @Test
    void testAskActionLeaveAlwaysBetray() {
	Mockito.doReturn(3, 2).when(spyInteraction).readInt();
	assertEquals(Action.TRAHIR, spy.askAction());
	assertTrue(!spy.getIsPlaying());
    }

    @Test
    void testAskActionIsNotPlaying() {
	test.leave();
	assertEquals(Action.COLLABORER, test.askAction());
	assertTrue(!test.getIsPlaying());
    }

    @Test
    void testAskNbTurn() {
	Mockito.doReturn(3).when(spyInteraction).readInt();
	assertEquals(3, spy.askNbTurn());
    }

    @Test
    void testAddScore() {
	assertEquals(0, test.getScore());
	test.addScore(5);
	assertEquals(5, test.getScore());
    }

    @Test
    void testAddTurn() {
	assertNull(test.getLastTurn());
	test.addTurn(turnMock);
	assertEquals(turnMock, test.getLastTurn());
    }

    @Test
    void testGetNumber() {
	assertEquals(n, test.getNumber());
    }

    @Test
    void testLeave() {
	assertTrue(test.getIsPlaying());
	test.leave();
	assertTrue(!test.getIsPlaying());
    }

    @Test
    void testReset() {
	test.reset();
	assertEquals(0, test.getScore());
	assertEquals(true, test.getIsPlaying());
	assertEquals(null, test.getOpponentLastAction());
    }

}
