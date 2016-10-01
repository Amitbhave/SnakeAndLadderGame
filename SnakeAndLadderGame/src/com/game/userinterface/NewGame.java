/**
 * 
 */
package com.game.userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.game.logic.Game;
import com.game.model.Dice;
import com.game.model.Player;

/**
 * @author Amit
 * 
 */
@SuppressWarnings("serial")
public class NewGame extends JFrame {

	// flag to decide who's turn is this. When set, it's player1 to play. Else
	// player2.
	static boolean player = true;

	boolean win = false;

	Player playerOne;
	Player playerTwo;

	Board board;

	Game game;

	JButton throwDice;
	static JLabel whosTurn;
	JLabel diceNumber;
	JPanel panel = new JPanel();

	public NewGame(String p1, String p2) {

		this.initGame(p1, p2);

		// Show who's turn is this
		whosTurn = new JLabel("It's " + p1 + "'s turn...");
		whosTurn.setBounds(1000, 400, 200, 30);

		// Dice
		throwDice = new JButton("Throw Dice");
		throwDice.setBounds(1000, 500, 200, 30);

		diceNumber = new JLabel();
		diceNumber.setBounds(1000, 600, 200, 30);

		// finally add board to the game!
		board = new Board();
		board.setBounds(50, 50, 700, 700);

		panel.add(board);
		panel.add(whosTurn);
		panel.add(throwDice);
		panel.add(diceNumber);
		panel.setLayout(null);
		panel.setVisible(true);
		add(panel);
		this.setVisible(true);
		
		throwDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int diceVal = 0;
				if (player) {
					diceVal = Dice.throwDice();
					movePlayer(playerOne, diceVal);
					//Swap player only if he has not got 6 on dice
					if(diceVal!=6){
						swapPlayers();
						changeWhosTurnLabel(playerTwo);
					}
				} else {
					diceVal = Dice.throwDice();
					movePlayer(playerTwo, diceVal);
					//Swap player only if he has not got 6 on dice
					if(diceVal!=6){
						swapPlayers();
						changeWhosTurnLabel(playerOne);
					}
				}

			}
		});
	}
	
	/**
	 * Method for player movement logic
	 * @param p
	 * @param diceVal
	 */
	public void movePlayer(Player p, int diceVal){
		diceNumber.setText("You got: " + diceVal);
		win = game.move(board, p, diceVal);
		if (win) {
			showResult(p);
		}
	}
	
	/**
	 * Initialize game state
	 * 
	 * @param player1
	 * @param player2
	 */
	public void initGame(String p1, String p2) {
		// Initialize game board
		game = new Game();
		game.initBoard();

		playerOne = new Player();
		playerOne.setId(1);
		playerOne.setName(p1);
		playerOne.setPosition(1);

		playerTwo = new Player();
		playerTwo.setId(2);
		playerTwo.setName(p2);
		playerTwo.setPosition(1);
	}

	/**
	 * Show game result
	 * 
	 * @param player
	 */
	public void showResult(Player player) {
		String[] options = new String[] { "Start new game", "Exit" };
		int userResponse = JOptionPane.showOptionDialog(null,
				"" + player.getName() + " won the game!", "Game Result",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				options, options[0]);
		if (userResponse == 0) {
			// Start new game
			MainScreen game = new MainScreen();
			game.setVisible(true);
			game.setExtendedState(JFrame.MAXIMIZED_BOTH);
			game.setDefaultCloseOperation(EXIT_ON_CLOSE);
			dispose();
		} else if (userResponse == 1) {
			// Exit
			System.exit(0);
		}
	}

	/**
	 * Change whosTurn label
	 * 
	 * @param player
	 */
	public static void changeWhosTurnLabel(Player p) {
		whosTurn.setText("It's " + p.getName() + "'s turn...");
	}

	/**
	 * Switch between players
	 */
	public static void swapPlayers() {
		player = !player;
	}
}
