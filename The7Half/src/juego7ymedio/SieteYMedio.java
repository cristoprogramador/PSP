package juego7ymedio;
import java.util.Random;
import java.util.Scanner;

import shareObject.Game;
import shareObject.Baraja;

public class SieteYMedio {

	private static double jugadaServer;
	private static double jugadaPlayer;
	Game game;

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		Baraja carta;
		
		boolean jugando = false;
		double puntosAcumulados = 0;
		boolean pedirCarta = false;

		do {
			// pedimos carta y añadimos al acumulador
			carta = new Baraja();
			System.out.println("Su carta es: " + carta.getNombre());			
			puntosAcumulados += carta.getValor();
			System.out.println("Puntuación actual: " + puntosAcumulados);
			// Si el total en el acumulado es mayor a siete y medio
			if (puntosAcumulados > 7.5) {
				// Si es mas de 7 y medio terminamos
				System.out.println("Perdiestes");
				jugando = false;
			}
			// si el acumulado no supera el siete y medio
			else {
				// preguntamos si quiere otra carta
				do {
					System.out.println("Otra carta? s/n");
					String respuesta = tec.next();
					//si no quiere carta salimos del bucle de juego
					if (respuesta.equalsIgnoreCase("n")) {
						jugando = false;
					} 
					//Si no responde bien,repreguntamos
					else if (!respuesta.equalsIgnoreCase("s")) {
						System.out.println("Responda s/n");
						pedirCarta = true;
					}
					//si responde si salimos del bucle de pregunta y nos mantenemos en el del juego
					else
						pedirCarta = false;
				} while (pedirCarta);
			}
		} while (jugando);

		// Al salir del bucle de juego devolvemos el total de puntos
		System.out.println("Total puntuación: " + puntosAcumulados);
	}
}
