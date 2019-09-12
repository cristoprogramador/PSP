import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * This class illustrates how to send information to a process. It uses the
 * OutputputStream class to send the information to the child process
 */
public class SendInformation {

	/**
	 * Main entry point of the program.
	 */
	public static void main(String[] args) {
		// Program to run
		String[] program = { "tr", "e", "E" };

		if (args.length != 0) {
			program = args;
		}

		// The class ProcessBuilder is needed to create a program
		ProcessBuilder pb = new ProcessBuilder(program);

		try {

			// The process is created
			Process process = pb.start();

			// This abstract class is the superclass of all classes representing an output
			// stream of bytes
			OutputStream os = process.getOutputStream();

			// An OutputStreamWriter is a bridge from character streams to byte streams:
			// Characters written to it are encoded into bytes using a specified charset
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

			// Prints formatted representations of objects to a text-output stream.
			PrintWriter ps = new PrintWriter(osw);

			// Send some information through the stream
			ps.println("Example stream to be changed by the command.");
			ps.println("All the letters 'e' in small caps will be replaced to the capital letter");

			// It is important to close the stream in order continue with the program
			ps.close();

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

			System.out.println("Exit of the process: " + Arrays.toString(program));

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
