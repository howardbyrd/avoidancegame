import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GameApp extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GamePanel _gamePanel = new GamePanel();
	private int _width = 600;
	private int _height = 550;
	private MovementHolder _hold;
	public static boolean _pauseStatus = false;

	public GameApp() {
		GameUI();

	}

	private void GameUI() {
		setTitle("Final Project");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(_width, _height);
		// Creating new JPanels including setting their dimensions
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		_gamePanel.setPreferredSize(new Dimension(600, 450));
		JPanel _buttonPanel = new JPanel();
		_buttonPanel.setPreferredSize(new Dimension(600, 30));
		_buttonPanel.setBackground(java.awt.Color.gray);
		JPanel _radioPanel = new JPanel();
		_radioPanel.setPreferredSize(new Dimension(600, 30));
		_radioPanel.setBackground(java.awt.Color.gray);
		JPanel _difficultyPanel = new JPanel();
		_difficultyPanel.setPreferredSize(new Dimension(600, 30));
		_difficultyPanel.setBackground(java.awt.Color.gray);
		JPanel _enemyPanel = new JPanel();
		_enemyPanel.setPreferredSize(new Dimension(600, 30));
		_enemyPanel.setBackground(java.awt.Color.gray);
		// Creating buttons
		JButton _startButton = new JButton("Start");
		JButton _pauseButton = new JButton("Pause/Resume");
		JButton _quitButton = new QuitButton();
		JRadioButton _8bit = new JRadioButton("8 Bit Avatar", true);
		JRadioButton _16bit = new JRadioButton("16 Bit Avatar", false);
		JRadioButton _modern = new JRadioButton("Modern Avatar", false);
		JRadioButton _easy = new JRadioButton("Easy", true);
		JRadioButton _medium = new JRadioButton("Medium", false);
		JRadioButton _hard = new JRadioButton("Hard", false);
		JRadioButton _lowEnemies = new JRadioButton("8 Enemies", true);
		JRadioButton _mediumEnemies = new JRadioButton("16 Enemies", false);
		JRadioButton _hardEnemies = new JRadioButton("32 Enemies", false);

		// Add buttons to a button group that way they are tied by logic
		ButtonGroup _avatarRadioButtons = new ButtonGroup();
		_avatarRadioButtons.add(_8bit);
		_avatarRadioButtons.add(_16bit);
		_avatarRadioButtons.add(_modern);
		ButtonGroup _difficultyRadioButtons = new ButtonGroup();
		_difficultyRadioButtons.add(_easy);
		_difficultyRadioButtons.add(_medium);
		_difficultyRadioButtons.add(_hard);
		ButtonGroup _enemyRadioButtons = new ButtonGroup();
		_enemyRadioButtons.add(_lowEnemies);
		_enemyRadioButtons.add(_mediumEnemies);
		_enemyRadioButtons.add(_hardEnemies);

		// Allows the action listener to be used for these buttons
		_8bit.addActionListener(new CharacterListener("8 Bit Avatar"));
		_16bit.addActionListener(new CharacterListener("16 Bit Avatar"));
		_modern.addActionListener(new CharacterListener("Modern Avatar"));
		_easy.addActionListener(new DifficultyListener("Easy"));
		_medium.addActionListener(new DifficultyListener("Medium"));
		_hard.addActionListener(new DifficultyListener("Hard"));
		_lowEnemies.addActionListener(new EnemyListener("8 Enemies"));
		_mediumEnemies.addActionListener(new EnemyListener("16 Enemies"));
		_hardEnemies.addActionListener(new EnemyListener("32 Enemies"));
		_pauseButton.addActionListener(new PauseListener());
		_startButton.addActionListener(new StartListener());
		// _modern.addActionListener(_gamePanel);
		// Adds the buttons to the panel
		_radioPanel.add(_8bit);
		_radioPanel.add(_16bit);
		_radioPanel.add(_modern);
		_buttonPanel.add(_startButton);
		_buttonPanel.add(_pauseButton);
		_buttonPanel.add(_quitButton);
		_difficultyPanel.add(_easy);
		_difficultyPanel.add(_medium);
		_difficultyPanel.add(_hard);
		_enemyPanel.add(_lowEnemies);
		_enemyPanel.add(_mediumEnemies);
		_enemyPanel.add(_hardEnemies);

		// Adds the JPanels to the JFrame
		add(_gamePanel);
		add(_buttonPanel);
		add(_radioPanel);
		add(_difficultyPanel);
		add(_enemyPanel);

		this.pack();
		setLocationRelativeTo(null);
		setResizable(false);

	}

	public static void main(String[] args) {

		GameApp game = new GameApp();
		game.setVisible(true);
	}

	// This listener reads in the string name of the button clicked
	private class CharacterListener implements ActionListener {

		private String _name;

		public CharacterListener(String name) {
			_name = name;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			_gamePanel.setCharacterPlayer(_name);
			_gamePanel.repaint();
		}

	}

	// This listener reads in the string name of the button clicked
	private class EnemyListener implements ActionListener {

		private String _name;

		public EnemyListener(String name) {
			_name = name;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			_gamePanel.setEnemies(_name);
			_gamePanel.repaint();
		}

	}

	// This listener reads in the string name of the button clicked
	private class DifficultyListener implements ActionListener {

		private String _name;

		public DifficultyListener(String name) {
			_name = name;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			_gamePanel.setDifficulty(_name, _hold);
			_gamePanel.repaint();
		}

	}

	// This listener changes the pause status of the game
	private class PauseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (_pauseStatus == true) {
				_pauseStatus = false;
				_gamePanel.resume();

			} else {
				_pauseStatus = true;
				_gamePanel.pause();

			}
		}

	}
	// This listener changes the pause status of the game
	private class StartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
				_gamePanel.start();

			}
		}


}
