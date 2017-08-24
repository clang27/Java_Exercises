import java.util.*;

public class Deck {
	private static Deck deck = null;
	private Stack<Card> cards = new Stack<Card>();
	private int deckSize = 0;
	
	protected Deck() {
		addCard("Hearts", "A");
		addCard("Hearts", "2");
		addCard("Hearts", "3");
		addCard("Hearts", "4");
		addCard("Hearts", "5");
		addCard("Hearts", "6");
		addCard("Hearts", "7");
		addCard("Hearts", "8");
		addCard("Hearts", "9");
		addCard("Hearts", "10");
		addCard("Hearts", "J");
		addCard("Hearts", "Q");
		addCard("Hearts", "K");
		addCard("Diamonds", "A");
		addCard("Diamonds", "2");
		addCard("Diamonds", "3");
		addCard("Diamonds", "4");
		addCard("Diamonds", "5");
		addCard("Diamonds", "6");
		addCard("Diamonds", "7");
		addCard("Diamonds", "8");
		addCard("Diamonds", "9");
		addCard("Diamonds", "10");
		addCard("Diamonds", "J");
		addCard("Diamonds", "Q");
		addCard("Diamonds", "K");
		addCard("Clubs", "A");
		addCard("Clubs", "2");
		addCard("Clubs", "3");
		addCard("Clubs", "4");
		addCard("Clubs", "5");
		addCard("Clubs", "6");
		addCard("Clubs", "7");
		addCard("Clubs", "8");
		addCard("Clubs", "9");
		addCard("Clubs", "10");
		addCard("Clubs", "J");
		addCard("Clubs", "Q");
		addCard("Clubs", "K");
		addCard("Spades", "A");
		addCard("Spades", "2");
		addCard("Spades", "3");
		addCard("Spades", "4");
		addCard("Spades", "5");
		addCard("Spades", "6");
		addCard("Spades", "7");
		addCard("Spades", "8");
		addCard("Spades", "9");
		addCard("Spades", "10");
		addCard("Spades", "J");
		addCard("Spades", "Q");
		addCard("Spades", "K");
		shuffle();
	}
	public static Deck getInstance() {
		if(deck == null) {
			deck = new Deck();
		}
		return deck;
	}
	public String toString() {
		Stack<Card> tempCards = new Stack<Card>();
		String completeString = "";
		while(!cards.empty()) {
			completeString += cards.peek().toString()+"\n";
			tempCards.push(cards.pop());
		}
		while(!tempCards.empty()) {
			cards.push(tempCards.pop());
		}
		return completeString;
	}
	public void addCard(String suit, String value) {
		cards.push(new Card(suit, value));
		deckSize++;
	}
	public void shuffle() {
		//Worst case: O(abc) a=Amount of Stacks b=Amount of Cards c=Amount of Shuffles.
		//i.e.:This goes to 100th stack and all 52 cards are that stack - 10 times. This is highly unlikely.
		int amountOfShufflesLeft = 10;
		do {
			final int AMOUNT_OF_STACKS= 100;
			List<Stack<Card>> stacks = new ArrayList<Stack<Card>>();
			for(int i = 0; i < AMOUNT_OF_STACKS; i++) {
				stacks.add(new Stack<Card>());
			}
			while(!cards.empty()) {
				int random = (int)(Math.random() * AMOUNT_OF_STACKS);
				stacks.get(random).push(cards.pop());
			}
			for(Stack<Card> stack: stacks) {
				while(!stack.isEmpty()) {
					cards.push(stack.pop());
				}
			}
		}while(amountOfShufflesLeft-- > 0);
	}
	public Card getTopCard() {
		return cards.pop();
	}
	public int getDeckSize() {
		return deckSize;
	}
}
