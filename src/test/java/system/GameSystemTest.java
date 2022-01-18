package system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import domain.GameInterface;
import domain.Player;
import domain.Point;
import strategies.BetrayStrategy;
import strategies.CooperateStrategy;
import strategy.Action;
import strategy.Adapter;
import strategy.AlwaysBetray;
import strategy.AlwaysCollaborate;
import strategy.GiveGive;

class GameSystemTest {

    GameSystem gameTest;
    InteractionConsole interaction = new InteractionConsole();
    @Spy
    InteractionConsole interactionMock = Mockito.spy(interaction);

    @BeforeEach
    void setUp() throws Exception {
	gameTest = new GameSystem();
	gameTest.setInteraction(interactionMock);
    }

    @Test
    void testPlayGameBothCollaborate1Run() {
	Player player1 = gameTest.getPlayer1();
	Player player2 = gameTest.getPlayer2();
	GameInterface game = gameTest.getGame();
	Mockito.doReturn(1).when(interactionMock).askNbTurn(player1);
	Mockito.doReturn(Action.COLLABORER).when(interactionMock).askAction(game, player1);
	Mockito.doReturn(false).when(interactionMock).playAgain(player1);
	Mockito.doReturn(Action.COLLABORER).when(interactionMock).askAction(game, player2);
	Mockito.doReturn(false).when(interactionMock).playAgain(player2);

	gameTest.playGame();

	assertEquals(Action.COLLABORER, game.getTurnList().get(0).getActionP1());
	assertEquals(Action.COLLABORER, game.getTurnList().get(0).getActionP2());
	assertEquals(Point.C, player1.getScore());
	assertEquals(Point.C, player2.getScore());
    }

    @Test
    void testPlayGameBothBetray1Run() {
	Player player1 = gameTest.getPlayer1();
	Player player2 = gameTest.getPlayer2();
	GameInterface game = gameTest.getGame();
	Mockito.doReturn(1).when(interactionMock).askNbTurn(player1);
	Mockito.doReturn(Action.TRAHIR).when(interactionMock).askAction(game, player1);
	Mockito.doReturn(false).when(interactionMock).playAgain(player1);
	Mockito.doReturn(Action.TRAHIR).when(interactionMock).askAction(game, player2);
	Mockito.doReturn(false).when(interactionMock).playAgain(player2);

	gameTest.playGame();

	assertEquals(Action.TRAHIR, game.getTurnList().get(0).getActionP1());
	assertEquals(Action.TRAHIR, game.getTurnList().get(0).getActionP2());
	assertEquals(Point.P, player1.getScore());
	assertEquals(Point.P, player2.getScore());
    }

    @Test
    void testPlayGameCollaborateBetray2Run() {
	Player player1 = gameTest.getPlayer1();
	Player player2 = gameTest.getPlayer2();
	GameInterface game = gameTest.getGame();
	Mockito.doReturn(1).when(interactionMock).askNbTurn(player1);
	Mockito.doReturn(Action.COLLABORER).when(interactionMock).askAction(game, player1);
	Mockito.doReturn(true).when(interactionMock).playAgain(player1);
	Mockito.doReturn(Action.TRAHIR).when(interactionMock).askAction(game, player2);
	Mockito.doReturn(true).when(interactionMock).playAgain(player2);
	Mockito.doReturn(1).when(interactionMock).askNbTurn(player1);
	Mockito.doReturn(Action.COLLABORER).when(interactionMock).askAction(game, player1);
	Mockito.doReturn(true).when(interactionMock).playAgain(player1);
	Mockito.doReturn(Action.TRAHIR).when(interactionMock).askAction(game, player2);
	Mockito.doReturn(false).when(interactionMock).playAgain(player2);

	gameTest.playGame();

	assertEquals(Action.COLLABORER, game.getTurnList().get(0).getActionP1());
	assertEquals(Action.TRAHIR, game.getTurnList().get(0).getActionP2());
	assertEquals(Point.D, player1.getScore());
	assertEquals(Point.T, player2.getScore());
    }

    @Test
    void testPlayGameBetrayCollaborate2Run() {
	Player player1 = gameTest.getPlayer1();
	Player player2 = gameTest.getPlayer2();
	GameInterface game = gameTest.getGame();
	Mockito.doReturn(1).when(interactionMock).askNbTurn(player1);
	Mockito.doReturn(Action.TRAHIR).when(interactionMock).askAction(game, player1);
	Mockito.doReturn(true).when(interactionMock).playAgain(player1);
	Mockito.doReturn(Action.COLLABORER).when(interactionMock).askAction(game, player2);
	Mockito.doReturn(true).when(interactionMock).playAgain(player2);
	Mockito.doReturn(1).when(interactionMock).askNbTurn(player1);
	Mockito.doReturn(Action.TRAHIR).when(interactionMock).askAction(game, player1);
	Mockito.doReturn(false).when(interactionMock).playAgain(player1);
	Mockito.doReturn(Action.COLLABORER).when(interactionMock).askAction(game, player2);
	Mockito.doReturn(true).when(interactionMock).playAgain(player2);

	gameTest.playGame();

	assertEquals(Action.TRAHIR, game.getTurnList().get(0).getActionP1());
	assertEquals(Action.COLLABORER, game.getTurnList().get(0).getActionP2());
	assertEquals(Point.T, player1.getScore());
	assertEquals(Point.D, player2.getScore());
    }

