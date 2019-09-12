package objects;
import java.io.Serializable;

/**
 * Object class. This is the object saves the info of the feedback  
 * communication between client and server
 * @author Trio Escalofrio
 *
 */
@SuppressWarnings("serial")
public class Game implements Serializable{
	
	private String name;
	private double bet;
	private String information;
	private boolean askHitCard;
	private boolean hitCard;
	

	/**
	 * Empty constructor method to make the object Game
	 */
	public Game() {

	} 
	
	/**
	 * Constructor method with the required parameters
	 * @param name
	 * @param bet
	 */
	public Game(String name, double bet) {
		this.name = name;
		this.bet = bet;
		this.hitCard = true;
		this.askHitCard = true;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the bet
	 */
	public double getBet() {
		return bet;
	}

	/**
	 * @param bet the bet to set
	 */
	public void setBet(double bet) {
		this.bet = bet;
	}

	/**
	 * @return the information
	 */
	public String getInformation() {
		return information;
	}

	/**
	 * @param information the information to set
	 */
	public void setInformation(String information) {
		this.information = information;
	}

	/**
	 * @return the askHitCard
	 */
	public boolean isAskHitCard() {
		return askHitCard;
	}

	/**
	 * @param askHitCard the askHitCard to set
	 */
	public void setAskHitCard(boolean askHitCard) {
		this.askHitCard = askHitCard;
	}

	/**
	 * @return the hitCard
	 */
	public boolean isHitCard() {
		return hitCard;
	}

	/**
	 * @param hitCard the hitCard to set
	 */
	public void setHitCard(boolean hitCard) {
		this.hitCard = hitCard;
	}
	
}