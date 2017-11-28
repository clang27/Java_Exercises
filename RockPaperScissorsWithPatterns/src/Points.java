//Supposed observer that maintains score, round, and end of game
public class Points implements GameStat {
	private  int points1 = 0;
	private  int points2 = 0;
	public void win(){
		points1++;
	}
	public  void lose(){
		points2++;
	}
	//Unused implementation of GameStat
	public void tie(){}
	
	//unused implementation of GameStat
	public int getRound() {
		return 0;
	}
	
	public int getPoints1() {
		return points1;
	}
	
	public int getPoints2() {
		return points2;
	}
	
	//unused implementation of GameStat
	public boolean lastTurn(){
		return false;
	}
	public void reset(){
		points1 = 0;
		points2 = 0;
	}
	//Unused implementation of GameStat
	public void setEndRound(int end) {}
	
	//Unused implementation of GameStat
	public int getEndRound(){
		return 0;
	}	
}
