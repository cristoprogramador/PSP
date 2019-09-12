package tcp_E2_SendingAndReceivingAMessages;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Sol_ClienteActividad2TCP {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		int puertoServidor = 2001;
		String ipServidor = "localhost";
try{
		Socket s = new Socket(InetAddress.getByName(ipServidor), puertoServidor);

		System.out.println("(Cliente): Se ha establecido una conexión con " + ipServidor + ":" + puertoServidor);

		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

		oos.writeObject(new String("Hola"));
		oos.flush();

		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

		String mensaje = (String) ois.readObject();

		if (mensaje == null) {
			System.out.println("(Servidor): No se ha recibido nada. El cliente ha cerrado la conexión");
		} else {
			System.out.println("(Servidor): Mensaje recibido: " + mensaje);
		}

		s.close();
		System.out.println("(Cliente): Se ha cerrado la conexión");
}catch (ConnectException e){
	System.out.println("No se ha podido conectar con el servidor");
}
	}

}
