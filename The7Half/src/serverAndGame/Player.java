package serverAndGame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import shareObject.Game;

public class Player {
	private static Game game;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Scanner tec = new Scanner(System.in);
		tec.useLocale(Locale.US);
		
		int puertoServidor = 2001;
		String ipServidor = "localhost";
		Socket s = new Socket(InetAddress.getByName(ipServidor), puertoServidor);
		System.out.println("(Cliente): Se ha establecido una conexión con " + ipServidor + ":" + puertoServidor);

		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());

	/*	//Pedimos el nombre
		System.out.println("Ingrese su nombre: ");
		String nombre = tec.nextLine();
		
		//pedimos la apuesta y nos aseguramos que cumpla los parametros
		double apuesta = 0;
		boolean incorrect;
		do {
			incorrect = false;
			try {
				System.out.println("Ingrese su apustas (0.50€ a 50€): ");
				apuesta = tec.nextDouble();
				tec.nextLine();
				if (apuesta < 0.5 || apuesta > 50) {
					incorrect = true;
					System.out.println("Ingrese un numero entre 0.50 y 50");
				}
			} catch (InputMismatchException e) {
				tec.nextLine();
				incorrect = true;
				System.out.println("Ingrese un numero entre 0.50 y 50");
			}
		} while (incorrect);
		*/

		// 01 Enviamos el objeto game con el nombre y la apuesta
		//oos.writeObject(new Game(nombre, apuesta));
		oos.writeObject(new Game("Cristobal", 25));
		oos.flush();
		
		System.out.println("Datos enviados\n");
		//System.out.println("Su turno " + nombre + "\n");
		
		//Comenzamos la partida con el cliente
		do {
			// 04 Recivimos el objeto con la primera carta y boleano de pregunta informamos
			recibeObjeto();
			System.out.println(game.getInformation());
			// si se ha preguntado por otra carta y se está en juego
			if (game.isPreguntaPedirCarta() && game.isJugar()) {
				// preguntamos al usuario
				boolean pedirCarta;
				do {
					pedirCarta = false;
					System.out.println("Otra carta? s/n");
					String respuesta = tec.nextLine();
					// si no quiere carta salimos del bucle de juego
					if (respuesta.equalsIgnoreCase("n")) {
						game.setpedirOtraCarta(false);
						game.setPreguntaPedirCarta(false);
					}
					// Si no responde bien,repreguntamos
					else if (!respuesta.equalsIgnoreCase("s")) {
						System.out.println("Responda s/n");
						pedirCarta = true;
					}
					// si responde si salimos del bucle de pregunta
					// y nos mantenemos en el del juego
					else
						game.setpedirOtraCarta(true);
					game.setPreguntaPedirCarta(false);
				} while (pedirCarta && game.isJugar());
				// 05 Enviamos el objeto con la respuesta
				enviaObjeto();
			}
		} while (game.ispedirOtraCarta());

		// Juega la banca
		// recivimos el objeto y escrivimos información hasta que acabe el juego
		if (game.getBet() != 0) {
			System.out.println("Juega la Banca\n");
			do {
				// 08 Recibe objeto
				recibeObjeto();
				System.out.println(game.getInformation());
				System.out.println();
				enviaObjeto();
			} while (!game.isFinJuego());
		}
		
		tec.close();
		ois.close();
		oos.close();
		s.close();
		System.out.println("(Cliente): Se ha cerrado la conexión");
	}

	private static void recibeObjeto() throws ClassNotFoundException, IOException {
		game = (Game) ois.readObject();
		if (game == null) {
			System.out.println("(Cliente): No se ha recibido nada. El cliente ha cerrado la conexión");
		}
	}

	private static void enviaObjeto() throws IOException {
		oos.writeObject(game);
		oos.flush();
	}

}
