import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ejemplo1 {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress direccion = InetAddress.getByName("localhost");
		System.out.println(direccion);

	}

}
