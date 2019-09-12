
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejemplo2Servidor {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		int puerto = 2001;

		ServerSocket ss = new ServerSocket(puerto);

		System.out.println("(Servidor): Esperando conexiones por el puerto: " + puerto);
		Socket s = ss.accept();
		System.out.println("(Servidor): Conexion establecida de " + s.getInetAddress() + ":" + s.getPort()
				+ ". Esperando mensaje");

		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

		Persona p = (Persona) ois.readObject();

		System.out.println("(Servidor): Mensaje recibido: " + p);

		s.close();
		System.out.println("(Servidor): Se ha cerrado la conexión con el cliente");

		ss.close();
		System.out.println("(Servidor): El servidor ha cerrado el socket");
	}

}
