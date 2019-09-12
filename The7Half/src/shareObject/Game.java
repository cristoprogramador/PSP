package shareObject;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Game implements Serializable{
	
	private String name;
	private double bet;
	private String information;

	private boolean preguntaPedirCarta;
	private boolean pedirOtraCarta;

	private double puntuacionPlayer;
	private double puntuacionDealer;

	public Game() {

	} 
	
	public Game(String name, double bet) {
		this.name = name;
		this.bet = bet;
		this.pedirOtraCarta = true;
		this.preguntaPedirCarta = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBet() {
		return bet;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public boolean isPreguntaPedirCarta() {
		return preguntaPedirCarta;
	}

	public void setPreguntaPedirCarta(boolean preguntaPedirCarta) {
		this.preguntaPedirCarta = preguntaPedirCarta;
	}

	public boolean ispedirOtraCarta() {
		return pedirOtraCarta;
	}

	public void setpedirOtraCarta(boolean respuesta) {
		this.pedirOtraCarta = respuesta;
	}

	public double getPuntuacionPlayer() {
		return puntuacionPlayer;
	}

	public void setPuntuacionPlayer(double puntuacionPlayer) {
		this.puntuacionPlayer = puntuacionPlayer;
	}

	public double getPuntuacionDealer() {
		return puntuacionDealer;
	}

	public void setPuntuacionDealer(double puntuacionDealer) {
		this.puntuacionDealer = puntuacionDealer;
	}
	
}