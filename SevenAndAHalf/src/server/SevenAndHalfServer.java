package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import objects.Partida;
import objects.SpanishDeck;

public class SevenAndHalfServer extends Thread {
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Partida partida;
	private SpanishDeck spanishDeck;
	private double acumulador;
	private double puntuacionPlayer;
	private double puntuacionDealer;

	public SevenAndHalfServer(Partida partida, Socket socket) {
		this.partida = partida;
		this.spanishDeck = new SpanishDeck();
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
		try {
			// 02 Recive objeto
			recibeObjeto();		
			System.out.println("(Servidor): "+partida.getName()+" ha establecido conexi�n por el puerto " + socket.getPort());
			juegaPlayer(); // juega el player
			// Si el jugador no ha perdido la conexi�n sigue abierta y hemos recibido el objeto game con pedircarta a false, la apuesta con el valor original
			// El metodo juega la Banca trata los datos del objeto para que juegue la banca

			// Si el jugador no ha perdido (su acumulado no es 0)
			if (puntuacionPlayer != 0) {
				juegaBanca(); // Juega la banca
				
				// Al salir del bucle de juego devolvemos el total de puntos				
				// Si la banca no ha perdido  (su acumulado no es 0)
				if (puntuacionDealer != 0) {
					partida.setInformation("Puntuaci�n de " + partida.getName() + ": " + puntuacionPlayer +
							"\nPuntuaci�n de la banca: " + puntuacionDealer);
					//si el jugador tiene una puntuaci�n mayor
					if (puntuacionPlayer > puntuacionDealer) {
						partida.setBet(partida.getBet() * 2);
						partida.setInformation(
								partida.getInformation() + "\nEnhorabuena ��� HAS GANADO " + partida.getBet() + "� "+
								partida.getName().toUpperCase() + " !!!");
						System.out.println("(Servidor port: " + socket.getPort() + ") " + socket.getInetAddress() + " " + partida.getName() + " ha ganado");
					} 
					//Si la banca tiene una puntuaci�n mayor
					else {						
						partida.setInformation(
								partida.getInformation() + "\nLo sentimos, ��� LA BANCA GANA !!!" + "\nHas perdido los " +
										partida.getBet() + "� de tu apuesta");
						partida.setBet(0);
						System.out.println("(Servidor port: " + socket.getPort() + ") " + socket.getInetAddress() + " " + partida.getName() + " ha perdido");
					}
					//ponemos a false la condici�n del bucle de recepci�n de informaci�n del cliente para que salga y cierre conexi�n tras devolvernos el objeto
					partida.setPreguntaPedirCarta(false);;
					enviaObjeto();
					recibeObjeto();
				} 
			}
			//Cerramos la conexi�n
			socket.close();
			System.out.println("(Servidor port: "+ socket.getPort()+"): Se ha cerrado la conexi�n con el cliente " + partida.getName());

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
		System.out.println("(Servidor port: " + socket.getPort() + ") " + socket.getInetAddress() + " " + partida.getName() + " est� jugando");

		do {
			// Get card saca una carta random y guarda la informaci�n en game
			getCard();
			// Guardamos el acumulador en la variable de la puntuaci�n del player
			puntuacionPlayer = acumulador;

			// Si el total en el acumulado es mayor a siete y medio configurarmos parametros y terminamos
			if (acumulador > 7.5) {
				partida.setInformation(partida.getInformation() + "\nTe pasaste de 7.5 \nLo sentimos, ��� LA BANCA GANA !!!" + "\nHas perdido los " +
						partida.getBet() + "� de tu apuesta\n");
				puntuacionPlayer = 0;
				// ok no solicitamos preguntar por otra carta
				partida.setPreguntaPedirCarta(false); 
				// ok pasamos a false la condici�n de bucle para salir y no esperar respuesta
				partida.setpedirOtraCarta(false);
				System.out.println("(Servidor port: " + socket.getPort() + ") " + socket.getInetAddress() + " " + partida.getName() + " ha perdido");
			} 

			// 03 Terminamos el tratamiento de datos y Enviamos el objeto
			// Si nos hemos pasado recibira la info correspondiente
			// Si no, enviamos petici�n de preguntar si pedir carta
			enviaObjeto();

			// Si hemos enviado petici�n de pregunta Esperar� a recivir el
			// objeto y continuar otra mano
			// Si no hay petici�n de pregunta no espera objeto.
			// 06 Recive objeto
			if (partida.isPreguntaPedirCarta())
				recibeObjeto();

		} while (partida.ispedirOtraCarta());
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
		// empieza a jugar la banca
		do {
			// Get card saca una carta random y guarda la informaci�n en
			// game
			getCard();
			// Guardamos el acumulador en la variable de la banca del objeto
			puntuacionDealer = acumulador;

			// Si el total en el acumulado es mayor a siete y medio
			if (acumulador > 7.5) {
				// Si es mas de 7 y medio terminamos
				pedirCarta = false;
				partida.setInformation(partida.getInformation() + "\nLa banca se pas� de 7.5 \nEnhorabuena ��� HAS GANADO " + partida.getBet() + "� "+
						partida.getName().toUpperCase() + " !!!\n");
				puntuacionDealer = 0; // ok
				// pasamos a false para que salga de la condici�n de bucle de recibir infomraci�n continua
				partida.setPreguntaPedirCarta(false); 
				System.out.println("(Servidor port: " + socket.getPort() + ") " + socket.getInetAddress() + " " + partida.getName() + " ha ganado");
			} else {
				// si el acumulado de la banca es mayor o igual a 4 se
				// planta
				if (acumulador >= 4) {
					pedirCarta = false;
					partida.setInformation(partida.getInformation() + "\nLa Banca se Planta");					
				}
			}
			
			// 07 Envia objeto con la informaci�n
			enviaObjeto();
			// Espera a recibir objeto
			recibeObjeto();
			// Esperamos un tiempo para dar realismo al juego
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
		// pedimos carta y a�adimos al acumulador
		spanishDeck.sacaCarta();
		acumulador += spanishDeck.getValor();
		partida.setInformation(new String("Carta: " + spanishDeck.getNombre() + "\nPuntuaci�n: " + acumulador));
	}

	/**
	 * METODO PARA RECIBIR EL OBJETO
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void recibeObjeto() throws ClassNotFoundException, IOException {
		// Recibe el objeto game con el nombre y la apuesta
		partida = (Partida) ois.readObject();
		if (partida == null) {
			System.out.println("(Servidor port: "+ socket.getPort()+"): No se ha recibido nada. El cliente ha cerrado la conexi�n");
		}
	}

	/**
	 * METODO PARA ENVIAR EL OBJETO
	 * 
	 * @throws IOException
	 */
	private void enviaObjeto() throws IOException {
		oos.writeObject(partida);
		oos.flush();
	}
}
