package tcp_E1_SendingAMessages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		ServerSocket ss;
		try {
			ss = new ServerSocket(2001);
			Socket s = ss.accept();
			
			BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));

			String mensaje = entrada.readLine();	

			System.out.println("mensaje recivido: " + mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
