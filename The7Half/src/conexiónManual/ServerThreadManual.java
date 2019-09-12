package conexiónManual;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import shareObject.Game;

public class ServerThreadManual extends Thread {
	private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private ObjectInputStream ois;
    private int idSessio;
    
    public ServerThreadManual(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServerThreadManual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThreadManual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        String accion = "";
        Game game = new Game();
        try {
        	int count = 0;
        	do{

            accion = dis.readUTF();
            System.out.println(accion);
        	game = (Game) ois.readObject();
            System.out.println(game.toString());
            /*if(accion.equals("hola")){
                System.out.println("El cliente con idSesion "+this.idSessio+" saluda");
                dos.writeUTF("adios");
            }*/
            count ++;
        	}while (count <5);
        	System.out.println("El cliente con idSesion "+this.idSessio+" saluda");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ServerThreadManual.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconnectar();
    }
}
