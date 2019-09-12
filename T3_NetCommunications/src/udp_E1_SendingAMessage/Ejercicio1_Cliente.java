package udp_E1_SendingAMessage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ejercicio1_Cliente {

	public static void main(String[] args) throws IOException {

		DatagramSocket ds = new DatagramSocket(2000);
		
		String msg = "Hoka ke ase";
		byte[] buffer = msg.getBytes();
		
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 2001);
		
		ds.send(dp);
		ds.close();
		

	}

}
