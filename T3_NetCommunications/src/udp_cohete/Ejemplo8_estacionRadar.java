package udp_cohete;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DecimalFormat;

class Ejemplo8_estacionRadar extends Thread {
	private String maquina;
	private int idEstacion;
	private int puerto;

	public Ejemplo8_estacionRadar(int id, String maq, String p) {
		idEstacion = id;
		maquina = maq;
		puerto = Integer.parseInt(p);
	}

	public void run() {
		try {
			DatagramSocket ds = new DatagramSocket();
			DecimalFormat formatear = new DecimalFormat("0.0000");
			ds.setSendBufferSize(1048576);

			double distancia = 1000 + 1000 * Math.random();

			int cont = 0;

			System.out.println("(Estacion " + idEstacion + ")  Inicio la transmisión de datos a la estación: " + maquina
					+ ":" + puerto);

			while (cont < 400) {

				double tiempo = (double) cont * 200;

				// Esto simula la medicion de la distancia
				distancia = distancia + 2 * (tiempo / 1000) * (tiempo / 1000);

				// Codifico toda la informacion en un String
				String mensaje = "E" + idEstacion + "\t\t" + cont + "\t\t" + formatear.format(distancia);

				// Convierto el string en un byte[]
				byte[] buffer = mensaje.getBytes();

				// creación del paquete a enviar
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(maquina), puerto);

				ds.send(dp);

				sleep(100);
				cont++;
			}

			System.out.println("(Estacion " + idEstacion + ")  Fin de la transmisión de datos.");
			ds.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println(
					"Para ejecutar el programa tienes que pasarle la direccion IP y el puerto de la máquina que procesará los datos como argumentos");
			System.err.println(
					"Para pasar argumentos a un programa en eclipse: Run --> Run configurations --> Pestaña arguments");
			System.exit(1);
		}

		for (int i = 0; i < 10; i++)
			new Ejemplo8_estacionRadar(i, args[0], args[1]).start();
	}
}
