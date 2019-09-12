package udp_examenFutbol;

import java.util.ArrayList;
import java.util.List;

/**
 * E3 Examen 2018
 * Se desea implementar un programa que sea capaz de recoger datos estadísticos en tiempo real de un partido de fútbol.
 * Para ello, cada uno de los 22 jugadores que se encuentran en el terreno de juego dispone de una pulsera inteligente que envía la siguiente información en tiempo real a un servidor remoto:
 * 
 * Latitiud
 * Longitud
 * Temperatura corporal
 * 
 * Cada 10 ms las pulseras envían información al servidor que almacena los datos recibidos en un fichero.
 * Se desea realizar una simulación para 10 segundos y un total de 22 jugadores (11 del equipo local y 11 del equipo visitante).
 * 
 * Ayuda:
 * 1. Para facilitarte el desarrollo del programa se proporciona el paquete Funciones que contiene el siguiente método implementado:
 * 
 * public static String getEstadisticasJugador(char equipo, int num)
 * 
 * L → Datos del equipo Local
 * V → Datos del equipo Visitante
 * 
 * Este método devuelve una cadena de texto con las estadísticas para un jugador de un equipo en un instante determinado.
 * La variable equipo es un carácter que representa el tipo de equipo y sólo puede tener los siguientes valores:
 * 
 * La variable num es un entero que representa el número de jugador. El número de jugador tendrá un valor entre 1 y 11.
 * A modo de ejemplo este es el resultado obtenido para la siguiente llamada
 * 
 * Funciones.getEstadisticasJugador('L', 1)
 * L 1 43,402140 39,955940 36,37
 * 
 * 2. En el fichero resultados1.txt tienes una posible salida del resultado esperado para un tiempo de ejecución de 10 segundos.
 * 3. Para escribir una línea de un fichero de texto de nombre fichero.txt y almacenarla en la cadena de texto valor
 * PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fichero.txt")));
 * pw.println("Cadena de texto");
 * pw.flush();
 * pw.close();
 * 
 * @author Cristóbal Salido
 *
 */
public class Simulador {
	
	public static void main(String[] args) {		
		//Ponemos en marcha el centro de datos
		ProcessCenter.main(null);
		
		//Generamos 11 jugadores locales y 11 visitantes y los ponemos en marcha
		for (int i = 1; i < 23; i++) {
			if (i < 12){
				new Jugador('L', i).start();;
			} else {
				new Jugador('V', i).start();;
			}
		}
		
	}

}
