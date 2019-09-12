import java.io.IOException;

/**
 * This class illustrates how to create a Process in Java. It uses the Runtime
 * class to execute the program corresponding "real" Java classes.
 */
public class RuntimeExample {

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
			// If we want to get the value returned of the process we use the method waitFor
			int returnValue = process.waitFor();
			// Print out the return value of the process.
			// 0 -> The process finished correctly
			// 1 -> The process was terminated
			System.out.println("The execution of " + program + " returns " + returnValue);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
