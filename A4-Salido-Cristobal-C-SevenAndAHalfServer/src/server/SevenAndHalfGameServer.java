package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import objects.Game;
import objects.SpanishDeck;

/**
 * Thread class Gameserver for the communication between the client and the server
 * @author Trio escalofrio
 *
 */
public class SevenAndHalfGameServer extends Thread {
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Game game;
	private SpanishDeck spanishDeck;
	//this accumulator keeps track of the values of cards
	private double accumulator;
	private double scorePlayer;
	private double scoreDealer;

	/**
	 * Constructor method with the required parameters
	 * @param game
	 * @param socket
	 */
	public SevenAndHalfGameServer(Game game, Socket socket) {
		this.game = game;
		this.spanishDeck = new SpanishDeck();
		this.socket = socket;
		this.accumulator = 0;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method contains the behaviour of the thread when it is called
	 */
	@Override
	public void run(){
		
		try {
			// Receives the object
			receivesObject();	
			System.out.println("(Server): "+game.getName()+" has been established the connection. Port " + socket.getPort());
			playerPlays(); 
			// if the player doesn't lose the connection is still open 
			//we receives the object with hit card = false
			//the bet still going on with the original value
			

			// if the player doesn't lose (his accumulator is not 0)
			if (scorePlayer != 0) {
				dealerPlays();
				
				// at the end of the playerPlays()loop we send the total score				
				// if the dealer doesn't lose (his accumulator is not 0)
				if (scoreDealer != 0) {
					game.setInformation(game.getName() +" score: " + scorePlayer +
							"\nDealer score: " + scoreDealer);
					//if the player score is greater than the dealer score(bet * 2)
					if (scorePlayer > scoreDealer) {
						game.setBet(game.getBet() * 2);
						game.setInformation(
								game.getInformation() + "\nCongratulations ¡¡¡ YOU WIN " + game.getBet() + "€ "+
								game.getName().toUpperCase() + " !!!");
						System.out.println("(Server port: "+ socket.getPort()+"): " 
								+ socket.getInetAddress()+ " " + game.getName() + " wins");
					} 
					//otherwise
					else {						
						game.setInformation(
								game.getInformation() + "\nIt is a great shame, ¡¡¡ THE DEALER WINS !!!" + "\nYou lose " +
										game.getBet() + "€ of your bet");
						game.setBet(0);
						System.out.println("(Server port: "+ socket.getPort()+"): "
								+ socket.getInetAddress()+ " " + game.getName() + " loses");
					}
					//// we set askHitCard to false to end the playerPlays()loop 
					//and close the connection after 
					game.setAskHitCard(false);;
					sendsObject();
					receivesObject();
				} 
			}
			//we close the connection
			socket.close();
			System.out.println("(Server port: "+ socket.getPort()+"): The connection with "+ game.getName()+" has been closed ");

		} catch (SocketException e){
			System.out.println("The connection with "+game.getName()+" has been lost");
		}catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * METHOD TO SIMULATE THE PLAYER GAME 
	 * It's the first time we receive the object and we start to
	 * deal with it in the game
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void playerPlays() throws ClassNotFoundException, IOException {
		
		accumulator = 0;
		System.out.println("(Server port: "+ socket.getPort()+"): "+ socket.getInetAddress()+ " "  + game.getName() + " is playing");

		do {
			// Get card deal a random card and save the info in the object "Game"
			getCard();
			// we save the accumulator in the score of the player
			 scorePlayer=accumulator;

			//If the player goes bust
			if (accumulator > 7.5) {
				game.setInformation(game.getInformation() + "\nYou go bust \nWe're sorry, ¡¡¡ THE DEALER WINS !!!" + "\nYou lose " +
						game.getBet() + "€ of your bet\n");
				scorePlayer=0;
				// no more cards
				game.setAskHitCard(false); 
				// we set askHitCard to false to end the loop
				game.setHitCard(false);
				System.out.println("(Server port: "+ socket.getPort()+"): "+ socket.getInetAddress()+ " "  + game.getName() + " loses");
			} 

			// Sends the object with the info
			sendsObject();

			// if we send the ask for hit card, he waits for the object and the player can continue 
			// with another hand
			// otherwise he doesn't  wait for the object
	
			if (game.isAskHitCard())
				receivesObject();

		} while (game.isHitCard());
	}

	/**
	 * METHOD TO SIMULATE THE DEALER GAME
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void dealerPlays() throws ClassNotFoundException, IOException {
		accumulator = 0;
		boolean hitCard = true;
		//The dealer starts his hand
		do {
			// Get card deal a random card and save the info in the object "Game"
			
			getCard();
			// we save the accumulator in the score of the dealer
			scoreDealer= accumulator;

			// if the dealer goes bust
			if (accumulator > 7.5) {
				//no more cards
				hitCard = false;
				game.setInformation(game.getInformation() + "\nThe dealer goes bust \nCongratulations ¡¡¡ YOU WIN " + game.getBet() + "€ "+
						game.getName().toUpperCase() + " !!!\n");
				scoreDealer=0; 
				//we set askHitCard to false to end the loop
				game.setAskHitCard(false); 
				System.out.println("(Server port: "+ socket.getPort()+"): "+ socket.getInetAddress()+ " "  + game.getName() + " wins");
			} else {
				//If the total is 4 or more the dealer stand
				if (accumulator >= 4) {
					hitCard = false;
					game.setInformation(game.getInformation() + "\nThe Dealer Stands");
					
				}
			}
			
			// Sends the object with the info
			sendsObject();
			// waits to receive the object
			receivesObject();
			// we wait 2 seconds to give the realistic view of the situation
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (hitCard);

	}

	/**
	 * This method calls give us a random card from the spanishdeck and keeps into the accumulator 
	 * the value of the card and set the info into a String
	 */
	private void getCard() {
		
		spanishDeck.getRandomCard();
		accumulator += spanishDeck.getValue();
		game.setInformation(new String("Card: " + spanishDeck.getCardName() + "\nScore: " + accumulator));
	}

	/**
	 * This method receives the object 
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void receivesObject() throws ClassNotFoundException, IOException {
		// Receives the object game with the name and the bet
		game = (Game) ois.readObject();
		if (game == null) {
			System.out.println("(Server port: "+ socket.getPort()+"): Nothing has been received. The client has closed the connection");
		}
	}

	/**
	 * This method sends the object 
	 * 
	 * @throws IOException
	 */
	private void sendsObject() throws IOException {
		oos.writeObject(game);
		oos.flush();
	}
}
