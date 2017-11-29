
public class Rounds implements GameStat{
	private int round=0;
	private int endRound=20;
	public void win(){
		round++;
	}
	public void lose(){
		round++;
	}	
	public void tie() {
		round++;
	}	
	public int getRound() {
		return round;
	}
	//Unused implementation of GameStat
	public int getPoints1() {
		return 0;
	}
	//Unused implementation of GameStat
	public int getPoints2() {
		return 0;
	}
	public boolean lastTurn(){
		if(round >= endRound - 1){
			return true;
		}
		return false; 
	}
	public void reset(){
		round=0;
	}
	public void setEndRound(int end) {	
		endRound = end;
	}
	public int getEndRound(){
		return endRound;
	}	
}
