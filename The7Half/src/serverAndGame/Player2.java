package serverAndGame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import shareObject.Game;

public class Player2 {
	private static Game game;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Scanner tec = new Scanner(System.in);
		tec.useLocale(Locale.US);

		if (args.length != 4) {
			System.err.println("Para ejecutar el programa tienes que pasarle "
					+ "\nla IP del servidor "
					+ "\nel puerto del servidor "
					+ "\nLa apuesta a realizar (entre 0.50€ y 50€) "
					+ "\nNombre del jugador "
					+ "\nEjemplo Player2 192.168.0.45, 2001, 25, Cristobal");
			System.err.println(
					"Para pasar argumentos a un programa en eclipse: Run --> Run configurations --> Pestaña arguments");
			System.exit(1);
		}
		
		int puertoServidor = Integer.parseInt(args[1]);
		String ipServidor = args[0];
		Socket s = new Socket(InetAddress.getByName(ipServidor), puertoServidor);
		System.out.println("(Cliente): Se ha establecido una conexión con " + ipServidor + ":" + puertoServidor);

		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());
		
		//////////////////// PREGUNTAMOS AL USUARIO LOS DATOS ////////////////////
		
		//Pedimos el nombre 
		//System.out.println("Ingrese su nombre: ");
		//String nombre = tec.nextLine();
		String nombre = args[3];
		
		//pedimos la apuesta y nos aseguramos que cumpla los parametros
		double apuesta = Double.parseDouble(args[2]); 
		/*boolean incorrect; 
		do {
			incorrect = false;
			try {
				System.out.println("Ingrese su apustas (0.50€ a 50€): "); 
				apuesta = tec.nextDouble(); 
				tec.nextLine(); 
				if (apuesta < 0.5 || apuesta > 50){ 
					incorrect = true;
					System.out.println("Ingrese un numero entre 0.50 y 50"); 
					} 
			} catch (InputMismatchException e) { 
				tec.nextLine(); incorrect = true;
				System.out.println("Ingrese un numero entre 0.50 y 50"); 
			} 
		} while (incorrect);*/
		 

		//01 Enviamos el objeto game con el nombre y la apuesta
		oos.writeObject(new Game(nombre, apuesta));
		//oos.writeObject(new Game("Cristobal", 25));
		oos.flush();

		System.out.println("Datos enviados\n");
		// System.out.println("Su turno " + nombre + "\n");
		
		//////////////////// PARTIDA CLIENTE ////////////////////

		// Comenzamos la partida con el cliente
		boolean juegaUsuario = true;
		do {
			// 04 Recivimos el objeto con la primera carta y boleano de pregunta
			// informamos
			recibeObjeto();
			System.out.println(game.getInformation());
			if (game.ispedirOtraCarta()) {
				// preguntamos al usuario
				boolean preguntarUsuario;
				do {
					preguntarUsuario = false;
					System.out.println("Otra carta? s/n");
					String respuesta = tec.nextLine();
					// si no quiere carta salimos del bucle del juego del usuario
					if (respuesta.equalsIgnoreCase("n")) {
						game.setpedirOtraCarta(false);
						juegaUsuario = false;
					}
					// Si no responde bien,repreguntamos
					else if (!respuesta.equalsIgnoreCase("s")) {
						System.out.println("Responda s/n");
						preguntarUsuario = true;
					}
					// si responde si salimos del bucle de pregunta
					// y nos mantenemos en el del juego

				} while (preguntarUsuario);
				// 05 Enviamos el objeto con la respuesta
				enviaObjeto();
			}else
				//Si no nos pregunta por otra carta es que hemos perdido y salimos del bucle del juego del usuario
				juegaUsuario = false;
		} while (juegaUsuario);

		//////////////////// PARTIDA BANCA ////////////////////
		// si el jugador no ha perdido (el servidor mantiene a true la pregunta de pedir carta)
		if (game.isPreguntaPedirCarta()) {
			System.out.println("Juega la Banca\n");
			do {
				// 08 Recibe objeto en bucle, actualiza la información y reenvia para refrescar?
				recibeObjeto();
				System.out.println(game.getInformation());
				System.out.println();
				enviaObjeto();
			} while (game.isPreguntaPedirCarta());
		}
		
		//CERRAMOS 
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
