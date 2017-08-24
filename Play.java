public class Play {
	public final static int BUSTED = 0;
	public final static int BLACKJACK = 1;
	public final static int IN_PLAY = 2;
	public static boolean DONE = false;
	
	public static void hit(Hand hand) {
			hand.drawCard();
			System.out.println(hand.toString());
			System.out.println("You have " + hand.handValue());
			if(hand.checkHandStatus() == BUSTED) {
				System.out.println("\nBusted!");
				DONE = true;
			}
			else if(hand.checkHandStatus() == BLACKJACK) {
				System.out.println("\nTwenty-One!");
				DONE = true;
			}
	}
	public static void stay(Hand hand) {
		System.out.println("You are staying with " + hand.handValue());
		DONE = true;
	}
}
