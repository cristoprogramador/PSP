package tcp_E1_SendingAMessages;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Sol_ClienteActividad1TCP {

	public static void main(String[] args) throws UnknownHostException, IOException {
		int puertoServidor = 2001;
		String ipServidor = "localhost";

		Socket s = new Socket(InetAddress.getByName(ipServidor), puertoServidor);

		System.out.println("(Cliente): Se ha establecido una conexión con " + ipServidor + ":" + puertoServidor);
		
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		
		oos.writeObject(new String("Hola"));
		oos.flush();

		s.close();
		System.out.println("(Cliente): Se ha cerrado la conexión");
	}

}
