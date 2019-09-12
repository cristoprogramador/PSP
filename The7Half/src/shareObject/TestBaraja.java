package shareObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TestBaraja {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		Baraja baraja = new Baraja();
		
/*		List<Integer> baraja = new ArrayList<Integer>();
			//Creamos lista de 40 numeros del 1 al 40
			for (int i = 1; i < 41; i++) {
				baraja.add(i);
			}
			
			while(true){
				tec.nextLine();
				System.out.println("Tamaño lista: " + baraja.size());
				Random r = new Random();
				int numCarta = r.nextInt(baraja.size());
				System.out.println(numCarta);
	
				Integer carta = baraja.get(numCarta);
				System.out.println("Numero de carta: " + carta);
				baraja.remove(carta);				
			}*/
		
		
		
		int count = 0;
		while(true){
			
			System.out.println("Numero de cartas: " + count);
			tec.nextLine();
			baraja.sacaCarta();
			System.out.println(baraja.getNombre());	
			count ++;
		}
		
	}

}
