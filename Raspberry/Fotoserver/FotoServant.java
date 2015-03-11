import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FotoServant extends Thread{

    public Socket socket;

    public FotoServant(Socket s){
	this.socket = s;
    }

    public void run(){

	try{
	    DataInputStream in = new DataInputStream(socket.getInputStream());
	    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	    BufferedImage img = ImageIO.read(new File("plankan.jpg"));
	    ImageIO.write(img, "JPG", socket.getOutputStream());
	    System.out.println("Bild skickad");

	    in.close();
	    out.close();
	    System.out.println(socket.isConnected());
	    System.out.println(socket.isClosed());
	    while(!socket.isClosed()){

	    }
	    
	} catch (IOException e){
	    e.printStackTrace();
	}

    }

    public String toString(){
	return socket.getInetAddress().getHostAddress() + ":" +
	    socket.getPort();
    }

}