    @Test
    void testPlayGameStrategieCollaborate1Run() {
	Player player1 = gameTest.getPlayer1();
	Player player2 = gameTest.getPlayer2();
	player1.setMyStrategy(new Adapter(new CooperateStrategy()));
	player2.setMyStrategy(new AlwaysCollaborate());
	player1.leave();
	player2.leave();
	GameInterface game = gameTest.getGame();
	Mockito.doReturn(5).when(interactionMock).askNbTurn(player1);
	Mockito.doReturn(false).when(interactionMock).playAgain(player1);
	Mockito.doReturn(false).when(interactionMock).playAgain(player2);

	gameTest.playGame();

	assertEquals(Action.COLLABORER, game.getTurnList().get(0).getActionP1());
	assertEquals(Action.COLLABORER, game.getTurnList().get(0).getActionP2());
	assertEquals(Action.COLLABORER, game.getTurnList().get(4).getActionP1());
	assertEquals(Action.COLLABORER, game.getTurnList().get(4).getActionP2());
	assertEquals(player2.getScore(), player1.getScore());
    }

    @Test
    void testPlayGameStrategieBetray1Run() {
	Player player1 = gameTest.getPlayer1();
	Player player2 = gameTest.getPlayer2();
	player1.setMyStrategy(new Adapter(new BetrayStrategy()));
	player2.setMyStrategy(new AlwaysBetray());
	player1.leave();
	player2.leave();
	GameInterface game = gameTest.getGame();
	Mockito.doReturn(5).when(interactionMock).askNbTurn(player1);
	Mockito.doReturn(false).when(interactionMock).playAgain(player1);
	Mockito.doReturn(false).when(interactionMock).playAgain(player2);

	gameTest.playGame();

	assertEquals(Action.TRAHIR, game.getTurnList().get(0).getActionP1());
	assertEquals(Action.TRAHIR, game.getTurnList().get(0).getActionP2());
	assertEquals(Action.TRAHIR, game.getTurnList().get(4).getActionP1());
	assertEquals(Action.TRAHIR, game.getTurnList().get(4).getActionP2());
	assertEquals(player2.getScore(), player1.getScore());
    }

    @Test
    void testPlayGameStrategieBetrayGiveGive1Run() {
	Player player1 = gameTest.getPlayer1();
	Player player2 = gameTest.getPlayer2();
	player1.setMyStrategy(new Adapter(new BetrayStrategy()));
	player2.setMyStrategy(new GiveGive());
	player1.leave();
	player2.leave();
	GameInterface game = gameTest.getGame();
	Mockito.doReturn(5).when(interactionMock).askNbTurn(player1);
	Mockito.doReturn(false).when(interactionMock).playAgain(player1);
	Mockito.doReturn(false).when(interactionMock).playAgain(player2);

	gameTest.playGame();

	assertEquals(Action.TRAHIR, game.getTurnList().get(0).getActionP1());
	assertEquals(Action.COLLABORER, game.getTurnList().get(0).getActionP2());
	assertEquals(Action.TRAHIR, game.getTurnList().get(4).getActionP1());
	assertEquals(Action.TRAHIR, game.getTurnList().get(4).getActionP2());
	assertEquals((Point.T + 4 * Point.P), player1.getScore());
	assertEquals((4 * Point.P), player2.getScore());
    }

    @Test
    void testPlayGameStrategieCollaborateGiveGive1Run() {
	Player player1 = gameTest.getPlayer1();
	Player player2 = gameTest.getPlayer2();
	player1.setMyStrategy(new Adapter(new CooperateStrategy()));
	player2.setMyStrategy(new GiveGive());
	player1.leave();
	player2.leave();
	GameInterface game = gameTest.getGame();
	Mockito.doReturn(5).when(interactionMock).askNbTurn(player1);
	Mockito.doReturn(false).when(interactionMock).playAgain(player1);
	Mockito.doReturn(false).when(interactionMock).playAgain(player2);

	gameTest.playGame();

	assertEquals(Action.COLLABORER, game.getTurnList().get(0).getActionP1());
	assertEquals(Action.COLLABORER, game.getTurnList().get(0).getActionP2());
	assertEquals(Action.COLLABORER, game.getTurnList().get(4).getActionP1());
	assertEquals(Action.COLLABORER, game.getTurnList().get(4).getActionP2());
	assertEquals(player2.getScore(), player1.getScore());
    }

}
