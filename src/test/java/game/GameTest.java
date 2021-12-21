package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import strategies.Action;

@ExtendWith(MockitoExtension.class)
class GameTest {

    Player p1 = new Player(1);
    Player p2 = new Player(2);
    Game gameTest;
    @Spy
    Player spyP1 = Mockito.spy(p1);
    @Spy
    Player spyP2 = Mockito.spy(p2);;

    @BeforeEach
    void setUp() throws Exception {
	gameTest = new Game();
	gameTest.setP1(spyP1);
	gameTest.setP2(spyP2);
    }

    @Test
    void testPlayGameBothCollaborate1Run() {

	Mockito.doReturn(1).when(spyP1).askNbTurn();
	Mockito.doReturn(Action.COLLABORER).when(spyP1).askAction();
	Mockito.doReturn(false).when(spyP1).playAgain();
	Mockito.doReturn(Action.COLLABORER).when(spyP2).askAction();
	Mockito.doReturn(false).when(spyP2).playAgain();

	gameTest.playGame();

	assertEquals(Action.COLLABORER, gameTest.getTurnList().get(0).getAction(spyP1));
	assertEquals(Action.COLLABORER, gameTest.getTurnList().get(0).getAction(spyP2));
    }

    @Test
    void testPlayGameBothBetray1Run() {

	Mockito.doReturn(1).when(spyP1).askNbTurn();
	Mockito.doReturn(Action.TRAHIR).when(spyP1).askAction();
	Mockito.doReturn(false).when(spyP1).playAgain();
	Mockito.doReturn(Action.TRAHIR).when(spyP2).askAction();
	Mockito.doReturn(false).when(spyP2).playAgain();

	gameTest.playGame();

	assertEquals(Action.TRAHIR, gameTest.getTurnList().get(0).getAction(spyP1));
	assertEquals(Action.TRAHIR, gameTest.getTurnList().get(0).getAction(spyP2));
    }

    @Test
    void testPlayGameCollaborateBetray2Run() {

	Mockito.doReturn(1).when(spyP1).askNbTurn();
	Mockito.doReturn(Action.COLLABORER).when(spyP1).askAction();
	Mockito.doReturn(true).when(spyP1).playAgain();
	Mockito.doReturn(Action.TRAHIR).when(spyP2).askAction();
	Mockito.doReturn(true).when(spyP2).playAgain();
	Mockito.doReturn(1).when(spyP1).askNbTurn();
	Mockito.doReturn(Action.COLLABORER).when(spyP1).askAction();
	Mockito.doReturn(false).when(spyP1).playAgain();
	Mockito.doReturn(Action.TRAHIR).when(spyP2).askAction();
	Mockito.doReturn(false).when(spyP2).playAgain();

	gameTest.playGame();

	assertEquals(Action.COLLABORER, gameTest.getTurnList().get(0).getAction(spyP1));
	assertEquals(Action.TRAHIR, gameTest.getTurnList().get(0).getAction(spyP2));
    }

    @Test
    void testPlayGameBetrayCollaborate2Run() {

	Mockito.doReturn(1).when(spyP1).askNbTurn();
	Mockito.doReturn(Action.COLLABORER).when(spyP1).askAction();
	Mockito.doReturn(true).when(spyP1).playAgain();
	Mockito.doReturn(Action.TRAHIR).when(spyP2).askAction();
	Mockito.doReturn(true).when(spyP2).playAgain();
	Mockito.doReturn(1).when(spyP1).askNbTurn();
	Mockito.doReturn(Action.TRAHIR).when(spyP1).askAction();
	Mockito.doReturn(false).when(spyP1).playAgain();
	Mockito.doReturn(Action.COLLABORER).when(spyP2).askAction();
	Mockito.doReturn(false).when(spyP2).playAgain();

	gameTest.playGame();

	assertEquals(Action.TRAHIR, gameTest.getTurnList().get(0).getAction(spyP1));
	assertEquals(Action.COLLABORER, gameTest.getTurnList().get(0).getAction(spyP2));
    }

}
