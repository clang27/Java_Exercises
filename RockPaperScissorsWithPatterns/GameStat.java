
//GameStat is an interface for concrete classes to implements basic
//game functions. Here the strategy pattern is being demonstrated through
//the use of the classes Rounds and Points 

public interface GameStat {
	public int endRound=0;
	//All arithmetic functionality the game concept exhibits
	public void win();
	public void lose();
	public void tie();
	public int getRound();
	public int getPoints1();
	public int getPoints2();
	public boolean lastTurn();
	public void reset();
	public void setEndRound(int end);
	public int getEndRound();
}

