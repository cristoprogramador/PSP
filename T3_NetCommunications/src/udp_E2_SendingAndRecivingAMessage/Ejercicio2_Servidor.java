package udp_E2_SendingAndRecivingAMessage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class Ejercicio2_Servidor {

	public static void main(String[] args) throws IOException {
		
		DatagramSocket ds = new DatagramSocket(2001);
		
		byte[] buffer = new byte[1024];
		DatagramPacket dp = new DatagramPacket (buffer, buffer.length);

		//Está a la espera hasta que recibe algo
		ds.receive(dp);
		
		System.out.println("Recibido: " + new String(buffer));
		
		//Enviamos
		Date d = new Date();
		byte[] hora = d.toString().getBytes();
		
		DatagramPacket respuesta  = new DatagramPacket (hora, hora.length, dp.getAddress(), dp.getPort());
		
		ds.send(respuesta);
		
		ds.close();
		}

}
