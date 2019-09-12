import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Ejemplo7_servidor {

	public static void main(String[] args) throws IOException {
		int puertoEscucha = 6666;

		// creaci�n de un socket que escucha al puerto...
		DatagramSocket ds = new DatagramSocket(puertoEscucha);

		System.out.println("(Servidor) Esperando peticiones...");

		while (true) {
			byte[] buffer = new byte[1024];

			// Creaci�n de un paquete que recibe un m�ximo de 1024 bytes
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

			// recepci�n de un paquete
			ds.receive(dp);

			System.out.println("(Servidor) He recibido el siguiente mensaje procedente de " + dp.getAddress() + ":"
					+ dp.getPort() + " = " + new String(buffer));

		}

	}

}
