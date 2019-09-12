package shareObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Baraja {
	private static double valor;
	private static String nombre;
	private static List<Integer> baraja;

	public Baraja() {
		baraja = new ArrayList<Integer>();
		//Creamos lista de 40 numeros del 1 al 40
		for (int i = 1; i < 41; i++) {
			baraja.add(i);
		}
	}

	public void sacaCarta(){
		if (baraja.size() != 0){
			//Generamos un numero random de 0 a la cantidad de 
			//numeros que queden en la lista
			Random r = new Random();
			int numCarta = r.nextInt(baraja.size());
			//Guardamos el numero que está en la posición random de la lista
			Integer carta = baraja.get(numCarta);
			//pasamos el numero al metodo carta que le dará el valor
			//correspondiente de la baraja
			carta(carta);
			//Quitamos el numero de la lista de numeros		
			baraja.remove(carta);
		}else{
			nombre = "No hay mas cartas";
			valor = 0;
		}

	}
	
	private static void carta(Integer carta){
		//Palo de la carta
		String nombrePalo = null;
		if (carta < 11)
			nombrePalo = "Oros";
		else if (carta<21)
			nombrePalo = "Copas";
		else if (carta<31)
			nombrePalo = "Espadas";
		else if (carta<41)
			nombrePalo = "Bastos";
		
		//Valor
		if (carta > 7 && carta < 11 || carta > 17 && carta < 21 || carta > 27 && carta < 31 || carta > 37 && carta < 41)
			valor = 0.5;
		else if (carta < 8)			
			valor = carta;
		else if (carta < 18)			
			valor = carta-10;
		else if (carta < 28)			
			valor = carta-20;
		else if (carta < 38)			
			valor = carta-30;
		
		//Nombre de carta
		if (carta == 8 || carta == 18 || carta == 28 || carta == 38)
			nombre = "Sota" + " de " + nombrePalo;				
		else if (carta == 9 || carta == 19 || carta == 29 || carta == 39)
			nombre = "Caballo" + " de " + nombrePalo;		
		else if (carta == 10 || carta == 20 || carta == 30 || carta == 40)
			nombre = "Rey" + " de " + nombrePalo;
		else
			nombre = (int)valor + " de " + nombrePalo;
	}
	
/*	// Saca una carta aleatoria.
	private void randomCard() {
		// Creamos una carta random del 1 al 10
		Random r = new Random();
		int carta = r.nextInt(10) + 1;

		// Creamos un palo random oros, copas, espadas o bastos.
		Random rc = new Random();
		int palo = rc.nextInt(4) + 1;
		String nombrePalo = "";
		if (palo == 1)
			nombrePalo = "Copas";
		if (palo == 2)
			nombrePalo = "Oros";
		if (palo == 3)
			nombrePalo = "Bastos";
		if (palo == 4)
			nombrePalo = "Espadas";

		// Asignamos el valor y el nombre a los atributos valor y nombre
		if (carta == 8) {
			valor = 0.5;
			nombre = "Sota" + " de " + nombrePalo;
		} else if (carta == 9) {
			valor = 0.5;
			nombre = "Caballo" + " de " + nombrePalo;
		} else if (carta == 10) {
			valor = 0.5;
			nombre = "Rey" + " de " + nombrePalo;
		} else {
			valor = carta;
			nombre = carta + " de " + nombrePalo;
		}
	}*/

	public double getValor() {
		return valor;
	}

	public String getNombre() {
		return nombre;
	}	
}
