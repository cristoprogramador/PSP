package udp_examenFutbol;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.List;

public class ProcessCenter {

	List<String> datos;

	public static void main(String[] args) {
		try {
			// Generamos e inicializamos el objeto Datagram Socket
			DatagramSocket ds = new DatagramSocket(6666);
			// Indicamos que a los 10 segundos sin recibir información salga del
			// proceso
			ds.setSoTimeout(10000);

			// lo ponemos en escucha permantene mediante el while true
			System.out.println("(Servidor) Esperando peticiones...");
			boolean fin = false;
			while (!fin) {
				// asignamos un buffer array de byte con un tamaño de 1024
				// posiciones
				byte[] buffer = new byte[1024];
				// Generamos el objeto datagram packet con nuestro bufes y su
				// tamaño
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

				try {
					// Estará esperando hasta que reciba algo
					ds.receive(dp);
					// Guardamos los datos recibidos en un string.
					String recivedData = new String(buffer);
					// mostramos el mensaje recivido por consola

					System.out.println(recivedData);

					// escribimos el mensaje recivido en archivo
					PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fichero.txt")));
					pw.println(recivedData);
					pw.flush();
					pw.close();
					
				} catch (IOException e) {
					fin = true;
					e.printStackTrace();
				}
			}

			ds.close();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
