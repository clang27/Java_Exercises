import java.util.*;

public class Hand {
	static Deck deck = Deck.getInstance();
	Card unseenCard;
	List<Card> seenCards = new ArrayList<Card>();
	
	public Hand() {
		unseenCard = deck.getTopCard();
		seenCards.add(deck.getTopCard());
	}
	public int checkHandStatus() {
		int value = 0;
		value+=unseenCard.getIntValue();
		for(Card card: seenCards)
			value += card.getIntValue();
		if(value > 21) {
			return Play.BUSTED;
		}
		else if(value == 21) {
			return Play.BLACKJACK;
		}
		else {
			return Play.IN_PLAY;
		}
	}
	public int handValue() {
		int value = 0;
		value+=unseenCard.getIntValue();
		for(Card card: seenCards)
			value += card.getIntValue();
		return value;
	}
	public void drawCard() {
		seenCards.add(deck.getTopCard());
	}
	public String toString() {
		String sentence = "Hand is: ";
		sentence+=unseenCard.toString()+", ";
		for(Card card: seenCards)
			sentence += (seenCards.indexOf(card)!=seenCards.size()-1)?card.toString()+", ":card.toString();
		return sentence;
	}
}
