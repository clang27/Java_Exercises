public class Scissors extends Move{
	private static int scissorMoves = 0;
	public Scissors(){
		setName("scissors");
	}
	public String versus(Move move) {
		scissorMoves++;
		if(move instanceof Scissors){
			return tie();
		}
		else if(move instanceof Rock){
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
		return "Scissors beats Paper. +1 for you.";
	}
	public String tie() {
		Game.Points.tie();
		Game.Rounds.tie();
		return "Scissors ties Scissors.";
	}
	public String lose() {
		Game.Points.lose();
		Game.Rounds.lose();
		return "Scissors doesn't beat Rock. +1 for Robot.";
	}
	public int moveNumber() {
		return scissorMoves;
	}
}
