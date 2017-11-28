//Since this class has 3 sub-classes, all of the commentary in paper applies to scissors and rock also. 

public abstract class Move {
	//Every move has a name
	private String name = "";
	public Move(){}
	
	//Every move has to have a match-up when playing
	public abstract String versus(Move move);
	
	//These are the different outcomes that happen during a match-up
	public abstract String win();
	public abstract String tie();
	public abstract String lose();
	
	//A move tracker for that respected move
	public abstract int moveNumber();
	
	//Getter and setters for the name of the move 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
