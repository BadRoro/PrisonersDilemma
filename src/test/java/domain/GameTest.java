package domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import strategy.Action;

@ExtendWith(MockitoExtension.class)
class GameTest {

    GameInterface game = new Game();

    @Test
    void TestResetGame() {
	Player player1 = game.getP1();
	Player player2 = game.getP2();
	player1.leave();
	player2.leave();
	game.getTurnList().add(new Turn(Action.COLLABORER, Action.TRAHIR));
	assertFalse(game.getTurnList().isEmpty());
	assertFalse(player1.getIsPlaying());
	assertFalse(player2.getIsPlaying());

	game.resetGame();

	assertTrue(game.getTurnList().isEmpty());
	assertTrue(player1.getIsPlaying());
	assertTrue(player2.getIsPlaying());
    }
}
