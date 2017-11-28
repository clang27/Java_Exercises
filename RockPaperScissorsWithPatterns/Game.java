import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JFrame{
	//Key constants for passing moves to other classes.
	final int ROCK = 0;
	final int PAPER = 1;
	final int SCISSORS = 2;
	
	//The concrete objects of the Strategy Pattern
	static GameStat Rounds = new Rounds();
	static GameStat Points = new Points();
	
	JTextArea m_resultArea = new JTextArea(6, 30);
    private static final long serialVersionUID = 1L;
    
    //Factory is generated when the game activates, which creates the players.
    GameFactory factory = GameFactory.getFactory();
    //Moves that will be constantly reallocated as moves are made.
    Move move1;
    Move move2;
    
    //IMAGES for center playing panel
	private ImageIcon humanPaper = new ImageIcon("images/Paper.png");
	private ImageIcon humanScissor = new ImageIcon("images/Scissors.png");
	private ImageIcon humanRock = new ImageIcon("images/Rock.png");
	private ImageIcon robotPaper = new ImageIcon("images/Paper2.png");
	private ImageIcon robotScissor = new ImageIcon("images/Scissors2.png");
	private ImageIcon robotRock = new ImageIcon("images/Rock2.png");
	private ImageIcon player = new ImageIcon("images/Rock.png");
	private ImageIcon robot = new ImageIcon("images/Rock2.png");
	
	//IMAGES for player buttons 
	private ImageIcon bthumanPaper = new ImageIcon("images/PaperButton.png");
	private ImageIcon bthumanScissor = new ImageIcon("images/ScissorButton.png");
	private ImageIcon bthumanRock = new ImageIcon("images/RockButton.png");
	
	//NORTH PANEL LABELS
	private static JLabel lblScorePlayer;
	private static JLabel lblScoreRobot;
	private static JLabel lblRound;
	
	//BUTTONS for lower right of GUI
	private JButton jbtReset = new JButton("Reset");
	private JButton jbtInstructions = new JButton("Instructions");
	private JButton jbtExit = new JButton("Exit");
	private JButton jbtCredit = new JButton("Credits");
	
	//BUTTONS for lower left of GUI
	private JButton jbtRock = new JButton (bthumanRock);
	private JButton jbtPaper = new JButton (bthumanPaper);
	private JButton jbtScissor = new JButton (bthumanScissor);
	
	//PANELS
	private static JPanel panelNorth = new JPanel(new BorderLayout());
	private static JPanel panelNorth2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private static JPanel panelNorth1 = new JPanel(new BorderLayout());
	private	static JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private	static JPanel panelCenter = new JPanel(new BorderLayout());
	private static JPanel panelMAIN = new JPanel(new BorderLayout());
	
	 //LABELS
	 JLabel lblhumanPaper = new JLabel("", humanPaper, JLabel.CENTER);
	 JLabel lblhumanRock = new JLabel("", humanRock, JLabel.CENTER);
	 JLabel lblhumanScissor = new JLabel("", humanScissor, JLabel.CENTER);
	 JLabel lblhumanRest = new JLabel("", player, JLabel.CENTER);
	 JLabel lblrobotRock = new JLabel("", robotRock, JLabel.CENTER);
	 JLabel lblrobotPaper = new JLabel("", robotPaper, JLabel.CENTER);
	 JLabel lblrobotScissor = new JLabel("", robotScissor, JLabel.CENTER);
	 JLabel lblrobotRest = new JLabel("", robot, JLabel.CENTER);
	 
	 //Action Listeners
	 ExitListener el = new ExitListener();
	 PaperListener pl = new PaperListener();
	 RockListener rckl = new RockListener();
	 ScissorListener sl = new ScissorListener();
	 InstructionListener il = new InstructionListener();
	 ResetListener rl = new ResetListener();
	 CreditListener cl = new CreditListener();
	
	 //The string for the outcome panel 
	 static String outcome = "";
	 static JLabel outComeInfo;
	
	 public Game(){	
		//User is prompted to input their name, which is put in the human player. 
		factory.getHumanPlayer().setName(JOptionPane.showInputDialog("What's your name?"));
		if(factory.getHumanPlayer().getName() == null || factory.getHumanPlayer().getName().equals("")){
			//Exception handling for a null or empty string of a name. 
			factory.getHumanPlayer().setName("Player");
		}
		//FRAME SETTINGS
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(3);
		setTitle("Rock, Papers, Scissors!");
		setLocationRelativeTo(null); // Center the frame
		setSize(800, 450);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
		setResizable(false);
		setVisible(true);
		add(panelMAIN);

		//PANELS ADDED TO MAIN PANEL
		panelMAIN.add(panelNorth, BorderLayout.PAGE_START);
		panelMAIN.add(panelSouth, BorderLayout.PAGE_END);
		panelMAIN.add(panelCenter, BorderLayout.CENTER);

		//NORTH PANEL SETTINGS
		panelNorth.setBackground(Color.white);
		panelNorth1.setBackground(Color.white);
		panelNorth2.setBackground(Color.white);
		panelNorth.add(panelNorth2, BorderLayout.PAGE_END);
		panelNorth.add(panelNorth1, BorderLayout.PAGE_START);
				
		//SOUTH PANEL SETTINGS
		panelSouth.setBackground(Color.blue);
		panelSouth.add(jbtRock);
		panelSouth.add(jbtPaper);
		panelSouth.add(jbtScissor);
		
		panelSouth.add(jbtCredit);
		panelSouth.add(jbtInstructions);
		panelSouth.add(jbtReset);
		panelSouth.add(jbtExit);
		
		//CENTER PANEL SETTINGS
		panelCenter.setBackground(Color.white);
	}
	
	 public void loadGame(){
		//Every time the game is loaded, the labels are filled and the action listeners are generated. 
		lblScorePlayer = new JLabel("                          " + factory.getHumanPlayer().getName() + "'s Score: " + Points.getPoints1());
		lblScoreRobot = new JLabel(factory.getRobotPlayer().getName() + "'s Score: " + Points.getPoints2() + "                          ");
		lblRound = new JLabel("ROUND: " + Rounds.getRound());
		panelNorth2.add(lblRound, BorderLayout.CENTER);
		panelNorth1.add(lblScorePlayer, BorderLayout.LINE_START);
		panelNorth1.add(lblScoreRobot, BorderLayout.LINE_END);
		try{ //Game asks for number of rounds, but if user doesn't put a number, the default number is 20.
			Rounds.setEndRound(Integer.parseInt(JOptionPane.showInputDialog("How many rounds?")));
			if(Rounds.getEndRound() < 1 || Rounds.getEndRound() > 150){
				Rounds.setEndRound(20);
			}
		}
		catch(NumberFormatException e){
			Points.setEndRound(20);
		}
		jbtCredit.addActionListener(cl);
		jbtExit.addActionListener(el);
		jbtPaper.addActionListener(pl);
		jbtRock.addActionListener(rckl);
		jbtScissor.addActionListener(sl);
		jbtInstructions.addActionListener(il);
		jbtReset.addActionListener(rl);
	}
	 //The last turn has one more outcome, and then the overall outcome of the entire game. Removes action listeners on the moves to end play. 
	 public void endGame(){
		outcome = move1.versus(move2) + "     " + winner();
		outComeInfo = new JLabel(outcome, JLabel.CENTER);
		panelCenter.add(outComeInfo, BorderLayout.PAGE_END);
		jbtPaper.removeActionListener(pl);
		jbtRock.removeActionListener(rckl);
		jbtScissor.removeActionListener(sl);
	}
	 //Repaint for the center panel to refresh the images that will be displayed. 
	 public void panelRepaint(){
		panelCenter.removeAll();
		panelCenter.revalidate();
		panelCenter.repaint();
	}
	 //Repaint for the north panel and all of the contents of the round and score. 
	 public void panelRepaint2(){
		panelNorth2.removeAll();
		panelNorth2.repaint();
		panelNorth2.revalidate();
		lblScorePlayer.setText("");
		lblScoreRobot.setText("");
	}
	 //When triggered, this method will generate the moves, input, and output. 
	 public void moveTrigger(int move){
		panelRepaint();
		panelRepaint2();
		//Notifies factory instances to make a move. 
		move1 = factory.getHumanPlayer().makeMove(move);
		move2 = factory.getRobotPlayer().makeMove(move);
		if(move2 instanceof Rock){
			panelCenter.add(lblrobotRock, BorderLayout.LINE_END);
		}
		else if(move2 instanceof Paper){
			panelCenter.add(lblrobotPaper, BorderLayout.LINE_END);
		}
		else{
			panelCenter.add(lblrobotScissor, BorderLayout.LINE_END);
		}
		outcome = move1.versus(move2);
		outComeInfo = new JLabel(outcome, JLabel.CENTER);
		panelCenter.add(outComeInfo, BorderLayout.PAGE_END);
		
		lblScorePlayer = new JLabel("                          " + factory.getHumanPlayer().getName() + "'s Score: " + Points.getPoints1());
		lblScoreRobot = new JLabel(factory.getRobotPlayer().getName() + "'s Score: " + Points.getPoints2() + "                          ");
		lblRound = new JLabel("ROUND: " + Rounds.getRound());
		panelNorth2.add(lblRound, BorderLayout.CENTER);
		panelNorth1.add(lblScorePlayer, BorderLayout.LINE_START);
		panelNorth1.add(lblScoreRobot, BorderLayout.LINE_END);
	}


	 public String winner(){
		if(Points.getPoints1() > Points.getPoints2()){
			return "Out of " + Rounds.getEndRound() + " rounds: You Win!";
		}
		else if(Points.getPoints1() == Points.getPoints2()){
			return "Out of " + Rounds.getEndRound() + " rounds: No Winner!";
		}
		else{
			return "Out of " + Rounds.getEndRound() + " rounds: You Lose!";
		}
	 }
	 class PaperListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(Rounds.lastTurn()){ //On the last round, a move is made and the game is ended. 
				moveTrigger(PAPER); //This passes the key to which moves have been made. 
				panelCenter.add(lblhumanPaper,  BorderLayout.LINE_START);
				endGame();
			}
			else{
				moveTrigger(PAPER);
				panelCenter.add(lblhumanPaper,  BorderLayout.LINE_START);
			}
		}
	}
	
	 class RockListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(Rounds.lastTurn()){
				moveTrigger(ROCK);
				panelCenter.add(lblhumanRock,  BorderLayout.LINE_START);
				endGame();
			}
			else{
				moveTrigger(ROCK);
				panelCenter.add(lblhumanRock,  BorderLayout.LINE_START);
			}
		}
	}
	
	 class ScissorListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(Rounds.lastTurn()){
				moveTrigger(SCISSORS);
				panelCenter.add(lblhumanScissor,  BorderLayout.LINE_START);
				endGame();
			}
			else{
				moveTrigger(SCISSORS);
				panelCenter.add(lblhumanScissor,  BorderLayout.LINE_START);
			}
		}
	}
	
	 //Buttons that display information, close the program, and reset the program. 
	 class InstructionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JOptionPane.showMessageDialog(null, "Choose a hand to play from the lower left.\nThe computer will also choose.\n\n" +
												"Rock beats Scissors\nScissors beats Paper\nPaper beats Rock", "Instructions", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	 class CreditListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JOptionPane.showMessageDialog(null, "Code: Collin Lang\nGUI: Nigam Patel\nDesign: Zak Ostendorf\nAI: Earnest Hall\nTester: Kadeem Forbes", "Credits", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	 class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	 class ResetListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Every variable is removed or set to empty. 
			panelNorth2.removeAll();
			panelNorth2.repaint();
			panelNorth2.revalidate();
			lblScorePlayer.setText("");
			lblScoreRobot.setText("");
			jbtCredit.removeActionListener(cl);
			jbtExit.removeActionListener(el);
			jbtPaper.removeActionListener(pl);
			jbtRock.removeActionListener(rckl);
			jbtScissor.removeActionListener(sl);
			jbtInstructions.removeActionListener(il);
			jbtReset.removeActionListener(rl);
			panelRepaint();
			Rounds.reset();
			Points.reset();
			//Game is reloaded. 
			loadGame();
		}
	}
}
