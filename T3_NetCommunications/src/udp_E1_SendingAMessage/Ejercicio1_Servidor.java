package udp_E1_SendingAMessage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Ejercicio1_Servidor {

	public static void main(String[] args) throws IOException {
		
		DatagramSocket ds = new DatagramSocket(2001);
		
		byte[] buffer = new byte[1024];
		DatagramPacket dp = new DatagramPacket (buffer, buffer.length);

		ds.receive(dp);
		
		System.out.println("Recibido: " + new String(buffer));
		
		ds.close();
		}

}
