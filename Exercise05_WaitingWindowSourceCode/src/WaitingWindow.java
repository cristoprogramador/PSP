
import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;


@SuppressWarnings("serial")
public class WaitingWindow extends Frame {

	private Button button;
	private Choice comboBox;

	public WaitingWindow() {
		setLayout(new FlowLayout());
		button = new Button("This is being delayed");
		add(button);

		comboBox = new Choice();
		add(comboBox);
		
		button.addActionListener(new ButtonListener(button));
		
		comboBox.addItem("Option number 1");
		comboBox.addItem("Option number 2");
		comboBox.addItem("Option number 3");
		setSize(300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new WaitingWindow();
	}
}
