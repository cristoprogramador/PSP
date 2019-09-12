
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ejemplo3Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		int puertoServidor = 2001;
		String ipServidor = "localhost";

		Socket s = new Socket(InetAddress.getByName(ipServidor), puertoServidor);

		System.out.println("(Cliente): Se ha establecido una conexión con " + ipServidor + ":" + puertoServidor);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		String cadena = "Hola";

		bw.write(cadena + '\n');

		bw.flush();

		System.out.println("(Cliente): Se ha enviado el siguiente mensaje: " + cadena);

		s.close();
		System.out.println("(Cliente): Se ha cerrado la conexión");
	}

}
