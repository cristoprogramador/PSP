import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ejemplo7_cliente {

	public static void esperar() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		// creación del socket
		DatagramSocket ds = new DatagramSocket();

		InetAddress destino = InetAddress.getByName("localhost");
		int puertoDestino = 6666;

		for (int i = 0; i < 10; i++) {
			String mensaje = "Texto numero " + i;

			byte[] buffer = mensaje.getBytes();

			// creación del paquete a enviar
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length, destino, puertoDestino);

			// Envio del paquete
			ds.send(dp);

			System.out.println("(Cliente) he enviado el mensaje: " + mensaje);

			esperar();
		}

		// Cerramos el socket
		ds.close();

	}

}
