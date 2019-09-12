
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejemplo3Servidor {

	public static void main(String[] args) throws IOException {

		int puerto = 2001;

		ServerSocket ss = new ServerSocket(puerto);

		System.out.println("(Servidor): Esperando conexiones por el puerto: " + puerto);
		Socket s = ss.accept();
		System.out.println("(Servidor): Conexion establecida de " + s.getInetAddress() + ":" + s.getPort()
				+ ". Esperando mensaje");

		BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String mensaje = entrada.readLine();

		if (mensaje == null) {
			System.out.println("(Servidor): No se ha recibido nada. El cliente ha cerrado la conexión");
		} else {
			System.out.println("(Servidor): Mensaje recibido: " + mensaje);
		}

		System.out.println("(Servidor): Mensaje recibido: " + mensaje);

		s.close();
		System.out.println("(Servidor): Se ha cerrado la conexión con el cliente");

		ss.close();
		System.out.println("(Servidor): El servidor ha cerrado el socket");
	}

}
