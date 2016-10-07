/**
 * 
 */
package com.game.userinterface;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Amit
 * 
 */
@SuppressWarnings("serial")
public class MainScreen extends JFrame {

	// Welcome screen
	JLabel gameLabel;
	JLabel blue;
	JLabel red;
	JTextField player1Name;
	JTextField player2Name;
	JButton play;
	JButton exit;

	public MainScreen() {

		Panel welcome = new Panel();

		gameLabel = new JLabel("Snake And Ladder");
		gameLabel.setBounds(450, 50, 400, 30);

		// Player1
		Icon blueIcon = new ImageIcon(
				"../SnakeAndLadderGame/src/images/blue.png");
		blue = new JLabel(blueIcon);
		blue.setBounds(400, 100, 50, 50);
		player1Name = new JTextField();
		player1Name.setBounds(500, 100, 100, 25);

		// Player2
		Icon redIcon = new ImageIcon("../SnakeAndLadderGame/src/images/red.png");
		red = new JLabel(redIcon);
		red.setBounds(400, 200, 50, 50);
		player2Name = new JTextField();
		player2Name.setBounds(500, 200, 100, 25);

		play = new JButton();
		play.setText("Play");
		play.setBounds(400, 300, 80, 25);

		exit = new JButton();
		exit.setText("Exit");
		exit.setBounds(500, 300, 80, 25);

		welcome.add(gameLabel);
		welcome.add(blue);
		welcome.add(player1Name);
		welcome.add(red);
		welcome.add(player2Name);
		welcome.add(play);
		welcome.add(exit);
		welcome.setLayout(null);
		welcome.setVisible(true);
		add(welcome);

		// Start new game
		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String p1 = player1Name.getText();
				String p2 = player2Name.getText();
				NewGame game = new NewGame(p1, p2);
				game.setVisible(true);
				game.setExtendedState(JFrame.MAXIMIZED_BOTH);
				game.setDefaultCloseOperation(EXIT_ON_CLOSE);
				dispose();

			}
		});

		// Exit from the game
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		enableNimbusUI();
		MainScreen game = new MainScreen();
		game.setVisible(true);
		game.setExtendedState(JFrame.MAXIMIZED_BOTH);
		game.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	// enable nimbus UI
	public static void enableNimbusUI() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

}
