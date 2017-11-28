public class Paper extends Move{
	//Times played paper 
	private static int paperMoves = 0;
	//Move name
	public Paper(){
		setName("paper");
	}
	//Instance method to compare moves and make an outcome
	public String versus(Move move) {
		paperMoves++;
		if(move instanceof Paper){
			return tie();
		}
		else if(move instanceof Scissors){
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
		return "Paper beats Rock. +1 for you.";
	}
	public String tie() {
		Game.Points.tie();
		Game.Rounds.tie();
		return "Paper ties Paper.";
	}
	public String lose() {
		Game.Points.lose();
		Game.Rounds.lose();
		return "Paper doesn't beat Scissors! +1 for robot.";
	}
	//Getter for the number of paper moves. 
	public int moveNumber() {
		return paperMoves;
	}
}
