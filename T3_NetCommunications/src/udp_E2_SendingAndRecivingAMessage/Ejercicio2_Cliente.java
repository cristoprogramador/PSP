package udp_E2_SendingAndRecivingAMessage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ejercicio2_Cliente {

	public static void main(String[] args) throws IOException {
		//Enviamos
		DatagramSocket ds = new DatagramSocket(2000);
		
		String msg = "hola";
		byte[] buffer = msg.getBytes();
		
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 2001);
		
		ds.send(dp);
		
		//Recivimos		
		byte[] mensajeRecibido = new byte[1024];
		DatagramPacket resp = new DatagramPacket(mensajeRecibido, mensajeRecibido.length);
		ds.receive(resp);
		System.out.println("Mensaje recivido del servidor: " + new String(mensajeRecibido));
		
		ds.close();
	}
}
