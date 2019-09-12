package udp_E3_SendingAndRecivingMessagesFromSomeClients;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class Sol_ServidorCapturaExcepciones {

	public static void main(String[] args) {
		boolean repetir = true;
		
		try {
			DatagramSocket ds = new DatagramSocket(2001);
			ds.setSoTimeout(15 * 1000);

			while (repetir) {

				byte[] buffer = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

				// Recepción de un paquete
				try {
					ds.receive(dp);

					System.out.println("Packete recibido de " + dp.getAddress() + ":" + dp.getPort());

					String datos = new Date().toString();
					buffer = datos.getBytes();
					InetAddress direccionCliente = dp.getAddress();
					int puertoCliente = dp.getPort();

					// Construyo el paquete respuesta a partir de los datos de
					// la
					// respuesta
					dp = new DatagramPacket(buffer, buffer.length, direccionCliente, puertoCliente);

					// Envío de un paquete respuesta
					ds.send(dp);
				} catch (IOException e) {
					repetir = false;
				}
			}

			ds.close();
		} catch (SocketException e) {

		}

	}

}
