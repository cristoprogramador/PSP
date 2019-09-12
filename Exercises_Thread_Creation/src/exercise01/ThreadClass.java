package exercise01;

import java.awt.Frame;

public class ThreadClass extends Frame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private int id;
	
	public ThreadClass (int id){
		this.id = id;
	}

	@Override
	public void run() {
		//Asignamos el texto al id
		if (id == 1)
			text= "Hello";
		else if (id == 2)
			text="Bye";
		else text ="Id sin texto asignado";
		//Escrivimos el texto 3 veces

		for (int i = 0; i < 300; i++) {
		System.out.println(text);
		}
		
	}

}
