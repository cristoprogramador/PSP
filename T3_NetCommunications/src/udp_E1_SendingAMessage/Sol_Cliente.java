package udp_E1_SendingAMessage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sol_Cliente {

	public static void main(String[] args) {
		String host = "localhost";
		int puerto = 2001;

		try {
			DatagramSocket ds = new DatagramSocket(2000);

			String mensaje = "Hola";
			byte[] buffer = mensaje.getBytes();
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), puerto);
			ds.send(dp);

			ds.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
