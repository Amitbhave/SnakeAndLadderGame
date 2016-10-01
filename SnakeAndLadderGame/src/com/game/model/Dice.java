/**
 * 
 */
package com.game.model;

import java.util.Random;

/**
 * @author Amit
 * 
 */
public class Dice {

	/**
	 * Return value on dice
	 * 
	 * @return number
	 */
	public static int throwDice() {

		Random random = new Random();
		int num = random.nextInt(6) + 1;

		return num;
	}

}
