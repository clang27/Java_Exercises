
public class GameFactory {
	static GameFactory factory;
	private Player humanPlayer;
	private Player robotPlayer;
	
	private GameFactory(){
		humanPlayer = new HumanPlayer();
		robotPlayer = new RobotPlayer();
	}
	public static synchronized GameFactory getFactory(){
		if(factory == null) factory = new GameFactory();
		return factory;
	}
	public Player getHumanPlayer() {
		return humanPlayer;
	}
	public Player getRobotPlayer() {
		return robotPlayer;
	}

}
