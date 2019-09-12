package udp_examenFutbol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

public class Funciones {
	private static Random r = new Random();

	private static double getTemperatura() {
		double tempInicial = 36.4d;
		double desviacion = 30;
		double media = tempInicial;
		double num = desviacion * r.nextGaussian() + media;

		return num;
	}

	private static double getLatitud() {
		int min = 43401543;
		int max = 43402402;
		int num = min + r.nextInt(max - min);

		return num / 1000000.0;
	}

	private static double getLongitud() {
		int min = 39955643;
		int max = 39956094;
		int num = min + r.nextInt(max - min);

		return num / 1000000.0;
	}

	public static String getEstadisticasJugador(char equipo, int num) {
		double latitud = Funciones.getLatitud();
		double longitud = Funciones.getLongitud();
		double temperatura = Funciones.getTemperatura();

		DecimalFormat formatCoordenadas = new DecimalFormat("00.000000");
		DecimalFormat formatTemperatura = new DecimalFormat("00.00");
		String cadenaFormateada;

		cadenaFormateada = equipo + "\t" + num + "\t" + formatCoordenadas.format(latitud).replace(',', '.') + "\t"
				+ formatCoordenadas.format(longitud).replace(',', '.') + "\t"
				+ formatTemperatura.format(temperatura).replace(',', '.');

		return cadenaFormateada;
	}

	private static int getNumLineasFichero(String fichero) throws IOException {
		int numLineas = 0;
		FileReader fr = new FileReader(fichero);

		BufferedReader br = new BufferedReader(fr);

		while (br.readLine() != null) {
			numLineas++;
		}
		br.close();
		fr.close();
		return numLineas;
	}

	public static String[] cargaFichero(String fichero) {
		FileReader fr;
		String cadena;

		String[] v = null;

		try {
			fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);

			v = new String[getNumLineasFichero(fichero)];

			int num = 0;

			while ((cadena = br.readLine()) != null) {
				v[num] = cadena;
				num++;
			}

			fr.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;

	}

	public static double getTemperatura(String linea) {

		return Double.parseDouble(linea.substring(linea.lastIndexOf("\t") + 1, linea.length()));
	}

	public static int getDorsal(String linea) {
		int ini = linea.indexOf("\t") + 1;
		String aux = linea.substring(ini);
		int fin = aux.indexOf("\t");

		return Integer.parseInt(aux.substring(0, fin));
	}

}
