package udp_E2_SendingAndRecivingAMessage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Sol_Servidor {

	public static void main(String[] args) {

		try {
			DatagramSocket ds = new DatagramSocket(2001);

			// Recepción de un paquete
			byte[] buffer = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);

			// Envío de un paquete
			String datos = new Date().toString();
			buffer = datos.getBytes();
			InetAddress direccionCliente = dp.getAddress();
			int puertoCliente = dp.getPort();
			dp = new DatagramPacket(buffer, buffer.length, direccionCliente, puertoCliente);
			ds.send(dp);

			ds.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
