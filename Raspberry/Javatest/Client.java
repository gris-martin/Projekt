import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    public Client(InetAddress adress, int port){
	try {
	    socket = new Socket(adress, port);
	    out = new PrintWriter(socket.getOutputStream(), true);
	    in = new BufferedReader(new InputStreamReader
				    (socket.getInputStream()));
	} catch (UnknownHostException e) {
	    System.out.println("Hittar inte adress.");
	} catch (IOException e) {
	    System.out.println("IOExcepiton in client.");           }
    }
    
    
    public static void main(String[] args) {
	InetAddress ip = null;
	try {
	    ip = InetAddress.getByName(args[0]);
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	    System.out.println("Kan inte konvertera arg till adress. ");
	}
	
	Client c = new Client(ip, 2020);
	BufferedReader stdIn = new BufferedReader
	    (new InputStreamReader(System.in));
	String userInput = "0";
	try {
	    while(true){
		userInput = stdIn.readLine();
		c.out.println(userInput);
		if(!userInput.equals(new String("quit"))){
		    System.out.println("Du skrev: " + userInput);
		    System.out.println(c.in.readLine());
		}
		else{
		    break;
		}
	    }
	} catch (IOException e) {
	    System.out.println("Kan inte lasa meddelandet.");
	    e.printStackTrace();
	}

    }
}

