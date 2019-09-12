package conexiónManual;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import shareObject.Game;

class Persona extends Thread {
    protected Socket sk;
    protected DataOutputStream dos;
    protected DataInputStream dis;
    protected ObjectOutputStream oos;
    private int id;
    public Persona(int id) {
        this.id = id;
    }
    
    @Override
    public void run() {
        try {
            sk = new Socket("127.0.0.1", 10578);
            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());
            oos = new ObjectOutputStream(sk.getOutputStream());
            System.out.println(id + " envía saludo");
            dos.writeUTF("no");
            oos.writeObject(new Game());
            dos.writeUTF("hola");
            oos.writeObject(new Game());
            String respuesta="";
            respuesta = dis.readUTF();
            System.out.println(id + " Servidor devuelve saludo: " + respuesta);
            dis.close();
            dos.close();
            sk.close();
        } catch (IOException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

public class ClienteManual {
	
    public static void main(String[] args) {
    	
        ArrayList<Thread> clients = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            clients.add(new Persona(i));
        }
        
        for (Thread thread : clients) {
            thread.start();
        }
    }
}


