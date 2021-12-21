package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import strategies.Action;

class InteractionTest {

    Player testPlayer;
    @Spy
    Player spyPlayer;
    Interaction testInteraction;
    @Spy
    Interaction spyInteraction;

    @BeforeEach
    void setUp() {
	testPlayer = new Player(1);
	spyPlayer = Mockito.spy(testPlayer);
	testInteraction = new Interaction(spyPlayer);
	spyInteraction = Mockito.spy(testInteraction);
    }

    @Test
    void testAskActionCollaborate() {
	Mockito.doReturn(1).when(spyInteraction).readInt();
	assertEquals(Action.COLLABORER, spyInteraction.askAction());
    }

    @Test
    void testAskActionBetray() {
	Mockito.doReturn(2).when(spyInteraction).readInt();
	assertEquals(Action.TRAHIR, spyInteraction.askAction());
    }

    @Test
    void testPlayAgainTrue() {
	Mockito.doReturn(1).when(spyInteraction).readInt();
	Mockito.doReturn(1).when(spyPlayer).getNumber();
	assertEquals(true, spyInteraction.playAgain());
    }

    @Test
    void testPlayAgainFalse() {
	Mockito.doReturn(2).when(spyInteraction).readInt();
	Mockito.doReturn(1).when(spyPlayer).getNumber();
	assertEquals(false, spyInteraction.playAgain());
    }

    @Test
    void testAskActionLeaveGiveGive() {
	Mockito.doReturn(3, 1).when(spyInteraction).readInt();
	assertEquals(Action.COLLABORER, spyInteraction.askAction());
	// assertTrue(!spyInteraction.getPlayer().getIsPlaying());
    }

    @Test
    void testAskActionLeaveAlwaysCollaborate() {
	Mockito.doReturn(3, 3).when(spyInteraction).readInt();
	assertEquals(Action.COLLABORER, spyInteraction.askAction());
    }

    @Test
    void testAskActionLeaveAlwaysBetray() {
	Mockito.doReturn(3, 2).when(spyInteraction).readInt();
	assertEquals(Action.TRAHIR, spyInteraction.askAction());
    }

    @Test
    void testAskActionLeaveOtherCollaborate() {
	Mockito.doReturn(3, 4, 3).when(spyInteraction).readInt();
	assertEquals(Action.COLLABORER, spyInteraction.askAction());
    }

    @Test
    void testAskActionLeaveOtherBetray() {
	Mockito.doReturn(3, 4, 2).when(spyInteraction).readInt();
	assertEquals(Action.TRAHIR, spyInteraction.askAction());
    }

    @Test
    void testAskNbTurn() {
	Mockito.doReturn(1).when(spyInteraction).readInt();
	Mockito.doReturn(1).when(spyPlayer).getNumber();
	assertEquals(1, spyInteraction.askNbTurn());
    }
}
