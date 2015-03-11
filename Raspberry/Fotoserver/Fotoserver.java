import java.io.IOException;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
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
	    System.out.println("Socket accepted from " +
			       s.clientSocket.getInetAddress().toString().substring(1));

	    DataInputStream in = new DataInputStream(s.clientSocket.getInputStream());
	    DataOutputStream out = new DataOutputStream(s.clientSocket.getOutputStream());
	    BufferedImage img = ImageIO.read(new File("plankan.jpg"));
	    ImageIO.write(img, "JPG", s.clientSocket.getOutputStream());
	    System.out.println("Bild skickad");

	    in.close();
	    out.close();

	} catch (IOException e){
	    e.printStackTrace();
	}

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

