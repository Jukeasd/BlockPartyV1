package hu.Ricsi.BlockParty.GameState;

public class State {
	
	public static GameState State = GameState.NULL;
	
	public static void setState(GameState state) {
		State = state;
	}

	public static GameState getState() {
		return State;
	}
}
