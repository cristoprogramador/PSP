import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ejemplo3 {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress[] addresses = InetAddress.getAllByName("localhost");
		for (int i = 0; i < addresses.length; i++) {
			System.out.println(addresses[i]);
		}

	}

}
