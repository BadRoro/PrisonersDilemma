package domain;

import java.util.List;

import strategy.Action;

public interface GameInterface {

    List<Turn> getTurnList();

    int getNumberOfTurns();

    void setNumberOfTurns(int numberOfTurns);

    Player getP1();

    Player getP2();

    Action getOpponentLastAction(Player player);

    String getOpponentLastActionToString(Player player);

    void resetGame();

}