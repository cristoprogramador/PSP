package server;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;

import objects.Game;

/**
 * Main class server for the comunication to the client 
 * @author Trio escalofrio
 *
 */
public class Server {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		if (args.length != 1) {
			System.err.println("The information required to run the program "	
					+ "\nserver port"
					+ "\nExample: 2001");
			System.err.println(
					"To pass the arguments to a program: Run --> Run configurations --> Tab arguments");
			System.exit(1);
		}
		
		//We generate the Server Socket at the port 2001
		int port = Integer.parseInt(args[0]);
		
		ServerSocket ss = new ServerSocket(port);
		System.out.println("(Server): Waiting connections on the Server: " + InetAddress.getLocalHost()+ " Port: " + port);
		// We put a time out 
		ss.setSoTimeout(300000);

		//counter of connections
		int count = 0;
		try{
			
			//Always the server socket will be listening
			while (true){	
				//If there is a request of connection 
				//we generate a new ServerGame Thread with a new object "Game" with a new socket and we start it.
				(new SevenAndHalfGameServer(new Game(), ss.accept())).start();
				count++;
			}				
				
		}catch (SocketTimeoutException e){
			//If the time out has passed without new connections the server socket will be closed
			System.out.println("(Server): Time out has been completed.");
			ss.close();
		}		
		System.out.println("(Server): Total games: " + count);
		System.out.println("(Server): The server has been closed.");
	}	
}
