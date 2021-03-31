
import javax.swing.*;
import java.awt.event.*;

public class QuitButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Anonymous class to close the program
	public QuitButton() {
		super("Quit");
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // exit program
			}
		});
	}
}