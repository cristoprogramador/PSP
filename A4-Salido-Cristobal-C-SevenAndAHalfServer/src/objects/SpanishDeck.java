package objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Object class with the list of cards(values and names) 
 * @author Trio escalofrio
 *
 */
public class SpanishDeck {
	private static double value;
	private static String cardName;
	private static List<Integer> spanishDeck;

	/**
	 * This method initiates an arrayList of cards and add this cards to the deck
	 */
	public SpanishDeck() {
		spanishDeck = new ArrayList<Integer>();
	
		for (int i = 1; i < 41; i++) {
			spanishDeck.add(i);
		}
	}
	/**
	 * we generate a random number between 0 and the amount of numbers that remains in the list 
	 */
	public void getRandomCard(){
		if (spanishDeck.size() != 0){
					
			Random r = new Random();
			int numCard = r.nextInt(spanishDeck.size());
			//we save the number of the card and we pass the number to the method card()
			Integer card = spanishDeck.get(numCard);
			
			card(card);
			//We remove the number to the list	
			spanishDeck.remove(card);
		}else{
			cardName = "There are not more cards";
			value = 0;
		}

	}
	/**
	 * This method will give to the card his value into the deck
	 * @param card
	 */
	private static void card(Integer card){
		//suit of deck
		String suitName = null;
		if (card < 11)
			suitName = "Oros";
		else if (card<21)
			suitName = "Copas";
		else if (card<31)
			suitName = "Espadas";
		else if (card<41)
			suitName = "Bastos";
		
		//Value
		if (card > 7 && card < 11 || card > 17 && card < 21 || card > 27 && card < 31 || card > 37 && card < 41)
			value = 0.5;
		else if (card < 8)			
			value = card;
		else if (card < 18)			
			value = card-10;
		else if (card < 28)			
			value = card-20;
		else if (card < 38)			
			value = card-30;
		
		//Name of the figure
		if (card == 8 || card == 18 || card == 28 || card == 38)
			cardName = "Sota" + " of " + suitName;				
		else if (card == 9 || card == 19 || card == 29 || card == 39)
			cardName = "Caballo" + " of " + suitName;		
		else if (card == 10 || card == 20 || card == 30 || card == 40)
			cardName = "Rey" + " of " + suitName;
		else
			cardName = (int)value + " of " + suitName;
	}
	
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @return the cardName
	 */
	public String getCardName() {
		return cardName;
	}
	

}
