package udp_cohete;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DecimalFormat;

class Ejemplo8_cohete extends Thread {
	private String maquina;
	private int puerto;

	public Ejemplo8_cohete(String maq, String p) {
		maquina = maq;
		puerto = Integer.parseInt(p);
	}

	public void run() {
		try {
			DatagramSocket ds = new DatagramSocket();
			ds.setSendBufferSize(1048576);

			double vh = 0;
			double vv = 0;
			double al = 0;
			double temp = 22;

			int cont = 0;
			DecimalFormat formatear = new DecimalFormat("0.0000");

			System.out.println("(Cohete) Inicio la transmisión de datos a la estación: " + maquina + ":" + puerto);

			while (cont < 2000) {

				// Tiempo en milisegundos
				double tiempo = (double) cont * 20;

				// Esto simula la medicion de las variables
				if (vh < 30)
					vh = vh + 0.1 * (tiempo / 1000);
				else
					vh = vh + 0.0001 * (tiempo / 1000);
				if (vv < 100)
					vv = vv + 0.2 * (tiempo / 1000);
				else
					vv = vv + 0.0002 * (tiempo / 1000);

				al = vv * (tiempo / 1000) + 2 * (tiempo / 1000) * (tiempo / 1000);
				temp = temp - 0.001 * (tiempo / 1000);

				// Codifico toda la informacion en un String
				String mensaje = "C\t" + formatear.format(vh) + "\t\t" + formatear.format(vv) + "\t\t"
						+ formatear.format(al) + "\t\t" + formatear.format(temp);

				// Convierto el string en un byte[]
				byte[] buffer = mensaje.getBytes();

				// creación del paquete a enviar
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(maquina), puerto);

				// Envio de la información
				ds.send(dp);

				sleep(20);
				cont++;

			}
			ds.close();
			System.out.println("(Cohete) Fin de la transmisión de datos.");

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

		new Ejemplo8_cohete(args[0], args[1]).start();
	}
}
