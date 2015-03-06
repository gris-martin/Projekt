import java.io.IOException;
import java.net.ServerSocket;


public class Server {
    public ServerSocket serverSocket;
    
    //Main (lyssnar efter nya anslutningar pa ServerSocket)                                                                               
    public static void main(String[] args) {
	Server s = new Server(2020);
	try{
	    while (true) {
		Servant r = new Servant(s.serverSocket.accept());
		r.start();
	    }
	} catch (IOException e){
	    System.out.println("Fel i serverSocket.accept()");
	}
	
    }
    
    //Konstruktor (skapar ny ServerSocket)                                                                                                
    public Server (int port){
	try {
	    serverSocket = new ServerSocket(port);
	} catch (IOException e) {
	    System.out.println("Server fel...");
	    e.printStackTrace();
	    System.exit(1);
	}
    }
}