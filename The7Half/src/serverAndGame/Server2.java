package serverAndGame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import shareObject.Game;

public class Server2 {

	private static List<JuegoSieteYMedio2> listaJuegos;
	private static List<Socket> listaSockets;
	private static boolean close;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int puerto = 2001;
		listaJuegos = new ArrayList<JuegoSieteYMedio2>();
		listaSockets = new ArrayList<Socket>();		
		
		ServerSocket ss = new ServerSocket(puerto);
		System.out.println("(Servidor): Esperando conexiones por el puerto: " + puerto);	
		
		close = false;
		do{
			
			listaSockets.add(ss.accept());
			System.out.println("Nuevo Socket");
			System.out.println("IP: " + listaSockets.get(0).getInetAddress());
			System.out.println("Puerto: " + listaSockets.get(0).getPort());
		
			System.out.println("adelante");
			listaJuegos.add(new JuegoSieteYMedio2(new Game(), listaSockets.remove(0)));
			listaJuegos.remove(0).start();
			System.out.println("genreado juego");

		}while (!close);
		
		ss.close();
		System.out.println("(Servidor): El servidor ha cerrado el socket");
	}	
}
