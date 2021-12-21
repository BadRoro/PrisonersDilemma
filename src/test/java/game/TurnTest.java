package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import strategies.Action;

@ExtendWith(MockitoExtension.class)

class TurnTest {
    private Player player1;
    private Player player2;

    private Player playerTest1;

    private Player playerTest2;
    private Turn turnTest;

    @BeforeEach
    void setUp() {
	player1 = new Player(1);
	player2 = new Player(2);
	playerTest1 = Mockito.spy(player1);
	playerTest2 = Mockito.spy(player2);
	turnTest = new Turn(playerTest1, playerTest2);
	turnTest.setActionP1(Action.COLLABORER);
	turnTest.setActionP2(Action.TRAHIR);
    }

    @Test
    void testResultRoundBothCollaborate() {
	Mockito.doReturn(Action.COLLABORER).when(playerTest1).askAction();
	Mockito.doReturn(Action.COLLABORER).when(playerTest2).askAction();
	turnTest.resultRound();
	assertEquals(Turn.C, playerTest1.getScore());
	assertEquals(Turn.C, playerTest2.getScore());
	assertEquals(Action.COLLABORER, playerTest1.getOpponentLastAction());
	assertEquals(Action.COLLABORER, playerTest2.getOpponentLastAction());
    }

    @Test
    void testResultRoundBothBetray() {
	Mockito.doReturn(Action.TRAHIR).when(playerTest1).askAction();
	Mockito.doReturn(Action.TRAHIR).when(playerTest2).askAction();
	turnTest.resultRound();
	assertEquals(Turn.P, playerTest1.getScore());
	assertEquals(Turn.P, playerTest2.getScore());
	assertEquals(Action.TRAHIR, playerTest1.getOpponentLastAction());
	assertEquals(Action.TRAHIR, playerTest2.getOpponentLastAction());
    }

    @Test
    void testResultRoundP1CollaborateP2Betray() {
	Mockito.doReturn(Action.COLLABORER).when(playerTest1).askAction();
	Mockito.doReturn(Action.TRAHIR).when(playerTest2).askAction();
	turnTest.resultRound();
	assertEquals(Turn.D, playerTest1.getScore());
	assertEquals(Turn.T, playerTest2.getScore());
	assertEquals(Action.TRAHIR, playerTest1.getOpponentLastAction());
	assertEquals(Action.COLLABORER, playerTest2.getOpponentLastAction());
    }

    @Test
    void testResultRoundP1BetrayP2Collaborate() {
	Mockito.doReturn(Action.TRAHIR).when(playerTest1).askAction();
	Mockito.doReturn(Action.COLLABORER).when(playerTest2).askAction();
	turnTest.resultRound();
	assertEquals(Turn.T, playerTest1.getScore());
	assertEquals(Turn.D, playerTest2.getScore());
	assertEquals(Action.COLLABORER, playerTest1.getOpponentLastAction());
	assertEquals(Action.TRAHIR, playerTest2.getOpponentLastAction());
    }

    @Test
    void testGetActionP1() {
	assertEquals(Action.COLLABORER, turnTest.getAction(playerTest1));
    }

    @Test
    void testGetActionP2() {
	assertEquals(Action.TRAHIR, turnTest.getAction(playerTest2));
    }

}
