package server;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import objects.Partida;

public class Server {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//Generamos el Server Socket en el puerto 2001
		int puerto = 2001;
		ServerSocket ss = new ServerSocket(puerto);
		System.out.println("(Servidor): Esperando conexiones en el Servidor: " + InetAddress.getLocalHost()+ " por el puerto: " + puerto);
		// Añadimos un tiempo de espera 
		ss.setSoTimeout(300000);

		List<SevenAndHalfServer> listaJuegos = new ArrayList<SevenAndHalfServer>();
		List<Socket> listaSockets = new ArrayList<Socket>();	
		
		//El server socket estará a la escucha
		int count = 0;
		try{
			
			boolean close = false;
			while (!close){		
				//Si hay petición de conexión 
				//generamos un nuevo hilo de Juego con un nuevo objeto game, con el nuevo socket generado por el server socket y lo ponemos en marcha.
				listaSockets.add(ss.accept());		
				listaJuegos.add(new SevenAndHalfServer(new Partida(), listaSockets.remove(0)));
				listaJuegos.remove(0).start();
				count++;
			}				
				
		}catch (SocketTimeoutException e){
			//sin pasa el tiempo de espera y no ha recibido nuevas conexiones se cierra el server socket
			System.out.println("(Servidor): Agotado el tiempo de espera.");
			ss.close();
		}		
		System.out.println("(Servidor): Partidas jugadas en total: " + count);
		System.out.println("(Servidor): Se ha cerrado el servidor.");
	}	
}
