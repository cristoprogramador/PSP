import java.io.IOException;

/**
 * This class illustrates how to create a Process in Java. It uses the Runtime
 * class to execute the program corresponding "real" Java classes.
 */
public class DestroyProcessExample {

	/**
	 * Main entry point of the program.
	 */
	public static void main(String[] args) {

		String program;
		if (args.length == 0) {
			program = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
		} else {
			program = args[0];
		}

		// The class ProcessBuilder is needed to create a program
		Runtime runtime = Runtime.getRuntime();
		try {
			// The method start of the class ProcessBuilder runs the program and returns a
			// process. In order to communicate with the process we have to store it
			Process process = runtime.exec(program);

			// Wait for 5 seconds
			Thread.sleep(5000);

			// We destroy the process
			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
