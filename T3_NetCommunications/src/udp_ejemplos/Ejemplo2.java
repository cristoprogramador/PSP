import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Ejemplo2 {

	public static void main(String[] args) throws UnknownHostException {
	    byte[] ipAddr = new byte[] { 127, 0, 0, 1 };
	    InetAddress addr = InetAddress.getByAddress(ipAddr);
	    System.out.println(Arrays.toString(addr.getAddress()));

	}

}
