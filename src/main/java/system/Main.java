package system;

public class Main {

    public static void main(String[] args) {
	GameSystem gameSystem = new GameSystem();

	gameSystem.setInteraction(new InteractionConsole());
	gameSystem.playGame();
    }

}
