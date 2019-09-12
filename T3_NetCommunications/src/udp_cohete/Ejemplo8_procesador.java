package udp_cohete;


import java.net.*;
import java.io.*;

class Ejemplo8_procesador {

	static String[] datosCohete = new String[2000];
	static String[] datosEstacion = new String[2000 * 20];

	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			System.err.println("Para ejecutar el programa tienes que pasarle el puerto por el que vas a escuchar");
			System.err.println(
					"Para pasar argumentos a un programa en eclipse: Run --> Run configurations --> Pestaña arguments");
			System.exit(1);
		}

		DatagramSocket ds = new DatagramSocket(Integer.parseInt(args[0]));

		ds.setReceiveBufferSize(1048576);
		ds.setSoTimeout(10000);

		boolean fin = false;

		int datosCoheteRecibidos = 0;
		int datosEstacionRecibidos = 0;
		System.out.println("Procesador: Esperando peticiones");
		while (!fin) {
			try {
				byte[] buffer = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
				ds.receive(dp);
				String datos = new String(buffer);

				if (datos != null && datos.charAt(0) == 'C') {
					datosCohete[datosCoheteRecibidos] = datos;
					datosCoheteRecibidos++;

				} else if (datos != null && datos.charAt(0) == 'E') {
					datosEstacion[datosEstacionRecibidos] = datos;
					datosEstacionRecibidos++;
				}

			} catch (SocketTimeoutException ee) {

				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("EstacionesRadar.txt")));
				for (int i = 0; i < datosEstacionRecibidos; i++) {
					pw.println(datosEstacion[i]);
				}

				pw.flush();
				pw.close();

				pw = new PrintWriter(new BufferedWriter(new FileWriter("Cohete.txt")));
				for (int i = 0; i < datosCoheteRecibidos; i++) {
					pw.println(datosCohete[i]);
				}

				pw.flush();
				pw.close();

				fin = true;
				ds.close();

			}

		}
		System.out.println("Procesador: Fin del programa");

	}

}
