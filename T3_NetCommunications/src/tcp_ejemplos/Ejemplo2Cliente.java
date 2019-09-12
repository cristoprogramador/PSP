
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ejemplo2Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		int puertoServidor = 2001;
		String ipServidor = "localhost";
		Persona p = new Persona(30, "Juan");

		Socket s = new Socket(InetAddress.getByName(ipServidor), puertoServidor);

		System.out.println("(Cliente): Se ha establecido una conexión con " + ipServidor + ":" + puertoServidor);

		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(p);
		oos.flush();

		System.out.println("(Cliente): Se ha enviado el siguiente mensaje: " + p);

		s.close();
		System.out.println("(Cliente): Se ha cerrado la conexión");
	}

}
