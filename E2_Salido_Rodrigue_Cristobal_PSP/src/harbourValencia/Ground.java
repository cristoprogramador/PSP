package harbourValencia;

import java.util.ArrayList;
import java.util.List;


public class Ground {

	private static List<Package> packageList;
	
	public Ground(){
		packageList = new ArrayList<Package>();
	}
	
	public synchronized void putPackage(Package prch) {
		if (packageList.size()>=16){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		packageList.add(prch);
		notify();
	}

	public synchronized Package getPackage() {
		if (packageList.size() == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return packageList.remove(0);
	}
}
