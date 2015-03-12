import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;

import java.util.ArrayList;

public class Server extends ServerSocket{
    private ArrayList<Servant> servantList;
    private Camera camera;

    public static void main(String[] args){
	try{
	    Server s = new Server(2020);
	} catch (IOException e){
	    e.printStackTrace();
	}
	
    }
    
    //Konstruktor
    public Server(int port) throws IOException{
	super(port);
	servantList = new ArrayList<Servant>();
	camera = new Camera();
	run();
    }

    //Ta bort servant ur servantlistan
    public void remove(Servant s){
	servantList.remove(s);
    }

    public void run(){
	while(true){
	    try{
		System.out.println("Waiting for connections...");
		Socket socket = accept();
		System.out.println("New connection from " + socket.getInetAddress().getHostName());
		Servant servant = new Servant(this, socket, camera);
		servantList.add(servant);
		servant.start();
	    } catch (IOException e){
		e.printStackTrace();
	    }
	}
    }
    
}
