import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Avatar extends GameObject {

	private int _dx;
	private int _playerStartX = 300;
	private int _playerStartY = 390;
	private int _width = 600;
	private int _height = 450;
	private Image _avatarImage;
	private String _name;

	public Avatar(String name, Image avatarImage) {
		setName(name);
		_avatarImage = avatarImage;
		createPlayer();
	}

	// Simply calls the methods needed to initialize the player.
	private void createPlayer() {

		setImage();
		getImageDimensions();
		resetAvatarState();
	}

	// This is the image of the player sprite.
	public void setImage() {

		_image = _avatarImage;
	}

	public void move() {
		//Moves with keyboard
		_x += _dx;
		//This prevents the avatar from moving out of bounds
		if (_x >= _width - _imageWidth) {
			_x = _width - _imageWidth;
		}
		
	}

	// Key Presses move the player you can arrow keys or A and D
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			_dx = -10;
		}

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			_dx = 10;
		}

	}

	// Moves player with mouse movements
	public void mouseMoved(MouseEvent e) {
		int x = Math.min(_width - _imageWidth, Math.max(0, e.getX() - _imageWidth / 2));
		this.setX(x);
		this.setY(_height - 50);
	}

	// Key release ...... I put it in because I used it in another project it
	// doesn't move the player.
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			_dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			_dx = 0;
		}
	}

	// Place player in the starting position.
	private void resetAvatarState() {

		_x = _playerStartX;
		_y = _playerStartY;
	}

	// Getter for the image used for avatars.
	public Image getImage() {
		return _avatarImage;
	}
	public boolean onScreen() {
        return _onScreen;
    }

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		_name = _name;
	}

}
