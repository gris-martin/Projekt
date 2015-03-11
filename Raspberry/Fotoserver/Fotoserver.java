import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.net.ServerSocket;


public class Fotoserver
{
    public ServerSocket serverSocket;
    public Socket clientSocket;
    
    public static void main(String args[])
    {
	Fotoserver s = new Fotoserver(2020);
	
	try{
	    s.clientSocket = s.serverSocket.accept();
	} catch (IOException e){
	    e.printStackTrace();
	}
	System.out.println("Socket accepted from " +
			   s.clientSocket.getInetAddress().toString().substring(1));

	DataOutputStream outputSender = new DataOutputStream(s.clientSocket.getOutputStream());
	FileInputStream fileStream = new FileInputStream(new File("testy.jpg"));
	byte[] bytesToSend;
	int numberOfBytes = fileStream.read(bytesToSend);
	fileStream.close();
	outputSender.close();
    }


    //Konstruktor
    public Fotoserver(int port){
	try{
	    serverSocket = new ServerSocket(port);
	} catch (IOException e){
	    e.printStackTrace();
	    System.exit(1);
	}
    }



}

