
public class HumanPlayer implements Player{
	String name = "";
	static int token;
	public HumanPlayer(){
		
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public Move makeMove(int key) {
		token = key;
		if(key == 0){
			return new Rock();
		}
		else if(key == 1){
			return new Paper();
		}
		else{ //Key == 2 
			return new Scissors();
		}
		
	}
}
