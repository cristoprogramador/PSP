package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import objects.Game;
/**
 * Main class client for the communication to the server 
 * @author Trio Escalofrio
 *
 */

public class SevenAndHalfClient {
	private static Game game;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;
	private static double totalBenefits;
	private static double totalBet;
	private static Scanner tec;
	
	/**
	 * The player will receive any information required to run in the arguments
	 * @param args (IP of the server, port of the server, betting and player name)
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		if (args.length != 4) {
			System.err.println("The information required to run the program "
					+ "\nserver IP "
					+ "\nserver port "
					+ "\nplayer's bet (beetwen 0.50€ and 50€) "
					+ "\nplayer's name "
					+ "\nExample: 127.0.0.1 2001 25 Padawan");
			System.err.println(
					"To pass the arguments to a program: Run --> Run configurations --> Tab arguments");
			System.exit(1);
		}
		
		// check that the port is a integer number
		int serverPort = 0;
		try {
			serverPort = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) { 
			System.err.println(
					"The port must be a integuer number");
			System.exit(1);
		}
		
		// check that the bet is a double number and is between 0.50 and 50
		double bet = 0;
		try {
			bet = Double.parseDouble(args[2]);
			if (bet < 0.5 || bet > 50){
				System.err.println(
						"The bet must be a number between 0.50 and 50\nTry to run the program again");
				System.exit(1);
			}
		} catch (NumberFormatException e) { 
			System.err.println(
					"The bet must be a number between 0.50 and 50\nTry to run the program again");
			System.exit(1);
		}

		String serverIP = args[0];
		String name = args[3];			
		
		tec = new Scanner(System.in);
		tec.useLocale(Locale.US);		
		try{
			boolean newGame = false;
			do{
							Socket s = new Socket(InetAddress.getByName(serverIP), serverPort);
				System.out.println("(Client): The connection has been established with " + serverIP + ":" + serverPort);
		
				oos = new ObjectOutputStream(s.getOutputStream());
				ois = new ObjectInputStream(s.getInputStream());			
				
				//if client start a new game he made a new bet
				if (newGame)
					bet = askBet();			
				
				///if we pass the arguments from keyboard
				///////////////// we ask to the client for the arguments ///////////////
				/*if (!newGame){
					//name
					System.out.println("Enter your name: ");
					name = tec.nextLine();			
					
					//bet and  we check if the arguments are correct		
					bet = askBet();
				}*/
				///////////////////////////////////////////////////////////////////////
				
				
				// We send the game object with the name and the bet
				oos.writeObject(new Game(name, bet));
				oos.flush();
		
				System.out.println("New Game\n");
				totalBet += bet;
				
				//////////////////// CLIENT GAME ////////////////////
				// Client game starts
				boolean clientHand= true;
				
				do {
					// the client receives the object game with the first card and the boolean to end the loop
					// then, we get the info
					receivesObject();
					System.out.println(game.getInformation());
					
					if (game.isHitCard()) {
						// we ask to the client(player)
						boolean answer = askYesNo("Hit card "+ name +"?");
						// if the client doesn't want another card we finish the loop of clientHand
						if (!answer){
							game.setHitCard(false);
							clientHand = false;
						}
						sendsObject();					
					}else
						//If the client doesn't hit a card that means the client loses his hand
						// and we end the client hand loop
						clientHand = false;
				} while (clientHand);
		
				//////////////////// DEALER GAME ////////////////////
				// if the client doesn't lose (the server keeps true to the question hit card)
				if (game.isAskHitCard()) {
					System.out.println("Dealer Game\n");
					do {
						// the Server receives the object game into a loop, refresh the info and send again
						receivesObject();
						System.out.println(game.getInformation());
						System.out.println();
						sendsObject();
					} while (game.isAskHitCard());
				}
				totalBenefits += game.getBet();
				
				//we close connections		
				ois.close();
				oos.close();
				s.close();
				//System.out.println("(Client): The connection has been closed");			
				newGame = askYesNo("Another game"+ name +"?");
				
			} while (newGame);
			
			System.out.println("Total bet: " + totalBet + "€");
			System.out.println("Total Benefits: " + totalBenefits + "€");
			System.out.println("Benefits/Losses: " + (totalBenefits- totalBet) + "€");
			System.out.println("\nTHANKS FOR PLAY WHIT US " + name.toUpperCase());
			System.out.println("(Client): The connection has been closed");
			tec.close();
			
		}catch (ConnectException e){
			System.out.println("Unable to connect to the server may be is powered off \nCheck the Ip & port and try again");
		}catch (SocketException e){
			System.out.println("The connection with the server has been lost");
		}
	}
	
	/**
	 * This method receives the object 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static void receivesObject() throws ClassNotFoundException, IOException {
		
		game = (Game) ois.readObject();
		if (game == null) {
			System.out.println("(Client): Nothing has been received. The client has been closed the connection");
		}
	}
	
	/**
	 * This method sends the object 
	 * @throws IOException
	 */
	private static void sendsObject() throws IOException {
		oos.writeObject(game);
		oos.flush();
	}
	
	/**
	 * This method asks to the client 
	 * @param question
	 * @return
	 */
	private static boolean askYesNo(String question){
		boolean answer = true;
		boolean askClient;
		
		do {
			askClient = false;
			//Asks to the client
			System.out.println(question +" y/n");
			String sAnswer = tec.nextLine();
			// if the client says "no", the loop end and the answer will be false
			if (sAnswer.equalsIgnoreCase("n")) {
				answer = false;
			}
			// If the client doesn't give us a correct answer, we ask again
			else if (!sAnswer.equalsIgnoreCase("y")) {
				System.out.println("Answer again y/n");
				askClient = true;
			}
			// if the client says "yes", the loop ends and the answer will be true
		} while (askClient);
		return answer;
	}
	
	/**
	 * This method asks to the client for a new bet
	 */
	private static double askBet(){
		double bet = 0;
		boolean incorrect; 
		
		do {
			incorrect = false;
			try {
				System.out.println("\nMake your bet between (0.50€ and 50€): "); 
				bet = tec.nextDouble(); 
				tec.nextLine(); 
				if (bet < 0.5 || bet > 50){ 
					incorrect = true;
					System.out.println("Enter a number between 0.50 and 50"); 
					} 
			} catch (InputMismatchException e) { 
				tec.nextLine(); 
				incorrect = true;
				System.out.println("Enter a number between 0.50 and 50"); 
			}			
		} while (incorrect);

		return bet;
	}

}
