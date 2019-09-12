package tcp_E1_SendingAMessages;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		try {
			//Generamos el socket
			Socket s = new Socket(InetAddress.getByName("localhost"), 2001);
			//Socket s = new Socket(InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 }), 2001);

			//BufferedWriter es para enviar Strings por el socket
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			//Escribimos la información
			bw.write("Hola");
			//Enviamos la información
			bw.flush();
		
			//Ceramos el cocket
			s.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Informamos del envio
		System.out.println("Mensaje Enviado");
	}
}
