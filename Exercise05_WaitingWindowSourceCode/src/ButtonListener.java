
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

	private Button button;

	public ButtonListener(Button button) {
		this.button = button;
	}

	public void actionPerformed(ActionEvent e) {
//		System.out.println("The window is going to be frozen for 5 seconds");
//		try {
//			this.button.setEnabled(false);
//			Thread.sleep(5000);
//			System.out.println("The time is over");
//			this.button.setEnabled(true);
//		} catch (Exception ex) {
//			throw new RuntimeException(ex);
//		}
		StopButton sbt = new StopButton(this.button);
		sbt.start();
		
	}
}