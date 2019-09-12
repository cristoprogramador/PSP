package udp_E1_SendingAMessage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Sol_Servidor {

	public static void main(String[] args) {

		try {
			DatagramSocket ds = new DatagramSocket(2001);

			byte[] buffer = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);
			String datos = new String(buffer);
			System.out.println(datos);

			ds.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
