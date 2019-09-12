package serverAsGame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import shareObject.Game;
import shareObject.Baraja;

public class Dealer {
	private static Game game;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;
	private static Baraja carta;
	private static double acumulador;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int puerto = 2001;

		ServerSocket ss = new ServerSocket(puerto);
		System.out.println("(Servidor): Esperando conexiones por el puerto: " + puerto);
		
		Socket s = ss.accept();
		System.out.println("(Servidor): Conexion establecida de " + s.getInetAddress() + ":" + s.getPort()
				+ ". Esperando mensaje");
		ois = new ObjectInputStream(s.getInputStream());
		oos = new ObjectOutputStream(s.getOutputStream());

		recibeObjeto();
		//juega el player
		jugar();
		// Al salir del bucle de juego devolvemos el total de puntos
		//enviaObjeto();
		
		//Si el jugador no ha perdido
		if(game.getBet() != 0 && !game.isJugar()){
			//jugar("Dealer");
			game.setInformation("Puntuación de la banca: " + game.getPuntuacionDealer());
			if (game.getPuntuacionPlayer() > game.getPuntuacionDealer()) {
				game.setBet(game.getBet() * 2);
				game.setInformation(game.getInformation() + "\n¡¡¡Enhorabuena has ganado!!!"
						+ "\nHas tenido una ganacia de " + game.getBet() + "€");
			} else {
				game.setBet(0);
				game.setInformation(game.getInformation() + "\nLo sentimos, la banca gana" + "\nHas perdido tu apuesta");
			}			
			game.setFinJuego(true);
			enviaObjeto();
			recibeObjeto();
		}
		else{
			game.setFinJuego(true);
			enviaObjeto();
			recibeObjeto();
		}
		
		s.close();
		System.out.println("(Servidor): Se ha cerrado la conexión con el cliente");
		ss.close();
		System.out.println("(Servidor): El servidor ha cerrado el socket");
	}

	private static void getCard() {
		// pedimos carta y añadimos al acumulador
		carta = new Baraja();
		acumulador += carta.getValor();
		game.setInformation(new String("Su carta es: " + carta.getNombre() + "\nPuntuación actual: " + acumulador));
	}
	
	private static void recibeObjeto() throws ClassNotFoundException, IOException {
		// Recibe el objeto game con el nombre y la apuesta
		game = (Game) ois.readObject();
		if (game == null) {
			System.out.println("(Servidor): No se ha recibido nada. El cliente ha cerrado la conexión");
		}
	}

	private static void enviaObjeto() throws IOException {
		oos.writeObject(game);
		oos.flush();
	}
	
	private static void jugar() throws ClassNotFoundException, IOException {
		boolean pedirCarta;
		acumulador = 0;
		do {
			System.out.println("Jugando mano Player");
			pedirCarta = true;
			//Get card saca una carta random y guarda la información en game
			getCard();
			// Guardamos el acumulador en el objeto
			game.setPuntuacionPlayer(acumulador);		

			// Si el total en el acumulado es mayor a siete y medio
			if (acumulador > 7.5) {
				// Si es mas de 7 y medio terminamos
				game.setInformation(game.getInformation() + "\nPerdiste :(");
				game.setBet(0);
				game.setPuntuacionDealer(0);
				game.setPreguntaPedirCarta(false);
				game.setJugar(false);
				game.setFinJuego(true);
				//enviamos el objeto con la información
				enviaObjeto();
			}

			// si el acumulado no supera el siete y medio	
			// preguntamos si quiere otra carta
			game.setPreguntaPedirCarta(true);
			enviaObjeto();
			recibeObjeto();
			pedirCarta = game.otraCarta();
			//enviaObjeto();
		} while (pedirCarta);
		
		//Cuando termina el jugador si este no ha perdido
		//y se sigue jugando
		//iniciamos las variables para que juege la banca
		if(game.isJugar() && pedirCarta == false){
			acumulador = 0;
			pedirCarta = true;
			System.out.println("Juega la Banca");
			
			//empieza a jugar la banca
			do{
				//Get card saca una carta random y guarda la información en game
				getCard(); 
				// Guardamos el acumulador en la variable de la banca del objeto			
				game.setPuntuacionDealer(acumulador);
				
				// Si el total en el acumulado es mayor a siete y medio
				if (acumulador > 7.5) {
					// Si es mas de 7 y medio terminamos
					pedirCarta = false;
					game.setInformation(game.getInformation() + "\nLa banca pierde");
					//game.setBet(game.getBet()*2);
					game.setPuntuacionDealer(0);
					//game.setPreguntaPedirCarta(false);				
					game.setJugar(false);
					//game.setFinJuego(true); //daba error de scocket al entrar 
					//enviamos el objeto con la información
					//enviaObjeto();
				}				
				else{
					// si el acumulado de la banca es mayor o igual a 4 se planta			
					if (acumulador >= 4){
						pedirCarta = false;
						game.setInformation(game.getInformation() + "\nLa Banca se Planta");
						game.setJugar(false);
					}
				/*	else{
						pedirCarta = true;	
					}*/
					//enviaObjeto();
					//recibeObjeto();	
				}
				
				enviaObjeto();
				recibeObjeto();
			}while (pedirCarta);
		}
	}
}
