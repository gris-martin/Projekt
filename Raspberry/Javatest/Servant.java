import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Servant extends Thread {
    Socket socket;
    public Servant(Socket socketAccept) {
	socket = socketAccept;
	
    }
    
    //Metod som kors nar start() kors (nar klient anslutits)                                                                              
    public void run(){
	
	//Adress och port som String                                                                                                  
	String clientAddress = socket.getInetAddress().getHostAddress() + ':' +
	    socket.getPort();
	
	
	String inputLine = "a";
	System.out.println ("Connection from : " +
			    clientAddress);
	try {
	    //Laser in meddelanden fran klienten tills den sander "quit"                                                          
	    while(!inputLine.equals(new String("quit"))){
		BufferedReader in = new BufferedReader
		    (new InputStreamReader
		     (socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		inputLine = in.readLine();
		if(!inputLine.equals(new String("quit"))){
		    out.println("Calculating: " + inputLine);
		    System.out.println(clientAddress + ": " + inputLine);
		}
		else{
		    System.out.println(clientAddress + " disconnected.");
		}
	    }
	    
	    
	    
	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("Servant : input fel!");
	    System.exit(1);
	}
    }
    
    private double toNumber(String expr){
	return Double.parseDouble(expr);
    }
    
    private double add (double a, double b){
	return a+b;
    }
    private double sub ( double a, double b){
	return a - b;
    }
    private double mul(double a, double b){
	return a * b;
    }
    private double div(double a, double b){
	return a / b;
    }
    
}
