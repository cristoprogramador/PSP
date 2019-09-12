package udp_examenFutbol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Jugador extends Thread{
	private char aequipo;
	private int jugador;
		
	public Jugador(char aequipo, int jugador) {
		super();
		this.aequipo = aequipo;
		this.jugador = jugador;
	}

	public void run(){
		try {
			//Declaramos el scocket para enviar el paquete
			DatagramSocket ds;
			//Inicializamos
			ds = new DatagramSocket();

			//Simulación durante 10 Segundos 10000/10 -> 1000 
			for (int i = 0; i < 1000; i++) {
				//generamos latitud, longitu y temperatura random
				String estadisticas = Funciones.getEstadisticasJugador(aequipo, jugador);
				//Los guardamos en un array de byte
				byte[] buffer = estadisticas.getBytes();
				//Creamos un objeto datagram packeta con los datos siguientes:
				//El array de byte con los textos, buffer
				//La cantidad de los datos que hay en el array buffer.lenght
				//La ip de envio, en nuestro caso localhost
				//El puerto de entrega de los datos en localhost.
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 6666);

				//mediante el objeto datagramsocket enviamos el datagram packet
				ds.send(dp);
				// esperamos 10 milisegundos
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//Cerramos el datagram socket
			ds.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
