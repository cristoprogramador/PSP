package objects;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Partida implements Serializable{
	
	private String name;
	private double bet;
	private String information;
	private boolean preguntaPedirCarta;
	private boolean pedirOtraCarta;

	public Partida() {

	} 
	
	public Partida(String name, double bet) {
		this.name = name;
		this.bet = bet;
		this.pedirOtraCarta = true;
		this.preguntaPedirCarta = true;
	}

	public String getName() {
		return name;
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
}