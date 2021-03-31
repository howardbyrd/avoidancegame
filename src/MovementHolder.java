
public class MovementHolder {
	private int _moveHolder;
	public final static int EASY = 250, MEDIUM = 100, HARD = 50; // Our settings for overall game speed
	// Constructor set at a default setting

	public MovementHolder() {
		_moveHolder = 250;
	}

	// Getter and setter for the holder
	public void setMovement(int moveHolder) {
		_moveHolder = moveHolder;
	}

	public int getMovement() {
		return _moveHolder;
	}
}
