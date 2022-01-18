package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

    Player test;
    int n = 1;

    @BeforeEach
    void setUp() throws Exception {
	test = new Player(n);
    }

    @Test
    void testAskActionIsNotPlaying() {
	test.leave();
	assertTrue(!test.getIsPlaying());
    }

    @Test
    void testAddScore() {
	assertEquals(0, test.getScore());
	test.addScore(5);
	assertEquals(5, test.getScore());
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
	assertEquals(null, test.getMyStrategy());
    }

}
