package udp_E2_SendingAndRecivingAMessage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sol_Cliente {

	public static void main(String[] args) {
		String host = "localhost";
		int puerto = 2001;

		try {
			DatagramSocket ds = new DatagramSocket(2000);

			// Envío de un paquete
			byte[] buffer = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), puerto);
			ds.send(dp);

			// Recepción de un paquete
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);
			System.out.println("Mensaje recibido: " + new String(dp.getData()));

			ds.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
