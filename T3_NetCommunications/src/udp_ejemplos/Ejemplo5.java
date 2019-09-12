
import java.net.*;

public class Ejemplo5 {
	public static void main(String[] args) throws UnknownHostException {

		// Si se pasa un argumento obtenemos la direccion IP
		if (args.length > 0) {
			String host = args[0];
			InetAddress[] direcciones = InetAddress.getAllByName(host);
			for (int i = 0; i < direcciones.length; i++) {
				System.out.println(direcciones[i]);
			}
		}
		// Si no, obtenemos la direccion IP de la maquina local
		else {
			InetAddress direccion = InetAddress.getLocalHost();
			System.out.println(direccion);
		}

	}
}