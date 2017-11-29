public class Rock extends Move{
	private static int rockMoves = 0;
	public Rock(){
		setName("rock");
	}
	public String versus(Move move) {
		rockMoves++;
		if(move instanceof Rock){
			return tie();
		}
		else if(move instanceof Paper){
			return lose();
		}
		else{
			return win();
		}
	}
	//Customized overridden messages for winning, losing, and tying during a match-up. 
	public String win() {
		Game.Points.win();
		Game.Rounds.win();
		return "Rock beats Scissors. +1 for you.";
	}
	public String tie() {
		Game.Points.tie();
		Game.Rounds.tie();
		return "Rock ties Rock.";
	}
	public String lose() {
		Game.Points.lose();
		Game.Rounds.lose();
		return "Rock doesn't beat Paper. +1 for Robot.";
	}
	public int moveNumber() {
		return rockMoves;
	}
}
