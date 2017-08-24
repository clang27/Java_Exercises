import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		Hand playersHand = new Hand();
		System.out.println(playersHand.toString());
		
		Scanner scan1 = new Scanner(System.in);
		char choice;
		while(!Play.DONE) {
			System.out.println("Would you like to hit(h), stay(s), or double(d)");
			choice = scan1.nextLine().charAt(0);
			switch (choice){
				case 'h':
					Play.hit(playersHand);
					break;
				case 's':
					Play.stay(playersHand);
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		}
		scan1.close();
	}
}
