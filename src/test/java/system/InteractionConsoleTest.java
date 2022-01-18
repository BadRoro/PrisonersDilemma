package system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import domain.Game;
import domain.GameInterface;
import domain.Player;
import strategy.Action;

class InteractionConsoleTest {

    Player testPlayer;
    @Spy
    Player spyPlayer;
    InteractionConsole testInteraction;
    @Spy
    InteractionConsole spyInteraction;
    GameInterface game = new Game();

    @BeforeEach
    void setUp() {
	testPlayer = new Player(1);
	spyPlayer = Mockito.spy(testPlayer);
	testInteraction = new InteractionConsole();
	spyInteraction = Mockito.spy(testInteraction);
    }

    @Test
    void testAskActionCollaborate() {
	Mockito.doReturn(1).when(spyInteraction).readInt();
	assertEquals(Action.COLLABORER, spyInteraction.askAction(game, spyPlayer));
    }

    @Test
    void testAskActionBetray() {
	Mockito.doReturn(2).when(spyInteraction).readInt();
	assertEquals(Action.TRAHIR, spyInteraction.askAction(game, spyPlayer));
    }

    @Test
    void testPlayAgainTrue() {
	Mockito.doReturn(1).when(spyInteraction).readInt();
	Mockito.doReturn(1).when(spyPlayer).getNumber();
	assertEquals(true, spyInteraction.playAgain(spyPlayer));
    }

    @Test
    void testPlayAgainFalse() {
	Mockito.doReturn(2).when(spyInteraction).readInt();
	Mockito.doReturn(1).when(spyPlayer).getNumber();
	assertEquals(false, spyInteraction.playAgain(spyPlayer));
    }

    @Test
    void testAskActionLeaveGiveGive() {
	Mockito.doReturn(3, 1).when(spyInteraction).readInt();
	assertEquals(Action.COLLABORER, spyInteraction.askAction(game, spyPlayer));
	// assertTrue(!spyInteraction.getPlayer().getIsPlaying());
    }

    @Test
    void testAskActionLeaveAlwaysCollaborate() {
	Mockito.doReturn(3, 3).when(spyInteraction).readInt();
	assertEquals(Action.COLLABORER, spyInteraction.askAction(game, spyPlayer));
    }

    @Test
    void testAskActionLeaveAlwaysBetray() {
	Mockito.doReturn(3, 2).when(spyInteraction).readInt();
	assertEquals(Action.TRAHIR, spyInteraction.askAction(game, spyPlayer));
    }

    @Test
    void testAskActionLeaveOtherCollaborate() {
	Mockito.doReturn(3, 4, 3).when(spyInteraction).readInt();
	assertEquals(Action.COLLABORER, spyInteraction.askAction(game, spyPlayer));
    }

    @Test
    void testAskActionLeaveOtherBetray() {
	Mockito.doReturn(3, 4, 2).when(spyInteraction).readInt();
	assertEquals(Action.TRAHIR, spyInteraction.askAction(game, spyPlayer));
    }

    @Test
    void testAskNbTurn() {
	Mockito.doReturn(1).when(spyInteraction).readInt();
	Mockito.doReturn(1).when(spyPlayer).getNumber();
	assertEquals(1, spyInteraction.askNbTurn(spyPlayer));
    }
}
