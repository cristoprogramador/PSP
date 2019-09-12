
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejemplo1Servidor {

	public static void main(String[] args) throws IOException {

		int puerto = 2001;

		ServerSocket ss = new ServerSocket(puerto);

		System.out.println("(Servidor): Esperando conexiones por el puerto: " + puerto);
		Socket s = ss.accept();
		System.out.println("(Servidor): Conexion establecida de " + s.getInetAddress() + ":" + s.getPort()
				+ ". Esperando mensaje");

		DataInputStream dis = new DataInputStream(s.getInputStream());

		//Se recibe y se muestra datos primitivos
		System.out.println("(Servidor): Mensaje recibido: "+dis.readInt());
		System.out.println("(Servidor): Mensaje recibido: "+dis.readFloat());
		System.out.println("(Servidor): Mensaje recibido: "+dis.readBoolean());
		System.out.println("(Servidor): Mensaje recibido: "+dis.readChar());

		
		s.close();
		System.out.println("(Servidor): Se ha cerrado la conexión con el cliente");

		ss.close();
		System.out.println("(Servidor): El servidor ha cerrado el socket");
	}

}
