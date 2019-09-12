
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ejemplo1Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		int puertoServidor = 2001;
		String ipServidor = "localhost";

		Socket s = new Socket(InetAddress.getByName(ipServidor), puertoServidor);

		System.out.println("(Cliente): Se ha establecido una conexi�n con " + ipServidor + ":" + puertoServidor);
		
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        
        // Se env�a datos primitivos
        dos.writeInt((int)30);
        dos.writeFloat((float)45.5);
        dos.writeBoolean((boolean)true);
        dos.writeChar((char)'t');
		
		dos.flush();
		
		System.out.println("(Cliente): Se ha enviado los datos ");

		s.close();
		System.out.println("(Cliente): Se ha cerrado la conexi�n");
	}

}
