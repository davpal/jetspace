package state;

import java.util.ArrayList;

public class GameStateManager {
	private ArrayList<GameState> gameStates = new ArrayList<GameState>();

	GameStateManager(){

	}

	public void add(GameState gameState){
		gameStates.add(gameState);
	}
}
