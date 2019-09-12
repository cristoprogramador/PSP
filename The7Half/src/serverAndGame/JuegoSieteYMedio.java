package serverAndGame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import shareObject.Game;
import shareObject.Baraja;

public class JuegoSieteYMedio extends Thread {
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Game game;
	private Baraja baraja;
	private double acumulador;

	public JuegoSieteYMedio(Game game, Socket socket) {
		this.game = game;
		this.baraja = new Baraja();
		this.socket = socket;
		this.acumulador = 0;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		System.out.println("(Servidor): Conexion establecida de " + socket.getInetAddress() + ":" + socket.getPort()
				+ ". Esperando mensaje");
		try {

			// 02 Recive objeto
			recibeObjeto();
			// juega el player
			jugar();

			// Al salir del bucle de juego devolvemos el total de puntos
			// enviaObjeto();
			// Si el jugador no ha perdido
			if (!game.isFinJuego()) {
				if (game.getBet() != 0 && !game.isJugar()) {
					// jugar("Dealer");
					game.setInformation("Puntuación de la banca: " + game.getPuntuacionDealer());
					if (game.getPuntuacionPlayer() > game.getPuntuacionDealer()) {
						game.setBet(game.getBet() * 2);
						game.setInformation(game.getInformation() + "\n¡¡¡Enhorabuena has ganado " + game.getBet() + "€ !!!");
					} else {
						game.setBet(0);
						game.setInformation(
								game.getInformation() + "\nLo sentimos, la banca gana" + "\nHas perdido tu apuesta");
					}
					game.setFinJuego(true);
					enviaObjeto();
					//recibeObjeto();
				}
				else {
					game.setFinJuego(true);
					enviaObjeto();
					// recibeObjeto();
				}
			}
			
			//recibeObjeto();
			if (game.isFinJuego());
				socket.close();
			System.out.println("(Servidor): Se ha cerrado la conexión con el cliente");

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * METODO COGER CARTA ALEATORIA
	 */
	private void getCard() {
		// pedimos carta y añadimos al acumulador
		baraja.sacaCarta();
		acumulador += baraja.getValor();
		game.setInformation(new String("Su carta es: " + baraja.getNombre() + "\nPuntuación actual: " + acumulador));
	}

	/**
	 * METODO DEL JUEGO
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void jugar() throws ClassNotFoundException, IOException {
		boolean pedirCarta;
		acumulador = 0;
		do {
			System.out.println("Turno de juego para " + game.getName());
			pedirCarta = true;
			// Get card saca una carta random y guarda la información en game
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
				game.setpedirOtraCarta(false);
				pedirCarta = false;
				// enviamos el objeto con la información
				// enviaObjeto();
			} else {
				// si el acumulado no supera el siete y medio
				// preguntamos si quiere otra carta
				game.setPreguntaPedirCarta(true);
			}

			// 03 Envia objeto
			enviaObjeto();

			// Esperará a recivir el objeto
			// 06 Recive objeto
			if(game.isJugar())
				recibeObjeto();
			pedirCarta = game.ispedirOtraCarta();

		} while (pedirCarta);

		// Cuando termina el jugador si este no ha perdido
		// y se sigue jugando
		// iniciamos las variables para que juege la banca
		if (game.isJugar() && pedirCarta == false) {
			acumulador = 0;
			pedirCarta = true;
			System.out.println("Turno de juedo para la banca");

			// empieza a jugar la banca
			do {
				// Get card saca una carta random y guarda la información en
				// game
				getCard();
				// Guardamos el acumulador en la variable de la banca del objeto
				game.setPuntuacionDealer(acumulador);

				// Si el total en el acumulado es mayor a siete y medio
				if (acumulador > 7.5) {
					// Si es mas de 7 y medio terminamos
					pedirCarta = false;
					game.setInformation(game.getInformation() + "\nLa banca pierde");
					// game.setBet(game.getBet()*2);
					game.setPuntuacionDealer(0);
					//game.setPreguntaPedirCarta(false);
					game.setJugar(false);
					game.setFinJuego(true); //daba error de scocket al entrar
					// enviamos el objeto con la información
					//enviaObjeto();
				} else {
					// si el acumulado de la banca es mayor o igual a 4 se
					// planta
					if (acumulador >= 1) {
						pedirCarta = false;
						game.setInformation(game.getInformation() + "\nLa Banca se Planta");
						game.setJugar(false);
					}
				}
				// 07 Envia objeto

				enviaObjeto();				
		
				// Espera a recibir objeto
				recibeObjeto();
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (pedirCarta);
		}
	}

	/**
	 * METODO PARA RECIBIR EL OBJETO
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void recibeObjeto() throws ClassNotFoundException, IOException {
		// Recibe el objeto game con el nombre y la apuesta
		game = (Game) ois.readObject();
		if (game == null) {
			System.out.println("(Servidor): No se ha recibido nada. El cliente ha cerrado la conexión");
		}
	}

	/**
	 * METODO PARA ENVIAR EL OBJETO
	 * @throws IOException
	 */
	private void enviaObjeto() throws IOException {
		oos.writeObject(game);
		oos.flush();
	}
}
