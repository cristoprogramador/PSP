package serverAndGame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import shareObject.Game;
import shareObject.Baraja;

public class JuegoSieteYMedio2 extends Thread {
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Game game;
	private Baraja baraja;
	private double acumulador;

	public JuegoSieteYMedio2(Game game, Socket socket) {
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
			juegaPlayer();
			// Si el jugador no ha perdido la conexión sigue abierta y hemos
			// recibido el objeto game
			// con pedircarta a false, la apuesta con el valor original
			// El metodo juega la Banca trata los datos del objeto para que
			// juegue la banca

			// Si el jugador no ha perdido (su acumulado no es 0)
			// Juega la banca
			if (game.getPuntuacionPlayer() != 0) {
				juegaBanca();
				// Hemos recibido un objeto game con el setjugar igual a false

				// Al salir del bucle de juego devolvemos el total de puntos
				// enviaObjeto();
				
				// Si la banca no ha perdido  (su acumulado no es 0)

				if (game.getPuntuacionDealer() != 0) {
					game.setInformation("Tu puntuación: " + game.getPuntuacionPlayer() +
							"\nPuntuación de la banca: " + game.getPuntuacionDealer());
					if (game.getPuntuacionPlayer() > game.getPuntuacionDealer()) {
						game.setBet(game.getBet() * 2);
						game.setInformation(
								game.getInformation() + "\nEnhorabuena ¡¡¡ HAS GANADO " + game.getBet() + "€ "+
								game.getName().toUpperCase() + " !!!");
					} else {
						game.setBet(0);
						game.setInformation(
								game.getInformation() + "\nLo sentimos, ¡¡¡ LA BANCA GANA !!!" + "\nHas perdido tu apuesta");
					}
					game.setPreguntaPedirCarta(false);;
					enviaObjeto();
					recibeObjeto();
				} 

			}

			socket.close();
			System.out.println("(Servidor): Se ha cerrado la conexión con el cliente " + game.getName());

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * METODO DEL JUEGO Recibimos por primera vez el objeto y empezamos a
	 * tratarlo para jugar
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void juegaPlayer() throws ClassNotFoundException, IOException {
		acumulador = 0;
		System.out.println("Turno de juego para " + game.getName());

		do {
			// Get card saca una carta random y guarda la información en game
			getCard();
			// Guardamos el acumulador en el objeto
			game.setPuntuacionPlayer(acumulador);

			// Si el total en el acumulado es mayor a siete y medio configurarmos parametros y terminamos
			if (acumulador > 7.5) {
				game.setInformation(game.getInformation() + "\nPerdiste :("); // ok
				game.setPuntuacionPlayer(0);
				// ok no solicitamos preguntar por otra carta
				game.setPreguntaPedirCarta(false); 
				// ok pasamos a false la condición de bucle para salir y no esperar respuesta
				game.setpedirOtraCarta(false);
			} 

			// 03 Terminamos el tratamiento de datos y Enviamos el objeto
			// Si nos hemos pasado recibira la info correspondiente
			// Si no, enviamos petición de preguntar si pedir carta
			enviaObjeto();

			// Si hemos enviado petición de pregunta Esperará a recivir el
			// objeto y continuar otra mano
			// Si no hay petición de pregunta no espera objeto.
			// 06 Recive objeto
			if (game.isPreguntaPedirCarta())
				recibeObjeto();

		} while (game.ispedirOtraCarta());
	}

	/**
	 * METODO DEL JUEGO PARA LA BANCA
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void juegaBanca() throws ClassNotFoundException, IOException {
		acumulador = 0;
		boolean pedirCarta = true;

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
				game.setPuntuacionDealer(0); // ok
				// pasamos a false para que salga de la condición de bucle de recibir infomración continua
				game.setPreguntaPedirCarta(false); 


			} else {
				// si el acumulado de la banca es mayor o igual a 4 se
				// planta
				if (acumulador >= 4) {
					pedirCarta = false;
					game.setInformation(game.getInformation() + "\nLa Banca se Planta");
				}
			}
			
			// 07 Envia objeto con la información
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
	 * METODO PARA RECIBIR EL OBJETO
	 * 
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
	 * 
	 * @throws IOException
	 */
	private void enviaObjeto() throws IOException {
		oos.writeObject(game);
		oos.flush();
	}
}
