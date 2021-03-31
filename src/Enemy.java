import javax.swing.ImageIcon;

public class Enemy extends GameObject {
	private boolean _onScreen;

	public Enemy(int x, int y) {

		createEnemy(x, y);
	}

	// Sets the location and loads the image in order to create an enemy
	private void createEnemy(int x, int y) {
		_x = x;
		_y = y;
		_onScreen = true;

		setImage();
		getImageDimensions();
	}

	// Chooses between two different images to be the enemy
	private void setImage() {

		int random = (int) (Math.random() * 2);
		if (random == 0) {
			ImageIcon _enemy = new ImageIcon("src/resources/boo.png");
			_image = _enemy.getImage();
		} else {
			ImageIcon _enemy = new ImageIcon("src/resources/booInverse.png");
			_image = _enemy.getImage();
		}
	}

	// Will set the enemy movement
	public void move() {
		// Go past the height of the panel then it will reposition it to the top
		if (_y > 450) {
			_y = 0;
		}
		_y += 10; // Just the amount of pixels it moves
	}

	public boolean onScreen() {
		return _onScreen;
	}

	public void setonScreen(Boolean onScreen) {
		_onScreen = onScreen;
	}

}
