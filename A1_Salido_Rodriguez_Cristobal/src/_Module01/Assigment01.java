package _Module01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class Assigment01 {
	public static void main(String[] args) {
		// Program to run
		String[] program1 = { "ls", "-la" };
		String[] program2 = { "tr", "d", "D" };
		if (args.length != 0) {
			program1 = args;
		}

		// The classes ProcessBuilder is needed to create two programs
		ProcessBuilder pb1 = new ProcessBuilder(program1);
		ProcessBuilder pb2 = new ProcessBuilder(program2);

		try {
			
			// Start the process
			Process process1 = pb1.start();
			Process process2 = pb2.start();
			
			//Reading the output of first process (program1)
			InputStream is = process1.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line; // line is a variable for save the output of process
			
			//Preparing to writing data to the second process (program2)
			OutputStream os = process2.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			PrintWriter ps = new PrintWriter(osw);

			//Saving stream data of first process at the buffer
			while ((line = br.readLine()) != null) {
				ps.println(line);
			}
			//Close the stream data in of the second process
			ps.close();
			
			//We don't need more the process1, then destroy the process
			process1.destroy();
			
			//Reading the output data of the second process
			//We reuse the variables who use to reading the output of first process
			is = process2.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			System.out.println("Exit of the process: " + Arrays.toString(program1)
			+" | " + Arrays.toString(program2));
			
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

			// The process2 is destroyed
			process2.destroy();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
