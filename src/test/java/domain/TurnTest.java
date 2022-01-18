package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import strategy.Action;

@ExtendWith(MockitoExtension.class)

class TurnTest {

    private Turn turnTest = new Turn(Action.COLLABORER, Action.TRAHIR);

    @Test
    void testGetActionP1() {
	assertEquals(Action.COLLABORER, turnTest.getActionP1());
    }

    @Test
    void testGetActionP2() {
	assertEquals(Action.TRAHIR, turnTest.getActionP2());
    }

}
