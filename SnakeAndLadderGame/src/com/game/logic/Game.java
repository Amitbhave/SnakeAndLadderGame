/**
 * 
 */
package com.game.logic;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.game.model.Player;
import com.game.userinterface.Board;

/**
 * @author Amit
 * 
 */
public class Game {

	public static final Icon blueIcon = new ImageIcon(
			"../SnakeAndLadderGame/src/images/blue.png");
	public static final Icon redIcon = new ImageIcon(
			"../SnakeAndLadderGame/src/images/red.png");

	int snakePositions[][] = { { 17, 7 }, { 54, 34 }, { 62, 19 }, { 64, 60 },
			{ 87, 24 }, { 93, 73 }, { 95, 75 }, { 99, 79 } };
	int ladderPositions[][] = { { 1, 38 }, { 4, 14 }, { 9, 31 }, { 21, 42 },
			{ 28, 84 }, { 51, 67 }, { 71, 91 }, { 80, 100 } };

	int boardMatrix[][] = new int[10][10];
	
	//store player positions
	int players[] = new int[2];

	public void initBoard() {
		int val = 100;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				boardMatrix[i][j] = val;
				val--;
			}
		}
	}

	/**
	 * Move the player to new position and return if either player has won the
	 * game
	 * 
	 * @param board
	 * @param player
	 * @param diceNumber
	 * @return winFlag
	 */
	public boolean move(Board board, Player player, int diceNumber) {
		boolean winFlag = false;
		int prevPosition = player.getPosition();
		int newPosition = prevPosition + diceNumber;

		// Check if newPosition is snake position
		for (int i = 0; i < snakePositions.length; i++) {
			if (newPosition == snakePositions[i][0]) {
				newPosition = snakePositions[i][1];
			}
		}

		// Check if newPosition is ladder position
		for (int i = 0; i < ladderPositions.length; i++) {
			if (newPosition == ladderPositions[i][0]) {
				newPosition = ladderPositions[i][1];
			}
		}

		player.setPosition(newPosition);

		if (newPosition < 100) {

			JLabel prevLabel = (JLabel) board.getComponent(prevPosition - 1);
			
			if(players[0]==players[1]){
				if(player.getId()==1){
					prevLabel.setIcon(redIcon);
				}
				else{
					prevLabel.setIcon(blueIcon);
				}
			}
			else{
				prevLabel.setIcon(null);
			}
			prevLabel.revalidate();

			JLabel nextLabel = (JLabel) board.getComponent(newPosition - 1);

			if (player.getId() == 1) {
				nextLabel.setIcon(blueIcon);
			} else {
				nextLabel.setIcon(redIcon);
			}
		} else {
			winFlag = true;
		}
		
		//players[0]=position of player1, players[1]=position of player2
		if(player.getId() == 1){
			players[0] = newPosition;
		} else{
			players[1] = newPosition;
		}

		return winFlag;
	}
}
