
public class Acount {

	private int ammount;
;

	public Acount(int am) {
		ammount = am;
	}

	public synchronized void get(int ret) {
		if (ammount > 0){
			ammount -= ret;
		}
	}

	public int getAmmount() {
		return ammount;
	}
}
