import java.awt.Image;
import java.awt.Rectangle;

public class GameObject {

	protected int _x;
	protected int _y;
	protected int _imageWidth;
	protected int _imageHeight;
	protected Image _image;
	protected boolean _onScreen;

	// This is just a class full of getters and setters
	protected void setX(int x) {
		_x = x;
	}

	protected int getX() {
		return _x;
	}

	protected void setY(int y) {
		_y = y;
	}

	protected int getY() {
		return _y;
	}

	protected int getImageWidth() {
		return _imageWidth;
	}

	protected int getImageHeight() {
		return _imageHeight;
	}

	protected Image getImage() {
		return _image;
	}

	// We use this to get the bounds that way we know when objects intersect it
	protected Rectangle getRectangle() {
		return new Rectangle(_x, _y, _image.getWidth(null), _image.getHeight(null));
	}

	protected void getImageDimensions() {

		_imageWidth = _image.getWidth(null);
		_imageHeight = _image.getHeight(null);
	}

	// We use this to know whether objects are on screen.
	protected boolean onScreen() {
		return _onScreen;
	}

}