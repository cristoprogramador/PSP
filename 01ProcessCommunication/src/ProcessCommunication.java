import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class illustrates how to communicate two processes. It uses the
 * InputStream class to send the information to the child process
 */
public class ProcessCommunication {

	/**
	 * Main entry point of the program.
	 */
	public static void main(String[] args) {

		String program;
		if (args.length == 0) {
			program = "ls -lr";
		} else {
			program = args[0];
		}

		// The class ProcessBuilder is needed to create a program
		Runtime runtime = Runtime.getRuntime();
		try {

			// The process is created
			Process process = runtime.exec(program);

			// This abstract class is the superclass of all classes representing an input
			// stream of bytes
			InputStream is = process.getInputStream();

			// An InputStreamReader is a bridge from byte streams to character streams: It
			// reads bytes and decodes them into characters using a specified charset
			InputStreamReader isr = new InputStreamReader(is);

			// Reads text from a character-input stream, buffering characters so as to
			// provide for the efficient reading of characters, arrays, and lines
			BufferedReader br = new BufferedReader(isr);
			String line;

			System.out.println("Exit of the process: " + program);

			// This loop reads the output of the child process and writes the result on the
			// console
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

			// The process is destroyed
			process.destroy();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
