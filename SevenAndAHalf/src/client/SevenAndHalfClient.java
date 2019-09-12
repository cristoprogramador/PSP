package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Scanner;

import objects.Partida;
/**
 * Class client for the server 
 * @author Trio Escalofrio
 *
 */
public class SevenAndHalfClient {
	private static Partida partida;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;
	private static double totalGanancias;
	private static double totalApostado;
	private static Scanner tec;

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		if (args.length != 4) {
			System.err.println("Para ejecutar el programa tienes que pasarle "
					+ "\nla IP del servidor "
					+ "\nel puerto del servidor "
					+ "\nLa apuesta a realizar (entre 0.50€ y 50€) "
					+ "\nNombre del jugador "
					+ "\nEjemplo 127.0.0.1 2001 25 Cristobal");
			System.err.println(
					"Para pasar argumentos a un programa en eclipse: Run --> Run configurations --> Pestaña arguments");
			System.exit(1);
		}
		
		tec = new Scanner(System.in);
		tec.useLocale(Locale.US);
		int puertoServidor = Integer.parseInt(args[1]);
		String ipServidor = args[0];
		
		do{
			Socket s = new Socket(InetAddress.getByName(ipServidor), puertoServidor);
			System.out.println("(Cliente): Se ha establecido una conexión con " + ipServidor + ":" + puertoServidor);
	
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());			
			
			String nombre = args[3];
			double apuesta = Double.parseDouble(args[2]); 

			///DESCOMENTAR PARA QUE FUNCIONE
			////////////////////PREGUNTAMOS AL USUARIO LOS DATOS ////////////////////
			/*
			//Pedimos el nombre 
			//System.out.println("Ingrese su nombre: ");
			//nombre = tec.nextLine();			
			
			//pedimos la apuesta y nos aseguramos que cumpla los parametros			
			boolean incorrect; 
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
			/////////////////////////////////////////////////////////////////////////
			
			
			// 01 Enviamos el objeto game con el nombre y la apuesta
			oos.writeObject(new Partida(nombre, apuesta));
			oos.flush();
	
			System.out.println("Nueva Partida\n");
			totalApostado += apuesta;
			
			//////////////////// PARTIDA CLIENTE ////////////////////
			// Comenzamos la partida con el cliente
			boolean juegaUsuario= true;
			do {
				// 04 Recivimos el objeto con la primera carta y boleano de pregunta
				// informamos
				recibeObjeto();
				System.out.println(partida.getInformation());
				
				if (partida.ispedirOtraCarta()) {
					// preguntamos al usuario
					boolean respuesta = preguntaSiNo("¿Otra carta?");
					// si no quiere carta salimos del bucle del juego del usuario
					if (!respuesta){
						partida.setpedirOtraCarta(false);
						juegaUsuario = false;
					}
					enviaObjeto();					
				}else
					//Si no nos pregunta por otra carta es que hemos perdido y salimos del bucle del juego del usuario
					juegaUsuario = false;
			} while (juegaUsuario);
	
			//////////////////// PARTIDA BANCA ////////////////////
			// si el jugador no ha perdido (el servidor mantiene a true la pregunta de pedir carta)
			if (partida.isPreguntaPedirCarta()) {
				System.out.println("Juega la Banca\n");
				do {
					// 08 Recibe objeto en bucle, actualiza la información y reenvia para refrescar?
					recibeObjeto();
					System.out.println(partida.getInformation());
					System.out.println();
					enviaObjeto();
				} while (partida.isPreguntaPedirCarta());
			}
			totalGanancias += partida.getBet();
			
			//CERRAMOS CONEXIONES		
			ois.close();
			oos.close();
			s.close();
			System.out.println("(Cliente): Se ha cerrado la conexión");			
			
		} while (preguntaSiNo("¿Otra partida?"));
		
		System.out.println("Total apostado: " + totalApostado + "€");
		System.out.println("Total Ganado: " + totalGanancias + "€");
		System.out.println("Beneficios/Perdidas: " + (totalGanancias- totalApostado) + "€");
		tec.close();
	}

	private static void recibeObjeto() throws ClassNotFoundException, IOException {
		partida = (Partida) ois.readObject();
		if (partida == null) {
			System.out.println("(Cliente): No se ha recibido nada. El cliente ha cerrado la conexión");
		}
	}

	private static void enviaObjeto() throws IOException {
		oos.writeObject(partida);
		oos.flush();
	}
	
	private static boolean preguntaSiNo(String pregunta){
		boolean respuesta = true;
		boolean preguntarUsuario;
		do {
			preguntarUsuario = false;
			//Preguntamos
			System.out.println(pregunta +" s/n");
			String resp = tec.nextLine();
			// si responde no salimos del bucle y la respuesta será false
			if (resp.equalsIgnoreCase("n")) {
				respuesta = false;
			}
			// Si no responde bien,repreguntamos
			else if (!resp.equalsIgnoreCase("s")) {
				System.out.println("Responda s/n");
				preguntarUsuario = true;
			}
			// si responde si salimos del bucle de pregunta
			// y la respuesta será true
		} while (preguntarUsuario);
		return respuesta;
	}
}
