import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer _timer;
	private String _endMessage = "Game Over, You Lose";
	private Avatar _player;
	private boolean _gameStarted;
	private int _delay = 250;
	private int _height = 450;
	private ArrayList<Enemy> _boos;
	private int _easyEnemies = 8;
	private int _mediumEnemies = 16;
	private int _hardEnemies = 32;
	private int _numberOfEnemies = 8;
	private int _score;
	private int _lives = 3;
	private String _scoreMessage = String.valueOf(_score);
	private String _livesMessage = String.valueOf(_lives);
	private Image _8bit, _16bit, _modern;
	public static boolean _pauseStatus = false;

	public GamePanel() {
		setupgamePanel();

	}

	// Adds all of the important listeners to the program including setting the
	// correct images
	public void setupgamePanel() {
		this.setBackground(Color.black);

		_8bit = new ImageIcon("src/resources/mushroom8bit.png").getImage();
		_16bit = new ImageIcon("src/resources/mushroom.png").getImage();
		_modern = new ImageIcon("src/resources/mushroommodern.png").getImage();
		gameSetup();
		addKeyListener(new PaddleListener());
		addMouseMotionListener(new PaddleListener());
		setFocusable(true);
	}

	// This is the pause and play function of the game
	public void actionPerformed(ActionEvent e) {
		_player.move();
		updateEnemy();
		checkCollision();
		repaint();

	}

	// This simply allows us the hear the music in the background
	public void play(String filename) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	// The initial game setup. Creates the player, and it creates our enemies
	private void gameSetup() {

		_player = new Avatar("8Bit Avatar", _8bit);
		makeEnemies();
	}

	// This makes sure that the boos that are created will move
	private void updateEnemy() {

		for (int i = 0; i < _boos.size(); i++) {

			Enemy _boo = _boos.get(i);
			_boo.move();

		}
	}

	// This is what we initially use to create our enemies
	private void makeEnemies() {

		_boos = new ArrayList<Enemy>();
		for (int i = 0; i < _numberOfEnemies; i++) {
			int random = (int) (Math.random() * 580);
			int random2 = (int) (Math.random() * 400);
			_boos.add(new Enemy(random + 10, random2));

		}
	}

	// Just used for drawing all components used in this app.
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		if (_gameStarted) {

			drawObjects(g2d);
			scoreCount(g2d);
			lifeCount(g2d);
		} else if (_gameStarted == false){

			gameOver(g2d);
		}

		Toolkit.getDefaultToolkit().sync();
	}

	// This method is what helps the sprites change from control panel.
	public void setCharacterPlayer(String name) {
		if (name.equals("8 Bit Avatar")) {
			_player = new Avatar("8Bit Avatar", _8bit);
			repaint();
		}

		if (name.equals("16 Bit Avatar")) {
			_player = new Avatar("16Bit Avatar", _16bit);
			repaint();
		}

		if (name.equals("Modern Avatar")) {
			_player = new Avatar("Modern Avatar", _modern);
			repaint();
		}
	}

	// This method changes the speed of the whole game
	public void setDifficulty(String name, MovementHolder hold) {
		// Set the delay to 250 ms
		if (name.equals("Easy")) {
			_delay = hold.EASY;
			_timer.stop();
			_timer = new Timer(_delay, this);
			_timer.start();
			repaint();
		}
		// Set the delay to 100 ms
		if (name.equals("Medium")) {
			_delay = hold.MEDIUM;
			_timer.stop();
			_timer = new Timer(_delay, this);
			_timer.start();
			repaint();
		}
		// Set the delay to 50 ms
		if (name.equals("Hard")) {
			_delay = hold.HARD;
			_timer.stop();
			_timer = new Timer(_delay, this);
			_timer.start();
			repaint();
		}
	}

	// This method allows us to set the number of enemies on the screen.
	public void setEnemies(String name) {
		// Creates 8 enemies on the screen at once
		if (name.equals("8 Enemies")) {
			_numberOfEnemies = _easyEnemies;
			_boos = new ArrayList<Enemy>();
			for (int i = 0; i < _numberOfEnemies; i++) {
				int random = (int) (Math.random() * 580);
				int random2 = (int) (Math.random() * 400);
				_boos.add(new Enemy(random + 10, random2));

			}
			repaint();
		}
		// Creates 16 enemies on the screen at once
		if (name.equals("16 Enemies")) {
			_numberOfEnemies = _mediumEnemies;
			_boos = new ArrayList<Enemy>();
			for (int i = 0; i < _numberOfEnemies; i++) {
				int random = (int) (Math.random() * 580);
				int random2 = (int) (Math.random() * 400);
				_boos.add(new Enemy(random + 10, random2));

			}
			repaint();
		}
		// Creates 32 enemies on the screen at once
		if (name.equals("32 Enemies")) {
			_numberOfEnemies = _hardEnemies;
			_boos = new ArrayList<Enemy>();
			for (int i = 0; i < _numberOfEnemies; i++) {
				int random = (int) (Math.random() * 580);
				int random2 = (int) (Math.random() * 400);
				_boos.add(new Enemy(random + 10, random2));

			}
			repaint();
		}
	}

	// Draws the boos and the player
	private void drawObjects(Graphics2D g2d) {

		g2d.drawImage(_player.getImage(), _player.getX(), _player.getY(), _player.getImageWidth(),
				_player.getImageHeight(), this);

		for (Enemy _boo : _boos) {
			g2d.drawImage(_boo.getImage(), _boo.getX(), _boo.getY(), _boo.getImageWidth(), _boo.getImageHeight(), this);
		}
	}

	// This screen displays when you win or lose
	private void gameOver(Graphics2D g2d) {

		Font font = new Font("Arial", Font.BOLD, 40);
		g2d.setColor(Color.YELLOW);
		g2d.setFont(font);
		g2d.drawString(_endMessage, 100, 225);
	}

	// This is the label that changes based on your score
	private void scoreCount(Graphics2D g2d) {

		Font font = new Font("Arial", Font.BOLD, 14);
		g2d.setColor(Color.WHITE);
		g2d.setFont(font);
		g2d.drawString("Score: " + _scoreMessage, 520, 20);
	}

	// This label tells us the amount of lives the player has
	private void lifeCount(Graphics2D g2d) {

		Font font = new Font("Arial", Font.BOLD, 14);
		g2d.setColor(Color.WHITE);
		g2d.setFont(font);
		g2d.drawString("Lives: " + _livesMessage, 520, 40);
	}

	// This method is used to pause the game
	void start() {
	if(_gameStarted != true) {
		_gameStarted = true;
		_timer = new Timer(_delay, this);
		_timer.start();
		gameSetup();
		play("src/resources/FrankyTheme.wav");
		repaint();
	}
	}
	void pause() {
		_timer.stop();
		repaint();
	}

	// This method resumes the game if it was paused
	void resume() {

		_timer = new Timer(_delay, this);
		_timer.start();
		repaint();

	}

	// This listener allows us to move the character with the keyboard or mouse
	private class PaddleListener implements KeyListener, MouseMotionListener {

		@Override
		public void keyReleased(KeyEvent e) {
			_player.keyReleased(e);
			repaint();

		}

		@Override
		public void keyPressed(KeyEvent e) {
			_player.keyPressed(e);
			repaint();

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			_player.mouseMoved(e);
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}
	}

	// Checks if the player hit an enemy boo.
	// and ends the game after you get
	// a high enough score.
	public void checkCollision() {
		for (Enemy _boo : _boos) {
			// Takes away a life if you're hit by a boo
			if ((_boo.getRectangle()).intersects(_player.getRectangle())) {
				if (_lives != 0) {
					_lives = _lives - 1;
					_livesMessage = String.valueOf(_lives);
					_boo.setonScreen(false);
				} else {
					_gameStarted = false;
				}
			}
			// Updates score if the boo moves past the bottom,
			// Also repositions the boos if it goes past the bottom of the screen
			if (_boo.getRectangle().getMaxY() > _height) {
				_score++;
				_scoreMessage = String.valueOf(_score);

			}
			// Ends the game after you get a high enough score.
			if (_score == 80 * _numberOfEnemies) {
				_endMessage = "Game Over, You Win";
				_gameStarted = false;

			}

		}

	}

}