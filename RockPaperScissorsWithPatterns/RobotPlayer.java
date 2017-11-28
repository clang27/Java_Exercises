public class RobotPlayer implements Player {
	String name = "Robot";
	protected int AICounters[][] = new int[3][3];
	int lastmove;
	int currentmove;

	public RobotPlayer() {
	}

	public int[][] getCounter() {
		return AICounters;
	}

	/**
	 * Controls player by passing a variable consisting of last move by human
	 * player 00 01 02 10 11 12 20 21 22
	 */
	public int playermovea(int n) {
		int playermove = currentmove;
		int fieldnumber = n;
		switch (playermove) {
		case 0:
			fieldnumber = fieldnumber - fieldnumber;
			break;
		case 1:
			fieldnumber = (fieldnumber - fieldnumber) + 1;
			break;
		case 2:
			fieldnumber = (fieldnumber - fieldnumber) + 2;
			break;
		default:
			break;
		}
		return fieldnumber;
	}

	public int playermoveb(int n) {
		int playermove = lastmove;
		int fieldnumber = n;
		switch (playermove) {
		case 0:
			fieldnumber = fieldnumber - fieldnumber;
			break;
		case 1:
			fieldnumber = (fieldnumber - fieldnumber) + 1;
			break;
		case 2:
			fieldnumber = (fieldnumber - fieldnumber) + 2;
			break;
		default:
			break;
		}
		return fieldnumber;
	}

	public Move makeMove(int key) {
		// Resets markov counters for robot AI
		// 0-Rock 1-Paper 2-Scissors 

		int n = Game.Rounds.getRound();

		Move AIMove = null;
		// If round last move is set for next round
		if (n == 0) {
			lastmove = HumanPlayer.token;
			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < 3; ++j) {
					this.AICounters[i][j] = 0;
				}
			}
			int RN = (int)(Math.random() * 3);
			switch (RN) {
			case 0:
				AIMove = new Rock();
				break;
			case 1:
				AIMove = new Paper();
				break;
			case 2:
				AIMove = new Scissors();
				break;
			}
		}
		// Takes last move in account
		else if (n > 0) {
			currentmove = HumanPlayer.token;
			// Get opponents last two moves
			int a = playermovea(n);
			int b = playermoveb(n);
			// Update Markov array
			this.AICounters[b][a] += 1;
			// Get the values from the Markov array that corresponds to his
			// latest move
			int[] counts = AICounters[a];
			// We think he will move rock, so we move paper to beat him.
			if (counts[0] > counts[1] && counts[0] > counts[2]) {
				AIMove = new Paper();
			}
			// We think he will move paper, so we move scissors to beat him.
			else if (counts[1] > counts[2]) {
				AIMove = new Scissors();

			}
			// We think he will move scissors, so we move rock to beat him.
			else {
				AIMove = new Rock();
			}

			lastmove = currentmove;
		}
		return AIMove;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

