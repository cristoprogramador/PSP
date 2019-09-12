package udp_ejemplos;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class DireccionesIP {

	public static void main(String[] args) {
		try {
			
		//Devuelve un objeto del tipo InetAddress a partir del nombre
		InetAddress direccion = InetAddress.getByName("localhost");
		System.out.println(direccion);
		
		//Devuelve la direccion IP en un vector de byte
		byte[] ipAddr = new byte[] { 127, 0, 0, 1 };
		InetAddress addr;
	
			addr = InetAddress.getByAddress(ipAddr);
		
    	System.out.println(Arrays.toString(addr.getAddress()));
    	
    	//Dado un nombre de servidor, devuelve un vector con todas sus direcciones IP. 
    	InetAddress[] addresses = InetAddress.getAllByName("localhost");
    	for (int i = 0; i < addresses.length; i++) {
    		System.out.println(addresses[i]);
    	}
    	
    	//Devuelve la direccion de la maquina local.
    	InetAddress address = InetAddress.getLocalHost();
    	System.out.println(address);
    	
    	
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
