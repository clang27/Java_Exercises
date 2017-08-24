
public class Card {
	private String suit;
	private String color;
	private String value;
	
	public Card(String s, String v) {
		suit = s;
		value = v;
		color = (suit.equals("Hearts")|suit.equals("Diamonds"))?"Red":"Black";
	}

	public String getSuit() {
		return suit;
	}
	public String getColor() {
		return color;
	}
	public String getValue() {
		return value;
	}
	public String toString() {
		return value + " of " + suit;
	}
	public int getIntValue() {
		int intValue;
		switch(value) {
			case "A":
				intValue = 1;//Will Update this later
				break;
			case "K":
				intValue = 10;
				break;
			case "Q":
				intValue = 10;
				break;
			case "J":
				intValue = 10;
				break;
			default:
				intValue = Integer.parseInt(value);
				break;
		}
		return intValue;
	}
}
